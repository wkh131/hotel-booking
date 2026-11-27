package com.github.wkh131.hotelbooking.listing.model.authz;

import org.springframework.stereotype.Service;

/**
 * Implementation of {@link AuthorizationService} that gives read access to all.
 * It does not give write access to anyone.
 * This will obviously need to be implemented.
 */
@Service
public class ReadOnlyAuthorizationService implements AuthorizationService {

    @Override
    public boolean isAuthorized(Action action, String resourceId) {
        return true;
    }
}
