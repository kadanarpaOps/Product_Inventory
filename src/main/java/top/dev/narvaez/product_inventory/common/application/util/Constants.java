package top.dev.narvaez.product_inventory.common.application.util;

public class Constants {

    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    // Utility Constants
    public static final Integer MIN_STOCK = 1;
    public static final Integer MAX_STOCK = 30;
    public static final String MANUFACTURER = "MIMI";
    public static final String AUDIT_USER = "SYSTEM";

    // Message Constants
    public static final String ENTITY_NOT_FOUND_EXCEPTION = "Not query result for entity %s with %s %s";
    public static final String MISMATCH_MODEL_ID_EXCEPTION = "The Rest %s id %d does not match the Body %d id %d";
    public static final String ID = "ID";
    public static final String NAME = "NAME";

    public static final String STOCK_BELOW_MINIMUM_EXCEPTION = "Stock %d is below the minimum limit %d";
    public static final String STOCK_ABOVE_MAXIMUM_EXCEPTION = "Stock %d is above the maximum limit %d";
    public static final String PRODUCT_ALREADY_ACTIVATED_EXCEPTION = "Product is already activated";
    public static final String PRODUCT_ALREADY_DISABLED_EXCEPTION = "Product is already disabled";

}
