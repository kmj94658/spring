package com.kh.productapp.product.dto;

public class ProductDTO {
	private String id; //상품아이디
	private String name; //상품명
	private String stock; //상품수량
	private String price; //상품가격
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "ProductDTO [id=" + id + ", name=" + name + ", stock=" + stock + ", price=" + price + "]";
	}
	
	
}
