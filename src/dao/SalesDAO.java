/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author HP
 */
import java.util.List;
import model.Sales;

public interface SalesDAO {

    boolean addSales(Sales sales);

    boolean updateSales(Sales sales);

    boolean deleteSales(int salesId);

    Sales getSalesById(int salesId);

    List<Sales> getAllSales();
}
