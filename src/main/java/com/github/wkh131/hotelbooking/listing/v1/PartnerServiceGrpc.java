package com.github.wkh131.hotelbooking.listing.v1;

import com.github.wkh131.hotelbooking.listing.model.authz.AuthorizationService;
import com.github.wkh131.hotelbooking.listing.model.contact.AddressLocation;
import com.github.wkh131.hotelbooking.listing.model.contact.Contact;
import com.github.wkh131.hotelbooking.listing.v1.common.ModelDao;
import com.github.wkh131.hotelbooking.listing.v1.common.ProtoMapper;
import com.github.wkh131.hotelbooking.listing.v1.common.ResourceStringsResolver;
import com.github.wkh131.hotelbooking.listing.v1.common.create.CreateRequest;
import com.github.wkh131.hotelbooking.listing.v1.common.create.CreateResourceService;
import com.github.wkh131.hotelbooking.listing.v1.common.create.DefaultCreateResourceService;
import com.github.wkh131.hotelbooking.listing.v1.common.delete.DefaultDeleteResourceService;
import com.github.wkh131.hotelbooking.listing.v1.common.delete.DeleteResourceService;
import com.github.wkh131.hotelbooking.listing.v1.common.get.DefaultGetResourceService;
import com.github.wkh131.hotelbooking.listing.v1.common.get.GetResourceService;
import com.github.wkh131.hotelbooking.listing.v1.common.update.DefaultUpdateResourceService;
import com.github.wkh131.hotelbooking.listing.v1.common.update.UpdateResourceService;
import com.google.type.PhoneNumber;
import com.google.type.PostalAddress;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Partner resource related implementation of methods in ListingService.
 */
@Component
public class PartnerServiceGrpc {

    private static final String RESOURCE_FIELD_NAME = "partner";

    private static final String RESOURCE_NAME_FORMAT = "partners/%s";

    private final GetResourceService<Partner> getResourceService;

    private final CreateResourceService<Partner> createResourceService;

    private final UpdateResourceService<Partner> updateResourceService;

    private final DeleteResourceService deleteResourceService;

    @Autowired
    public PartnerServiceGrpc(final AuthorizationService authorizationService,
                              final ModelDao<com.github.wkh131.hotelbooking.listing.model.partner.Partner> partnerDao) {
        PartnerProtoMapper partnerProtoMapper = new PartnerProtoMapper();
        PartnerResourceStringsResolver resourceStringsResolver = new PartnerResourceStringsResolver();
        this.getResourceService = new DefaultGetResourceService<>(authorizationService, partnerDao,
                partnerProtoMapper);
        this.createResourceService = new DefaultCreateResourceService<>(authorizationService, partnerDao,
                partnerProtoMapper, resourceStringsResolver);
        this.updateResourceService = new DefaultUpdateResourceService<>(authorizationService, partnerDao,
                partnerProtoMapper, resourceStringsResolver);
        this.deleteResourceService = new DefaultDeleteResourceService<>(authorizationService, partnerDao);
    }

    public void getPartner(GetPartnerRequest request, StreamObserver<Partner> responseObserver) {
        getResourceService.get(request::getName, responseObserver);
    }

    public void createPartner(CreatePartnerRequest request, StreamObserver<Partner> responseObserver) {
        createResourceService.create(new CreateRequest<>() {
            @Override
            public String getParent() {
                // no parent node for partners
                return "";
            }

            @Override
            public String getId() {
                return request.getPartnerId();
            }

            @Override
            public Partner getProto() {
                return request.getPartner();
            }
        }, responseObserver);
    }

    public void updatePartner(UpdatePartnerRequest request, StreamObserver<Partner> responseObserver) {
        updateResourceService.update(request::getPartner, responseObserver);
    }

    public void deletePartner(DeletePartnerRequest request,
                              StreamObserver<com.google.protobuf.Empty> responseObserver) {
        deleteResourceService.delete(request::getName, responseObserver);
    }

    private static class PartnerProtoMapper implements ProtoMapper<com.github.wkh131.hotelbooking.listing.model.partner.Partner, Partner> {

        @Override
        public com.github.wkh131.hotelbooking.listing.model.partner.Partner fromProto(Partner proto) {
            String resourceName = proto.getName();
            String title = proto.getTitle();
            String addressLine = proto.getPostalAddress().getAddressLines(0);
            String locality = proto.getPostalAddress().getLocality();
            String stateProvince = proto.getPostalAddress().getAdministrativeArea();
            String countryCode = proto.getPostalAddress().getRegionCode();
            String postalCode = proto.getPostalAddress().getPostalCode();
            String phoneNumber = proto.getPhoneNumber().toString();
            String emailAddress = proto.getEmailAddress();
            AddressLocation location = new AddressLocation(addressLine, locality, stateProvince, countryCode,
                    postalCode);
            Contact contact = new Contact(phoneNumber, emailAddress);
            return new com.github.wkh131.hotelbooking.listing.model.partner.Partner(resourceName, title,
                    location, contact);
        }

        @Override
        public Partner toProto(com.github.wkh131.hotelbooking.listing.model.partner.Partner rModel) {
            String name = rModel.resourceName();
            String title = rModel.title();
            String addressLine = rModel.location().addressLine();
            String locality = rModel.location().locality();
            String administrativeArea = rModel.location().stateProvince();
            String regionCode = rModel.location().countryCode();
            String postalCode = rModel.location().postalCode();
            String phoneNumber = rModel.contact().phoneNumber();
            String emailAddress = rModel.contact().emailAddress();
            PostalAddress postalAddress = PostalAddress.newBuilder()
                                                       .addAddressLines(addressLine)
                                                       .setLocality(locality)
                                                       .setAdministrativeArea(administrativeArea)
                                                       .setRegionCode(regionCode)
                                                       .setPostalCode(postalCode)
                                                       .build();
            // TODO: set correctly
            PhoneNumber phoneNumberProto = PhoneNumber.newBuilder().build();
            return Partner.newBuilder()
                          .setName(name)
                          .setTitle(title)
                          .setPostalAddress(postalAddress)
                          .setPhoneNumber(phoneNumberProto)
                          .setEmailAddress(emailAddress)
                          .build();
        }
    }

    private static class PartnerResourceStringsResolver implements ResourceStringsResolver<Partner> {

        @Override
        public String getName(Partner proto) {
            return proto.getName();
        }

        @Override
        public String getFieldName() {
            return RESOURCE_FIELD_NAME;
        }

        @Override
        public String makeName(String parentName, String resourceId) {
            return parentName + String.format(RESOURCE_NAME_FORMAT, resourceId);
        }
    }
}
