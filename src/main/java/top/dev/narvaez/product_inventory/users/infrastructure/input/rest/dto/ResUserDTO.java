package top.dev.narvaez.product_inventory.users.infrastructure.input.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import top.dev.narvaez.product_inventory.users.domain.models.RoleName;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResUserDTO {

    private Long id;
    private String name;
    private String username;
    private List<RoleName> roles;

}