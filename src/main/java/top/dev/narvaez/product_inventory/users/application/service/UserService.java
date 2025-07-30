package top.dev.narvaez.product_inventory.users.application.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import top.dev.narvaez.product_inventory.common.application.util.ProvisionalConstants;

@Service
public class UserService {

    public String getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth == null ? ProvisionalConstants.AUDIT_USER : auth.getName();
    }

}
