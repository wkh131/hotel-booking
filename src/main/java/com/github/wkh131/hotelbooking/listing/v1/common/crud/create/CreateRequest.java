package com.github.wkh131.hotelbooking.listing.v1.common.crud.create;

import com.github.wkh131.hotelbooking.listing.v1.common.Resource;

public interface CreateRequest<R extends Resource> {
    String getParent();

    String getId();

    R getProto();
}
