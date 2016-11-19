package com.janosgyerik.tools.util;

import java.net.URL;

/**
 * Utility methods related to classpath.
 */
public final class ClassPathUtils {

    private ClassPathUtils() {
        throw new AssertionError("utility class, forbidden constructor");
    }

    /**
     * Get the URL of a resource by name.
     *
     * @param resourceName name of the resource to find
     * @return URL of the resource
     */
    public URL getResourceUrl(String resourceName) {
        return getClass().getClassLoader().getResource(resourceName);
    }

    /**
     * Get the URL of the JAR from which a class was loaded.
     *
     * @param clazz the class to find
     * @return the URL of the class
     */
    public static URL getJarUrl(Class<?> clazz) {
        return clazz.getProtectionDomain().getCodeSource().getLocation();
    }

    /**
     * Get the URL of the JAR from which a class was loaded.
     * This can be useful when the Class instance is not available directly in the caller,
     * for example because it would be loaded further down the stack.
     *
     * @param className the name of the class to find
     * @return the URL of the class
     */
    public static URL getJarUrl(String className) {
        try {
            return getJarUrl(Class.forName(className));
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("class not found: " + className, e);
        }
    }

    /**
     * Get the URL of the class file from which a class was loaded.
     *
     * @param clazz the class to find
     * @return the URL of the class
     */
    public static URL getClassUrl(Class<?> clazz) {
        return clazz.getResource("/" + clazz.getName().replace(".", "/") + ".class");
    }

}
