package com.oasis.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.oasis.db.DBConnection;

public class CancellationFrame extends JFrame {

	JTextField txtPNR;
	JTextArea txtDetails;

	JButton btnFetch;
	JButton btnCancel;

	public CancellationFrame() {

		setTitle("Online Reservation System - Cancellation");
		setSize(600, 450);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		JLabel lblPNR = new JLabel("PNR Number");
		lblPNR.setBounds(40, 40, 100, 25);

		txtPNR = new JTextField();
		txtPNR.setBounds(150, 40, 150, 25);

		btnFetch = new JButton("Fetch");
		btnFetch.setBounds(330, 40, 100, 25);

		txtDetails = new JTextArea();
		txtDetails.setEditable(false);

		JScrollPane scrollPane = new JScrollPane(txtDetails);
		scrollPane.setBounds(40, 90, 500, 220);

		btnCancel = new JButton("Cancel Reservation");
		btnCancel.setBounds(180, 340, 180, 35);

		add(lblPNR);
		add(txtPNR);
		add(btnFetch);
		add(scrollPane);
		add(btnCancel);

		// Fetch Reservation
		btnFetch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {

					int pnr = Integer.parseInt(txtPNR.getText().trim());

					Connection con = DBConnection.getConnection();

					String query = "SELECT * FROM reservations WHERE pnr = ?";

					PreparedStatement pst = con.prepareStatement(query);

					pst.setInt(1, pnr);

					ResultSet rs = pst.executeQuery();

					if (rs.next()) {

						txtDetails.setText("PNR Number : " + rs.getInt("pnr") + "\nPassenger : "
								+ rs.getString("passenger_name") + "\nTrain Number : " + rs.getInt("train_number")
								+ "\nTrain Name : " + rs.getString("train_name") + "\nClass : "
								+ rs.getString("class_type") + "\nJourney Date : " + rs.getDate("journey_date")
								+ "\nSource : " + rs.getString("source_station") + "\nDestination : "
								+ rs.getString("destination_station"));

					} else {

						JOptionPane.showMessageDialog(null, "PNR not found");
						txtDetails.setText("");

					}

					rs.close();
					pst.close();
					con.close();

				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}

		});

		// Cancel Reservation
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {

					int pnr = Integer.parseInt(txtPNR.getText().trim());

					int option = JOptionPane.showConfirmDialog(null,
							"Are you sure you want to cancel this reservation?", "Confirm Cancellation",
							JOptionPane.YES_NO_OPTION);

					if (option == JOptionPane.YES_OPTION) {

						Connection con = DBConnection.getConnection();

						String query = "DELETE FROM reservations WHERE pnr = ?";

						PreparedStatement pst = con.prepareStatement(query);

						pst.setInt(1, pnr);

						int rows = pst.executeUpdate();

						if (rows > 0) {

							JOptionPane.showMessageDialog(null, "Reservation Cancelled Successfully!");

							txtPNR.setText("");
							txtDetails.setText("");

						} else {

							JOptionPane.showMessageDialog(null, "PNR not found.");

						}

						pst.close();
						con.close();

					}

				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}

		});

		setVisible(true);
	}
}