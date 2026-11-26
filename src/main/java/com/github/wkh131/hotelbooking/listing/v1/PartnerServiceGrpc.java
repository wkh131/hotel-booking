package com.github.wkh131.hotelbooking.listing.v1;

import com.github.wkh131.hotelbooking.listing.model.authz.Action;
import com.github.wkh131.hotelbooking.listing.model.authz.AuthorizationService;
import com.github.wkh131.hotelbooking.listing.model.partner.PartnerDao;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.github.wkh131.hotelbooking.listing.v1.StringUtils.isIdValid;
import static com.github.wkh131.hotelbooking.listing.v1.StringUtils.isNameValid;

/**
 * Partner resource related implementation of methods in ListingService.
 */
@Component
public class PartnerServiceGrpc {

    private static final String RESOURCE_FORMAT = "partners/%s";

    // TODO: obtain from authentication result
    private static final String userId = "";

    private final AuthorizationService authorizationService;

    private final PartnerDao partnerDao;

    @Autowired
    public PartnerServiceGrpc(final AuthorizationService authorizationService,
                              final PartnerDao partnerDao) {
        this.authorizationService = authorizationService;
        this.partnerDao = partnerDao;
    }

    public void getPartner(GetPartnerRequest request, StreamObserver<Partner> responseObserver) {
        final String resourceId = request.getName();
        if (!isNameValid(resourceId)) {
            GrpcServiceUtils.notifyInvalidNameError(responseObserver);
        } else if (!authorizationService.isAuthorized(userId, Action.READ, resourceId)) {
            responseObserver.onError(Status.PERMISSION_DENIED.asRuntimeException());
        } else {
            partnerDao.findPartner(resourceId)
                      .map(PartnerServiceGrpc::toProto)
                      .ifPresentOrElse(responseObserver::onNext, () -> responseObserver.onError(
                              Status.NOT_FOUND.asRuntimeException()));
            responseObserver.onCompleted();
        }
    }

    public void createPartner(CreatePartnerRequest request,
                              StreamObserver<Partner> responseObserver) {
        final String partnerId = request.getPartnerId();
        if (!isIdValid(partnerId)) {
            // invalid input
            responseObserver.onError(
                    Status.INVALID_ARGUMENT.withDescription("invalid partner_id format")
                                           .asRuntimeException());
        } else {
            String resourceId = String.format(RESOURCE_FORMAT, partnerId);
            if (!authorizationService.isAuthorized(userId, Action.MODIFY, resourceId)) {
                // unauthorized
                responseObserver.onError(Status.PERMISSION_DENIED.asRuntimeException());
            } else {
                final Partner partnerProto = request.getPartner();
                final com.github.wkh131.hotelbooking.listing.model.partner.Partner partner =
                        fromProto(
                        partnerProto);
                boolean duplicate = !partnerDao.createPartner(partner);
                if (duplicate) {
                    // duplicate found
                    responseObserver.onError(Status.ALREADY_EXISTS.asRuntimeException());
                } else {
                    responseObserver.onNext(toProto(partner));
                    responseObserver.onCompleted();
                }
            }
        }
    }

    public void updatePartner(UpdatePartnerRequest request,
                              StreamObserver<Partner> responseObserver) {
        Partner partnerProto = request.getPartner();
        if (!request.hasPartner()) {
            responseObserver.onError(
                    Status.INVALID_ARGUMENT.withDescription("partner field is required")
                                           .asRuntimeException());
        }
        if (!authorizationService.isAuthorized(userId, Action.MODIFY, partnerProto.getName())) {

        } else {
            boolean updated = partnerDao.updatePartner(fromProto(partnerProto));
            if (!updated) {
                responseObserver.onError(Status.PERMISSION_DENIED.asRuntimeException());
            }
        }
    }

    public void deletePartner(DeletePartnerRequest request,
                              StreamObserver<com.google.protobuf.Empty> responseObserver) {
    }

    // TODO: Implement
    private static Partner toProto(com.github.wkh131.hotelbooking.listing.model.partner.Partner partner) {
        return Partner.newBuilder().build();
    }

    // TODO: Implement
    private static com.github.wkh131.hotelbooking.listing.model.partner.Partner fromProto(Partner partnerProto) {
        //        String name = String.format("partners/%s", partnerId);
        //        String title = partnerProto.getTitle();
        //        String addressLine = partnerProto.getPostalAddress().getAddressLines(0);
        return null;
    }
}
