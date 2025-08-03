package com.dev.synapse.components;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.JButton;

public class PrimaryButton extends JButton {

    public PrimaryButton() {
        init();
    }

    private void init() {
        putClientProperty(FlatClientProperties.STYLE, "arc:20");

        setBackground(new java.awt.Color(49, 151, 149));
        setFont(new java.awt.Font("Poppins", 0, 14));
        setForeground(new java.awt.Color(255, 255, 255));
    }
}
