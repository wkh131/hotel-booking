package com.github.wkh131.hotelbooking.listing.v1.common.crud.update;

import com.github.wkh131.hotelbooking.listing.v1.common.Resource;

public interface UpdateRequest<R extends Resource> {
    R get();
}
