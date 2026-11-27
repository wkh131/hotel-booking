package com.github.wkh131.hotelbooking.listing.v1.common.crud.get;

import com.github.wkh131.hotelbooking.listing.model.authz.Action;
import com.github.wkh131.hotelbooking.listing.model.authz.AuthorizationService;
import com.github.wkh131.hotelbooking.listing.v1.StringUtils;
import com.github.wkh131.hotelbooking.listing.v1.common.ProtoMapper;
import com.github.wkh131.hotelbooking.listing.v1.common.ResourceDao;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

public class BaseGetResourceService<T, R, Req extends GetRequest> implements GetResourceService<R, Req> {

    private final AuthorizationService authorizationService;

    private final ResourceDao<T> resourceDao;

    private final ProtoMapper<T, R> protoMapper;

    public BaseGetResourceService(final AuthorizationService authorizationService,
                                  final ResourceDao<T> resourceDao,
                                  final ProtoMapper<T, R> protoMapper) {
        this.authorizationService = authorizationService;
        this.resourceDao = resourceDao;
        this.protoMapper = protoMapper;
    }

    @Override
    public void get(Req request, StreamObserver<R> responseObserver) {
        final String resourceName = request.getName();
        if (resourceName.isEmpty()) {
            responseObserver.onError(
                    Status.INVALID_ARGUMENT.withDescription("name field is required").asRuntimeException());
        } else if (resourceName.length() > StringUtils.MAX_LENGTH_RESOURCE_NAME) {
            responseObserver.onError(Status.INVALID_ARGUMENT.withDescription(
                    "name field max length is " + StringUtils.MAX_LENGTH_RESOURCE_NAME).asRuntimeException());
        } else if (!authorizationService.isAuthorized(Action.READ, resourceName)) {
            responseObserver.onError(Status.PERMISSION_DENIED.asRuntimeException());
        } else {
            resourceDao.get(resourceName)
                       .map(protoMapper::toProto)
                       .ifPresentOrElse(responseObserver::onNext,
                               () -> responseObserver.onError(Status.NOT_FOUND.asRuntimeException()));
            responseObserver.onCompleted();
        }
    }
}
