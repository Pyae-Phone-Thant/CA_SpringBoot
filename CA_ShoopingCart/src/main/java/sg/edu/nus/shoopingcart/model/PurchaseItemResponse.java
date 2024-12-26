package sg.edu.nus.shoopingcart.model;


//Author; Siow Xiang Ying
public class PurchaseItemResponse {
    private Long itemId;
    private String name;
    private String imageUrl;
    private String status;
    private String price;
    private int quantity;

    public PurchaseItemResponse(Long itemId, String name, String imageUrl, String status, String price, int quantity) {
        this.itemId = itemId;
        this.name = name;
        this.imageUrl = imageUrl;
        this.status = status;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters and Setters
    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}