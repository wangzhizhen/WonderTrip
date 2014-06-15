package com.wondertrip.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Product implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8713598873614089837L;
	public static final int HOTEL  =  3;
	public static final int SIGHTSPOT  =  4;
	public static final int ROUTE  =  1;
	public static final int TRAFFIC  =  2;
	public static final int Combination = 5;
	private boolean isComplexProduct;
	private int id;
	private int classId;
	private String name;
	private String description;
	private float price;
	private int productType;
	private Date lastUpdateDate;
	private String pageUrl;
	private String imgUrl;
	private String source;
	private ArrayList<Product> listOfProducts;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getProductType() {
		return productType;
	}
	public void setProductType(int productType) {
		this.productType = productType;
	}
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	public String getPageUrl() {
		return pageUrl;
	}
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public boolean isComplexProduct() {
		return isComplexProduct;
	}
	public void setComplexProduct(boolean isComplexProduct) {
		this.isComplexProduct = isComplexProduct;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public void addProduct(Product product){
		if(listOfProducts == null){
			listOfProducts = new ArrayList<Product>();
		}
		listOfProducts.add(product);
	}
	public ArrayList<Product> getListOfProducts(){
		return listOfProducts;
	}
	
	
	public static void sortOrderedProduct(ArrayList<Product> orderedProducts) {
		// TODO Auto-generated method stub
		
	}
	public Product copy(){
		Product product = new Product();
		product.setComplexProduct(this.isComplexProduct());
		product.setId(this.getId());
		product.setClassId(this.getClassId());
		product.setName(this.getName());
		product.setDescription(this.getDescription());
		product.setPrice(this.getPrice());
		product.setProductType(this.productType);
		if(this.isComplexProduct()){
			for(Product p:this.getListOfProducts()){
				product.addProduct(p);
			}
		}
		return product;
	}
}