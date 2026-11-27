package com.github.wkh131.hotelbooking.listing.v1.partner;

import com.github.wkh131.hotelbooking.listing.v1.Partner;
import com.github.wkh131.hotelbooking.listing.v1.common.ProtoMapper;
import org.springframework.stereotype.Component;

@Component
public class PartnerProtoMapper implements ProtoMapper<com.github.wkh131.hotelbooking.listing.model.partner.Partner, Partner> {

    @Override
    public com.github.wkh131.hotelbooking.listing.model.partner.Partner fromProto(Partner proto) {
        return null;
    }

    @Override
    public Partner toProto(com.github.wkh131.hotelbooking.listing.model.partner.Partner rModel) {
        return null;
    }
}
