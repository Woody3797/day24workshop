package ibf2022.paf.day24workshop.model;

public class LineItem {
    
    private Integer itemId;
    private Integer quantity;
    private String product;
    
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public String getProduct() {
        return product;
    }
    public void setProduct(String product) {
        this.product = product;
    }
    public Integer getItemId() {
        return itemId;
    }
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public LineItem() {
    }

    public LineItem(Integer itemId, Integer quantity, String product) {
        this.itemId = itemId;
        this.quantity = quantity;
        this.product = product;
    }

    public LineItem(String product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }



    @Override
    public String toString() {
        return "LineItem [itemId=" + itemId + ", quantity=" + quantity + ", product=" + product + "]";
    }

    
    
}
