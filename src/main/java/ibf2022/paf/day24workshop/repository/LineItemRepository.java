package ibf2022.paf.day24workshop.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.paf.day24workshop.model.FruitProducts;
import ibf2022.paf.day24workshop.model.LineItem;
import ibf2022.paf.day24workshop.service.OrderUtility;

@Repository
public class LineItemRepository {
    
    @Autowired
    JdbcTemplate template;

    public static BigDecimal discount;
    public List<FruitProducts> getProducts() {
        
        String getAllquery = DBQueries.GET_ALL_PRODUCTS;
        return template.query(getAllquery, (rs, rownum) -> {
            FruitProducts fruitProducts = new FruitProducts();
            fruitProducts.setId(rs.getInt("id"));
            fruitProducts.setName(rs.getString("name"));
            fruitProducts.setStandardPrice(rs.getBigDecimal("standard_price"));
            fruitProducts.setDiscount(rs.getBigDecimal("discount"));
            return fruitProducts;
        });
    }

    public void addLineItems(List<LineItem> lineItems, String orderId) {
        List<Object[]> data = lineItems.stream()
            .map(li -> {
            Object[] objs = new Object[5];
            objs[0] = li.getProduct();
            objs[1] = OrderUtility.calculateUnitPrice(li.getProduct(), li.getQuantity());
            objs[2] = discount;
            objs[3] = li.getQuantity();
            objs[4] = orderId;
            return objs;
        }).toList();

        template.batchUpdate(DBQueries.INSERT_PURCHASE_ORDER_DETAILS, data);
    }
}
