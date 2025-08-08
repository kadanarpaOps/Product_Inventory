package top.dev.narvaez.product_inventory.users.domain.ports.out;

import top.dev.narvaez.product_inventory.users.domain.models.UserModel;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryPort {

    UserModel saveProduct(UserModel product);

    List<UserModel> selectAll();

    Optional<UserModel> selectById(Long id);

    Optional<UserModel> selectByUsername(String email);

}
