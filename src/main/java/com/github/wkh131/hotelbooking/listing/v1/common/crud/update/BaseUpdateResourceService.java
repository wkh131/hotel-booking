package com.github.wkh131.hotelbooking.listing.v1.common.crud.update;

import com.github.wkh131.hotelbooking.listing.model.authz.Action;
import com.github.wkh131.hotelbooking.listing.model.authz.AuthorizationService;
import com.github.wkh131.hotelbooking.listing.v1.common.ProtoMapper;
import com.github.wkh131.hotelbooking.listing.v1.common.Resource;
import com.github.wkh131.hotelbooking.listing.v1.common.ResourceDao;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

public class BaseUpdateResourceService<M, R extends Resource, Req extends UpdateRequest<R>> implements UpdateResourceService<R, Req> {

    private final AuthorizationService authorizationService;

    private final ResourceDao<M> resourceDao;

    private final ProtoMapper<M, R> protoMapper;

    public BaseUpdateResourceService(final AuthorizationService authorizationService,
                                     final ResourceDao<M> resourceDao,
                                     final ProtoMapper<M, R> protoMapper) {
        this.authorizationService = authorizationService;
        this.resourceDao = resourceDao;
        this.protoMapper = protoMapper;
    }

    public void update(Req request, StreamObserver<R> responseObserver) {
        R proto = request.get();
        if (proto == null) {
            responseObserver.onError(Status.INVALID_ARGUMENT.withDescription("partner field is required")
                                                            .asRuntimeException());
        } else if (proto.getName().isEmpty()) {
            responseObserver.onError(
                    Status.INVALID_ARGUMENT.withDescription("name field in resource is required")
                                           .asRuntimeException());
        } else if (proto.getName().length() > 2) {
            responseObserver.onError(Status.INVALID_ARGUMENT.withDescription("name field max length exceeded")
                                                            .asRuntimeException());
        } else if (!authorizationService.isAuthorized(Action.MODIFY, proto.getName())) {
            responseObserver.onError(Status.PERMISSION_DENIED.asRuntimeException());
        } else {
            M rModel = protoMapper.fromProto(request.get());
            if (!resourceDao.update(rModel)) {
                responseObserver.onError(Status.PERMISSION_DENIED.asRuntimeException());
            } else {
                responseObserver.onNext(protoMapper.toProto(rModel));
                responseObserver.onCompleted();
            }
        }
    }
}
