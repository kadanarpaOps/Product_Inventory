package top.dev.narvaez.product_inventory.users.application.service.users;

import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.dev.narvaez.product_inventory.common.application.util.Constants;
import top.dev.narvaez.product_inventory.common.model.exceptions.EntityNotFoundException;
import top.dev.narvaez.product_inventory.users.domain.models.UserModel;
import top.dev.narvaez.product_inventory.users.domain.ports.out.UserRepositoryPort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepositoryPort userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserModel user = userRepository.selectByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(Constants.USER_ENTITY, Constants.USERNAME, username));

        List<SimpleGrantedAuthority> roles = new ArrayList<>();

        user.getRoles().forEach(role -> {
            roles.add(new SimpleGrantedAuthority("ROLE_" + role.getName().name()));
        });

        return new User(user.getUsername(), user.getPassword(), roles);

    }

}