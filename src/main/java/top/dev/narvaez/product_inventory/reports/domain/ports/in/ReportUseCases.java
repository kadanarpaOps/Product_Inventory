package top.dev.narvaez.product_inventory.reports.domain.ports.in;

import net.sf.jasperreports.engine.JRException;

import java.util.List;

public interface ReportUseCases {

    public byte[] exportListReport(String reportFormat, List<?> modelsList) throws JRException;

}
