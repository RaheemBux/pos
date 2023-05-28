/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import static contants.CustomerType.CUSTOMER;
import contants.PaymentType;
import contants.Unit;
import dao.CustomerDAO;
import dao.ProductDAO;
import dao.PurchaseDAO;
import daoimpl.CustomerDAOImpl;
import daoimpl.ProductDAOImpl;
import daoimpl.PurchaseDAOImpl;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Customer;
import model.Product;
import model.Purchase;

/**
 *
 *
 */
public class PurchaseFrame extends javax.swing.JFrame {

    /**
     * Creates new form RegisterFrame
     */
    Object columns[] = {"Id", "Customer", "P-Date", "P-Number", "Product",
        "Quantity", "Price", "Total-Amount", "Paid", "Remaning", "Tax", "Payment Type"};

    DefaultTableModel defaultTableModel = new DefaultTableModel(columns, 0);

    private static Integer purchaseId = 0;

    PurchaseDAO purchaseDAO = new PurchaseDAOImpl();
    CustomerDAO customerDAO = new CustomerDAOImpl();
    ProductDAO productDAO = new ProductDAOImpl();

    public PurchaseFrame() {
        initComponents();
        purchaseNumberField.setText(getPurchaseCode());
        editBtn.setEnabled(false);
        deleteBtn.setEnabled(false);
        fillTable();
        fillCustomerCombo();
        fillProductCombo();
        setSize(930, 820);
        setLocation(350, 50);

    }

    public boolean isAnyFieldsEmpty() {
        if (priceField.getText().equals("")) {
            return true;
        }
        return false;
    }

    public void setFields(Purchase purchase) {
        purchaseDateField.setDate(purchase.getPurchaseDate());
        purchaseNumberField.setText(purchase.getPurchaseNumber());
        customerCombo.setSelectedItem(purchase.getCustomer().getName());
        productCombo.setSelectedItem(purchase.getProduct().getName());;
        quantityField.setValue(purchase.getQuantity());
        unitCombo.setSelectedItem(purchase.getUnit());
        priceField.setText(String.valueOf(purchase.getPrice()));
        paidAmountField.setText(String.valueOf(purchase.getAmountPaid()));
        remainingAmountField.setText(String.valueOf(purchase.getAmountRemaining()));
        totalField.setText(String.valueOf(purchase.getTotalAmount()));
        paymentTypeCombo.setSelectedItem(purchase.getPaymentType());
        if (purchase.isTaxable()) {
            vatCheck.setSelected(true);
        } else {
            vatCheck.setSelected(false);
        }

    }

    public void fillCustomerCombo() {
        List<Customer> customers = customerDAO.getAllCustomers();
        for (Customer customer : customers) {
            if (!CUSTOMER.equals(customer.getCustomerType())) {
                customerCombo.addItem(customer.getName());
            }
        }
    }

    public void fillProductCombo() {
        List<Product> products = productDAO.getAllProducts();
        for (Product product : products) {
            productCombo.addItem(product.getName());
        }
    }

    public Purchase getPurchase() {
        java.sql.Date purchaseDate = new java.sql.Date(purchaseDateField.getDate().getTime());
        String purchaseNumber = purchaseNumberField.getText();
        String customerName = customerCombo.getSelectedItem().toString();
        Customer customer = customerDAO.getCustomerByName(customerName);
        String productName = productCombo.getSelectedItem().toString();
        Product product = productDAO.getProductByName(productName);
        Integer quantity = Integer.parseInt(quantityField.getValue().toString());
        Unit unit = Unit.valueOf(unitCombo.getSelectedItem().toString());
        double price = Double.parseDouble(priceField.getText());
        double paid = Double.parseDouble(paidAmountField.getText());
        double total = quantity * price;
        double remaining = Double.parseDouble(remainingAmountField.getText());
        PaymentType paymentType = PaymentType.valueOf(paymentTypeCombo.getSelectedItem().toString());
        boolean vat = false;
        if (vatCheck.isSelected()) {
            vat = true;
        }
        Purchase purchase = new Purchase(0, purchaseDate, quantity, unit, price,
                purchaseNumber, paid, remaining, total, vat, paymentType, customer, product);
        return purchase;
    }

    public void clearFields() {
    }

    public String getPurchaseCode() {
        // Get current date and time
        LocalDateTime now = LocalDateTime.now();

        // Format the date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String formattedDateTime = now.format(formatter);

        // Generate the auto number
        return "NM-" + formattedDateTime;
    }

