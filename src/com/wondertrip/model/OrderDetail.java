package com.wondertrip.model;

public class OrderDetail {
	private int id;
	private int orderId;
	private int productId;
	private String productName;
	private int productCount;
	private String productPrice;
	private String totalProductPrice;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getProductCount() {
		return productCount;
	}
	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}
	public String getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
		double price = Double.parseDouble(productPrice);
		price*=productCount;
		String priceS = (price+"");
		int index = priceS.indexOf(".");
		int length = priceS.length();
		if(index!=-1){
			if(index<=length-2){
				this.totalProductPrice = priceS.substring(0,index+2);
			}
			else{
				for(int i=0;i<length-index-1;i++){
					priceS+="0";
				}
			}
			this.totalProductPrice = priceS;
		}
		else{
			this.totalProductPrice = price+".00";
		}
	}
	public String getTotalProductPrice() {
		return totalProductPrice;
	}
	
}
