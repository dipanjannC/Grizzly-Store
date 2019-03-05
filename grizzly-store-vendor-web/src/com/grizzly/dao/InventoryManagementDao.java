package com.grizzly.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.grizzly.pojo.InventoryDetailsPojo;
import com.grizzly.pojo.InventoryPojo;
import com.grizzly.pojo.ProductDetailsPojo;
import com.grizzly.validation.WebsiteException;

public class InventoryManagementDao {

	static Connection connect = null;
	static Statement statement = null;

	public static ArrayList<ProductDetailsPojo> fetchProducts() throws WebsiteException {

		connect = DBUtil.makeConnection();
		ResultSet resultset;

		ArrayList<ProductDetailsPojo> allProducts = new ArrayList<ProductDetailsPojo>();

		try {
			statement = connect.createStatement();
			resultset = statement.executeQuery("select * from product_details");

			while (resultset.next()) {
				int productId = Integer.parseInt(resultset.getString(1));
				ProductDetailsPojo pojo = new ProductDetailsPojo();
				pojo.setProductId(productId);

				String productName = resultset.getString(2);
				String productBrand = resultset.getString(3);
				String productCategory = resultset.getString(4);
			//	String productDescription = resultset.getString(5);
				double productRating = Double.parseDouble(resultset.getString(6));
			//	String productPrice = resultset.getString(7);
				
				pojo.setProductName(productName);
				pojo.setProductBrand(productBrand);
				pojo.setProductCategory(productCategory);
				pojo.setProductRating(productRating);

				allProducts.add(pojo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new WebsiteException("Falied to fetch Products.");
		}
		return allProducts;
	}

	public static int delete(int productId) throws WebsiteException {

		System.out.println("Dao ID:" + productId);
		connect = DBUtil.makeConnection();
		int deleteFromProductTable = 0;
		int deleteFromInventoryTable = 0;

		try {
			statement = connect.createStatement();

			// delete only when deleted from both the tables
			connect.setAutoCommit(false);
			deleteFromProductTable = statement.executeUpdate("delete from product_details where product_id='" + productId + "'");
			connect.commit();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new WebsiteException("Falied to delete Product.");
		}

		return deleteFromProductTable + deleteFromInventoryTable;
	}

	public static int addProduct(ProductDetailsPojo pojo) throws WebsiteException {
		connect = DBUtil.makeConnection();
		int productTableInsert = 0;
		int inventoryTableInsert = 0;

		try {
			// creating Query statement
			statement = connect.createStatement();
			String productTableQuery = "insert into product_details(product_name," 
					+ "product_brand,"
					+ "product_category," 
					+ "product_rating," 
					+ "product_description," 
					+ "product_price) values ('"
					+ pojo.getProductName() + "','" 
					+ pojo.getProductBrand() + "','" 
					+ pojo.getProductCategory() + "','"
					+ pojo.getProductRating() + "','" 
					+ pojo.getProductDescription() + "','" 
					+ pojo.getProductPrice()
					+ "')"; // numerical with quotes

			// only update is added in both the Tables i.e Product and Inventory
			connect.setAutoCommit(false);
			// Inserting Details into Product table
			productTableInsert = statement.executeUpdate(productTableQuery, Statement.RETURN_GENERATED_KEYS);
			ResultSet resultKey = statement.getGeneratedKeys();
			int key = 0;
			if (resultKey.next()) {
				key = resultKey.getInt(1);
			}
			// Inserting Details into Inventory table
			String inventoryTableQuery = "insert into inventory(product_id,inventory_buffer) values(" + key + ","
					+ pojo.getProductBuffer() + ")";// numerical with quotes

			inventoryTableInsert = statement.executeUpdate(inventoryTableQuery);
			connect.commit();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new WebsiteException("Failed to add Product.");
		}

		return productTableInsert + inventoryTableInsert;
	}

	
	public static int addBufferStock(InventoryPojo pojo) throws WebsiteException {
		connect = DBUtil.makeConnection();
		int inventoryTableInsert = 0;

		try {
			statement = connect.createStatement();

			String inventoryTableQuery = "insert into inventory(inventory_buffer," 
										+ "inventory_stock) values("
										+ pojo.getInventoryBuffer() + ","
										+ pojo.getInventoryStock() + ")";

			inventoryTableInsert = statement.executeUpdate(inventoryTableQuery);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new WebsiteException("Falied to addBufferStock.");
		}

		return inventoryTableInsert;
	}

	public static ArrayList<InventoryDetailsPojo> fetchInvetory() throws WebsiteException {

		// Initialization and making up connection through DBUtil
		connect = DBUtil.makeConnection();
		ResultSet resultset;

		ArrayList<InventoryDetailsPojo> allInventoryItems = new ArrayList<InventoryDetailsPojo>();

		try {
			statement = connect.createStatement();

			// creating a query to fetch the inventory Items

			String query = "select product_details.product_id," 
							+ "product_details.product_name,"
							+ "product_details.product_brand," 
							+ "product_details.product_category,"
							+ "product_details.product_rating," 
							+ "product_details.product_price,"
							+ "inventory.inventory_stock," 
							+ "inventory.inventory_buffer from inventory "
							+ "join product_details on product_details.product_id=inventory.product_id";

			// executing the query .
			resultset = statement.executeQuery(query);

			while (resultset.next()) {
				InventoryDetailsPojo pojo = new InventoryDetailsPojo();

				// extracting details from the records from the database

				int productId = Integer.parseInt(resultset.getString(1));
				String productName = resultset.getString(2);
				String productBrand = resultset.getString(3);
				String productCategory = resultset.getString(4);
				double productRating = Double.parseDouble(resultset.getString(5));
				double productPrice = Double.parseDouble(resultset.getString(6));
				int inventoryBuffer = Integer.parseInt(resultset.getString(7));
				int inventoryStock = Integer.parseInt(resultset.getString(8));

				// adding details to the Pojo object
				pojo.setProductId(productId);
				pojo.setProductName(productName);
				pojo.setProductBrand(productBrand);
				pojo.setProductCategory(productCategory);
				pojo.setProductRating(productRating);
				pojo.setProductPrice(productPrice);
				pojo.setInventoryBuffer(inventoryBuffer);
				pojo.setInventoryStock(inventoryStock);

				allInventoryItems.add(pojo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new WebsiteException("Fetch From the inventory falied.");
		}
		return allInventoryItems;
	}

	public static ArrayList<InventoryPojo> fetchInvetoryItems(int inventoryProductId) throws WebsiteException {

		// Initialization and making up connection through DBUtil
		connect = DBUtil.makeConnection();
		ResultSet resultset;

		ArrayList<InventoryPojo> allInventoryItems = new ArrayList<InventoryPojo>();

		try {
			statement = connect.createStatement();

			// creating a query to fetch the inventory Items

			String query = "select * from inventory where product_id="+inventoryProductId;
			// executing the query .
			resultset = statement.executeQuery(query);

			while (resultset.next()) {
				InventoryPojo pojo = new InventoryPojo();

				// extracting details from the records from the database

				int productId = Integer.parseInt(resultset.getString(1));
				int inventoryStock = Integer.parseInt(resultset.getString(2));
				int inventoryBuffer = Integer.parseInt(resultset.getString(3));

				// adding details to the Pojo object
				pojo.setProductId(productId);
				pojo.setInventoryStock(inventoryStock);
				pojo.setInventoryBuffer(inventoryBuffer);

				allInventoryItems.add(pojo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new WebsiteException("Falied to fetch Inventory Items.");
		}
		return allInventoryItems;
	}

}
