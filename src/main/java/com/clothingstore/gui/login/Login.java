package com.clothingstore.gui.login;

// import com.bookstore.bus.UserBUS;
// import com.bookstore.gui.components.buttons.Button;
// import com.bookstore.gui.factories.UIFactory;
// import com.bookstore.models.UserModel;
// import com.bookstore.services.Authentication;
// import com.bookstore.util.InputValidator;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.WindowConstants;

import com.clothingstore.bus.UserBUS;
import com.clothingstore.models.UserModel;

import services.Authentication;

public class Login extends JFrame {

    private static Login instance;

    private LayoutManager layout;
    private JPanel loginPanel;
    private JButton exitButton;
    private JButton loginButton;
    private JLabel LogoLabel;
    private JLabel passwordLabel;
    private JLabel usernameLabel;
    private JPasswordField passwordField;
    private JTextField usernameTextField;
    private JToggleButton toggleButton;
    private ImageIcon showPassword;
    private ImageIcon hidePassword;

    public static Login getInstance() {
        if (instance == null) {
            instance = new Login();
        }
        return instance;
    }

    private Login() {
        initComponents();
        getRootPane().setDefaultButton(loginButton);
        setPreferredSize(new Dimension(700, 600));
        setMinimumSize(new Dimension(700, 600));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        layout = new FlowLayout(FlowLayout.CENTER, 10, 10);
        setLayout(layout);
        LogoLabel = new JLabel();
        LogoLabel.setIcon(new ImageIcon("src/main/java/resources/images/logo.png"));

        loginPanel = new JPanel();

        usernameLabel = new JLabel();
        usernameLabel.setText("Username");
        passwordLabel = new JLabel();
        passwordLabel.setText("Password");
        usernameTextField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        exitButton = new JButton("Exit");
        showPassword = new ImageIcon("src/main/java/resources/icons/show_password.png");
        hidePassword = new ImageIcon("src/main/java/resources/icons/hide_password.png");
        toggleButton = new JToggleButton(hidePassword);
        loginButton.addActionListener(loginButtonActionListener);
        exitButton.addActionListener(exitButtonActionListener);
        toggleButton.addActionListener(showPasswordButtonActionListener);

        loginPanel.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        loginPanel.add(usernameLabel, gridBagConstraints);

        gridBagConstraints.gridy = 1;
        loginPanel.add(usernameTextField, gridBagConstraints);

        gridBagConstraints.gridy = 2;
        loginPanel.add(passwordLabel, gridBagConstraints);
        gridBagConstraints.gridy = 3;
        loginPanel.add(passwordField, gridBagConstraints);
        JButton addButton = new JButton("");

        addButton.add(toggleButton);

        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;

        loginPanel.add(toggleButton, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridy = 4;
        loginPanel.add(loginButton, gridBagConstraints);
        gridBagConstraints.gridx = 3;
        loginPanel.add(exitButton, gridBagConstraints);

        add(LogoLabel);
        add(loginPanel);
    }

    private ActionListener showPasswordButtonActionListener = e -> {
        if (toggleButton.isSelected()) {
            passwordField.setEchoChar((char) 0);
            toggleButton.setIcon(showPassword);
        } else {
            passwordField.setEchoChar('\u25cf');
            toggleButton.setIcon(hidePassword);
        }
    };

    private ActionListener loginButtonActionListener = e -> {
        try {
            String username = usernameTextField.getText();
            String password = String.valueOf(passwordField.getPassword());

            UserModel user = UserBUS.getInstance().login(username, password);
            Authentication.setCurrentUser(user);
            dispose();
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(
                    null,
                    exception.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        passwordField.setText("");
    };

    private ActionListener exitButtonActionListener = e -> {
        System.exit(0);
    };

}
