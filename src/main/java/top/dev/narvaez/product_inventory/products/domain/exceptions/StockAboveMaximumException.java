package top.dev.narvaez.product_inventory.products.domain.exceptions;

import top.dev.narvaez.product_inventory.common.application.util.Constants;

public class StockAboveMaximumException extends RuntimeException {
    public StockAboveMaximumException(Integer stock, Integer maxStock) {
        super(String.format(Constants.STOCK_ABOVE_MAXIMUM_EXCEPTION, stock, maxStock));
    }
}
