package top.daisy_dev.simple_auditing.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReqProductDTO {

    private String name;
    private String description;
    private BigDecimal price;

}
