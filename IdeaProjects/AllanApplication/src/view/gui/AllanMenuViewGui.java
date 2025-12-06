package view.gui;

import model.AllanApplication;
import model.customer.Customer;
import model.PersistenceService;
import view.AllanMenuView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;


/**
 * GUI implementation of the {@link view.AllanMenuView}.
 * <p>
 * Renders the main dashboard as a {@link javax.swing.JFrame}, featuring a data table
 * for customers and a toolbar for main actions (Register, Save, Remove).
 *
 * @author zafir
 * @see view.AllanMenuView
 */
public class AllanMenuViewGui extends JFrame implements AllanMenuView {
    private final AllanApplication application;
    private JTable clientsTable;
    private DefaultTableModel tableModel;

    private final PersistenceService<AllanApplication> saver = new PersistenceService<>();
    private static final String DB_FILE_NAME = "allan_db.ser";

    public AllanMenuViewGui(AllanApplication application) {
        this.application = application;
        initComponents();
    }

    private void initComponents() {
        setTitle("Allan Stock Management - Dashboard");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);

        JButton btnRegister = new JButton("➕ Register");
        JButton btnRemove = new JButton("❌ Remove");
        JButton btnOpen = new JButton("📂 Open/Select");

        // --- NOVO BOTÃO AQUI ---
        JButton btnReport = new JButton("📋 List/Report");

        JButton btnSave = new JButton("💾 Save");
        JButton btnExit = new JButton("🚪 Exit");

        toolBar.add(btnRegister);
        toolBar.add(btnRemove);
        toolBar.add(btnOpen);
        toolBar.add(btnReport);
        toolBar.addSeparator();
        toolBar.add(btnSave);
        toolBar.add(Box.createHorizontalGlue());
        toolBar.add(btnExit);

        add(toolBar, BorderLayout.NORTH);

        String[] columns = {"Name", "Type", "Phone", "City", "State"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        clientsTable = new JTable(tableModel);
        clientsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(clientsTable), BorderLayout.CENTER);

        btnRegister.addActionListener(e -> {
            AllanRegisterCustomerViewGui registerView = new AllanRegisterCustomerViewGui(this, application);
            registerView.showRegisterCustomerForm();
            refreshTable();
        });

        btnRemove.addActionListener(e -> removeSelectedCustomer());

        btnOpen.addActionListener(e -> openSelectedCustomer());


        btnReport.addActionListener(e -> {
            AllanListCustomerViewGui listView = new AllanListCustomerViewGui(this, application);
            listView.showClientList();
        });

        clientsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    openSelectedCustomer();
                }
            }
        });

        btnSave.addActionListener(e -> saveData());

        btnExit.addActionListener(e -> saveAndExit());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                saveAndExit();
            }
        });
    }

    private void removeSelectedCustomer() {
        int row = clientsTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a customer to remove.");
            return;
        }

        Customer customer = application.getCustomers().get(row);
        int confirm = JOptionPane.showConfirmDialog(this,
                "Remove customer: " + customer.getName() + "?",
                "Confirm", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                application.removeCustomer(customer);
                refreshTable();
                JOptionPane.showMessageDialog(this, "Removed successfully.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }
    }

    private void openSelectedCustomer() {
        int row = clientsTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a customer.");
            return;
        }
        Customer selectedCustomer = application.getCustomers().get(row);

        AllanCustomerViewGui clientView = new AllanCustomerViewGui(this, selectedCustomer);
        clientView.showCustomerOptions();
        refreshTable();
    }

    private void saveData() {
        try {
            saver.save(application, DB_FILE_NAME);
            JOptionPane.showMessageDialog(this, "Data saved!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving: " + e.getMessage());
        }
    }

    private void saveAndExit() {
        int confirm = JOptionPane.showConfirmDialog(this, "Save changes before exiting?", "Exit", JOptionPane.YES_NO_CANCEL_OPTION);
        if (confirm == JOptionPane.CANCEL_OPTION) return;
        if (confirm == JOptionPane.YES_OPTION) saveData();
        System.exit(0);
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        for (Customer c : application.getCustomers()) {
            tableModel.addRow(new Object[]{
                    c.getName(),
                    c.getClass().getSimpleName(),
                    c.getPhoneNumber().toString(),
                    c.getAddress().getCity(),
                    c.getAddress().getStateName()
            });
        }
    }

    @Override
    public void showMenu() {
        refreshTable();
        setVisible(true);
    }
}