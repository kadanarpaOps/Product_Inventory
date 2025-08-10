package top.dev.narvaez.product_inventory.reports.infrastructure.input.rest;

import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.dev.narvaez.product_inventory.common.application.util.Constants;
import top.dev.narvaez.product_inventory.products.domain.models.ProductModel;
import top.dev.narvaez.product_inventory.products.domain.ports.in.ProductUseCases;
import top.dev.narvaez.product_inventory.reports.domain.ports.in.ReportUseCases;

import java.util.List;

import static top.dev.narvaez.product_inventory.reports.infrastructure.input.rest.util.HeadersBuilder.prepareHeaders;

@AllArgsConstructor
@RestController
@RequestMapping("/api/report/product")
public class ProductReportRestController {

    private final ProductUseCases productService;

    private final ReportUseCases reportService;

    @PostMapping("/list")
    public ResponseEntity<byte[]> generatePdfListReport(
            @RequestParam(defaultValue = "pdf") String format
    ) throws JRException {
        List<ProductModel> data = productService.findAllProducts();

        byte[] pdfBytes = reportService.exportListReport(data, Constants.PRODUCT_LIST, format);

        return new ResponseEntity<>(pdfBytes, prepareHeaders(format), HttpStatus.OK);
    }


}
