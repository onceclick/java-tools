package com.janosgyerik.tools.util;

import java.net.URL;

public final class ClassPathUtils {

    private ClassPathUtils() {
        throw new AssertionError("utility class, forbidden constructor");
    }

    public URL getResourceUrl(String resourceName) {
        return getClass().getClassLoader().getResource(resourceName);
    }

    public static URL getJarUrl(Class<?> clazz) {
        return clazz.getProtectionDomain().getCodeSource().getLocation();
    }

    public static URL getJarUrl(String className) {
        try {
            return getJarUrl(Class.forName(className));
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("class not found: " + className, e);
        }
    }

    public static URL getClassUrl(Class<?> clazz) {
        return clazz.getResource("/" + clazz.getName().replace(".", "/") + ".class");
    }

}
