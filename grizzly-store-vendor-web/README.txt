

Grizzly -Store(ADMIN & VENDOR) WEB Project


This porject is based upon the basic concepts of Core & advanced(Servlet and JDBC) Java for the Backend,and 
HTML,CSS, BOOTSTRAP,JSP for the Front End.
Following the MODEL VIEW CONTROLLER(MVC) Architechture

Its a responsive Website,it would redirect to differnt webpages according to the User i.e ADMIN or VENDOR
Also, it will lock the account permanentely if there are 3 failed attempts on the go.


**************************************************************************************************************************************

DATABASE- MYSQL
**************************************************************************************************************************************

Database - grizzly_storeDB;

Tables - 
1. user_details - storing the login credentials for all the users , with columns 
	user_name-varchar (as primary key)
	user_password-varchar
	user_role-(as admin or vendor) 
	user_status-varchar (as active or inactive as the account will be inactive after 3 falied attempts of login).
	
Login Credentials-

ADMIN
	1. UserName- dipanjan
   	   Password- paradox

	2. UserName- conan
           Password- sherlocked
 
	3. UserName- paulo
   	   Password- alchemist

VENDOR
	1. UserName- google
	   Password- firebase

	2. UserName- facebook
           Password- cassandra

	3. UserName- amazon
           Password- dynamo


	 	    
2.product_details - storing the data related to products 
	product_id-int (as primary key)
	product_name-varchar 
	product_brand-varchar
	product_category-varchar
	product_description-varchar
	product_rating-double 
	product_price-double 

3.inventory - storing the additional data related to the product  accessable only by the vendor
	product_id-int(as foreign key to the  product_id of product_details table)
	inventory_buffer-int (would be updated only by vendor)
	inventory_stock-int(would be updated only by admin)

*************************************************************************************************************************************

DEVELOPMENT- JAVA & (HTML,CSS,BOOTSTRAP,JSP)
*************************************************************************************************************************************

1.FRONT END 

	-Login.JSP
		This page is taking the user credentials as input and redicting to the #FetchServlet. 
	
	-AdminDisplay.JSP
		This page is displaying view of ADMINS Access with features of adding a new product which 
		would be stored in the PRODUCT_DETAILS table in the DB.

	-VendorDisplay.JSP
		This page is displaying view of VENDORS Access with features of adding a product which 
		would be stored in the INVENTORY table in the DB.

	-ManageStock.JSP
		This page is displaying the content of the product listed in the PRODUCT_DETAILS table in the form
		where the VENDOR can update INVENTORY_STOCK

*************************************************************************************************************************************

2. BACKEND

   There are four packages
	i)com.grizzly.controller- Having all the JAVA Servlet Classes
		-LoginServlet : Fetchs the input from the JSP pages and call the DAO Layer functions.
				FUNCTIONALITY : Validates the login credentials and redirects accordingly i.e ADMIN or VENDOR.
 
		-FetchServlet : Fetchs the input from the LoginDAO and call the DAO Layer functions.
				FUNCTIONALITY : Fetches the Details from the Dao Layer as ArrayLists and redirects to the JSP Pages .

		-AddDeleteServlet : Fetchs the input from the JSP pages and call the DAO Layer functions.
				FUNCTIONALITY : For Adding and Deleteing a Product 
 
	ii)com.grizzly.dao- Having all the JAVA Data Access Object(DAO) Classes 
		-DBUtil : Used for making the Database connection.	
		-LoginDao : Having the Fuctionalties to LOCK and FETCH the User Credentials from the DATABASE.
		-InventoryManagementDao : Having all the fuctions related to DATABASE.
	
	iii)com.grizzly.pojo - Having all the Plain Old JAVA Objects(POJO) Classes
		-LoginPojo : Login Data values taken as a input from Login.JSP page to check accordingly
		-ProductDetailsPojo : Product Data values to be played in the AdminDisplay.JSP page i.e when ADMIN accesess.
		-InventoryDetailsPojo : Inventory Data values to be played in the VendorDisplay.JSP page i.e when VENDOR accesess.
		-InventoryPojo : Inventory Data values to be displayed while adding Stock in the ManageStock.JSP page. 
	
	iv)com.grizzly.validation- Having the basic JAVA Classes for Input Validations
		-InputValidation.java :	Checking if the rating inserted while adding product is a Integer or Decimal(upto 1 Decimal place).
	

*******************************************************************************************************************************************

Advancements to Make

-User Defined Exceptions
-Implementing Log4j
 
*******************************************************************************************************************************************
                                                             THANK YOU