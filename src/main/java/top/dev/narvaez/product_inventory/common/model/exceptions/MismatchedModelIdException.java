package top.dev.narvaez.product_inventory.common.model.exceptions;

import top.dev.narvaez.product_inventory.common.application.util.Constants;

public class MismatchedModelIdException extends RuntimeException {
    public MismatchedModelIdException(Long restId, Long bodyId, String model) {
        super(String.format(Constants.MISMATCH_MODEL_ID_EXCEPTION, model, restId, model, bodyId));
    }
}
