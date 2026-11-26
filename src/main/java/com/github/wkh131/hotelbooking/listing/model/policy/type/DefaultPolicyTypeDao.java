package com.github.wkh131.hotelbooking.listing.model.policy.type;

import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Default implementation of {@link PolicyTypeDao}.
 */
@Repository
public class DefaultPolicyTypeDao implements PolicyTypeDao {

    @Override
    public Optional<PolicyType> findPolicyType(String id) {
        return Optional.empty();
    }

    @Override
    public boolean createPolicyType(PolicyType policyType) {
        return false;
    }

    @Override
    public boolean updatePolicyType(PolicyType policyType) {
        return false;
    }

    @Override
    public boolean deletePolicyType(String id) {
        return false;
    }
}
