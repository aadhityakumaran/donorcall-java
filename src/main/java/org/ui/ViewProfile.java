package org.ui;

import javax.swing.*;
import java.awt.*;
import org.back.Donor_back;

public class ViewProfile {
    public ViewProfile(int user_id) {
        createAndShowViewProfile(user_id);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ViewProfile(8389));
    }

    public void createAndShowViewProfile(int user_id){
        JFrame frame = new JFrame("User Information");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2)); // 2 columns for label-value pairs

        String[] userData = Donor_back.getUserData(user_id);
        if (userData != null) {
            addLabelValuePair(panel, "Name:", userData[0]);
            addLabelValuePair(panel, "Phone:", userData[1]);
            addLabelValuePair(panel, "Blood Type:", userData[2]);
            addLabelValuePair(panel, "Last Donated on:", userData[3]);
        }

        frame.add(panel);
        frame.setVisible(true);

    }

    private static void addLabelValuePair(JPanel panel, String label, String value) {
        JLabel labelComponent = new JLabel(label);
        JLabel valueComponent = new JLabel(value);
        panel.add(labelComponent);
        panel.add(valueComponent);
    }

}
