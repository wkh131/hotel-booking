package com.github.wkh131.hotelbooking.listing.model.policy.type;

import java.util.Optional;

/**
 * Data Access Object of {@link PolicyTypeDao}.
 */
public interface PolicyTypeDao {

    /**
     * Finds a PolicyType by ID.
     *
     * @param id the id of the PolicyType.
     * @return an optional containing the PolicyType, if found, otherwise empty.
     */
    Optional<PolicyType> findPolicyType(String id);

    /**
     * Creates a PolicyType.
     *
     * @param policyType the PolicyType to create.
     * @return true if not already existed and is created, otherwise false.
     */
    boolean createPolicyType(PolicyType policyType);

    /**
     * Updates a PolicyType.
     *
     * @param policyType the value of the PolicyType to update to.
     * @return true if already existed and is updated, otherwise false.
     */
    boolean updatePolicyType(PolicyType policyType);

    /**
     * Deletes a PolicyType.
     *
     * @param id the id of the PolicyType to delete.
     * @return true if already existed and is deleted, otherwise false.
     */
    boolean deletePolicyType(String id);
}
