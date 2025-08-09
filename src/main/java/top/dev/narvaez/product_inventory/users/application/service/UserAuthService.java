package top.dev.narvaez.product_inventory.users.application.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import top.dev.narvaez.product_inventory.common.application.util.Constants;
import top.dev.narvaez.product_inventory.users.domain.exceptions.NoAuthenticatedUserException;
import top.dev.narvaez.product_inventory.users.domain.models.RoleModel;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserAuthService {

    private final UserServicePort userServicePort;

    public String getAuthUsername() {
        return getAuthenticatedUser().getUsername();
    }

    public Long getAuthUserId() {
        return userServicePort.findAnyUserByUsername(getAuthUsername()).getId();
    }

    public List<String> getAuthRoles() {
        return getAuthenticatedUser().getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }

    public UserDetails getAuthenticatedUser() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() != null) return (UserDetails) auth.getPrincipal();

        throw new NoAuthenticatedUserException();

    }

}