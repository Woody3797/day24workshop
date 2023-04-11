package ibf2022.paf.day24workshop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ibf2022.paf.day24workshop.exception.OrderException;
import ibf2022.paf.day24workshop.model.LineItem;
import ibf2022.paf.day24workshop.model.PurchaseOrder;
import ibf2022.paf.day24workshop.service.OrderService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(path = "/checkout")
public class CheckoutController {
    
    OrderService orderService;

    CheckoutController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public String postCheckout(HttpSession session, Model model, @RequestParam("name") String name, @RequestParam("ship_address") String ship_address, @RequestParam("notes") String notes) throws OrderException {
        List<LineItem> lineItems = (List<LineItem>) session.getAttribute("cart");
        PurchaseOrder purchaseOrder = (PurchaseOrder) session.getAttribute("checkoutCart");

        purchaseOrder.setName(name);
        purchaseOrder.setShip_address(ship_address);
        purchaseOrder.setNotes(notes);
        orderService.createOrder(purchaseOrder);

        model.addAttribute("total", lineItems.size());

        return "checkout";
    }

    @ExceptionHandler(OrderException.class)
    public String handleOrderException(OrderException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "error";
    }

}