    public void fillTable() {
        defaultTableModel = new DefaultTableModel(columns, 0);
        List<Purchase> purchases = purchaseDAO.getAllPurchases();
        double taxAmount = 0;
        for (Purchase p : purchases) {
            if (p.isTaxable()) {
                taxAmount = p.getTotalAmount() * 0.05;
                p.setTotalAmount(p.getTotalAmount() + taxAmount);

            }
            Object row[] = {p.getPurchaseId(), p.getCustomer().getName(),
                p.getPurchaseDate(), p.getPurchaseNumber(), p.getProduct().getName(),
                p.getQuantity(), p.getPrice(), p.getTotalAmount(), p.getAmountPaid(),
                p.getAmountRemaining(),taxAmount, p.getPaymentType()};
            defaultTableModel.addRow(row);
            purchaseTable.setModel(defaultTableModel);
        }
        purchaseTable.getColumnModel().getColumn(0).setWidth(0);
        purchaseTable.getColumnModel().getColumn(0).setMinWidth(0);
        purchaseTable.getColumnModel().getColumn(0).setMaxWidth(0);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        headerLbl = new javax.swing.JLabel();
        nameLbl = new javax.swing.JLabel();
        phoneLbl = new javax.swing.JLabel();
        emailLbl = new javax.swing.JLabel();
        addressLbl = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        purchaseTable = new javax.swing.JTable();
        addBtn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        priceField = new javax.swing.JTextField();
        backBtn = new javax.swing.JButton();
        passwordLbl = new javax.swing.JLabel();
        customerCombo = new javax.swing.JComboBox<>();
        unitCombo = new javax.swing.JComboBox<>();
        productCombo = new javax.swing.JComboBox<>();
        addressLbl1 = new javax.swing.JLabel();
        paidAmountField = new javax.swing.JTextField();
        addressLbl2 = new javax.swing.JLabel();
        remainingLbl = new javax.swing.JLabel();
        paymentTypeCombo = new javax.swing.JComboBox<>();
        addressLbl4 = new javax.swing.JLabel();
        vatCheck = new javax.swing.JCheckBox();
        quantityField = new javax.swing.JSpinner();
        purchaseNumberField = new javax.swing.JLabel();
        purchaseDateLbl = new javax.swing.JLabel();
        pNumberLbl = new javax.swing.JLabel();
        totalLbl = new javax.swing.JLabel();
        totalField = new javax.swing.JLabel();
        remainingAmountField = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        headerLbl.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        headerLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        headerLbl.setText("Purchase");

        nameLbl.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        nameLbl.setText("Quantity");

        phoneLbl.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        phoneLbl.setText("Product");

        emailLbl.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        emailLbl.setText("Unit");

        addressLbl.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        addressLbl.setText("Price");

        purchaseTable.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        purchaseTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        purchaseTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                purchaseTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(purchaseTable);

        addBtn.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        addBtn.setText("Add");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        editBtn.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        editBtn.setText("Edit");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });

        deleteBtn.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        deleteBtn.setText("Delete");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        priceField.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        priceField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                priceFieldKeyReleased(evt);
            }
        });

        backBtn.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        backBtn.setText("Back");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        passwordLbl.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        passwordLbl.setText("Vendor");

        customerCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerComboActionPerformed(evt);
            }
        });

        unitCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "KG", "MT" }));
        unitCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unitComboActionPerformed(evt);
            }
        });

        productCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productComboActionPerformed(evt);
            }
        });

        addressLbl1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        addressLbl1.setText("Paid");

        paidAmountField.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        paidAmountField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                paidAmountFieldKeyReleased(evt);
            }
        });

        addressLbl2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        addressLbl2.setText("VAT");

        remainingLbl.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        remainingLbl.setText("Remainig");

        paymentTypeCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CASH", "CHEQUE", "ONLINE" }));
        paymentTypeCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentTypeComboActionPerformed(evt);
            }
        });

        addressLbl4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        addressLbl4.setText("Payment Type");

        vatCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vatCheckActionPerformed(evt);
            }
        });

        purchaseNumberField.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        purchaseNumberField.setText("Purchase #");

        purchaseDateLbl.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        purchaseDateLbl.setText("Purchase Date");

        pNumberLbl.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        pNumberLbl.setText("Purchase #");

        totalLbl.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        totalLbl.setText("Total Price");

        totalField.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        totalField.setText("0.0");

        remainingAmountField.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        remainingAmountField.setText("0.0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69)
                .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(212, 212, 212))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(nameLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(addressLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(27, 27, 27)
                                                        .addComponent(quantityField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                        .addGap(18, 18, 18)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(totalField, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(priceField, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(125, 125, 125)
                                                .addComponent(customerCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(57, 57, 57)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(emailLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(addressLbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(phoneLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(pNumberLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(remainingLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGap(37, 37, 37))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(addressLbl2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(60, 60, 60)
                                        .addComponent(vatCheck)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(addressLbl4)
                                        .addGap(40, 40, 40)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(paymentTypeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(unitCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(paidAmountField, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(productCombo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(purchaseNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(remainingAmountField, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(totalLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(770, 770, 770))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(backBtn)
                                .addGap(184, 184, 184)
                                .addComponent(headerLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(purchaseDateLbl)
                                    .addComponent(passwordLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 827, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(backBtn)
                    .addComponent(headerLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(purchaseDateLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(pNumberLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(purchaseNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customerCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phoneLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(productCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(emailLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(unitCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(quantityField, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nameLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(priceField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addressLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addressLbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(paidAmountField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(totalLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addressLbl2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(remainingLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(remainingAmountField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(totalField, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(paymentTypeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(addressLbl4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(vatCheck)))))
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void purchaseTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_purchaseTableMouseClicked
        purchaseId = (Integer) purchaseTable.getValueAt(purchaseTable.getSelectedRow(), 0);
        editBtn.setEnabled(true);
        deleteBtn.setEnabled(true);
        Purchase purchase = purchaseDAO.getPurchaseById(purchaseId);
        setFields(purchase);

    }//GEN-LAST:event_purchaseTableMouseClicked

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        if (isAnyFieldsEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!!");
        } else {
            Purchase purchase = getPurchase();
            boolean b = purchaseDAO.addPurchase(purchase);
            if (b) {
                JOptionPane.showMessageDialog(this, "Record Added Successfully");
                fillTable();
                clearFields();
            }
        }

    }//GEN-LAST:event_addBtnActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        if (isAnyFieldsEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!!");
        } else {
            Purchase purchase = getPurchase();
            purchase.setPurchaseId(purchaseId);
            boolean b = purchaseDAO.updatePurchase(purchase);
            if (b) {
                JOptionPane.showMessageDialog(this, "Record Updated Successfully");
                fillTable();
                clearFields();
                editBtn.setEnabled(false);
                deleteBtn.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(this, "Error Occurred");
            }
        }
    }//GEN-LAST:event_editBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        purchaseId = (Integer) purchaseTable.getValueAt(purchaseTable.getSelectedRow(), 0);
        boolean b = purchaseDAO.deletePurchase(purchaseId);
        if (b) {
            JOptionPane.showMessageDialog(this, "Record Deleted Successfully");
            fillTable();
            clearFields();
            editBtn.setEnabled(false);
            deleteBtn.setEnabled(false);
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        Home frame = new Home();
        frame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backBtnActionPerformed

    private void customerComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_customerComboActionPerformed

    private void unitComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unitComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_unitComboActionPerformed

    private void productComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_productComboActionPerformed

    private void paymentTypeComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentTypeComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_paymentTypeComboActionPerformed

    private void vatCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vatCheckActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_vatCheckActionPerformed

    private void paidAmountFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_paidAmountFieldKeyReleased
        Integer quantity = Integer.parseInt(quantityField.getValue().toString());
        Integer price = Integer.parseInt(priceField.getText());
        Integer paid = Integer.parseInt(paidAmountField.getText());
        Integer total = quantity * price;
        Integer remaining = total - paid;
        remainingAmountField.setText(remaining.toString());
    }//GEN-LAST:event_paidAmountFieldKeyReleased

    private void priceFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_priceFieldKeyReleased
        Integer quantity = Integer.parseInt(quantityField.getValue().toString());
        Integer price = Integer.parseInt(priceField.getText());
        Integer total = quantity * price;
        totalField.setText(total.toString());

    }//GEN-LAST:event_priceFieldKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PurchaseFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PurchaseFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PurchaseFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PurchaseFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PurchaseFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JLabel addressLbl;
    private javax.swing.JLabel addressLbl1;
    private javax.swing.JLabel addressLbl2;
    private javax.swing.JLabel addressLbl4;
    private javax.swing.JButton backBtn;
    private javax.swing.JComboBox<String> customerCombo;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JLabel emailLbl;
    private javax.swing.JLabel headerLbl;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel nameLbl;
    private javax.swing.JLabel pNumberLbl;
    private javax.swing.JTextField paidAmountField;
    private javax.swing.JLabel passwordLbl;
    private javax.swing.JComboBox<String> paymentTypeCombo;
    private javax.swing.JLabel phoneLbl;
    private javax.swing.JTextField priceField;
    private javax.swing.JComboBox<String> productCombo;
    private javax.swing.JLabel purchaseDateLbl;
    private javax.swing.JLabel purchaseNumberField;
    private javax.swing.JTable purchaseTable;
    private javax.swing.JSpinner quantityField;
    private javax.swing.JLabel remainingAmountField;
    private javax.swing.JLabel remainingLbl;
    private javax.swing.JLabel totalField;
    private javax.swing.JLabel totalLbl;
    private javax.swing.JComboBox<String> unitCombo;
    private javax.swing.JCheckBox vatCheck;
    // End of variables declaration//GEN-END:variables
}
