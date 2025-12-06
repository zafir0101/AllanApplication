package view.gui;

import model.AllanApplication;
import model.customer.*;
import model.customer.taxregime.*;
import model.types.Address;
import model.types.PhoneNumber;
import view.AllanRegisterCustomerView;

import javax.swing.*;
import java.awt.*;

/**
 * GUI implementation of the registration form using a modal {@link javax.swing.JDialog}.
 * <p>
 * Provides text fields and dropdowns for user input.
 * The parent window is blocked until this dialog is closed.
 *
 * @author zafir
 */
public class AllanRegisterCustomerViewGui extends JDialog implements AllanRegisterCustomerView {
    private final AllanApplication application;

    private JTextField txtName, txtStreet, txtNumber, txtCity, txtDdd, txtPhone;
    private JComboBox<String> cmbState, cmbRegime, cmbType;

    public AllanRegisterCustomerViewGui(JFrame parent, AllanApplication application) {
        super(parent, "Register New Customer", true); // true = Modal
        this.application = application;
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridLayout(10, 2, 10, 10));
        setSize(400, 500);
        setLocationRelativeTo(getParent());

        // Componentes
        add(new JLabel("Name:"));
        txtName = new JTextField();
        add(txtName);

        add(new JLabel("Street:"));
        txtStreet = new JTextField();
        add(txtStreet);

        add(new JLabel("Number:"));
        txtNumber = new JTextField();
        add(txtNumber);

        add(new JLabel("City:"));
        txtCity = new JTextField();
        add(txtCity);

        add(new JLabel("State (UF):"));
        String[] states = {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"};
        cmbState = new JComboBox<>(states);
        cmbState.setSelectedItem("SP");
        add(cmbState);

        add(new JLabel("DDD (2 digits):"));
        txtDdd = new JTextField();
        add(txtDdd);

        add(new JLabel("Phone Number:"));
        txtPhone = new JTextField();
        add(txtPhone);

        add(new JLabel("Tax Regime:"));
        cmbRegime = new JComboBox<>(new String[]{"Simples Nacional", "Lucro Presumido", "Lucro Real"});
        add(cmbRegime);

        add(new JLabel("Customer Type:"));
        cmbType = new JComboBox<>(new String[]{"Business (Comércio)", "Industry (Indústria)"});
        add(cmbType);

        JButton btnCancel = new JButton("Cancel");
        JButton btnSave = new JButton("Save");

        btnCancel.addActionListener(e -> dispose());
        btnSave.addActionListener(e -> saveCustomer());

        add(btnCancel);
        add(btnSave);
    }

    private void saveCustomer() {
        try {
            int number = Integer.parseInt(txtNumber.getText());
            Address address = new Address(txtStreet.getText(), number, txtCity.getText(), (String) cmbState.getSelectedItem());

            PhoneNumber phone = new PhoneNumber(txtDdd.getText(), txtPhone.getText());

            TaxRegime regime = switch (cmbRegime.getSelectedIndex()) {
                case 0 -> new SimplesNacional();
                case 1 -> new LucroPresumido();
                case 2 -> new LucroReal();
                default -> throw new IllegalStateException("Unexpected value");
            };

            Customer newCustomer;
            String name = txtName.getText();
            if (cmbType.getSelectedIndex() == 0) {
                newCustomer = new BusinessCustomer(name, address, phone, regime);
            } else {
                newCustomer = new IndustryCustomer(name, address, phone, regime);
            }

            application.addCustomer(newCustomer);

            JOptionPane.showMessageDialog(this, "Customer registered successfully!");
            dispose();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid number format in Number or fields.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Validation Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Unexpected error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void showRegisterCustomerForm() {
        setVisible(true);
    }
}