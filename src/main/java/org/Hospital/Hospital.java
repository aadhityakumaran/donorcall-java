package org.Hospital;

import javax.swing.*;
import java.awt.*;
public class Hospital {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Hospital::createAndShowHospitalUI);
    }

    public static void createAndShowHospitalUI() {
        JFrame frame = new JFrame("Hospital Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new GridLayout(2, 1));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton callDonorButton = new JButton("Emergency Register");
        buttonPanel.add(callDonorButton);

        JButton viewPatients = new JButton("Check calls");
        buttonPanel.add(viewPatients);

        frame.add(callDonorButton);
        frame.add(viewPatients);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        callDonorButton.addActionListener(e -> {
            // Open ViewProfile
            SwingUtilities.invokeLater(PatientRegistration::new);
            frame.dispose();
        });

        viewPatients.addActionListener(e -> {
            // Add your code for "Call for donors" here
            JOptionPane.showMessageDialog(frame, "Call for donors functionality will be implemented here.");
        });
    }
}
