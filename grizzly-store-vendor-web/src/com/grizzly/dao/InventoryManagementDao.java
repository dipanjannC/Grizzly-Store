package com.grizzly.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.grizzly.pojo.ProductDetailsPojo;

public class InventoryManagementDao {
	
	static Connection connect=null;
	static Statement statement=null;
	
	public static ArrayList<ProductDetailsPojo> fetchProducts(){
		
		connect=DBUtil.makeConnection();
		ResultSet resultset;
		
		ArrayList<ProductDetailsPojo> allProducts=new ArrayList<ProductDetailsPojo>();
		
		try {
			statement=connect.createStatement();
			resultset=statement.executeQuery("select * from product_details");
			
			while(resultset.next())
			{
				int productId=Integer.parseInt(resultset.getString(1));
				ProductDetailsPojo pojo=new ProductDetailsPojo();
				pojo.setProductId(productId);
				 
				String productName=resultset.getString(2);
				String productBrand=resultset.getString(3);
				String productCategory=resultset.getString(4);
				double productRating=Double.parseDouble(resultset.getString(5));
				
				
				pojo.setProductName(productName);
				pojo.setProductBrand(productBrand);
				pojo.setProductCategory(productCategory);
				pojo.setProductRating(productRating);
				
				allProducts.add(pojo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allProducts;
	}
	
	public static int delete(int productId)
	{
		
		System.out.println("Dao ID:"+productId);
		connect=DBUtil.makeConnection();
		int count=0;
		
		try {
			statement=connect.createStatement();
			count=statement.executeUpdate("delete from product_details where product_id='"+productId+"'");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return count;
	}

}
