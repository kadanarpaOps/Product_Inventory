package top.dev.narvaez.product_inventory.users.domain.exceptions;

import top.dev.narvaez.product_inventory.common.application.util.Constants;

public class NoAuthenticatedUserException extends RuntimeException {
    public NoAuthenticatedUserException() {
        super(Constants.NO_AUTH_USER_EXCEPTION);
    }
}
