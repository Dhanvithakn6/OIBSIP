package com.oasis.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import com.oasis.db.DBConnection;

public class ReservationFrame extends JFrame {

    JTextField txtPassenger;
    JTextField txtTrainNo;
    JTextField txtTrainName;
    JTextField txtClass;
    JTextField txtDate;
    JTextField txtSource;
    JTextField txtDestination;

    JButton btnFetch;
    JButton btnBook;
    JButton btnCancel;

    public ReservationFrame() {

        setTitle("Online Reservation System - Reservation");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblPassenger = new JLabel("Passenger Name");
        lblPassenger.setBounds(50,40,120,25);

        txtPassenger = new JTextField();
        txtPassenger.setBounds(180,40,180,25);

        JLabel lblTrainNo = new JLabel("Train Number");
        lblTrainNo.setBounds(50,80,120,25);

        txtTrainNo = new JTextField();
        txtTrainNo.setBounds(180,80,180,25);

        JLabel lblTrainName = new JLabel("Train Name");
        lblTrainName.setBounds(50,120,120,25);

        txtTrainName = new JTextField();
        txtTrainName.setBounds(180,120,180,25);
        txtTrainName.setEditable(false);

        JLabel lblClass = new JLabel("Class");
        lblClass.setBounds(50,160,120,25);

        txtClass = new JTextField();
        txtClass.setBounds(180,160,180,25);
        txtClass.setEditable(false);

        JLabel lblDate = new JLabel("Journey Date");
        lblDate.setBounds(50,200,120,25);

        txtDate = new JTextField();
        txtDate.setBounds(180,200,180,25);

        JLabel lblSource = new JLabel("Source");
        lblSource.setBounds(50,240,120,25);

        txtSource = new JTextField();
        txtSource.setBounds(180,240,180,25);
        txtSource.setEditable(false);

        JLabel lblDestination = new JLabel("Destination");
        lblDestination.setBounds(50,280,120,25);

        txtDestination = new JTextField();
        txtDestination.setBounds(180,280,180,25);
        txtDestination.setEditable(false);

        btnFetch = new JButton("Fetch Train");
        btnFetch.setBounds(400,80,150,30);

        btnBook = new JButton("Book Ticket");
        btnBook.setBounds(180,340,150,35);
        
        btnCancel = new JButton("Cancel Ticket");
        btnCancel.setBounds(360,340,150,35);

        add(lblPassenger);
        add(txtPassenger);

        add(lblTrainNo);
        add(txtTrainNo);

        add(lblTrainName);
        add(txtTrainName);

        add(lblClass);
        add(txtClass);

        add(lblDate);
        add(txtDate);

        add(lblSource);
        add(txtSource);

        add(lblDestination);
        add(txtDestination);

        add(btnFetch);
        add(btnBook);
        add(btnCancel);
        btnFetch.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String trainNo = txtTrainNo.getText().trim();

                if (!trainNo.matches("\\d+")) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Please enter a valid numeric train number.");
                    return;
                }

                try {

                    Connection con = DBConnection.getConnection();

                    String query = "SELECT * FROM trains WHERE train_number = ?";

                    PreparedStatement pst = con.prepareStatement(query);

                    pst.setInt(1, Integer.parseInt(trainNo));

                    ResultSet rs = pst.executeQuery();

                    if (rs.next()) {

                        txtTrainName.setText(rs.getString("train_name"));
                        txtClass.setText(rs.getString("class_type"));
                        txtSource.setText(rs.getString("source_station"));
                        txtDestination.setText(rs.getString("destination_station"));

                    } else {

                        JOptionPane.showMessageDialog(null, "Train not found");

                    }

                    rs.close();
                    pst.close();
                    con.close();

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error while fetching train details.");
                }

            }

        });
        btnBook.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String passenger = txtPassenger.getText();
                String trainNo = txtTrainNo.getText().trim();
                if (!trainNo.matches("\\d+")) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Please enter a valid numeric train number.");
                    return;
                }
                String trainName = txtTrainName.getText();
                String classType = txtClass.getText();
                String journeyDate = txtDate.getText();
                try {
                    LocalDate.parse(journeyDate);
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Please enter the journey date in YYYY-MM-DD format.");
                    return;
                }
                String source = txtSource.getText();
                String destination = txtDestination.getText();
                if (passenger.isEmpty() ||
                	    trainNo.isEmpty() ||
                	    journeyDate.isEmpty()) {

                	    JOptionPane.showMessageDialog(
                	            null,
                	            "Please fill all required fields.");

                	    return;
                	}

                try {

                    Connection con = DBConnection.getConnection();

                    String query = "INSERT INTO reservations(passenger_name, train_number, train_name, class_type, journey_date, source_station, destination_station) VALUES(?,?,?,?,?,?,?)";

                    PreparedStatement pst = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
                    pst.setString(1, passenger);
                    pst.setInt(2, Integer.parseInt(trainNo));
                    pst.setString(3, trainName);
                    pst.setString(4, classType);
                    pst.setString(5, journeyDate);
                    pst.setString(6, source);
                    pst.setString(7, destination);

                    pst.executeUpdate();
                    ResultSet rs = pst.getGeneratedKeys();

                    if (rs.next()) {

                        int pnr = rs.getInt(1);

                        JOptionPane.showMessageDialog(
                                null,
                                "Booking Successful!\n\n"
                                + "PNR Number : " + pnr
                                + "\nPassenger : " + passenger
                                + "\nTrain No : " + trainNo
                                + "\nTrain Name : " + trainName
                                + "\nClass : " + classType
                                + "\nJourney Date : " + journeyDate
                                + "\nSource : " + source
                                + "\nDestination : " + destination
                        );
                        txtPassenger.setText("");
                        txtTrainNo.setText("");
                        txtTrainName.setText("");
                        txtClass.setText("");
                        txtDate.setText("");
                        txtSource.setText("");
                        txtDestination.setText("");

                    }

                    rs.close();
                    pst.close();
                    con.close();

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(
                            null,
                            ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }

            }

        });
        btnCancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                new CancellationFrame();

            }

        });

        setVisible(true);
    }
}