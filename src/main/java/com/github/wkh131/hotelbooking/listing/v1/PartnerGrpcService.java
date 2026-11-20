package com.github.wkh131.hotelbooking.listing.v1;

import com.github.wkh131.hotelbooking.listing.partner.PartnerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.github.wkh131.hotelbooking.listing.v1.StringUtils.isIdValid;

// TODO: Document this class
@Component
public class PartnerGrpcService {

    private final PartnerDao partnerDao;

    @Autowired
    public PartnerGrpcService(final PartnerDao partnerDao) {
        this.partnerDao = partnerDao;
    }

    public Partner createPartner(CreatePartnerRequest request) {
        final String partnerId = request.getPartnerId();
        if (!isIdValid(partnerId)) {
            // TODO: add response error
            // TODO: need to check authz error first
        }
        final Partner partnerProto = request.getPartner();
        String name = String.format("partners/%s", partnerId);
        String title = partnerProto.getTitle();
        String addressLine = partnerProto.getPostalAddress().getAddressLines(0);
        partnerDao.createPartner(new com.github.wkh131.hotelbooking.listing.partner.Partner(name, title, addressLine));
        return partnerProto.newBuilderForType().setName(name).build();
    }
}
