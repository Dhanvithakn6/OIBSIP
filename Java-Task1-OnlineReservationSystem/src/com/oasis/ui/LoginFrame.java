package com.oasis.ui;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.oasis.db.DBConnection;
public class LoginFrame extends JFrame {

    JLabel lblUsername, lblPassword;
    JTextField txtUsername;
    JPasswordField txtPassword;
    JButton btnLogin;
    

    public LoginFrame() {

        setTitle("Online Reservation System - Login");
        setSize(400,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);

        lblUsername = new JLabel("Username");
        lblUsername.setBounds(50,50,100,25);

        txtUsername = new JTextField();
        txtUsername.setBounds(150,50,150,25);

        lblPassword = new JLabel("Password");
        lblPassword.setBounds(50,100,100,25);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(150,100,150,25);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(150,160,100,30);

        add(lblUsername);
        add(txtUsername);
        add(lblPassword);
        add(txtPassword);
        add(btnLogin);
        btnLogin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

            	String username = txtUsername.getText();
            	String password = new String(txtPassword.getPassword());

            	try {

            	    Connection con = DBConnection.getConnection();

            	    String query = "SELECT * FROM users WHERE username=? AND password=?";

            	    PreparedStatement pst = con.prepareStatement(query);

            	    pst.setString(1, username);
            	    pst.setString(2, password);

            	    ResultSet rs = pst.executeQuery();

            	    if (rs.next()) {

            	        JOptionPane.showMessageDialog(null, "Login Successful");

            	        LoginFrame.this.dispose();

            	        new ReservationFrame();

            	    } else {

            	        JOptionPane.showMessageDialog(null, "Invalid Username or Password");

            	    }

            	} catch (Exception ex) {
            	    ex.printStackTrace();
            	}
            }

        });

        setVisible(true);
    }
}