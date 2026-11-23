package com.github.wkh131.hotelbooking.listing.model.partner;

/**
 * Data-Access-Object for {@link Partner}.
 */
public interface PartnerDao {

    /**
     * Creates a partner that can list properties.
     */
    void createPartner(Partner partner);
}
