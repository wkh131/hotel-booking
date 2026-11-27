package com.github.wkh131.hotelbooking.listing.v1.common;

public interface ProtoMapper<M, R extends Resource> {
    M fromProto(R proto);

    R toProto(M rModel);
}
