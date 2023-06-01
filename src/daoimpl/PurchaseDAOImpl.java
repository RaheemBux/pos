/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoimpl;

/**
 *
 * @author HP
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import contants.PaymentType;
import contants.Unit;
import dao.CustomerDAO;
import dao.ProductDAO;
import dao.PurchaseDAO;
import dbmanager.DBConnection;
import model.Customer;
import model.Product;
import model.Purchase;

public class PurchaseDAOImpl implements PurchaseDAO {

    private Connection conn = DBConnection.getConnection();
    private CustomerDAO customerDAO = new CustomerDAOImpl();
    private ProductDAO productDAO = new ProductDAOImpl();

    @Override
    public boolean addPurchase(Purchase purchase) {
        try {
            String query = "INSERT INTO purchase(purchase_date, quantity, unit, price, purchase_number, amount_paid, amount_remaining, total_amount, payment_type, customer_id, product_id,is_taxable,created_date,created_by) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,now(),?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setDate(1, new java.sql.Date(purchase.getPurchaseDate().getTime()));
            ps.setInt(2, purchase.getQuantity());
            ps.setString(3, purchase.getUnit().toString());
            ps.setDouble(4, purchase.getPrice());
            ps.setString(5, purchase.getPurchaseNumber());
            ps.setDouble(6, purchase.getAmountPaid());
            ps.setDouble(7, purchase.getAmountRemaining());
            ps.setDouble(8, purchase.getTotalAmount());
            ps.setString(9, purchase.getPaymentType().toString());
            ps.setInt(10, purchase.getCustomer().getCustomerId());
            ps.setInt(11, purchase.getProduct().getProductId());
            ps.setBoolean(12, purchase.isTaxable());
            ps.setString(13, purchase.getCreatedBy());
            int count = ps.executeUpdate();
            if (count > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean updatePurchase(Purchase purchase) {
        try {
            String query = "UPDATE purchase SET purchase_date=?, quantity=?, unit=?, price=?, purchase_number=?, amount_paid=?, "
                    + "amount_remaining=?, total_amount=?, payment_type=?, is_taxable,customer_id=?, product_id=?,last_modified_date=now(),last_modified_by=? "
                    + "WHERE purchase_id=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setDate(1, new java.sql.Date(purchase.getPurchaseDate().getTime()));
            ps.setInt(2, purchase.getQuantity());
            ps.setString(3, purchase.getUnit().toString());
            ps.setDouble(4, purchase.getPrice());
            ps.setString(5, purchase.getPurchaseNumber());
            ps.setDouble(6, purchase.getAmountPaid());
            ps.setDouble(7, purchase.getAmountRemaining());
            ps.setDouble(8, purchase.getTotalAmount());
            ps.setString(9, purchase.getPaymentType().toString());
            ps.setInt(10, purchase.getCustomer().getCustomerId());
            ps.setInt(11, purchase.getProduct().getProductId());
            ps.setBoolean(12, purchase.isTaxable());
            ps.setString(13, purchase.getLastModifiedBy());
            ps.setInt(14, purchase.getPurchaseId());
            int count = ps.executeUpdate();
            if (count > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean deletePurchase(int purchaseId) {
        boolean isDeleted = false;
        try {
            String sql = "DELETE FROM purchase WHERE purchase_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, purchaseId);
            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                isDeleted = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return isDeleted;
    }

    @Override
    public List<Purchase> getAllPurchases() {
        List<Purchase> purchases = new ArrayList<>();
        String query = "SELECT * FROM purchase";
        try (
                PreparedStatement statement = conn.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Purchase purchase = new Purchase();
                purchase.setPurchaseId(resultSet.getInt("purchase_id"));
                purchase.setPurchaseDate(resultSet.getDate("purchase_date"));
                purchase.setQuantity(resultSet.getInt("quantity"));
                purchase.setUnit(Unit.valueOf(resultSet.getString("unit")));
                purchase.setPrice(resultSet.getInt("price"));
                purchase.setPurchaseNumber(resultSet.getString("purchase_number"));
                purchase.setAmountPaid(resultSet.getInt("amount_paid"));
                purchase.setAmountRemaining(resultSet.getInt("amount_remaining"));
                purchase.setTotalAmount(resultSet.getInt("total_amount"));
                purchase.setTaxable(resultSet.getBoolean("is_taxable"));
                purchase.setPaymentType(PaymentType.valueOf(resultSet.getString("payment_type")));
                Customer customer = customerDAO.getCustomerById(resultSet.getInt("customer_id"));
                purchase.setCustomer(customer);
                Product product = productDAO.getProductById(resultSet.getInt("product_id"));
                purchase.setProduct(product);
                purchases.add(purchase);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return purchases;
    }

    @Override
    public Purchase getPurchaseById(int purchaseId) {
        Purchase purchase = null;
        String query = "SELECT * FROM purchase WHERE purchase_id = ?";
        try (
                PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, purchaseId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                purchase = new Purchase();
                purchase.setPurchaseId(resultSet.getInt("purchase_id"));
                purchase.setPurchaseDate(resultSet.getDate("purchase_date"));
                purchase.setQuantity(resultSet.getInt("quantity"));
                purchase.setUnit(Unit.valueOf(resultSet.getString("unit")));
                purchase.setPrice(resultSet.getInt("price"));
                purchase.setPurchaseNumber(resultSet.getString("purchase_number"));
                purchase.setAmountPaid(resultSet.getInt("amount_paid"));
                purchase.setAmountRemaining(resultSet.getInt("amount_remaining"));
                purchase.setTotalAmount(resultSet.getInt("total_amount"));
                purchase.setTaxable(resultSet.getBoolean("is_taxable"));
                purchase.setPaymentType(PaymentType.valueOf(resultSet.getString("payment_type")));
                Customer customer = customerDAO.getCustomerById(resultSet.getInt("customer_id"));
                purchase.setCustomer(customer);
                Product product = productDAO.getProductById(resultSet.getInt("product_id"));
                purchase.setProduct(product);;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return purchase;
    }

}
