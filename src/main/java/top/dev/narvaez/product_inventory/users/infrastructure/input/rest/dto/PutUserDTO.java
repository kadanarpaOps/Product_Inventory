package top.dev.narvaez.product_inventory.users.infrastructure.input.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PutUserDTO extends ReqUserDTO {

    private Long id;

}