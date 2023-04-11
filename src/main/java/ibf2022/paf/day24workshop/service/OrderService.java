package ibf2022.paf.day24workshop.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ibf2022.paf.day24workshop.exception.OrderException;
import ibf2022.paf.day24workshop.model.FruitProducts;
import ibf2022.paf.day24workshop.model.PurchaseOrder;
import ibf2022.paf.day24workshop.repository.LineItemRepository;
import ibf2022.paf.day24workshop.repository.PurchaseOrderRepository;

@Service
public class OrderService {

    LineItemRepository itemRepository;
    PurchaseOrderRepository purchaseOrderRepository;

    OrderService(LineItemRepository itemRepository, PurchaseOrderRepository purchaseOrderRepository) {
        this.itemRepository = itemRepository;
        this.purchaseOrderRepository = purchaseOrderRepository;
    }

    @Transactional(rollbackFor = OrderException.class)
    public void createOrder(PurchaseOrder purchaseOrder) throws OrderException {
        // create order id
        Random rand = new Random();
        int ordId = rand.nextInt(10000000);
        String orderId = String.valueOf(ordId);

        purchaseOrder.setOrderId(ordId);
        FruitProducts.fruitProducts = itemRepository.getProducts();
        purchaseOrderRepository.insertPurchaseOrder(purchaseOrder);

        if (purchaseOrder.getLineItems().size() > 5) {
            throw new OrderException("cannot order more than 5 items");
        }

        itemRepository.addLineItems(purchaseOrder.getLineItems(), orderId);
    }
    

}
