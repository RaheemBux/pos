/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoimpl;

import contants.PaymentType;
import contants.Unit;
import dao.CustomerDAO;
import dao.ProductDAO;
import dao.SalesDAO;
import dbmanager.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Customer;
import model.Product;
import model.Sales;

/**
 *
 * @author HP
 */
public class SalesDAOImpl implements SalesDAO {

    private Connection conn = DBConnection.getConnection();
    private CustomerDAO customerDAO = new CustomerDAOImpl();
    private ProductDAO productDAO = new ProductDAOImpl();

    @Override
    public boolean addSales(Sales sales) {
        try {
            String query = "INSERT INTO sales (sales_date, quantity, unit, price, sales_number, amount_paid, amount_remaining, "
                    + "total_amount, payment_type, customer_id, product_id,created_date,created_by) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,now(),?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setDate(1, sales.getSalesDate());
            statement.setInt(2, sales.getQuantity());
            statement.setString(3, sales.getUnit().toString());
            statement.setInt(4, sales.getPrice());
            statement.setString(5, sales.getSalesNumber());
            statement.setInt(6, sales.getAmountPaid());
            statement.setInt(7, sales.getAmountRemaining());
            statement.setInt(8, sales.getTotalAmount());
            statement.setString(9, sales.getPaymentType().toString());
            statement.setInt(10, sales.getCustomer().getCustomerId());
            statement.setInt(11, sales.getProduct().getProductId());
            statement.setString(12, sales.getCreatedBy() );
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateSales(Sales sales) {
        try {
            String query = "UPDATE sales SET sales_date=?, quantity=?, unit=?, price=?, sales_number=?, amount_paid=?, "
                    + "amount_remaining=?, total_amount=?, payment_type=?, customer_id=?, product_id=?,last_modified_date=now(),last_modified_by=? WHERE sales_id=?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setDate(1, sales.getSalesDate());
            statement.setInt(2, sales.getQuantity());
            statement.setString(3, sales.getUnit().toString());
            statement.setInt(4, sales.getPrice());
            statement.setString(5, sales.getSalesNumber());
            statement.setInt(6, sales.getAmountPaid());
            statement.setInt(7, sales.getAmountRemaining());
            statement.setInt(8, sales.getTotalAmount());
            statement.setString(9, sales.getPaymentType().toString());
            statement.setInt(10, sales.getCustomer().getCustomerId());
            statement.setInt(11, sales.getProduct().getProductId());
            statement.setString(12, sales.getLastModifiedBy());
            statement.setInt(13, sales.getSalesId());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteSales(int salesId) {
        try {
            String query = "DELETE FROM sales WHERE sales_id=?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, salesId);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Sales> getAllSales() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Sales> salesList = new ArrayList<>();
        String query = "SELECT * FROM sales";
        try {
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Sales sales = new Sales();
                sales.setSalesId(rs.getInt("sales_id"));
                sales.setSalesDate(rs.getDate("sales_date"));
                sales.setQuantity(rs.getInt("quantity"));
                sales.setUnit(Unit.valueOf(rs.getString("unit")));
                sales.setPrice(rs.getInt("price"));
                sales.setSalesNumber(rs.getString("sales_number"));
                sales.setAmountPaid(rs.getInt("amount_paid"));
                sales.setAmountRemaining(rs.getInt("amount_remaining"));
                sales.setTotalAmount(rs.getInt("total_amount"));
                sales.setPaymentType(PaymentType.valueOf(rs.getString("payment_type")));
                Customer customer = customerDAO.getCustomerById(rs.getInt("customer_id"));
                Product product = productDAO.getProductById(rs.getInt("product_id"));
                sales.setCustomer(customer);
                sales.setProduct(product);
                salesList.add(sales);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salesList;
    }

    @Override
    public Sales getSalesById(int salesId) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Sales sales = null;
        String query = "SELECT * FROM sales WHERE sales_id = ?";
        try {
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, salesId);
            rs = stmt.executeQuery();
            if (rs.next()) {
                sales = new Sales();
                sales.setSalesId(rs.getInt("sales_id"));
                sales.setSalesDate(rs.getDate("sales_date"));
                sales.setQuantity(rs.getInt("quantity"));
                sales.setUnit(Unit.valueOf(rs.getString("unit")));
                sales.setPrice(rs.getInt("price"));
                sales.setSalesNumber(rs.getString("sales_number"));
                sales.setAmountPaid(rs.getInt("amount_paid"));
                sales.setAmountRemaining(rs.getInt("amount_remaining"));
                sales.setTotalAmount(rs.getInt("total_amount"));
                sales.setPaymentType(PaymentType.valueOf(rs.getString("payment_type")));
                Customer customer = customerDAO.getCustomerById(rs.getInt("customer_id"));
                Product product = productDAO.getProductById(rs.getInt("product_id"));
                sales.setCustomer(customer);
                sales.setProduct(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return sales;
    }

}
