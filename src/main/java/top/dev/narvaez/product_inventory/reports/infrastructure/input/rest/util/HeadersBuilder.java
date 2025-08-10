package top.dev.narvaez.product_inventory.reports.infrastructure.input.rest.util;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import top.dev.narvaez.product_inventory.common.application.util.Constants;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
public class HeadersBuilder {

    public static HttpHeaders prepareHeaders(String fileFormat) {
        HttpHeaders headers = new HttpHeaders();

        if (fileFormat.equalsIgnoreCase(Constants.FORMAT_HTML))
            headers.setContentType(MediaType.TEXT_HTML);
        else if (fileFormat.equalsIgnoreCase(Constants.FORMAT_XLS))
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        else
            headers.setContentType(MediaType.APPLICATION_PDF);

        DateTimeFormatter format = DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss");
        headers.setContentDispositionFormData("attachment", "ProductsList_" + LocalDateTime.now().format(format) + "." +
                (fileFormat.toLowerCase() == Constants.FORMAT_HTML ? "html" :
                        (fileFormat.toLowerCase() == Constants.FORMAT_XLS ? "xls" : "pdf")));

        return headers;
    }

}
