package top.dev.narvaez.product_inventory.reports.infrastructure.input.rest;

import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.dev.narvaez.product_inventory.common.application.util.Constants;
import top.dev.narvaez.product_inventory.products.domain.models.ProductModel;
import top.dev.narvaez.product_inventory.products.domain.ports.in.ProductUseCases;
import top.dev.narvaez.product_inventory.reports.domain.ports.in.ReportUseCases;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/report/product")
public class ProductReportRestController {

    private final ProductUseCases productService;

    private final ReportUseCases reportService;

    @GetMapping("/list")
    public ResponseEntity<byte[]> generatePdfListReport() throws JRException {
        List<ProductModel> data = productService.findAllProducts();

        byte[] pdfBytes = reportService.exportListReport(data, Constants.PRODUCT_LIST, Constants.FORMAT_PDF);

        return new ResponseEntity<>(pdfBytes, prepareHeaders(), HttpStatus.OK);
    }

    private HttpHeaders prepareHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("ddMMyyyy-HHmmss");
        headers.setContentDispositionFormData("attachment", "ProductsList_" + LocalDateTime.now().format(format) + ".pdf");
        return headers;
    }

}
