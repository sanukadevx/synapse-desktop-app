package com.dev.synapse.components;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.JButton;

public class SecondaryButton extends JButton {

    public SecondaryButton() {
        init();
    }

    private void init() {
        putClientProperty(FlatClientProperties.STYLE, "arc:20");

        setBackground(new java.awt.Color(255, 255, 255));
        setFont(new java.awt.Font("Poppins", 0, 14));
        setForeground(new java.awt.Color(50, 50, 50));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 200, 200)));
    }
}
