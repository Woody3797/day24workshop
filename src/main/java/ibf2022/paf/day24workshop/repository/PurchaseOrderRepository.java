package ibf2022.paf.day24workshop.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.paf.day24workshop.model.PurchaseOrder;

@Repository
public class PurchaseOrderRepository {

    @Autowired
    JdbcTemplate template;

    public boolean insertPurchaseOrder(PurchaseOrder purchaseOrder) {
        return template.update(DBQueries.INSERT_PURCHASE_ORDER, purchaseOrder.getOrderId(), purchaseOrder.getName(), purchaseOrder.getShip_address(), purchaseOrder.getNotes()) > 0;
    }
    
}
