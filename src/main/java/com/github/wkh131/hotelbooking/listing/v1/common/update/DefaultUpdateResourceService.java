package com.github.wkh131.hotelbooking.listing.v1.common.update;

import com.github.wkh131.hotelbooking.listing.model.authz.Action;
import com.github.wkh131.hotelbooking.listing.model.authz.AuthorizationService;
import com.github.wkh131.hotelbooking.listing.v1.StringUtils;
import com.github.wkh131.hotelbooking.listing.v1.common.ModelDao;
import com.github.wkh131.hotelbooking.listing.v1.common.ProtoMapper;
import com.github.wkh131.hotelbooking.listing.v1.common.ResourceStringsResolver;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

public class DefaultUpdateResourceService<M, R> implements UpdateResourceService<R> {

    private final AuthorizationService authorizationService;

    private final ModelDao<M> resourceDao;

    private final ProtoMapper<M, R> protoMapper;

    private final ResourceStringsResolver resourceStringsResolver;

    public DefaultUpdateResourceService(final AuthorizationService authorizationService,
                                        final ModelDao<M> resourceDao,
                                        final ProtoMapper<M, R> protoMapper,
                                        final ResourceStringsResolver<R> resourceStringsResolver) {
        this.authorizationService = authorizationService;
        this.resourceDao = resourceDao;
        this.protoMapper = protoMapper;
        this.resourceStringsResolver = resourceStringsResolver;
    }

    @Override
    public void update(UpdateRequest<R> request, StreamObserver<R> responseObserver) {
        R proto = request.get();
        if (proto == null) {
            String msg = String.format("% is required", resourceStringsResolver.getFieldName());
            responseObserver.onError(Status.INVALID_ARGUMENT.withDescription(msg).asRuntimeException());
        } else {
            String name = resourceStringsResolver.getName(proto);
            if (name.isEmpty()) {
                String msg = String.format("%s.name is required", resourceStringsResolver.getFieldName());
                responseObserver.onError(Status.INVALID_ARGUMENT.withDescription(msg).asRuntimeException());
                // TODO: move resource name max length?
            } else if (name.length() > StringUtils.MAX_LENGTH_RESOURCE_NAME) {
                String msg = String.format("%s.name must be at most %d characters long",
                        resourceStringsResolver.getFieldName(), StringUtils.MAX_LENGTH_RESOURCE_NAME);
                responseObserver.onError(Status.INVALID_ARGUMENT.withDescription(msg).asRuntimeException());
            } else if (!authorizationService.isAuthorized(Action.MODIFY, name)) {
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
}
