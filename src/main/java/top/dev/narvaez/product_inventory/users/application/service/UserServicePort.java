package top.dev.narvaez.product_inventory.users.application.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import top.dev.narvaez.product_inventory.common.application.util.Constants;
import top.dev.narvaez.product_inventory.common.model.exceptions.EntityNotFoundException;
import top.dev.narvaez.product_inventory.common.model.exceptions.MismatchedModelIdException;
import top.dev.narvaez.product_inventory.users.domain.models.RoleModel;
import top.dev.narvaez.product_inventory.users.domain.models.UserModel;
import top.dev.narvaez.product_inventory.users.domain.ports.in.RoleUseCases;
import top.dev.narvaez.product_inventory.users.domain.ports.in.UserUseCases;
import top.dev.narvaez.product_inventory.users.domain.ports.out.UserRepositoryPort;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class UserServicePort implements UserUseCases {

    private final UserRepositoryPort userRepository;

    private final RoleUseCases roleService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserModel saveUser(UserModel userModel) {
        encodePassword(userModel);
        assignRoles(userModel);
        return userRepository.saveUser(userModel);
    }

    @Override
    public UserModel updateUser(UserModel userModel, Long userId) {
        if (userModel.getId() == null) userModel.setId(userId);
        if (!userModel.getId().equals(userId))
            throw new MismatchedModelIdException(userId, userModel.getId(), UserModel.class.getSimpleName());

        UserModel userFromEntity = findAnyUserById(userId);

        if (!passwordEncoder.matches(userModel.getPassword(), userFromEntity.getPassword())) {
            userFromEntity.setPassword(userModel.getPassword());
            encodePassword(userFromEntity);
        }

        if (userModel.getName() != null ) userFromEntity.setName(userModel.getName());
        if (userModel.getUsername() != null) userFromEntity.setUsername(userModel.getUsername());

        if (!userModel.getRoles().isEmpty()) {
            userFromEntity.setRoles(userModel.getRoles());
            assignRoles(userFromEntity);
        }

        return userRepository.saveUser(userFromEntity);
    }

    @Override
    public UserModel findAnyUserById(Long id) {
        return userRepository.selectById(id)
                .orElseThrow(() -> new EntityNotFoundException(Constants.USER_ENTITY, Constants.ID, id.toString()));
    }

    @Override
    public UserModel findAnyUserByUsername(String username) {
        return userRepository.selectByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(Constants.USER_ENTITY, Constants.USERNAME, username));
    }

    @Override
    public List<UserModel> findAllUsers() {
        return userRepository.selectAll();
    }

    private void encodePassword(UserModel userModel) {
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
    }

    private void assignRoles(UserModel userModel) {
        List<RoleModel> roles = new ArrayList<>();
        userModel.getRoles().forEach(role -> {
            roles.add(roleService.findAnyRoleByName(role.getName()));
        });
        userModel.setRoles(roles);
    }

}