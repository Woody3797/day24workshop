package ibf2022.paf.day24workshop.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.stereotype.Component;

import ibf2022.paf.day24workshop.model.FruitProducts;
import ibf2022.paf.day24workshop.repository.LineItemRepository;

@Component
public class OrderUtility {

    public static Object calculateUnitPrice(String product, Integer quantity) {
        List<FruitProducts> fruitProducts = FruitProducts.fruitProducts;
        BigDecimal standardPrice = new BigDecimal(0.0);
        BigDecimal discount = new BigDecimal(0.0);

        for (FruitProducts fruit : fruitProducts) {
            if (fruit.getName().equalsIgnoreCase(product)) {
                standardPrice = fruit.getStandardPrice();
                discount = fruit.getDiscount();
                LineItemRepository.discount = discount;
            }
        }

        BigDecimal totalPrice = BigDecimal.valueOf(quantity).multiply(standardPrice);
        BigDecimal totalPriceAfterDiscount;

        if (discount.compareTo(BigDecimal.ONE) == 0) {
            totalPriceAfterDiscount = totalPrice;
        } else {
            totalPriceAfterDiscount = totalPrice.multiply(BigDecimal.ONE.subtract(discount));
        }

        BigDecimal totalPriceAfterTax = totalPriceAfterDiscount.add(totalPriceAfterDiscount.multiply(BigDecimal.valueOf(0.05)));
        return totalPriceAfterTax.setScale(2, RoundingMode.DOWN);
    }
    
}
