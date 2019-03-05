package com.grizzly.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.grizzly.dao.LoginDao;
import com.grizzly.pojo.LoginPojo;
import com.grizzly.validation.WebsiteException;

/**
 * Servlet implementation class Login
 */
@WebServlet("/LoginLogout")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		if (request.getParameter("logout-option") != null && request.getParameter("logout-option").equals("Yes")) {
			HttpSession session = request.getSession(false);
			session.invalidate();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("Login.jsp");
			requestDispatcher.forward(request, response);
		} else {

			String user = request.getParameter("username");
			String password = request.getParameter("password");

			LoginPojo pojo = new LoginPojo();
			pojo.setUsername(user);

			try {
				pojo = LoginDao.loginValidation(pojo);
				if (pojo.getRole() == null) {
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("Login.jsp");
					request.setAttribute("error", "Incorrect Username/Password.");
					requestDispatcher.forward(request, response);
				} else {
					

					if (pojo.getRole().equals("vendor")) {

						if (pojo.getStatus().equals("active")) {

							if (pojo.getPassword().equals(password)) {

								// Staring User-Session
								HttpSession session = request.getSession();
								session.setAttribute("username", user);
								session.setAttribute("role", pojo.getRole());

								// Requesting the Dispatcher
								RequestDispatcher requestDispatcher = request.getRequestDispatcher("FetchServlet");
								session.removeAttribute("attempts");
								requestDispatcher.forward(request, response);

							} else {
								// only if password doesn't match
								HttpSession session = request.getSession();
								String username = (String) session.getAttribute("username");

								if (request.getParameter("username").equals(username)) {

									String attempts = (String) session.getAttribute("attempts");

									if (attempts == null) {
										// sessionsetMaxInactiveInterval(20*60);
										session.setAttribute("username", user);
										session.setAttribute("attempts", "1");
										
										RequestDispatcher requestDispatcher = request.getRequestDispatcher("Login.jsp");
										request.setAttribute("error", "Invalid Password! Please try again vendor.");
										requestDispatcher.forward(request, response);

									} else if (attempts.equals("1")) {

										int attemptInteger = Integer.parseInt(attempts);

										// changing Integer attempts to String attempts
										session.setAttribute("attempts", (++attemptInteger) + "");

										RequestDispatcher requestDispatcher = request.getRequestDispatcher("Login.jsp");
										request.setAttribute("error", "Invalid Password! Please try again vendor.");
										requestDispatcher.forward(request, response);

									} else {

										session.invalidate();
										LoginDao.lockAccount(user);
										
										RequestDispatcher requestDispatcher = request.getRequestDispatcher("Login.jsp");
										request.setAttribute("error"," You have entered wrong password more than 3 times. Account is Locked vendor!!");
										requestDispatcher.forward(request, response);

									}

								} else {

									// sessionsetMaxInactiveInterval(20*60);
									session.setAttribute("username", user);
									session.setAttribute("attempts", "1");
									
									RequestDispatcher requestDispatcher = request.getRequestDispatcher("Login.jsp");
									request.setAttribute("error", "Invalid Password! Please try again. 1 vendor");
									requestDispatcher.forward(request, response);
								}

							}

						} else {

							RequestDispatcher requestDispatcher = request.getRequestDispatcher("Login.jsp");
							request.setAttribute("error", "Account Is Inactive vendor!!");
							requestDispatcher.forward(request, response);

						}

					} else if (pojo.getRole().equals("admin")) {

						if (pojo.getStatus().equals("active")) {

							if (pojo.getPassword().equals(password)) {

								// Staring User-Session
								HttpSession session = request.getSession();
								session.setAttribute("username", user);
								session.setAttribute("role", pojo.getRole());

								// Requesting the Dispatcher
								RequestDispatcher requestDispatcher = request.getRequestDispatcher("FetchServlet");
								session.removeAttribute("attempts");
								requestDispatcher.forward(request, response);

							} else {
								// only if password doesn't match
								HttpSession session = request.getSession();
								String username = (String) session.getAttribute("username");

								if (request.getParameter("username").equals(username)) {

									String attempts = (String) session.getAttribute("attempts");

									if (attempts == null) {
										// sessionsetMaxInactiveInterval(20*60);
										session.setAttribute("username", user);
										session.setAttribute("attempts", "1");
									
										RequestDispatcher requestDispatcher = request.getRequestDispatcher("Login.jsp");
										request.setAttribute("error", "Invalid Password! Please try again.");
										requestDispatcher.forward(request, response);

									} else if (attempts.equals("1")) {

										int attemptInteger = Integer.parseInt(attempts);

										// changing Integer attempts to String attempts
										session.setAttribute("attempts", (++attemptInteger) + "");

										RequestDispatcher requestDispatcher = request.getRequestDispatcher("Login.jsp");
										request.setAttribute("error", "Invalid Password! Please try again.");
										requestDispatcher.forward(request, response);

									} else {

										session.invalidate();
										LoginDao.lockAccount(user);
										RequestDispatcher requestDispatcher = request.getRequestDispatcher("Login.jsp");
										request.setAttribute("error"," You have entered wrong password more than 3 times. Account is Locked!!");
										requestDispatcher.forward(request, response);

									}

								} else {

									// sessionsetMaxInactiveInterval(20*60);

									session.setAttribute("username", user);
									session.setAttribute("attempts", "1");
									
									RequestDispatcher requestDispatcher = request.getRequestDispatcher("Login.jsp");
									request.setAttribute("error", "Invalid Password! Please try again. 1");
									requestDispatcher.forward(request, response);
								}

							}

						} else {

							RequestDispatcher requestDispatcher = request.getRequestDispatcher("Login.jsp");
							request.setAttribute("error", "Account Is Inactive!!");
							requestDispatcher.forward(request, response);

						}
					}
				}

			}

			catch (WebsiteException e) {
				// TODO Auto-generated catch block
				
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("ErrorPage.jsp");
				request.setAttribute("error", e.getMessage());
				requestDispatcher.forward(request, response);
			}

			// Logout Servlet

			/*
			 * String optionLogout=request.getParameter("logout-option");
			 * 
			 * 
			 * if(optionLogout.equals("Yes")) { HttpSession
			 * session=request.getSession(false); session.invalidate(); RequestDispatcher
			 * requestDispatcher=request.getRequestDispatcher("Login.jsp");
			 * requestDispatcher.forward(request,response); }
			 */
		}
	}
	}
