package com.github.wkh131.hotelbooking.listing.v1;

import com.github.wkh131.hotelbooking.listing.model.visualasset.photo.PhotoDao;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PhotoServiceGrpc {

    private final PhotoDao photoDao;

    @Autowired
    public PhotoServiceGrpc(final PhotoDao photoDao) {
        this.photoDao = photoDao;
    }

    public void getPhoto(GetPhotoRequest request, StreamObserver<Photo> responseObserver) {
    }

    public void listPhotos(ListPhotosRequest request,
                           StreamObserver<ListPhotosResponse> responseObserver) {
    }

    public void createPhoto(CreatePhotoRequest request, StreamObserver<Photo> responseObserver) {
    }

    public void updatePhoto(UpdatePhotoRequest request, StreamObserver<Photo> responseObserver) {
    }

    public void deletePhoto(DeletePhotoRequest request,
                            StreamObserver<com.google.protobuf.Empty> responseObserver) {
    }
}
