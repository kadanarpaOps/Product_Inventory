package top.dev.narvaez.product_inventory.users.infrastructure.config.sql;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import top.dev.narvaez.product_inventory.users.domain.models.RoleModel;
import top.dev.narvaez.product_inventory.users.domain.models.RoleName;
import top.dev.narvaez.product_inventory.users.domain.models.UserModel;
import top.dev.narvaez.product_inventory.users.infrastructure.output.persistence.adapter.RoleAdapterPort;
import top.dev.narvaez.product_inventory.users.infrastructure.output.persistence.adapter.UserAdapterPort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Component
public class UserDataLoader implements CommandLineRunner {

    private final UserAdapterPort userRepository;

    private final RoleAdapterPort roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.verifyRepositoryReady()) {
            Arrays.stream(RoleName.values()).forEach(roleName -> {
                if (roleRepository.selectByName(roleName).isEmpty())
                    roleRepository.saveRole(new RoleModel(null, roleName));
            });
        }

        if (userRepository.verifyRepositoryReady()) {
            List<RoleModel> roles = new ArrayList<>();
            Arrays.stream(RoleName.values()).forEach(roleName -> {
                roles.add(roleRepository.selectByName(roleName).get());
            });
            userRepository.saveList(List.of(
                    new UserModel(null, "Kaleth Daniel Narváez Paredes", "kadanarpa", encode("200548"), List.of(roles.get(0))),
                    new UserModel(null, "María José Botello Palacios", "mimi_jjj", encode("190406"), List.of(roles.get(1))),
                    new UserModel(null, "Liang Camilo Álvarez Tierradentro", "linlin", encode("250206"), List.of(roles.get(2)))
            ));
        }
    }

    private String encode(String password) {
        return passwordEncoder.encode(password);
    }

}