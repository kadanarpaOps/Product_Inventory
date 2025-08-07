package top.dev.narvaez.product_inventory.products.domain.exceptions;

import top.dev.narvaez.product_inventory.common.application.util.Constants;

public class StockBelowMinimumException extends RuntimeException {
    public StockBelowMinimumException(Integer stock, Integer minStock) {
        super(String.format(Constants.STOCK_BELOW_MINIMUM_EXCEPTION, stock, minStock));
    }
}
