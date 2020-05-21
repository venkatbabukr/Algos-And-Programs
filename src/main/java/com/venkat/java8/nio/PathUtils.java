package com.venkat.java8.nio;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class PathUtils {

    private PathUtils() { }

    public static final Path getResourcePath(String res) throws URISyntaxException {
        return Paths.get(PathUtils.class
                            .getClassLoader().getResource(res)
                            .toURI());
    }

}
