USE audit_labs;

DELIMITER $$

CREATE TRIGGER trg_products_update_audit
    AFTER UPDATE
    ON PRODUCTS
    FOR EACH ROW
BEGIN
    INSERT INTO AUDIT_PRODUCTS (PRODUCT_ID,
                                OLD_PRODUCT_NAME,
                                NEW_PRODUCT_NAME,
                                OLD_PRODUCT_DESCRIPTION,
                                NEW_PRODUCT_DESCRIPTION,
                                OLD_PRODUCT_PRICE,
                                NEW_PRODUCT_PRICE,
                                OLD_PRODUCT_STOCK,
                                NEW_PRODUCT_STOCK,
                                OPERATION,
                                AUDIT_USER,
                                AUDIT_DATE)
    VALUES (
            NEW.PRODUCT_ID,
            OLD.PRODUCT_NAME,
            NEW.PRODUCT_NAME,
            OLD.PRODUCT_DESCRIPTION,
            NEW.PRODUCT_DESCRIPTION,
            OLD.PRODUCT_PRICE,
            NEW.PRODUCT_PRICE,
            OLD.PRODUCT_STOCK,
            NEW.PRODUCT_STOCK,
            COALESCE(@operation, 'UPDATE_SYSTEM'),
            COALESCE(@audit_user, 'TRIGGER_SYSTEM'),
            COALESCE(@audit_date, NOW()));
END$$

DELIMITER ;