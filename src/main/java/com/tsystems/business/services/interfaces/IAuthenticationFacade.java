package com.tsystems.business.services.interfaces;

import org.springframework.security.core.Authentication;

/**
 * Created by nikita on 04.10.2020.
 */
public interface IAuthenticationFacade {
    Authentication getAuthentication();
}
