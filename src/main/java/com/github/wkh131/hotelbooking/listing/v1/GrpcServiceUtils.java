package com.github.wkh131.hotelbooking.listing.v1;

// TODO: DOCUMENT
public class GrpcServiceUtils {

    public static String nameMaxLengthErrorMsg(String namePath) {
        return String.format("%s must be at most %d characters long", namePath,
                StringUtils.MAX_LENGTH_RESOURCE_NAME);
    }

    public static String fieldMissingErrorMsg(String fieldPath) {
        return String.format("%s is required", fieldPath);
    }

    public static String topNameMissingErrorMsg() {
        return fieldMissingErrorMsg("name");
    }
}
