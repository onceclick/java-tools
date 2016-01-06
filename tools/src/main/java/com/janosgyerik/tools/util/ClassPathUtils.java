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

    public static URL getClassUrl(Class<?> clazz) {
        return clazz.getResource("/" + clazz.getName().replace(".", "/") + ".class");
    }

    public static URL getJarUrl(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            return clazz.getProtectionDomain().getCodeSource().getLocation();
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

}
