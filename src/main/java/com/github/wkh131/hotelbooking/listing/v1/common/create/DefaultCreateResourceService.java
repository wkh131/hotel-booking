package com.github.wkh131.hotelbooking.listing.v1.common.create;

import com.github.wkh131.hotelbooking.listing.model.authz.Action;
import com.github.wkh131.hotelbooking.listing.model.authz.AuthorizationService;
import com.github.wkh131.hotelbooking.listing.v1.common.ModelDao;
import com.github.wkh131.hotelbooking.listing.v1.common.ProtoMapper;
import com.github.wkh131.hotelbooking.listing.v1.common.ResourceStringsResolver;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

import static com.github.wkh131.hotelbooking.listing.v1.GrpcServiceUtils.fieldMissingErrorMsg;
import static com.github.wkh131.hotelbooking.listing.v1.StringUtils.isResourceIdValid;

public class DefaultCreateResourceService<M, R> implements CreateResourceService<R> {

    private final AuthorizationService authorizationService;

    private final ModelDao<M> resourceDao;

    private final ProtoMapper<M, R> protoMapper;

    private final ResourceStringsResolver<R> resourceStringsResolver;

    public DefaultCreateResourceService(final AuthorizationService authorizationService,
                                        final ModelDao<M> resourceDao,
                                        final ProtoMapper<M, R> protoMapper,
                                        final ResourceStringsResolver<R> resourceStringsResolver) {
        this.authorizationService = authorizationService;
        this.resourceDao = resourceDao;
        this.protoMapper = protoMapper;
        this.resourceStringsResolver = resourceStringsResolver;
    }

    @Override
    public void create(CreateRequest<R> request, StreamObserver<R> responseObserver) {
        final String resourceId = request.getId();
        if (resourceId.isEmpty()) {
            responseObserver.onError(Status.INVALID_ARGUMENT.withDescription(
                    fieldMissingErrorMsg(resourceStringsResolver.getFieldName())).asRuntimeException());
        } else if (!isResourceIdValid(resourceId)) {
            // TODO: change from resource id?
            responseObserver.onError(Status.INVALID_ARGUMENT.withDescription("invalid resource id format")
                                                            .asRuntimeException());
        } else {
            final String parentName = request.getParent();
            // TODO: need to check that parentName actually exists?
            String resourceName = resourceStringsResolver.makeName(parentName, resourceId);
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
}
