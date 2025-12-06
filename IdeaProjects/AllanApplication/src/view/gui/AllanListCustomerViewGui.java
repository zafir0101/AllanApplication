package view.gui;

import model.AllanApplication;
import model.customer.Customer;
import view.AllanListCustomerView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * GUI implementation for the customer report list.
 * <p>
 * Renders a modal {@link javax.swing.JDialog} containing a detailed table of all registered
 * customers, including their tax regime and calculated profit.
 *
 * @author zafir
 */
public class AllanListCustomerViewGui extends JDialog implements AllanListCustomerView {
    private final AllanApplication application;

    public AllanListCustomerViewGui(JFrame parent, AllanApplication application) {
        super(parent, "Customer List Report", true);
        this.application = application;
        initComponents();
    }

    private void initComponents() {
        setSize(800, 500);
        setLocationRelativeTo(getParent());
        setLayout(new BorderLayout());


        JLabel lblTitle = new JLabel("Full Customer Report", SwingConstants.CENTER);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        add(lblTitle, BorderLayout.NORTH);

        String[] columns = {"Name", "Type", "Tax Regime", "Phone", "Address (City/UF)", "Profit"};

        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (Customer c : application.getCustomers()) {
            model.addRow(new Object[]{
                    c.getName(),
                    c.getClass().getSimpleName(),
                    c.getTaxRegime().toString(),
                    c.getPhoneNumber().toString(),
                    c.getAddress().getCity() + " - " + c.getAddress().getAcronym(),
                    String.format("R$ %.2f", c.getProfit())
            });
        }

        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);
        table.setRowHeight(25);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton btnClose = new JButton("Close Report");
        btnClose.addActionListener(e -> dispose());

        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        buttonPanel.add(btnClose);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void showClientList() {
        setVisible(true);
    }
}