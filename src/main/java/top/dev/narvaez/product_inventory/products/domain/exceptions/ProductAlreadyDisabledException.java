package top.dev.narvaez.product_inventory.products.domain.exceptions;

import top.dev.narvaez.product_inventory.common.application.util.Constants;

public class ProductAlreadyDisabledException extends RuntimeException {
    public ProductAlreadyDisabledException() {
        super(Constants.PRODUCT_ALREADY_DISABLED_EXCEPTION);
    }
}
