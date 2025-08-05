package top.dev.narvaez.product_inventory.reports.application.service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.stereotype.Service;
import top.dev.narvaez.product_inventory.common.application.util.Constants;
import top.dev.narvaez.product_inventory.reports.domain.ports.in.ReportUseCases;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportServicePort implements ReportUseCases {

    public byte[] exportListReport(List<?> modelsList, String reportFile, String reportFormat) throws JRException {

        InputStream modelReportStream = getClass().getResourceAsStream(Constants.REPORT_PATH.concat(reportFile));
        JasperReport jasperReport = JasperCompileManager.compileReport(modelReportStream);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(modelsList);

        Map<String, Object> params = new HashMap<>();
        params.put("imagesPath", Constants.IMG_PATH);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        if (reportFormat.equalsIgnoreCase(Constants.FORMAT_HTML)) {
            HtmlExporter htmlExporter = new HtmlExporter();
            htmlExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            htmlExporter.setExporterOutput(new SimpleHtmlExporterOutput(outputStream));
            htmlExporter.exportReport();
        } else if (reportFormat.equalsIgnoreCase(Constants.FORMAT_XLS)) {
            JRXlsxExporter xlsExporter = new JRXlsxExporter();
            xlsExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            xlsExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
            xlsExporter.exportReport();
        } else {
            return JasperExportManager.exportReportToPdf(jasperPrint);
        }
        return outputStream.toByteArray();
    }

}