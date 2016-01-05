package com.janosgyerik.tools.files.csv.reading;

public interface RowMapper<T> {
    /**
     * Create an object of type T from the columns
     *
     * @param cols columns on the line
     * @return new object of type T
     */
    T mapRow(String[] cols);

    /**
     * Check if the input row is valid.
     *
     * @param cols columns on the line
     * @return true if valid line that can be mapped to an object
     */
    boolean isValidRow(String[] cols);
}
