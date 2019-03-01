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
import com.grizzly.pojo.ProductDetailsPojo;

/**
 * Servlet implementation class FetchServlet
 */
@WebServlet("/FetchServlet")
public class FetchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<ProductDetailsPojo> productsView=InventoryManagementDao.fetchProducts();
		
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("Display.jsp");
		request.setAttribute("productsViewAdmin", productsView);
		requestDispatcher.forward(request, response);
	}

}
