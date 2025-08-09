package top.dev.narvaez.product_inventory.common.model.exceptions;

import top.dev.narvaez.product_inventory.common.application.util.Constants;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(String entity, String param, String value) {
        super(String.format(Constants.ENTITY_NOT_FOUND_EXCEPTION, entity, param, value));
    }
}
