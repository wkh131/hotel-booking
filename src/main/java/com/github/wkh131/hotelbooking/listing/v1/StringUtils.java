package com.github.wkh131.hotelbooking.listing.v1;

/**
 * String utility functions.
 */
public class StringUtils {

    private static final int MIN_ID_LENGTH_INC = 4;
    private static final int MAX_ID_LENGTH_INC = 63;

    // TODO: maybe move this
    public static final int MAX_LENGTH_RESOURCE_NAME = 2048;

    /**
     * Validates that the id string conforms to RFC-1034 DNS labels and has no upper case letters.
     * Valid characters are in /[a-z][0-9]-/ and length between 4-63.
     * This is the string used in the last part of the resource name.
     *
     * @param id the id string to validate.
     * @return true if valid, otherwise false.
     */
    public static boolean isResourceIdValid(String id) {
        int n = id.length();
        if (n < MIN_ID_LENGTH_INC || n > MAX_ID_LENGTH_INC) return false;
        if (!Character.isLowerCase(id.charAt(0))) return false;
        if (!Character.isLowerCase(id.charAt(n - 1))) return false;
        for (int i = 1; i < n - 1; i++) {
            char c = id.charAt(i);
            if (!(Character.isLowerCase(c) || Character.isDigit(c) || c == '-')) {
                return false;
            }
        }
        return true;
    }

    /**
     * Validates the resource name string.
     *
     * @param name the resource name string to validate.
     * @return true if valid, otherwise false.
     */
    public static boolean isResourceNameValid(String name) {
        return !name.isEmpty() && name.length() < MAX_LENGTH_RESOURCE_NAME;
    }
}
