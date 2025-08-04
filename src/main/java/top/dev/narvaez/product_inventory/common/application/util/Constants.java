package top.dev.narvaez.product_inventory.common.application.util;

public class Constants {

    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    // Product Constants
    public static final Integer MIN_STOCK = 1;
    public static final Integer MAX_STOCK = 30;
    public static final String MANUFACTURER = "MIMI";
    public static final String AUDIT_USER = "SYSTEM";

    // Report Constants
    public static final String FORMAT_PDF = "pdf";
    public static final String FORMAT_HTML = "html";
    public static final String REPORT_PATH = "/templates/report/";
    public static final String IMG_PATH = "classpath:/static/images/";

    public static final String PRODUCT_LIST = "ProductList.jrxml";

}
