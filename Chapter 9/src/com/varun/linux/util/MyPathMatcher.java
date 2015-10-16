package com.varun.linux.util;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class MyPathMatcher extends SimpleFileVisitor<Path> {

    private PathMatcher matcher;

    private String pattern = "glob:**";

    public MyPathMatcher(String pattern) {
        this.pattern += pattern;
        this.pattern += "**";
        matcher = FileSystems.getDefault().getPathMatcher(this.pattern);
    }

    /**
     * @return the matcher
     */
    public PathMatcher getMatcher() {
        return matcher;
    }

    /**
     * @param matcher the matcher to set
     */
    public void setMatcher(PathMatcher matcher) {
        this.matcher = matcher;
    }

    /**
     * @return the pattern
     */
    public String getPattern() {
        return pattern;
    }

    /**
     * @param pattern the pattern to set
     */
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if (matcher.matches(file)) {
            System.out.println(file);
        }
        return FileVisitResult.CONTINUE;
    }

    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        return FileVisitResult.CONTINUE;
    }

}
