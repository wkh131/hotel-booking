package com.github.wkh131.hotelbooking.listing.model.authz;

import org.springframework.stereotype.Service;

/**
 * Implementation of {@link AuthorizationService} that gives full access to all.
 * This will obviously need to be implemented.
 */
@Service
public class PermitAllAuthorizationService implements AuthorizationService {

    @Override
    public boolean isAuthorized(String userId, Action action, String resourceId) {
        return true;
    }
}
