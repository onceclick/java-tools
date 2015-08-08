package com.janosgyerik.tools.files.csv.writing;

public interface Columnizer<T> {

    /**
     * Get the column headers
     *
     * @return array of objects to use as the column headers
     */
    Object[] getHeaders();

    /**
     * Get the column values of specified object
     *
     * @param item the object from which to extract column values
     * @return array of objects to use as the column values
     */
    Object[] getValues(T item);
}
