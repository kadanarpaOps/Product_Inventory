package top.dev.narvaez.product_inventory.common.infrastructure.input.rest.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.dev.narvaez.product_inventory.common.infrastructure.input.rest.messages.ErrorResponse;
import top.dev.narvaez.product_inventory.common.model.exceptions.EntityNotFoundException;
import top.dev.narvaez.product_inventory.common.model.exceptions.MismatchedModelIdException;

import static top.dev.narvaez.product_inventory.common.infrastructure.input.rest.util.HandlerBuilder.buildResponse;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MismatchedModelIdException.class)
    public ResponseEntity<ErrorResponse> handleMismatchedModelIdException(MismatchedModelIdException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, ex);
    }

}