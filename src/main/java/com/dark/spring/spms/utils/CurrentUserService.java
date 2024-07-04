package com.dark.spring.spms.utils;

import com.dark.spring.spms.entity.Customer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserService {

    public Customer getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Rana CurrentUserService authentication=" + authentication);
        if (authentication != null ) {
            return (Customer) authentication.getPrincipal();
        }
        return null;
    }
}
