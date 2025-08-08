package top.dev.narvaez.product_inventory.common.infrastructure.input.rest.util;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import top.dev.narvaez.product_inventory.common.application.util.Constants;
import top.dev.narvaez.product_inventory.common.infrastructure.input.rest.messages.ErrorResponse;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public class HandlerBuilder {

    public static ResponseEntity<ErrorResponse> buildResponse(HttpStatus status, RuntimeException ex) {
        Map<String, Object> data = new HashMap<>();
        data.put(Constants.TRACE, getStackTraceAsString(ex));

        return new ResponseEntity<>(new ErrorResponse(status.value(), ex.getMessage(), LocalDateTime.now(), data), status);
    }

    private static String getStackTraceAsString(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);
        return sw.toString();
    }

}
