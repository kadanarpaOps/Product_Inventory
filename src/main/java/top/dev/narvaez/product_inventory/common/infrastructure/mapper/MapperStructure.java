package top.dev.narvaez.product_inventory.common.infrastructure.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

public abstract class MapperStructure {

    protected final ModelMapper mapper = new ModelMapper();

}
