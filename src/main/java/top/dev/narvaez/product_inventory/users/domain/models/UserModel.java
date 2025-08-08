package top.dev.narvaez.product_inventory.users.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserModel {

    private Long id;
    private String name;
    private String username;
    private String password;

}
