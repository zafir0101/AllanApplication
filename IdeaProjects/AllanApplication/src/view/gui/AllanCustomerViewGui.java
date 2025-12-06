package view.gui;

import model.customer.Customer;
import model.stock.Product;
import model.stock.ProductWithQuantity;
import view.AllanCustomerView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * GUI implementation for managing a specific Customer.
 * <p>
 * Displays a window with the customer's stock details and financial metrics.
 * Allows the user to add or remove products visually and updates the calculated
 * profit in real-time.
 *
 * @author zafir
 */
public class AllanCustomerViewGui extends JFrame implements AllanCustomerView {
    private final Customer customer;
    private JTable productsTable;
    private DefaultTableModel tableModel;
    private JLabel lblTotalStock, lblTotalCost, lblProfit;

    public AllanCustomerViewGui(JFrame parent, Customer customer) {
        this.customer = customer;
        initComponents();
    }

    private void initComponents() {
        setTitle("Managing: " + customer.getName());
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Customer Info"));
        infoPanel.add(new JLabel("Address: " + customer.getAddress()));
        infoPanel.add(new JLabel("Regime: " + customer.getTaxRegime().getClass().getSimpleName() + " | Type: " + customer.getClass().getSimpleName()));
        add(infoPanel, BorderLayout.NORTH);

        String[] columns = {"Product", "Desc", "Qty", "Price (Unit)", "Cost (Unit)", "Total Value"};
        tableModel = new DefaultTableModel(columns, 0);
        productsTable = new JTable(tableModel);
        add(new JScrollPane(productsTable), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());

        JPanel buttonsPanel = new JPanel();
        JButton btnAddProduct = new JButton("Add Product");
        JButton btnRemoveProduct = new JButton("Remove Selected");
        buttonsPanel.add(btnAddProduct);
        buttonsPanel.add(btnRemoveProduct);
        bottomPanel.add(buttonsPanel, BorderLayout.WEST);

        JPanel statsPanel = new JPanel(new GridLayout(3, 1));
        lblTotalStock = new JLabel("Gross Value: R$ 0.00");
        lblTotalCost = new JLabel("Total Cost: R$ 0.00");
        lblProfit = new JLabel("Est. Profit: R$ 0.00");

        statsPanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
        statsPanel.add(lblTotalStock);
        statsPanel.add(lblTotalCost);
        statsPanel.add(lblProfit);
        bottomPanel.add(statsPanel, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);

        btnAddProduct.addActionListener(e -> showAddProductDialog());

        btnRemoveProduct.addActionListener(e -> {
            int row = productsTable.getSelectedRow();
            if (row != -1) {
                ProductWithQuantity item = customer.getStock().getProductWithQuantityList().get(row);
                customer.removeProductFromStock(item);
                refreshData();
            }
        });

        refreshData();
    }

    private void showAddProductDialog() {
        JDialog dialog = new JDialog(this, "Add Product", true);
        dialog.setLayout(new GridLayout(6, 2, 5, 5));
        dialog.setSize(300, 300);
        dialog.setLocationRelativeTo(this);

        JTextField txtName = new JTextField();
        JTextField txtDesc = new JTextField();
        JTextField txtPrice = new JTextField();
        JTextField txtCost = new JTextField();
        JTextField txtQty = new JTextField();

        dialog.add(new JLabel("Name:")); dialog.add(txtName);
        dialog.add(new JLabel("Desc:")); dialog.add(txtDesc);
        dialog.add(new JLabel("Sell Price:")); dialog.add(txtPrice);
        dialog.add(new JLabel("Buy Cost:")); dialog.add(txtCost);
        dialog.add(new JLabel("Quantity:")); dialog.add(txtQty);

        JButton btnAdd = new JButton("Add");
        btnAdd.addActionListener(ev -> {
            try {
                String name = txtName.getText();
                String desc = txtDesc.getText();
                double price = Double.parseDouble(txtPrice.getText());
                double cost = Double.parseDouble(txtCost.getText());
                int qty = Integer.parseInt(txtQty.getText());

                Product p = new Product(name, desc, price, cost);
                ProductWithQuantity pq = new ProductWithQuantity(p, qty);

                customer.addProductToStock(pq);
                refreshData();
                dialog.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Error: " + ex.getMessage());
            }
        });
        dialog.add(new JLabel(""));
        dialog.add(btnAdd);

        dialog.setVisible(true);
    }

    private void refreshData() {
        tableModel.setRowCount(0);
        for (ProductWithQuantity pq : customer.getStock().getProductWithQuantityList()) {
            tableModel.addRow(new Object[]{
                    pq.getProduct().getName(),
                    pq.getProduct().getDescription(),
                    pq.getQuantity(),
                    pq.getProduct().getPrice(),
                    pq.getProduct().getCost(),
                    pq.getTotalValue()
            });
        }

        lblTotalStock.setText(String.format("Gross Value: R$ %.2f", customer.getStock().getTotalStockPrice()));
        lblTotalCost.setText(String.format("Total Cost (Tax/Overhead): R$ %.2f", customer.getTotalCost()));
        lblProfit.setText(String.format("Est. Profit: R$ %.2f", customer.getProfit()));

        if (customer.getProfit() >= 0) lblProfit.setForeground(new Color(0, 150, 0)); // Verde
        else lblProfit.setForeground(Color.RED);
    }

    @Override
    public void showCustomerOptions() {
        setVisible(true);
    }
}