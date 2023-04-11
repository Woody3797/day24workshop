package ibf2022.paf.day24workshop.repository;

public class DBQueries {
    
    public static final String GET_ALL_PRODUCTS = "SELECT * FROM fruits_products";
    public static final String INSERT_PURCHASE_ORDER = "INSERT INTO purchase_order(order_id, order_date, customer_name, ship_address, notes, tax) VALUES(?, SYSDATE(), ?, ?, ?, 0.05)";
    public static final String INSERT_PURCHASE_ORDER_DETAILS = "INSERT INTO purchase_order_details(product, unit_price, discount, quantity, order_id) VALUES(?, ?, ?, ?, ?)";
}
