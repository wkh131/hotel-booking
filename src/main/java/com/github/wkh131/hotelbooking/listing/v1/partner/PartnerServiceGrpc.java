package com.github.wkh131.hotelbooking.listing.v1.partner;

import com.github.wkh131.hotelbooking.listing.model.authz.AuthorizationService;
import com.github.wkh131.hotelbooking.listing.model.partner.PartnerDao;
import com.github.wkh131.hotelbooking.listing.v1.CreatePartnerRequest;
import com.github.wkh131.hotelbooking.listing.v1.DeletePartnerRequest;
import com.github.wkh131.hotelbooking.listing.v1.GetPartnerRequest;
import com.github.wkh131.hotelbooking.listing.v1.Partner;
import com.github.wkh131.hotelbooking.listing.v1.UpdatePartnerRequest;
import com.github.wkh131.hotelbooking.listing.v1.common.crud.create.CreateRequest;
import com.github.wkh131.hotelbooking.listing.v1.common.crud.create.CreateResourceService;
import com.github.wkh131.hotelbooking.listing.v1.common.crud.delete.DeleteRequest;
import com.github.wkh131.hotelbooking.listing.v1.common.crud.delete.DeleteResourceService;
import com.github.wkh131.hotelbooking.listing.v1.common.crud.get.BaseGetResourceService;
import com.github.wkh131.hotelbooking.listing.v1.common.crud.get.GetRequest;
import com.github.wkh131.hotelbooking.listing.v1.common.crud.get.GetResourceService;
import com.github.wkh131.hotelbooking.listing.v1.common.crud.update.UpdateRequest;
import com.github.wkh131.hotelbooking.listing.v1.common.crud.update.UpdateResourceService;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Partner resource related implementation of methods in ListingService.
 */
@Component
public class PartnerServiceGrpc {

    private static class GetPartnerRequestImpl implements GetRequest {

        private final GetPartnerRequest req;

        public GetPartnerRequestImpl(GetPartnerRequest req) {
            this.req = req;
        }

        @Override
        public String getName() {
            return req.getName();
        }
    }

    private static class CreatePartnerRequestImpl implements CreateRequest<Partner> {

        private final CreatePartnerRequest req;

        public CreatePartnerRequestImpl(CreatePartnerRequest req) {
            this.req = req;
        }

        @Override
        public String getParent() {
            return "";
        }

        @Override
        public String getId() {
            return req.getPartnerId();
        }

        @Override
        public Partner getProto() {
            return req.getPartner();
        }
    }

    private static class UpdatePartnerRequestImpl implements UpdateRequest<Partner> {

        private final UpdatePartnerRequest req;

        public UpdatePartnerRequestImpl(UpdatePartnerRequest req) {
            this.req = req;
        }

        @Override
        public Partner get() {
            return req.getPartner();
        }
    }

    private static class DeletePartnerRequestImpl implements DeleteRequest {

        private final DeletePartnerRequest req;

        public DeletePartnerRequestImpl(DeletePartnerRequest req) {
            this.req = req;
        }

        @Override
        public String getName() {
            return req.getName();
        }
    }

    //    private static class PartnerProtoMapper implements ProtoMapper<com.github.wkh131.hotelbooking
    //    .listing.model.partner.Partner, Partner> {
    //
    //        @Override
    //        public com.github.wkh131.hotelbooking.listing.model.partner.Partner fromProto(Partner proto) {
    //            return null;
    //        }
    //
    //        @Override
    //        public Partner toProto(com.github.wkh131.hotelbooking.listing.model.partner.Partner rModel) {
    //            return null;
    //        }
    //    }

    private static final String RESOURCE_FORMAT = "partners/%s";

    private final GetResourceService<com.github.wkh131.hotelbooking.listing.model.partner.Partner, Partner,
            GetPartnerRequestImpl> getResourceService;

    private final CreateResourceService<com.github.wkh131.hotelbooking.listing.model.partner.Partner,
            Partner, CreatePartnerRequestImpl> createResourceService;

    private final UpdateResourceService<com.github.wkh131.hotelbooking.listing.model.partner.Partner,
            Partner, UpdatePartnerRequestImpl> updateResourceService;

    private final DeleteResourceService<com.github.wkh131.hotelbooking.listing.model.partner.Partner,
            Partner, DeletePartnerRequestImpl> deleteResourceService;

    @Autowired
    public PartnerServiceGrpc(final AuthorizationService authorizationService, final PartnerDao partnerDao) {
        this.getResourceService = new BaseGetResourceService<>(authorizationService, partnerDao,
                new PartnerProtoMapper());
        this.createResourceService = new CreateResourceService<>(authorizationService, partnerDao,
                new PartnerProtoMapper());
    }

    public void getPartner(GetPartnerRequest request, StreamObserver<Partner> responseObserver) {
        getResourceService.get(new GetPartnerRequestImpl(request), responseObserver);
    }

    public void createPartner(CreatePartnerRequest request, StreamObserver<Partner> responseObserver) {
        createResourceService.create(new CreatePartnerRequestImpl(request), responseObserver);
    }

    public void updatePartner(UpdatePartnerRequest request, StreamObserver<Partner> responseObserver) {
        updateResourceService.update(new UpdatePartnerRequestImpl(request), responseObserver);
    }

    public void deletePartner(DeletePartnerRequest request,
                              StreamObserver<com.google.protobuf.Empty> responseObserver) {
    }
}
