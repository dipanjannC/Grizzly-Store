package com.grizzly.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.grizzly.dao.InventoryManagementDao;
import com.grizzly.pojo.InventoryDetailsPojo;
import com.grizzly.pojo.InventoryPojo;
import com.grizzly.pojo.ProductDetailsPojo;
import com.grizzly.validation.WebsiteException;

/**
 * Servlet implementation class FetchServlet
 */
@WebServlet("/FetchServlet")
public class FetchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static Logger logger=Logger.getLogger("grizzly-store-vendor-web");  

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req,resp);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// Fetching the user role from the database, for redirecting accordingly
		HttpSession session = request.getSession();
		String role=(String)session.getAttribute("role");
		
		
		// If the user is ADMIN 
		if(role.equals("admin"))
		{

			ArrayList<ProductDetailsPojo> adminView;
			
			try {
				adminView = InventoryManagementDao.fetchProducts();
				
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("AdminDisplay.jsp");
				request.setAttribute("productsViewAdmin", adminView);
				requestDispatcher.forward(request, response);
			
			} catch (WebsiteException e) {
				// TODO Auto-generated catch block
				
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("ErrorPage.jsp");
				request.setAttribute("error", e.getMessage());
				requestDispatcher.forward(request, response);
							
			}

		}
		// If the user is VENDOR
		else if(role.equals("vendor"))
		{
			if (request.getParameter("manage") != null) {
				
				int inventoryProductId = Integer.parseInt(request.getParameter("inventoryProductId"));
				ArrayList<InventoryPojo> stockUpdateView;
				
				try {
					stockUpdateView = InventoryManagementDao.fetchInvetoryItems(inventoryProductId);
					
					// Redirecting through Request Dispatcher to ManageStock page
					
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("ManageStock.jsp");
					request.setAttribute("inventoryList", stockUpdateView);
					requestDispatcher.forward(request, response);
				}
				catch (WebsiteException e) {
					
					// TODO Auto-generated catch block
					
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("ErrorPage.jsp");
					request.setAttribute("error", e.getMessage());
					requestDispatcher.forward(request, response);
				}

			} else {
				
				// Fetching the ArrayList from DAO layer
				ArrayList<InventoryDetailsPojo> vendorView;
				
				try {
					vendorView = InventoryManagementDao.fetchInvetory();
				
					// Redirecting through Request Dispatcher to Vendor Display
					
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

}
