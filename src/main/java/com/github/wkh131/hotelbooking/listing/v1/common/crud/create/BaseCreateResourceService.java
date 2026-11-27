package com.github.wkh131.hotelbooking.listing.v1.common.crud.create;

import com.github.wkh131.hotelbooking.listing.model.authz.Action;
import com.github.wkh131.hotelbooking.listing.model.authz.AuthorizationService;
import com.github.wkh131.hotelbooking.listing.v1.common.ProtoMapper;
import com.github.wkh131.hotelbooking.listing.v1.common.Resource;
import com.github.wkh131.hotelbooking.listing.v1.common.ResourceDao;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

import static com.github.wkh131.hotelbooking.listing.v1.StringUtils.isResourceIdValid;

public abstract class BaseCreateResourceService<M, R extends Resource, Req extends CreateRequest<R>> implements CreateResourceService<R, Req> {

    private final AuthorizationService authorizationService;

    private final ResourceDao<M> resourceDao;

    private final ProtoMapper<M, R> protoMapper;

    public BaseCreateResourceService(final AuthorizationService authorizationService,
                                     final ResourceDao<M> resourceDao,
                                     final ProtoMapper<M, R> protoMapper) {
        this.authorizationService = authorizationService;
        this.resourceDao = resourceDao;
        this.protoMapper = protoMapper;
    }

    @Override
    public void create(Req request, StreamObserver<R> responseObserver) {
        final String resourceId = request.getId();
        if (resourceId.isEmpty()) {
            responseObserver.onError(Status.INVALID_ARGUMENT.withDescription(" format").asRuntimeException());
        } else if (!isResourceIdValid(resourceId)) {
            // TODO: change from resource id?
            responseObserver.onError(Status.INVALID_ARGUMENT.withDescription("invalid resource id format")
                                                            .asRuntimeException());
        } else {
            final String parentName = request.getParent();
            // TODO: need to check that parentName actually exists?
            String resourceName = makeName(parentName, resourceId);
            if (!authorizationService.isAuthorized(Action.MODIFY, resourceName)) {
                responseObserver.onError(Status.PERMISSION_DENIED.asRuntimeException());
            } else {
                final M rModel = protoMapper.fromProto(request.getProto());
                if (!resourceDao.create(rModel)) {
                    // duplicate encountered
                    // TODO: add exception handling in case of data access issues
                    responseObserver.onError(Status.ALREADY_EXISTS.asRuntimeException());
                } else {
                    responseObserver.onNext(protoMapper.toProto(rModel));
                    responseObserver.onCompleted();
                }
            }
        }
    }

    protected abstract String makeName(String parentName, String resourceId);
}
