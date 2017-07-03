package com.antonjohansson.brmg.core.common;

import static java.util.stream.Collectors.toList;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Finds files matching a given pattern.
 */
public class FileVisitor extends SimpleFileVisitor<Path>
{
    private final List<PathMatcher> matchers;
    private final List<File> foundFiles = new ArrayList<>();

    public FileVisitor(Collection<String> patterns)
    {
        matchers = patterns.stream()
                .map(pattern -> FileSystems.getDefault().getPathMatcher("glob:" + pattern))
                .collect(toList());
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException
    {
        if (matchers.stream().anyMatch(matcher -> matcher.matches(file)))
        {
            foundFiles.add(file.toFile());
        }
        return FileVisitResult.CONTINUE;
    }

    /**
     * Starts visiting files in the given root.
     *
     * @param root The root to start in.
     */
    public void visit(File root)
    {
        foundFiles.clear();

        try
        {
            Files.walkFileTree(root.toPath(), this);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets a list of all the found files.
     *
     * @return Returns the found files.
     */
    public List<File> getFoundFiles()
    {
        return new ArrayList<>(foundFiles);
    }
}
