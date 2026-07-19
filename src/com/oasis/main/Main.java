package com.oasis.main;

import javax.swing.SwingUtilities;

import com.oasis.ui.LoginFrame;
public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
        	new LoginFrame();
        });

    }
}