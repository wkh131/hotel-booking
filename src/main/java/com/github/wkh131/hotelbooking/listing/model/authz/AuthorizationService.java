package com.github.wkh131.hotelbooking.listing.model.authz;

/**
 * Simple authorization service for checking authorization based on the triple (user,action,resource).
 * TODO: expand and implement
 */
public interface AuthorizationService {

    /**
     * Determines if user is authorized to perform action on the resource.
     * @param userId the unique ID of the user.
     * @param action the action being performed by the suer.
     * @param resourceId the resource that the action is being performed on.
     * @return true if authorized, otherwise false.
     */
    boolean isAuthorized(String userId, Action action, String resourceId);
}
