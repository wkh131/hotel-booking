package com.github.wkh131.hotelbooking.listing.v1.common.create;

public interface CreateRequest<R> {
    String getParent();

    String getId();

    R getProto();
}
