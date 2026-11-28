package com.github.wkh131.hotelbooking.listing.v1.common;

public interface ResourceStringsResolver<R> {

    String getName(R proto);

    String getFieldName();

    String makeName(String parentName, String resourceId);
}
