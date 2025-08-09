package top.dev.narvaez.product_inventory.products.domain.exceptions;

import top.dev.narvaez.product_inventory.common.application.util.Constants;

public class ProductAlreadyActivatedException extends RuntimeException {
    public ProductAlreadyActivatedException() {
        super(Constants.PRODUCT_ALREADY_ACTIVATED_EXCEPTION);
    }
}
