package ibf2022.paf.day24workshop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ibf2022.paf.day24workshop.model.LineItem;
import ibf2022.paf.day24workshop.model.PurchaseOrder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(path = "/order")
public class CartController {

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @SuppressWarnings("unchecked")
    public String postChart(HttpServletRequest httpRequest, Model model, HttpSession session) {

        List<LineItem> lineItems = (List<LineItem>) session.getAttribute("cart");
        if (null == lineItems) {
            lineItems = new ArrayList<>();
            session.setAttribute("cart", lineItems);
        }

        String product = httpRequest.getParameter("item");
        Integer quantity = Integer.parseInt(httpRequest.getParameter("quantity"));
        lineItems.add(new LineItem(product, quantity));
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setLineItems(lineItems);
        session.setAttribute("checkoutCart", purchaseOrder);
        model.addAttribute("lineItems", lineItems);

        return "cart_template";
    }
    
}
