/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoimpl;

import dao.CustomerDAO;
import dao.LedgerDAO;
import dbmanager.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Customer;
import model.Ledger;

/**
 *
 * @author HP
 */
public class LedgerDAOImpl implements LedgerDAO {

    private final Connection connection = DBConnection.getConnection();
    CustomerDAO customerDAO = new CustomerDAOImpl();

    @Override
    public boolean addLedger(Ledger ledger) {
        String sql = "INSERT INTO ledger (order_number, amount_paid, amount_remaining, total_amount, customer_id) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, ledger.getOrderNumber());
            statement.setDouble(2, ledger.getAmountPaid());
            statement.setDouble(3, ledger.getAmountRemaining());
            statement.setDouble(4, ledger.getTotalAmount());
            statement.setInt(5, ledger.getCustomer().getCustomerId());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Ledger getByLedgerId(int ledgerId) {
        String sql = "SELECT * FROM ledger WHERE ledger_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, ledgerId);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Customer customer = customerDAO.getCustomerById(resultSet.getInt("customer_id"));
                Ledger ledger = new Ledger(
                        resultSet.getInt("ledger_id"),
                        resultSet.getString("order_number"),
                        resultSet.getDouble("amount_paid"),
                        resultSet.getDouble("amount_remaining"),
                        resultSet.getDouble("total_amount"),
                        customer
                );
                return ledger;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateLedger(Ledger ledger) {
        String sql = "UPDATE ledger SET order_number = ?, amount_paid = ?, amount_remaining = ?, total_amount = ?, customer_id = ? WHERE ledger_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, ledger.getOrderNumber());
            statement.setDouble(2, ledger.getAmountPaid());
            statement.setDouble(3, ledger.getAmountRemaining());
            statement.setDouble(4, ledger.getTotalAmount());
            statement.setInt(5, ledger.getCustomer().getCustomerId());
            statement.setInt(6, ledger.getLedgerId());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteLedger(int ledgerId) {
        String sql = "DELETE FROM ledger WHERE ledger_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, ledgerId);

            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Ledger> getAllLedgers() {
        List<Ledger> ledgers = new ArrayList<>();
        String sql = "SELECT * FROM ledger";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Customer customer = customerDAO.getCustomerById(resultSet.getInt("customer_id"));
                Ledger ledger = new Ledger(
                        resultSet.getInt("ledger_id"),
                        resultSet.getString("order_number"),
                        resultSet.getDouble("amount_paid"),
                        resultSet.getDouble("amount_remaining"),
                        resultSet.getDouble("total_amount"),
                        customer
                );
                ledgers.add(ledger);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ledgers;
    }

    @Override
    public List<Ledger> getAllLedgersByCustomerId(int customerId) {
        List<Ledger> ledgers = new ArrayList<>();
        String sql = "SELECT * FROM ledger WHERE customer_id=?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, customerId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Customer customer = customerDAO.getCustomerById(resultSet.getInt("customer_id"));
                Ledger ledger = new Ledger(
                        resultSet.getInt("ledger_id"),
                        resultSet.getString("order_number"),
                        resultSet.getDouble("amount_paid"),
                        resultSet.getDouble("amount_remaining"),
                        resultSet.getDouble("total_amount"),
                        customer
                );
                ledgers.add(ledger);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ledgers;
    }

    @Override
    public List<Ledger> getAllLedgersByOrderNumber(String orderNumber) {
        List<Ledger> ledgers = new ArrayList<>();
        String sql = "SELECT * FROM ledger WHERE order_number=?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, orderNumber);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Customer customer = customerDAO.getCustomerById(resultSet.getInt("customer_id"));
                Ledger ledger = new Ledger(
                        resultSet.getInt("ledger_id"),
                        resultSet.getString("order_number"),
                        resultSet.getDouble("amount_paid"),
                        resultSet.getDouble("amount_remaining"),
                        resultSet.getDouble("total_amount"),
                        customer
                );
                ledgers.add(ledger);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ledgers;
    }

}
