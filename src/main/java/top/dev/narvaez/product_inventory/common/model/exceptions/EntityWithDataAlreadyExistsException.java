package top.dev.narvaez.product_inventory.common.model.exceptions;

import top.dev.narvaez.product_inventory.common.application.util.Constants;

public class EntityWithDataAlreadyExistsException extends RuntimeException {
    public EntityWithDataAlreadyExistsException(String entity, String data, String value) {
        super(String.format(Constants.ENTITY_ALREADY_EXISTS_WITH_DATA, entity, data, value));
    }
}
