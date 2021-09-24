package com.exp.Ass05Thymeleaf.data_access;

import com.exp.Ass05Thymeleaf.logging.SqlToConsole;
import com.exp.Ass05Thymeleaf.models.Customer;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@Repository
public class CustomerRepoImpl implements CustomerRepo {

    private final SqlToConsole logger;

    // Setting up the connection object we need.
    private String URL = ConnectionHelper.CONNECTION_URL;
    private Connection conn = null;

    public CustomerRepoImpl(SqlToConsole logger) {
        this.logger = logger;
    }


    public ArrayList<Customer> getAllCustomers(){
        ArrayList<Customer> customers = new ArrayList<>();
        try{
            // Connect to DB
            conn = DriverManager.getConnection(URL);
            logger.log("Connection established.");

            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT Id,FirstName, LastName, Country, PostalCode, Phone, EMail FROM customer");
            // Execute Query
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                customers.add(
                        new Customer(
                                resultSet.getString("Id"),
                                resultSet.getString("FirstName"),
                                resultSet.getString("LastName"),
                                resultSet.getString("Country"),
                                resultSet.getString("PostalCode"),
                                resultSet.getString("Phone"),
                                resultSet.getString("EMail")
                        ));
            }
            logger.log("Select successful");
        }
        catch (Exception exception){
            logger.log(exception.toString());
        }
        finally {
            try {
                conn.close();
            }
            catch (Exception exception){
                logger.log(exception.toString());
            }
        }
        return customers;
    }

    public Customer getCustomerById(String custId){
        Customer customer = null;
        try{
            // Connect to DB
            conn = DriverManager.getConnection(URL);
            logger.log("Connection established.");

            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT Id,FirstName, LastName, Country, PostalCode, Phone, EMail FROM customer WHERE Id = ?");
            preparedStatement.setString(1,custId);
            // Execute Query
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                customer = new Customer(
                        resultSet.getString("Id"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Country"),
                        resultSet.getString("PostalCode"),
                        resultSet.getString("Phone"),
                        resultSet.getString("EMail")
                );
            }
            logger.log("Select successful");
        }
        catch (Exception exception){
            logger.log(exception.toString());
        }
        finally {
            try {
                conn.close();
            }
            catch (Exception exception){
                logger.log(exception.toString());
            }
        }
        return customer;
    }

    public Boolean addCustomer(Customer customer){
        Boolean success = false;
        try{
            // Connect to DB
            conn = DriverManager.getConnection(URL);
            logger.log("Connection established.");

            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("INSERT INTO customer(Id, FirstName, LastName, Country, PostalCode, Phone, EMail) VALUES(?,?,?,?,?,?,?)");
            preparedStatement.setString(1, customer.getCustomerId());
            preparedStatement.setString(2, customer.getFirstName());
            preparedStatement.setString(3, customer.getLastName());
            preparedStatement.setString(4, customer.getCountry());
            preparedStatement.setString(5, customer.getPostalCode());
            preparedStatement.setString(6, customer.getPhone());
            preparedStatement.setString(7, customer.getEmail());
            // Execute Query
            int result = preparedStatement.executeUpdate();
            success = (result != 0);
            logger.log("Add successful");
        }
        catch (Exception exception){
            logger.log(exception.toString());
        }
        finally {
            try {
                conn.close();
            }
            catch (Exception exception){
                logger.log(exception.toString());
            }
        }
        return success;
    }

    public Boolean updateCustomer(Customer customer){
        Boolean success = false;
        try{
            // Connect to DB
            conn = DriverManager.getConnection(URL);
            logger.log("Connection established.");

            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("UPDATE customer SET Id = ?, FirstName = ?, LastName = ?, Country = ?, PostalCode = ?, Phone = ?, EMail = ? WHERE Id=?");
            preparedStatement.setString(1, customer.getCustomerId());
            preparedStatement.setString(2, customer.getFirstName());
            preparedStatement.setString(3, customer.getLastName());
            preparedStatement.setString(4, customer.getCountry());
            preparedStatement.setString(5, customer.getPostalCode());
            preparedStatement.setString(6, customer.getPhone());
            preparedStatement.setString(7, customer.getEmail());
            preparedStatement.setString(8, customer.getCustomerId());
            // Execute Query
            int result = preparedStatement.executeUpdate();
            success = (result != 0);
            logger.log("Update successful");
        }
        catch (Exception exception){
            logger.log(exception.toString());
        }
        finally {
            try {
                conn.close();
            }
            catch (Exception exception){
                logger.log(exception.toString());
            }
        }
        return success;
    }
}
