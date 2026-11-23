package com.github.wkh131.hotelbooking.listing.v1;

import com.github.wkh131.hotelbooking.listing.model.policy.HotelPolicyDao;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HotelPolicyServiceGrpc {

    private final HotelPolicyDao hotelPolicyDao;

    @Autowired
    public HotelPolicyServiceGrpc(final HotelPolicyDao hotelPolicyDao) {
        this.hotelPolicyDao = hotelPolicyDao;
    }

    public void getHotelPolicy(GetHotelPolicyRequest request,
                               StreamObserver<HotelPolicy> responseObserver) {
    }

    public void listHotelPolicies(ListHotelPoliciesRequest request,
                                  StreamObserver<ListHotelPoliciesResponse> responseObserver) {
    }

    public void createHotelPolicy(CreateHotelPolicyRequest request,
                                  StreamObserver<HotelPolicy> responseObserver) {
    }

    public void updateHotelPolicy(UpdateHotelPolicyRequest request,
                                  StreamObserver<HotelPolicy> responseObserver) {
    }

    public void deleteHotelPolicy(DeleteHotelPolicyRequest request,
                                  StreamObserver<com.google.protobuf.Empty> responseObserver) {
    }
}
