package top.dev.narvaez.product_inventory.products.infrastructure.input.rest.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.dev.narvaez.product_inventory.common.infrastructure.input.rest.messages.ErrorResponse;
import top.dev.narvaez.product_inventory.products.domain.exceptions.ProductAlreadyActivatedException;
import top.dev.narvaez.product_inventory.products.domain.exceptions.ProductAlreadyDisabledException;
import top.dev.narvaez.product_inventory.products.domain.exceptions.StockAboveMaximumException;
import top.dev.narvaez.product_inventory.products.domain.exceptions.StockBelowMinimumException;

import static top.dev.narvaez.product_inventory.common.infrastructure.input.rest.util.HandlerBuilder.buildResponse;

@RestControllerAdvice
public class ProductExceptionHandler {

    @ExceptionHandler({
            StockBelowMinimumException.class,
            StockAboveMaximumException.class,
            ProductAlreadyActivatedException.class,
            ProductAlreadyDisabledException.class
    })
    public ResponseEntity<ErrorResponse> handleBadRequests(RuntimeException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex);
    }


}
