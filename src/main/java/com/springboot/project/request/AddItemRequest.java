package com.springboot.project.request;


public class AddItemRequest {

    private Long productId;
    private String size;
    private int quantity;
    private Integer price;
    public Long getProductId() {
        return productId;
    }
    public AddItemRequest() {
    }
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }
   

    
    
}
