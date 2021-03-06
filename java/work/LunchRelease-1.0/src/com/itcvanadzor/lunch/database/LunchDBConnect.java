/**
 * @package com.itcvanadzor.lunch.database
 */

package com.itcvanadzor.lunch.database;

/**
 * ********* JAVA Project which working with Databases *************** ********
 * Version 1.0 ********************************************
 */

/**
 * Using standart libraries
 */

import java.sql.*;
import java.util.*;

/**
 * @brief Class to work with the database
 * @detailed Connects to the database, when creating
 */

public class LunchDBConnect {

    /**
     * @param st preparating to database request
     * @param rs saving2ssss request answer
     * @param connection creating connection to database
     */

    public Statement st = null;
    public ResultSet rs = null;
    public Connection connection = null;

    /**
     * @brief Constructor
     * @param url full path to database for connect
     * @param user username for database
     * @param password password for user in database
     * @throws java.sql.SQLException if an error occurred, when connected to a database
     *
     */

    public LunchDBConnect(String url, String user, String password) throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
    }

    /**
     * @brief Select from the database a list of places
     student@192.168.33.64's password: 
     * @return List of places
     * @throws java.sql.SQLException error if an error occurred working with database
     * @throws Exception error if the places list is empty
     */

    public ArrayList getPlaces() throws Exception, SQLException {
        ArrayList<Places> placesList = new ArrayList<Places>();
        st = connection.createStatement();
        if (st == null) {
            throw new SQLException();
        }
        rs = st.executeQuery("SELECT * FROM place");
        while (rs.next()) {
            Places place = new Places(rs.getInt("id"), rs.getString("place_name"));
            placesList.add(place);
        }
        return placesList;
    }

    /**
     * @detailed This method gets login id with username and password and
     * returns generated session id
     * @param email for logged in user
     * @param password for logged in user
     * @return session id for current user
     * @throws java.sql.SQLException error if an error occurred working with database
     * @throws java.sql.SQLException error, when wrong username or password
     */ 


    public int login(String email, String password) throws SQLException {
        st = connection.createStatement();
        if (st == null) {

            throw new SQLException();
        }
        st.executeUpdate("INSERT INTO session(login_id) VALUES ((SELECT id FROM login WHERE email='" + email + "' AND password='" + password + "'))", Statement.RETURN_GENERATED_KEYS);
        rs = st.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt("session_id");
        } else {
            throw new SQLException("Invalid username or password");
        }
    }

    /**
     * @detailed This method gets session id and returns order list for current
     * user
     * @param session_id generating, when gets login id
     * @return order list for current user
     * @throws java.sql.SQLException error if an error occurred working with database
     * @throws Exception error, if the orderList is empty
     */

    public ArrayList getOrderList(int session_id) throws Exception, SQLException {
        ArrayList<Order> orderList = new ArrayList<Order>();
        st = connection.createStatement();
        if (st == null) {
            throw new SQLException();
        }
        rs = st.executeQuery("SELECT Orders.id,products_id,products_name,place_id,place_name,Orders.count, Orders.login_id,Orders.date,status FROM productsByPlaces,Orders,products, place WHERE unique_product_id=productsByPlaces.id AND place_id=place.id AND products_id=products.id AND Orders.date=CURRENT_DATE AND Orders.login_id=(SELECT login_id FROM session WHERE session_id=" + session_id + ")");

        while (rs.next()) {
            Order order = new Order(rs.getInt("id"), rs.getString("place_name"), rs.getInt("place_Id"), rs.getString("products_name"), rs.getInt("products_id"), rs.getInt("count"), rs.getString("date"), rs.getString("status"));
            orderList.add(order);
        }
        return orderList;
    }

    /**
     * @detailed This method gets session id and order id and removes order for
     * current user
     * @param session_id generating, when gets login id
     * @param order_id unique value of order
     * @return true, when successfully removed order 
     * @throws java.sql.SQLException error if an error occurred working with database
     * @throws Exception error if an invalid order id and order not removed
     */

    public boolean deleteOrder(int session_id, int order_id) throws Exception, SQLException {
        st = connection.createStatement();
        if (st == null) {
            throw new SQLException();
        }
        int deleteRowsCount = st.executeUpdate("DELETE FROM Orders WHERE Orders.login_id=(SELECT login_id FROM session WHERE session_id=" + session_id + ") AND Orders.id=" + order_id);
        if (deleteRowsCount != 0) {
            return true;
        } else {
            throw new Exception("Invalid order id");
        }
    }

    /**
     * @detailed This method gets place id and a few letters for product name
     * and returns product name list, which starting with that letters
     * @param place_id unique values of places
     * @param word part of letters for product name
     * @return products list,which starting whith that letters
     * @throws java.sql.SQLException error if an error occurred working with database
     * @throws Exception error if an products list is empty
     */

    public ArrayList getProducts(int place_id, String word) throws Exception, SQLException {
        ArrayList<Products> productsList = new ArrayList<Products>();
        st = connection.createStatement();
        if (st == null) {
            throw new SQLException();
        }
        rs = st.executeQuery("SELECT products_id, products_name FROM products,productsByPlaces WHERE productsByPlaces.place_id=" + place_id + " AND products_id=products.id AND products_name Like '" + word.toLowerCase() + "%'");
        while (rs.next()) {
            Products products = new Products(rs.getString("products_name"), rs.getInt("products_id"));
            productsList.add(products);
        }
        return productsList;
    }

    /**
     * @detailed This method gets session id , place id , products id and
     * product count, and inserts its to order list and return order id
     * @param session_id unique value for current user
     * @param place_id unique values for places
     * @param products_id unique values for products
     * @param count count of products
     * @return order id unique value for current order
     * @throws java.sql.SQLException error if an error occurred working with database
     * @throws Exception error if this place doesn't have this product
     */

    public int  addOrder(int session_id, int place_id, int products_id, int count) throws Exception, SQLException {
        st = connection.createStatement();
        if (st == null) {
            throw new SQLException();
        }
        st.executeQuery("SELECT id,login_id FROM delivery WHERE place_id="+place_id);
        st.executeUpdate("INSERT INTO Orders(login_id,unique_product_id,count,date,status) VALUES ((SELECT login_id FROM session WHERE session_id=" + session_id + "),(SELECT id FROM productsByPlaces WHERE place_id=" + place_id + " AND products_id=" + products_id + ")," + count + ",CURRENT_DATE,'yes')", Statement.RETURN_GENERATED_KEYS);
        rs = st.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt("id");
        } else {
            throw new Exception("This place doesn't have this product");
        }
    }

    /**
     * @brief This method returns distributors list
     * @return distributors list for current day
     * @throws java.sql.SQLException error if an error occurred working with database
     * @throws Exception error if an distributors list is empty
     */

    public ArrayList getDistributors() throws Exception, SQLException {
        ArrayList<Distributors> distributorsList = new ArrayList<Distributors>();
        st = connection.createStatement();
        if (st == null) {
            throw new SQLException();
        }
        rs = st.executeQuery("SELECT * FROM delivery LEFT JOIN login ON login_id=login.id LEFT JOIN place ON place_id=place.id");
        while (rs.next()) {
            Distributors distributor = new Distributors(rs.getString("username"), rs.getInt("login_id"), rs.getString("place_name"), rs.getInt("place_id"));
            distributorsList.add(distributor);
        }
        return distributorsList;
    }

    /**
     * @detailed This method gets session id, place id and inserts into delivery
     * list current username
     * @param session_id unique value for current user
     * @param place_id unique values for places
     * @return true, when successfully inserted order
     * @throws java.sql.SQLException error if an error occurred working with database
     * @throws Exception error if an insert into delivery failed
     * @throws Exception error if an there are people going to this place
     */

    public boolean becomeDistributors(int session_id, int place_id) throws Exception, SQLException {
        st = connection.createStatement();
        if (st == null) {
            throw new SQLException();
        }
        //rs = st.executeQuery("SELECT id FROM delivery WHERE date=CURRENT_DATE AND place_id=" + place_id);
        //if (!rs.next()) {
        int insertRowCount = st.executeUpdate("UPDATE delivery SET login_id=(SELECT login_id FROM session WHERE session_id=" + session_id + "), date=CURRENT_DATE WHERE place_id="+place_id);
        if (insertRowCount != 0) {
            return true;
        } else {
            throw new Exception("//Insert into delivery failed");
        }
        // } else {
        //      throw new Exception("There are people going to this place");
        //      }
    }

    /**
     * @detailed This method gets place id and returns overall count of orders
     * by product name and product id
     * @param place_id unique values for places
     * @return products list from the selected place
     * @throws java.sql.SQLException error if an error occurred working with database
     * @throws Exception error if an products list is empty
     */

    public ArrayList getProducts(int place_id) throws Exception, SQLException {
        ArrayList<Products> productsList = new ArrayList<Products>();
        st = connection.createStatement();
        if (st == null) {
            throw new SQLException();
        }
        rs = st.executeQuery("SELECT products_name,products_id,SUM(count) FROM products,Orders,productsByPlaces WHERE Orders.date=CURRENT_DATE AND productsByPlaces.products_id=products.id AND Orders.unique_product_id=productsByPlaces.id AND productsByPlaces.place_id=" + place_id + " GROUP BY products_name,products.id,productsByPlaces.id");
        while (rs.next()) {
            Products products = new Products(rs.getString("products_name"), rs.getInt("products_id"), rs.getInt("sum"));
            productsList.add(products);
        }
        return productsList;
    }

    /**
     * @detailed This method gets place id and login id and returns orders for
     * the current user at current day
     * @param place_id unique values of places
     * @param login_id unique value for current user
     * @return orders by places list
     * @throws java.sql.SQLException error if an error occurred working with database
     * @throws Exception error if an orders by places list is empty
     */

    public ArrayList getOrders(int place_id, int login_id) throws Exception, SQLException {
        ArrayList<OrdersByCostumer> ordersByCostumerList = new ArrayList<OrdersByCostumer>();
        st = connection.createStatement();
        if (st == null) {
            throw new SQLException();
        }
        rs = st.executeQuery("SELECT products_name,sum(count) FROM products,Orders,productsByPlaces,login WHERE Orders.date=CURRENT_DATE AND login.id=Orders.login_id AND Orders.login_id=" + login_id + " AND productsByPlaces.products_id=products.id AND Orders.unique_product_id=productsByPlaces.id AND productsByPlaces.place_id=" + place_id + " GROUP BY products_name,products.id,login.id");
        while (rs.next()) {
            OrdersByCostumer orderByCostumer = new OrdersByCostumer(rs.getString("products_name"), rs.getInt("sum"));
            ordersByCostumerList.add(orderByCostumer);
        }
        return ordersByCostumerList;
    }

    /**
     * @detailed This method gets place id and returns costumers list,
     * who have order from the selected place
     * by product name and product id for all users at current day
     * @param place_id unique values of places
     * @return costumers list,who have order from the selected place
     * @throws java.sql.SQLException error if an error occurred working with database
     * @throws Exception error if an orders by places list is empty
     */

    public ArrayList getCustomers(int place_id) throws Exception {
        ArrayList<CustomersByPlace> costumersByPlaceList = new ArrayList<CustomersByPlace>();
        st = connection.createStatement();
        if (st == null) {
            throw new SQLException();
        }
        rs = st.executeQuery("SELECT Orders.login_id,login.username FROM Orders,login,productsByPlaces WHERE unique_product_id=productsByPlaces.id AND productsByPlaces.place_id=" + place_id + " AND date=CURRENT_DATE AND Orders.login_id=login.id GROUP BY login_id,login.id");
        while (rs.next()) {
            CustomersByPlace costumersByPlace = new CustomersByPlace(rs.getInt("login_id"), rs.getString("username"));
            costumersByPlaceList.add(costumersByPlace);
        }
        return costumersByPlaceList;
    }

    /**
     * @detailed This method gets session id and removes it from database (log
     * outing)
     * @param session_id unique value for current user
     * @return true, when session removed in database
     * @throws java.sql.SQLException error if an error occurred working with database
     * @throws Exception error if session id not found and delete failed
     */

    public boolean logout(int session_id) throws Exception, SQLException {
        st = connection.createStatement();
        if (st == null) {
            throw new SQLException();
        }
        int deleteRowsCount = st.executeUpdate("DELETE FROM session WHERE session_id=" + session_id);
        if (deleteRowsCount != 0) {
            return true;
        } else {
            throw new Exception("Session id not found.Delete failed");
        }
    }

    /**
     * @detailed This method checks whether there is a sesssion id in the table 
     * @param session_id unique value for current user
     * @return true, if there session id
     * @throws java.sql.SQLException error if an error occurred working with database
     * @throws Exception error if session id not found 
     */
    public boolean isSessionId(int session_id) throws Exception, SQLException {
        st = connection.createStatement();
        if (st == null) {
            throw new SQLException();
        }
        rs = st.executeQuery("SELECT session_id FROM session WHERE session_id=" + session_id);
        if (rs.next()) {
            return true;
        } else {
            return false;
        }
    }

    public void filter() throws SQLException  {
        st = connection.createStatement();
        if (st == null) {
            throw new SQLException();
        }
        int updateRowsCount = st.executeUpdate("Update delivery SET login_id=null, date=null");
    }
}
