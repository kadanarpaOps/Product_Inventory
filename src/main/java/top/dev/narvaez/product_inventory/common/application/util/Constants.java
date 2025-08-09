package top.dev.narvaez.product_inventory.common.application.util;

import top.dev.narvaez.product_inventory.users.infrastructure.output.persistence.entity.RoleEntity;
import top.dev.narvaez.product_inventory.users.infrastructure.output.persistence.entity.UserEntity;

public class Constants {

    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    // Utility Constants
    public static final Integer MIN_STOCK = 1;
    public static final Integer MAX_STOCK = 30;
    public static final String MANUFACTURER = "MIMI";
    public static final String AUDIT_USER = "SYSTEM";

    public static final String ADMIN_PASSWORD = "200548";

    // Message Constants
    public static final String ENTITY_NOT_FOUND_EXCEPTION = "Not query result for entity %s with %s %s";
    public static final String ENTITY_ALREADY_EXISTS_WITH_DATA = "Entity %s already exists with %s %s";
    public static final String MISMATCH_MODEL_ID_EXCEPTION = "The Rest %s id %d does not match the Body %s id %d";
    public static final String TRACE = "trace";
    public static final String ID = "ID";
    public static final String NAME = "NAME";
    public static final String USERNAME = "USERNAME";

    public static final String USER_ENTITY = UserEntity.class.getSimpleName();
    public static final String ROLE_ENTITY = RoleEntity.class.getSimpleName();

    public static final String STOCK_BELOW_MINIMUM_EXCEPTION = "Stock %d is below the minimum limit %d";
    public static final String STOCK_ABOVE_MAXIMUM_EXCEPTION = "Stock %d is above the maximum limit %d";
    public static final String PRODUCT_ALREADY_ACTIVATED_EXCEPTION = "Product is already activated";
    public static final String PRODUCT_ALREADY_DISABLED_EXCEPTION = "Product is already disabled";

    public static final String NO_AUTH_USER_EXCEPTION = "No result for an authenticated user search";

}
