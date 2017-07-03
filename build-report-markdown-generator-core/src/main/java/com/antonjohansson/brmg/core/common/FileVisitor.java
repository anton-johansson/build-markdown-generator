/**
 * Copyright 2017 Anton Johansson
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
