package top.dev.narvaez.product_inventory.users.infrastructure.output.persistence.adapter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import top.dev.narvaez.product_inventory.users.domain.models.UserModel;
import top.dev.narvaez.product_inventory.users.domain.ports.out.UserRepositoryPort;
import top.dev.narvaez.product_inventory.users.infrastructure.output.persistence.mapper.UserPersistenceMapper;
import top.dev.narvaez.product_inventory.users.infrastructure.output.persistence.repository.JpaUserRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
public class UserAdapterPort implements UserRepositoryPort {

    private final JpaUserRepository userRepository;

    private final UserPersistenceMapper mapper;

    @Override
    public UserModel saveUser(UserModel userModel) {
        return mapper.toModel(userRepository.save(mapper.toEntity(userModel)));
    }

    @Override
    public List<UserModel> selectAll() {
        return userRepository.findAll().stream().map(mapper::toModel).toList();
    }

    @Override
    public Optional<UserModel> selectById(Long id) {
        return userRepository.findById(id).map(mapper::toModel);
    }

    @Override
    public Optional<UserModel> selectByUsername(String username) {
        return userRepository.findUserByUsername(username).map(mapper::toModel);
    }
}
