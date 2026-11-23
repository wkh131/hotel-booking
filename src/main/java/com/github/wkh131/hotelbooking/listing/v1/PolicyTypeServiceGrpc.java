package com.github.wkh131.hotelbooking.listing.v1;

import com.github.wkh131.hotelbooking.listing.model.policy.type.PolicyTypeDao;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PolicyTypeServiceGrpc {

    private final PolicyTypeDao policyTypeDao;

    @Autowired
    public PolicyTypeServiceGrpc(final PolicyTypeDao policyTypeDao) {
        this.policyTypeDao = policyTypeDao;
    }

    public void getPolicyType(GetPolicyTypeRequest request,
                              StreamObserver<PolicyType> responseObserver) {
    }

    public void listPolicyTypes(ListPolicyTypesRequest request,
                                StreamObserver<ListPolicyTypesResponse> responseObserver) {
    }

    public void createPolicyType(CreatePolicyTypeRequest request,
                                 StreamObserver<PolicyType> responseObserver) {
    }

    public void updatePolicyType(UpdatePolicyTypeRequest request,
                                 StreamObserver<PolicyType> responseObserver) {
    }

    public void deletePolicyType(DeletePolicyTypeRequest request,
                                 StreamObserver<com.google.protobuf.Empty> responseObserver) {
    }
}
