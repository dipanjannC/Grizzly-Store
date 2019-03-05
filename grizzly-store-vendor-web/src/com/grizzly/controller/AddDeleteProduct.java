package com.grizzly.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.grizzly.dao.InventoryManagementDao;
import com.grizzly.pojo.InventoryDetailsPojo;
import com.grizzly.pojo.InventoryPojo;
import com.grizzly.pojo.ProductDetailsPojo;
import com.grizzly.validation.WebsiteException;

/**
 * Servlet implementation class AddDeleteProduct
 */
@WebServlet("/AddDeleteProduct")
public class AddDeleteProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		if (request.getParameter("add") != null) {

			// Extracting the Parameters from the Display Page
			String productName = request.getParameter("newProductName");
			String productBrand = request.getParameter("newProductBrand");
			String productCategory = request.getParameter("newProductCategory");
			double productRating = Double.parseDouble(request.getParameter("newProductRating"));
			boolean resultRating=false;
			
			//INPUT VALIDATION
			String error="Please enter valid rating";
			if(resultRating==false)
			{
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("AdminDisplay.jsp");
				request.setAttribute("error", error);
				requestDispatcher.forward(request, response);
			}
			
			String productDescription = request.getParameter("newProductDescription");
			int productBuffer = Integer.parseInt(request.getParameter("newProductBuffer"));
			double productPrice = Double.parseDouble(request.getParameter("newProductPrice"));

			// Setting it to the Product Details Pojo
			ProductDetailsPojo pojo = new ProductDetailsPojo();
			pojo.setProductName(productName);
			pojo.setProductBrand(productBrand);
			pojo.setProductCategory(productCategory);
			pojo.setProductRating(productRating);
			pojo.setProductDescription(productDescription);
			pojo.setProductBuffer(productBuffer);
			pojo.setProductPrice(productPrice);

			try {
				InventoryManagementDao.addProduct(pojo);
			} 
			catch (WebsiteException e) {
				// TODO Auto-generated catch block
				
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("ErrorPage.jsp");
				request.setAttribute("error", e.getMessage());
				requestDispatcher.forward(request, response);
			}

			ArrayList<ProductDetailsPojo> adminView;
			
			try {
				adminView = InventoryManagementDao.fetchProducts();
				
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("AdminDisplay.jsp");
				request.setAttribute("productsViewAdmin", adminView);
				requestDispatcher.forward(request, response);
			} 
			catch (WebsiteException e) {
				// TODO Auto-generated catch block
				
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("ErrorPage.jsp");
				request.setAttribute("error", e.getMessage());
				requestDispatcher.forward(request, response);
			}

			
		} else if (request.getParameter("delete") != null) {
			
			// Delete Servlet Functionality
			String id = request.getParameter("productId");
			int productId = Integer.parseInt(id);

			try {
				InventoryManagementDao.delete(productId);
			} 
			catch (WebsiteException e) {
				// TODO Auto-generated catch block
				
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("ErrorPage.jsp");
				request.setAttribute("error", e.getMessage());
				requestDispatcher.forward(request, response);
			}

			ArrayList<ProductDetailsPojo> adminView;
			
			try {
				adminView = InventoryManagementDao.fetchProducts();
				
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("AdminDisplay.jsp");
				request.setAttribute("productsViewAdmin", adminView);
				requestDispatcher.forward(request, response);
			} 
			catch (WebsiteException e) {
				// TODO Auto-generated catch block
				
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("ErrorPage.jsp");
				request.setAttribute("error", e.getMessage());
				requestDispatcher.forward(request, response);
			}

			

		} else if (request.getParameter("update") != null){
			
			int productId = Integer.parseInt(request.getParameter("inventoryProductId"));
			int inventoryStock = Integer.parseInt(request.getParameter("inventoryStock"));
			int inventoryBuffer = Integer.parseInt(request.getParameter("inventoryBuffer"));

			InventoryPojo pojo = new InventoryPojo();
			
			pojo.setProductId(productId);
			pojo.setInventoryStock(inventoryStock);
			pojo.setInventoryBuffer(inventoryBuffer);

			try {
				InventoryManagementDao.addBufferStock(pojo);
			} 
			catch (WebsiteException e) {
				// TODO Auto-generated catch block
				
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("ErrorPage.jsp");
				request.setAttribute("error", e.getMessage());
				requestDispatcher.forward(request, response);
			}

			ArrayList<InventoryDetailsPojo> vendorView;
			try {
				vendorView = InventoryManagementDao.fetchInvetory();
				
				// Redirecting through Request Dispatcher
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("VendorDisplay.jsp");
				request.setAttribute("inventoryView", vendorView);
				requestDispatcher.forward(request, response);
			} 
			catch (WebsiteException e) {
				// TODO Auto-generated catch block
				
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("ErrorPage.jsp");
				request.setAttribute("error", e.getMessage());
				requestDispatcher.forward(request, response);
			}

			

		}

	}

}
