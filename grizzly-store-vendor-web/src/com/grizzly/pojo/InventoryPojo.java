package com.grizzly.pojo;

public class InventoryPojo {

	private int productId;
	private int inventoryBuffer;
	private int inventoryStock;
	
	//getters and setters for productId 
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	//getters and setters for inventoryBuffer
	public int getInventoryBuffer() {
		return inventoryBuffer;
	}
	public void setInventoryBuffer(int inventoryBuffer) {
		this.inventoryBuffer = inventoryBuffer;
	}
	
	//getters and setters for inventory Stock 
	public int getInventoryStock() {
		return inventoryStock;
	}
	public void setInventoryStock(int inventoryStock) {
		this.inventoryStock = inventoryStock;
	}
}
