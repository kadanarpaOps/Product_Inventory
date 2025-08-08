package top.dev.narvaez.product_inventory.users.domain.ports.in;

import top.dev.narvaez.product_inventory.users.domain.models.UserModel;

import java.util.List;

public interface UserUseCases {

    UserModel saveUser(UserModel user);

    UserModel updateUser(UserModel user);

    UserModel findAnyUserById(Long id);

    UserModel findAnyUserByUsername(String username);

    List<UserModel> findAllUsers();

}