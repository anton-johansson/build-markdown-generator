pipeline
{
    agent any
    tools
    {
        maven 'maven-3.5.0'
        jdk 'java-8'
    }
    stages
    {
        stage('prepare')
        {
            steps
            {
                script
                {
                    currentBuild.displayName = "${env.BUILD_NUMBER} (${env.gitlabSourceBranch})"
                }

                checkout changelog: true, poll: true,
                    scm: [
                        $class: 'GitSCM',
                        branches: [[name: 'origin/${env.gitlabSourceBranch}']],
                        userRemoteConfigs: [[credentialsId: 'gitlab', url: 'git@git.sample.com:sample-namespace/sample-project.git']]
                    ]

                updateGitlabCommitStatus name: 'build', state: 'running'
            }
        }
        stage('unit-tests')
        {
            steps
            {
                sh 'mvn clean'
                sh 'mvn -Dmaven.test.failure.ignore=true -Dcheckstyle.skip=true -Dcobertura.report.format=xml -Dcobertura.aggregate=true cobertura:cobertura'
                junit healthScaleFactor: 0.0, testResults: '**/target/surefire-reports/*.xml' 
                step([$class: 'CoberturaPublisher', onlyStable: false, coberturaReportFile: '**/target/site/cobertura/coverage.xml'])
            }
        }
        stage('code-analyzis')
        {
            steps
            {
                sh 'mvn checkstyle:checkstyle'
                checkstyle canRunOnFailed: true, unstableTotalAll: '0', pattern: '**/target/checkstyle-result.xml'
            }
        }
        stage('generate-report')
        {
            steps
            {
                sh """
                    mvn com.anton-johansson:build-markdown-generator-maven-plugin:1.0.0:generate \
                        -DbuildMarkdownGenerator.detailedReportURL=${BUILD_URL} \
                        -DbuildMarkdownGenerator.checkstyleReportPatterns=**/target/checkstyle-result.xml \
                        -DbuildMarkdownGenerator.checkstyleDetailedReportURL=${BUILD_URL}checkstyleResult/ \
                        -DbuildMarkdownGenerator.junitReportPatterns=**/target/surefire-reports/TEST-*.xml \
                        -DbuildMarkdownGenerator.junitDetailedReportURL=${BUILD_URL}testReport/ \
                        -DbuildMarkdownGenerator.junitDetailedReportForTestURL=${BUILD_URL}testReport/[packageName]/[simpleClassName]/[testName] \
                        -DbuildMarkdownGenerator.coberturaCoverageReport=target/site/cobertura/coverage.xml \
                        -DbuildMarkdownGenerator.coberturaLineThreshold=90 \
                        -DbuildMarkdownGenerator.coberturaBranchThreshold=80 \
                        -DbuildMarkdownGenerator.coberturaDetailedReportURL=${BUILD_URL}cobertura/
                    """

                script
                {
                    markdown = readFile 'target/build-markdown.md'
                }

                addGitLabMRComment comment: markdown
            }
        }
    }
    post
    {
        success
        {
            updateGitlabCommitStatus name: 'build', state: 'success'
        }
        failure
        {
            updateGitlabCommitStatus name: 'build', state: 'failed'
        }
        unstable
        {
            updateGitlabCommitStatus name: 'build', state: 'failed'
        }
    }
}
