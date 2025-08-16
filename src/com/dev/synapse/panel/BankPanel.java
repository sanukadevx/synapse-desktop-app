package com.dev.synapse.panel;

import com.dev.synapse.classes.UrgentRequests;
import com.dev.synapse.connection.MySQL;
import com.dev.synapse.dialogs.ViewBloodRequests;
import com.dev.synapse.gui.HomeScreen;
import com.dev.synapse.subpanels.UrgentRequestSubPanel;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Color;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.SwingConstants;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class BankPanel extends javax.swing.JPanel {

    private HomeScreen homeScreen;

    public BankPanel() {
        initComponents();
        styles();
        loadUrgentRequests();
        loadBloodTypes();
        loadData();
    }

    private void loadData() {
        String totalUnitsSql = "SELECT SUM(available_units) FROM blood_types";
        String recievedRequests = "";
        String dispensedUnits = "";

        try {
            ResultSet rs1 = MySQL.executeSearch(totalUnitsSql);

            while (rs1.next()) {
                int totalUnits = rs1.getInt("SUM(available_units)");
                totalBloodUnitsLabel.setText(String.valueOf(totalUnits));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadBloodTypes() {
        try {
            ResultSet rs = MySQL.executeSearch("SELECT blood_type, available_units FROM blood_types");

            while (rs.next()) {
                String group = rs.getString("blood_type");
                int units = rs.getInt("available_units");

                switch (group) {
                    case "A+":
                        availableUnitsLabel.setText(String.valueOf(units));
                        break;
                    case "A-":
                        availableUnitsLabel1.setText(String.valueOf(units));
                        break;
                    case "B+":
                        availableUnitsLabel2.setText(String.valueOf(units));
                        break;
                    case "B-":
                        availableUnitsLabel4.setText(String.valueOf(units));
                        break;
                    case "O+":
                        availableUnitsLabel5.setText(String.valueOf(units));
                        break;
                    case "O-":
                        availableUnitsLabel6.setText(String.valueOf(units));
                        break;
                    case "AB+":
                        availableUnitsLabel7.setText(String.valueOf(units));
                        break;
                    case "AB-":
                        availableUnitsLabel8.setText(String.valueOf(units));
                        break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadUrgentRequests() {
        requestContainer.removeAll();

        try {
            ResultSet rs = MySQL.executeSearch("SELECT * "
                    + "FROM blood_requests a "
                    + "INNER JOIN blood_types p ON a.blood_type_id = p.blood_type_id "
                    + "INNER JOIN urgency_level ad ON a.urgency_level_id = ad.urgency_level_id "
                    + "INNER JOIN institutions at ON a.requesting_hospital_id= at.institution_id "
            );

            while (rs.next()) {
                UrgentRequests urgentRequests = new UrgentRequests(
                        rs.getString("blood_type"),
                        rs.getString("urgency_level"),
                        rs.getString("institution_name"),
                        rs.getString("units_required"),
                        LocalDate.now()
                );

                UrgentRequestSubPanel urgentRequestPanel = new UrgentRequestSubPanel(urgentRequests, this);
                urgentRequestPanel.populateData();

                // Set alignment for each panel
                urgentRequestPanel.setAlignmentX(urgentRequestPanel.LEFT_ALIGNMENT);
                urgentRequestPanel.setAlignmentY(urgentRequestPanel.TOP_ALIGNMENT);

                requestContainer.add(urgentRequestPanel);
                requestContainer.add(Box.createVerticalStrut(5));
            }

            // CRUCIAL: Add glue at the end to push everything to top
            requestContainer.add(Box.createVerticalGlue());

        } catch (SQLException ex) {
            Logger.getLogger(UrgentRequests.class.getName()).log(Level.SEVERE, null, ex);
        }

        requestContainer.revalidate();
        requestContainer.repaint();
    }

    private void styles() {
        titlePanel.putClientProperty(FlatClientProperties.STYLE, "arc:15");
        cardPanel01.putClientProperty(FlatClientProperties.STYLE, "arc:15");
        cardPanel02.putClientProperty(FlatClientProperties.STYLE, "arc:15");
        cardPanel03.putClientProperty(FlatClientProperties.STYLE, "arc:15");
        cardPanel04.putClientProperty(FlatClientProperties.STYLE, "arc:15");
        manageBloodBtn.putClientProperty(FlatClientProperties.STYLE, "arc:15");
        inventoryPanel.putClientProperty(FlatClientProperties.STYLE, "arc:15");
        urgentReqPanel.putClientProperty(FlatClientProperties.STYLE, "arc:15");

        FlatSVGIcon clockIcon = new FlatSVGIcon("com/dev/synapse/assets/clock-icon.svg", 30, 30);
        clockIcon.setColorFilter(new FlatSVGIcon.ColorFilter(color -> Color.red));
        iconLabel.putClientProperty(FlatClientProperties.STYLE, "arc:15");
        iconLabel.setIcon(clockIcon);
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        iconLabel.setVerticalAlignment(SwingConstants.CENTER);

        FlatSVGIcon peopleIcon = new FlatSVGIcon("com/dev/synapse/assets/people-icon.svg", 32, 32);
        peopleIcon.setColorFilter(new FlatSVGIcon.ColorFilter(color -> Color.red));
        iconLabel1.putClientProperty(FlatClientProperties.STYLE, "arc:15");
        iconLabel1.setIcon(peopleIcon);
        iconLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        iconLabel1.setVerticalAlignment(SwingConstants.CENTER);

        FlatSVGIcon heartIcon = new FlatSVGIcon("com/dev/synapse/assets/heart-icon.svg", 30, 30);
        heartIcon.setColorFilter(new FlatSVGIcon.ColorFilter(color -> Color.red));
        iconLabel2.putClientProperty(FlatClientProperties.STYLE, "arc:15");
        iconLabel2.setIcon(heartIcon);
        iconLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        iconLabel2.setVerticalAlignment(SwingConstants.CENTER);

        FlatSVGIcon warningIcon = new FlatSVGIcon("com/dev/synapse/assets/warning-icon.svg", 32, 32);
        warningIcon.setColorFilter(new FlatSVGIcon.ColorFilter(color -> Color.red));
        iconLabel3.putClientProperty(FlatClientProperties.STYLE, "arc:15");
        iconLabel3.setIcon(warningIcon);
        iconLabel3.setHorizontalAlignment(SwingConstants.CENTER);
        iconLabel3.setVerticalAlignment(SwingConstants.CENTER);

        FlatSVGIcon heartIcon2 = new FlatSVGIcon("com/dev/synapse/assets/people-icon.svg", 28, 28);
        heartIcon2.setColorFilter(new FlatSVGIcon.ColorFilter(color -> Color.red));
        recentBloodReqLabel.setIcon(heartIcon2);

        FlatSVGIcon peopleIcon2 = new FlatSVGIcon("com/dev/synapse/assets/heart-icon.svg", 28, 28);
        peopleIcon2.setColorFilter(new FlatSVGIcon.ColorFilter(color -> Color.red));
        urgentReq.setIcon(peopleIcon2);

        jScrollPane1.setViewportBorder(null);
        jScrollPane1.setBorder(BorderFactory.createEmptyBorder());
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(10);

        bloodTypeLabel.putClientProperty(FlatClientProperties.STYLE, "arc:999");
        bloodTypeLabel1.putClientProperty(FlatClientProperties.STYLE, "arc:999");
        bloodTypeLabel2.putClientProperty(FlatClientProperties.STYLE, "arc:999");
        bloodTypeLabel4.putClientProperty(FlatClientProperties.STYLE, "arc:999");
        bloodTypeLabel5.putClientProperty(FlatClientProperties.STYLE, "arc:999");
        bloodTypeLabel6.putClientProperty(FlatClientProperties.STYLE, "arc:999");
        bloodTypeLabel7.putClientProperty(FlatClientProperties.STYLE, "arc:999");
        bloodTypeLabel8.putClientProperty(FlatClientProperties.STYLE, "arc:999");

        reportPanel1.putClientProperty(FlatClientProperties.STYLE, "arc:15");
        reportPanel2.putClientProperty(FlatClientProperties.STYLE, "arc:15");

        stockReportBtn.putClientProperty(FlatClientProperties.STYLE, "arc:15");
        FlatSVGIcon eyeIcon = new FlatSVGIcon("com/dev/synapse/assets/eye-icon.svg", 20, 20);
        stockReportBtn.setIcon(eyeIcon);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        cardPanel01 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        totalBloodUnitsLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        iconLabel = new javax.swing.JLabel();
        cardPanel02 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        iconLabel1 = new javax.swing.JLabel();
        cardPanel03 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        iconLabel2 = new javax.swing.JLabel();
        cardPanel04 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        iconLabel3 = new javax.swing.JLabel();
        titlePanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        inventoryPanel = new javax.swing.JPanel();
        recentBloodReqLabel = new javax.swing.JLabel();
        manageBloodBtn = new javax.swing.JButton();
        inventoryContainer = new javax.swing.JPanel();
        panelA1 = new javax.swing.JPanel();
        jProgressBar1 = new javax.swing.JProgressBar();
        bloodTypeLabel = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        availableUnitsLabel = new javax.swing.JLabel();
        panelA2 = new javax.swing.JPanel();
        jProgressBar2 = new javax.swing.JProgressBar();
        bloodTypeLabel1 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        availableUnitsLabel1 = new javax.swing.JLabel();
        panelB1 = new javax.swing.JPanel();
        jProgressBar3 = new javax.swing.JProgressBar();
        bloodTypeLabel2 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        availableUnitsLabel2 = new javax.swing.JLabel();
        panelB2 = new javax.swing.JPanel();
        jProgressBar5 = new javax.swing.JProgressBar();
        bloodTypeLabel4 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        availableUnitsLabel4 = new javax.swing.JLabel();
        panelO1 = new javax.swing.JPanel();
        jProgressBar6 = new javax.swing.JProgressBar();
        bloodTypeLabel5 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        availableUnitsLabel5 = new javax.swing.JLabel();
        panelO2 = new javax.swing.JPanel();
        jProgressBar7 = new javax.swing.JProgressBar();
        bloodTypeLabel6 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        availableUnitsLabel6 = new javax.swing.JLabel();
        panelAB1 = new javax.swing.JPanel();
        jProgressBar8 = new javax.swing.JProgressBar();
        bloodTypeLabel7 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        availableUnitsLabel7 = new javax.swing.JLabel();
        panelAB2 = new javax.swing.JPanel();
        jProgressBar9 = new javax.swing.JProgressBar();
        bloodTypeLabel8 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        availableUnitsLabel8 = new javax.swing.JLabel();
        urgentReqPanel = new javax.swing.JPanel();
        urgentReq = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        requestContainer = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        reportPanel1 = new javax.swing.JPanel();
        stockReportBtn = new javax.swing.JButton();
        reportPanel2 = new javax.swing.JPanel();

        jPanel1.setBackground(new java.awt.Color(244, 244, 244));

        jPanel4.setBackground(new java.awt.Color(242, 242, 242));
        jPanel4.setLayout(new java.awt.GridLayout(1, 4, 15, 0));

        cardPanel01.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Total Blood Units");

        totalBloodUnitsLabel.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        totalBloodUnitsLabel.setForeground(new java.awt.Color(0, 0, 0));
        totalBloodUnitsLabel.setText("495");

        jLabel5.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("In Inventory");

        iconLabel.setBackground(new java.awt.Color(255, 214, 214));
        iconLabel.setOpaque(true);

        javax.swing.GroupLayout cardPanel01Layout = new javax.swing.GroupLayout(cardPanel01);
        cardPanel01.setLayout(cardPanel01Layout);
        cardPanel01Layout.setHorizontalGroup(
            cardPanel01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardPanel01Layout.createSequentialGroup()
                .addGroup(cardPanel01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cardPanel01Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel3))
                    .addGroup(cardPanel01Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(cardPanel01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(cardPanel01Layout.createSequentialGroup()
                                .addComponent(totalBloodUnitsLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 148, Short.MAX_VALUE)
                                .addComponent(iconLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(25, 25, 25))
        );
        cardPanel01Layout.setVerticalGroup(
            cardPanel01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardPanel01Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel3)
                .addGroup(cardPanel01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cardPanel01Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(iconLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(cardPanel01Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(totalBloodUnitsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)))
                .addContainerGap())
        );

        jPanel4.add(cardPanel01);

        cardPanel02.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Low Stocks");

        jLabel10.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("3");

        jLabel11.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("Needs now");

        iconLabel1.setBackground(new java.awt.Color(255, 214, 214));
        iconLabel1.setOpaque(true);

        javax.swing.GroupLayout cardPanel02Layout = new javax.swing.GroupLayout(cardPanel02);
        cardPanel02.setLayout(cardPanel02Layout);
        cardPanel02Layout.setHorizontalGroup(
            cardPanel02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardPanel02Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(cardPanel02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cardPanel02Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addContainerGap(199, Short.MAX_VALUE))
                    .addGroup(cardPanel02Layout.createSequentialGroup()
                        .addGroup(cardPanel02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(iconLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))))
        );
        cardPanel02Layout.setVerticalGroup(
            cardPanel02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardPanel02Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel9)
                .addGap(6, 6, 6)
                .addGroup(cardPanel02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cardPanel02Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel11))
                    .addComponent(iconLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel4.add(cardPanel02);

        cardPanel03.setBackground(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setText("Requests Received");

        jLabel13.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("24");

        jLabel14.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("This month");

        iconLabel2.setBackground(new java.awt.Color(255, 214, 214));
        iconLabel2.setOpaque(true);

        javax.swing.GroupLayout cardPanel03Layout = new javax.swing.GroupLayout(cardPanel03);
        cardPanel03.setLayout(cardPanel03Layout);
        cardPanel03Layout.setHorizontalGroup(
            cardPanel03Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardPanel03Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(cardPanel03Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(iconLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        cardPanel03Layout.setVerticalGroup(
            cardPanel03Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardPanel03Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cardPanel03Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cardPanel03Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14))
                    .addComponent(iconLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel4.add(cardPanel03);

        cardPanel04.setBackground(new java.awt.Color(255, 255, 255));
        cardPanel04.setPreferredSize(new java.awt.Dimension(213, 117));

        jLabel6.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Units Dispensed");

        jLabel7.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("142");

        jLabel8.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("This month");

        iconLabel3.setBackground(new java.awt.Color(255, 214, 214));
        iconLabel3.setOpaque(true);

        javax.swing.GroupLayout cardPanel04Layout = new javax.swing.GroupLayout(cardPanel04);
        cardPanel04.setLayout(cardPanel04Layout);
        cardPanel04Layout.setHorizontalGroup(
            cardPanel04Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardPanel04Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(cardPanel04Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cardPanel04Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addContainerGap(165, Short.MAX_VALUE))
                    .addGroup(cardPanel04Layout.createSequentialGroup()
                        .addGroup(cardPanel04Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(iconLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))))
        );
        cardPanel04Layout.setVerticalGroup(
            cardPanel04Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardPanel04Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cardPanel04Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cardPanel04Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8))
                    .addComponent(iconLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel4.add(cardPanel04);

        titlePanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Blood Bank Dashboard");

        jLabel2.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Manage inventory and respond to blood requests");

        javax.swing.GroupLayout titlePanelLayout = new javax.swing.GroupLayout(titlePanel);
        titlePanel.setLayout(titlePanelLayout);
        titlePanelLayout.setHorizontalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titlePanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        titlePanelLayout.setVerticalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titlePanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(0, 0, 0)
                .addComponent(jLabel2)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        inventoryPanel.setBackground(new java.awt.Color(255, 255, 255));

        recentBloodReqLabel.setFont(new java.awt.Font("Poppins SemiBold", 0, 20)); // NOI18N
        recentBloodReqLabel.setForeground(new java.awt.Color(0, 0, 0));
        recentBloodReqLabel.setText("Blood Inventory Overview");

        manageBloodBtn.setBackground(new java.awt.Color(244, 244, 244));
        manageBloodBtn.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        manageBloodBtn.setForeground(new java.awt.Color(102, 102, 102));
        manageBloodBtn.setText("Manage");
        manageBloodBtn.setBorder(null);
        manageBloodBtn.setBorderPainted(false);
        manageBloodBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageBloodBtnActionPerformed(evt);
            }
        });

        inventoryContainer.setBackground(new java.awt.Color(255, 255, 255));
        inventoryContainer.setLayout(new java.awt.GridLayout(2, 4, 20, 20));

        panelA1.setBackground(new java.awt.Color(255, 255, 255));
        panelA1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), null));
        panelA1.setPreferredSize(new java.awt.Dimension(285, 158));

        jProgressBar1.setBackground(new java.awt.Color(204, 204, 204));
        jProgressBar1.setForeground(new java.awt.Color(255, 0, 0));

        bloodTypeLabel.setBackground(new java.awt.Color(255, 204, 204));
        bloodTypeLabel.setFont(new java.awt.Font("Poppins SemiBold", 0, 14)); // NOI18N
        bloodTypeLabel.setForeground(new java.awt.Color(255, 0, 0));
        bloodTypeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bloodTypeLabel.setText("A+");
        bloodTypeLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bloodTypeLabel.setOpaque(true);

        jLabel15.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(153, 153, 153));
        jLabel15.setText("of 100 units");

        availableUnitsLabel.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        availableUnitsLabel.setForeground(new java.awt.Color(51, 51, 51));
        availableUnitsLabel.setText("85");

        javax.swing.GroupLayout panelA1Layout = new javax.swing.GroupLayout(panelA1);
        panelA1.setLayout(panelA1Layout);
        panelA1Layout.setHorizontalGroup(
            panelA1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelA1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panelA1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bloodTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(availableUnitsLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelA1Layout.setVerticalGroup(
            panelA1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelA1Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(bloodTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(availableUnitsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        inventoryContainer.add(panelA1);

        panelA2.setBackground(new java.awt.Color(255, 255, 255));
        panelA2.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), null));
        panelA2.setPreferredSize(new java.awt.Dimension(285, 158));

        jProgressBar2.setBackground(new java.awt.Color(204, 204, 204));
        jProgressBar2.setForeground(new java.awt.Color(255, 0, 0));

        bloodTypeLabel1.setBackground(new java.awt.Color(255, 204, 204));
        bloodTypeLabel1.setFont(new java.awt.Font("Poppins SemiBold", 0, 14)); // NOI18N
        bloodTypeLabel1.setForeground(new java.awt.Color(255, 0, 0));
        bloodTypeLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bloodTypeLabel1.setText("A-");
        bloodTypeLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bloodTypeLabel1.setOpaque(true);

        jLabel16.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(153, 153, 153));
        jLabel16.setText("of 100 units");

        availableUnitsLabel1.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        availableUnitsLabel1.setForeground(new java.awt.Color(51, 51, 51));
        availableUnitsLabel1.setText("85");

        javax.swing.GroupLayout panelA2Layout = new javax.swing.GroupLayout(panelA2);
        panelA2.setLayout(panelA2Layout);
        panelA2Layout.setHorizontalGroup(
            panelA2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelA2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panelA2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bloodTypeLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(availableUnitsLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelA2Layout.setVerticalGroup(
            panelA2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelA2Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(bloodTypeLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(availableUnitsLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jProgressBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        inventoryContainer.add(panelA2);

        panelB1.setBackground(new java.awt.Color(255, 255, 255));
        panelB1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), null));
        panelB1.setPreferredSize(new java.awt.Dimension(285, 158));

        jProgressBar3.setBackground(new java.awt.Color(204, 204, 204));
        jProgressBar3.setForeground(new java.awt.Color(255, 0, 0));

        bloodTypeLabel2.setBackground(new java.awt.Color(255, 204, 204));
        bloodTypeLabel2.setFont(new java.awt.Font("Poppins SemiBold", 0, 14)); // NOI18N
        bloodTypeLabel2.setForeground(new java.awt.Color(255, 0, 0));
        bloodTypeLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bloodTypeLabel2.setText("B+");
        bloodTypeLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bloodTypeLabel2.setOpaque(true);

        jLabel17.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(153, 153, 153));
        jLabel17.setText("of 100 units");

        availableUnitsLabel2.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        availableUnitsLabel2.setForeground(new java.awt.Color(51, 51, 51));
        availableUnitsLabel2.setText("85");

        javax.swing.GroupLayout panelB1Layout = new javax.swing.GroupLayout(panelB1);
        panelB1.setLayout(panelB1Layout);
        panelB1Layout.setHorizontalGroup(
            panelB1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelB1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panelB1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bloodTypeLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(availableUnitsLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelB1Layout.setVerticalGroup(
            panelB1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelB1Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(bloodTypeLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(availableUnitsLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jProgressBar3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        inventoryContainer.add(panelB1);

        panelB2.setBackground(new java.awt.Color(255, 255, 255));
        panelB2.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), null));
        panelB2.setPreferredSize(new java.awt.Dimension(285, 158));

        jProgressBar5.setBackground(new java.awt.Color(204, 204, 204));
        jProgressBar5.setForeground(new java.awt.Color(255, 0, 0));

        bloodTypeLabel4.setBackground(new java.awt.Color(255, 204, 204));
        bloodTypeLabel4.setFont(new java.awt.Font("Poppins SemiBold", 0, 14)); // NOI18N
        bloodTypeLabel4.setForeground(new java.awt.Color(255, 0, 0));
        bloodTypeLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bloodTypeLabel4.setText("B-");
        bloodTypeLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bloodTypeLabel4.setOpaque(true);

        jLabel19.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(153, 153, 153));
        jLabel19.setText("of 100 units");

        availableUnitsLabel4.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        availableUnitsLabel4.setForeground(new java.awt.Color(51, 51, 51));
        availableUnitsLabel4.setText("85");

        javax.swing.GroupLayout panelB2Layout = new javax.swing.GroupLayout(panelB2);
        panelB2.setLayout(panelB2Layout);
        panelB2Layout.setHorizontalGroup(
            panelB2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelB2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panelB2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bloodTypeLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(availableUnitsLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelB2Layout.setVerticalGroup(
            panelB2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelB2Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(bloodTypeLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(availableUnitsLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jProgressBar5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        inventoryContainer.add(panelB2);

        panelO1.setBackground(new java.awt.Color(255, 255, 255));
        panelO1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), null));
        panelO1.setPreferredSize(new java.awt.Dimension(285, 158));

        jProgressBar6.setBackground(new java.awt.Color(204, 204, 204));
        jProgressBar6.setForeground(new java.awt.Color(255, 0, 0));

        bloodTypeLabel5.setBackground(new java.awt.Color(255, 204, 204));
        bloodTypeLabel5.setFont(new java.awt.Font("Poppins SemiBold", 0, 14)); // NOI18N
        bloodTypeLabel5.setForeground(new java.awt.Color(255, 0, 0));
        bloodTypeLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bloodTypeLabel5.setText("O+");
        bloodTypeLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bloodTypeLabel5.setOpaque(true);

        jLabel20.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(153, 153, 153));
        jLabel20.setText("of 100 units");

        availableUnitsLabel5.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        availableUnitsLabel5.setForeground(new java.awt.Color(51, 51, 51));
        availableUnitsLabel5.setText("85");

        javax.swing.GroupLayout panelO1Layout = new javax.swing.GroupLayout(panelO1);
        panelO1.setLayout(panelO1Layout);
        panelO1Layout.setHorizontalGroup(
            panelO1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelO1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panelO1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bloodTypeLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(availableUnitsLabel5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelO1Layout.setVerticalGroup(
            panelO1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelO1Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(bloodTypeLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(availableUnitsLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jProgressBar6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        inventoryContainer.add(panelO1);

        panelO2.setBackground(new java.awt.Color(255, 255, 255));
        panelO2.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), null));
        panelO2.setPreferredSize(new java.awt.Dimension(285, 158));

        jProgressBar7.setBackground(new java.awt.Color(204, 204, 204));
        jProgressBar7.setForeground(new java.awt.Color(255, 0, 0));

        bloodTypeLabel6.setBackground(new java.awt.Color(255, 204, 204));
        bloodTypeLabel6.setFont(new java.awt.Font("Poppins SemiBold", 0, 14)); // NOI18N
        bloodTypeLabel6.setForeground(new java.awt.Color(255, 0, 0));
        bloodTypeLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bloodTypeLabel6.setText("O-");
        bloodTypeLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bloodTypeLabel6.setOpaque(true);

        jLabel21.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(153, 153, 153));
        jLabel21.setText("of 100 units");

        availableUnitsLabel6.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        availableUnitsLabel6.setForeground(new java.awt.Color(51, 51, 51));
        availableUnitsLabel6.setText("85");

        javax.swing.GroupLayout panelO2Layout = new javax.swing.GroupLayout(panelO2);
        panelO2.setLayout(panelO2Layout);
        panelO2Layout.setHorizontalGroup(
            panelO2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelO2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panelO2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bloodTypeLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(availableUnitsLabel6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelO2Layout.setVerticalGroup(
            panelO2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelO2Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(bloodTypeLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(availableUnitsLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jProgressBar7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        inventoryContainer.add(panelO2);

        panelAB1.setBackground(new java.awt.Color(255, 255, 255));
        panelAB1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), null));
        panelAB1.setPreferredSize(new java.awt.Dimension(285, 158));

        jProgressBar8.setBackground(new java.awt.Color(204, 204, 204));
        jProgressBar8.setForeground(new java.awt.Color(255, 0, 0));

        bloodTypeLabel7.setBackground(new java.awt.Color(255, 204, 204));
        bloodTypeLabel7.setFont(new java.awt.Font("Poppins SemiBold", 0, 14)); // NOI18N
        bloodTypeLabel7.setForeground(new java.awt.Color(255, 0, 0));
        bloodTypeLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bloodTypeLabel7.setText("AB+");
        bloodTypeLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bloodTypeLabel7.setOpaque(true);

        jLabel22.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(153, 153, 153));
        jLabel22.setText("of 100 units");

        availableUnitsLabel7.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        availableUnitsLabel7.setForeground(new java.awt.Color(51, 51, 51));
        availableUnitsLabel7.setText("85");

        javax.swing.GroupLayout panelAB1Layout = new javax.swing.GroupLayout(panelAB1);
        panelAB1.setLayout(panelAB1Layout);
        panelAB1Layout.setHorizontalGroup(
            panelAB1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAB1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panelAB1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bloodTypeLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(availableUnitsLabel7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelAB1Layout.setVerticalGroup(
            panelAB1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAB1Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(bloodTypeLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(availableUnitsLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jProgressBar8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        inventoryContainer.add(panelAB1);

        panelAB2.setBackground(new java.awt.Color(255, 255, 255));
        panelAB2.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), null));
        panelAB2.setPreferredSize(new java.awt.Dimension(285, 158));

        jProgressBar9.setBackground(new java.awt.Color(204, 204, 204));
        jProgressBar9.setForeground(new java.awt.Color(255, 0, 0));

        bloodTypeLabel8.setBackground(new java.awt.Color(255, 204, 204));
        bloodTypeLabel8.setFont(new java.awt.Font("Poppins SemiBold", 0, 14)); // NOI18N
        bloodTypeLabel8.setForeground(new java.awt.Color(255, 0, 0));
        bloodTypeLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bloodTypeLabel8.setText("AB-");
        bloodTypeLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bloodTypeLabel8.setOpaque(true);

        jLabel23.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(153, 153, 153));
        jLabel23.setText("of 100 units");

        availableUnitsLabel8.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        availableUnitsLabel8.setForeground(new java.awt.Color(51, 51, 51));
        availableUnitsLabel8.setText("85");

        javax.swing.GroupLayout panelAB2Layout = new javax.swing.GroupLayout(panelAB2);
        panelAB2.setLayout(panelAB2Layout);
        panelAB2Layout.setHorizontalGroup(
            panelAB2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAB2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panelAB2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bloodTypeLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(availableUnitsLabel8))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelAB2Layout.setVerticalGroup(
            panelAB2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAB2Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(bloodTypeLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(availableUnitsLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jProgressBar9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        inventoryContainer.add(panelAB2);

        javax.swing.GroupLayout inventoryPanelLayout = new javax.swing.GroupLayout(inventoryPanel);
        inventoryPanel.setLayout(inventoryPanelLayout);
        inventoryPanelLayout.setHorizontalGroup(
            inventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inventoryPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(inventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(inventoryPanelLayout.createSequentialGroup()
                        .addComponent(inventoryContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(22, 22, 22))
                    .addGroup(inventoryPanelLayout.createSequentialGroup()
                        .addComponent(recentBloodReqLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(manageBloodBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))))
        );
        inventoryPanelLayout.setVerticalGroup(
            inventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inventoryPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(inventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(recentBloodReqLabel)
                    .addComponent(manageBloodBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(inventoryContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        urgentReqPanel.setBackground(new java.awt.Color(255, 255, 255));
        urgentReqPanel.setPreferredSize(new java.awt.Dimension(213, 117));

        urgentReq.setFont(new java.awt.Font("Poppins SemiBold", 0, 20)); // NOI18N
        urgentReq.setForeground(new java.awt.Color(0, 0, 0));
        urgentReq.setText("Urgent Requests");

        requestContainer.setBackground(new java.awt.Color(255, 255, 255));
        requestContainer.setLayout(new javax.swing.BoxLayout(requestContainer, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(requestContainer);

        javax.swing.GroupLayout urgentReqPanelLayout = new javax.swing.GroupLayout(urgentReqPanel);
        urgentReqPanel.setLayout(urgentReqPanelLayout);
        urgentReqPanelLayout.setHorizontalGroup(
            urgentReqPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(urgentReqPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(urgentReqPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(urgentReq)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        urgentReqPanelLayout.setVerticalGroup(
            urgentReqPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(urgentReqPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(urgentReq)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(141, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(244, 244, 244));
        jPanel5.setLayout(new java.awt.GridLayout(1, 2, 20, 0));

        reportPanel1.setBackground(new java.awt.Color(255, 255, 255));

        stockReportBtn.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        stockReportBtn.setText("View Monthly Stock Report");
        stockReportBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockReportBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout reportPanel1Layout = new javax.swing.GroupLayout(reportPanel1);
        reportPanel1.setLayout(reportPanel1Layout);
        reportPanel1Layout.setHorizontalGroup(
            reportPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reportPanel1Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(stockReportBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                .addGap(56, 56, 56))
        );
        reportPanel1Layout.setVerticalGroup(
            reportPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reportPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(stockReportBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel5.add(reportPanel1);

        reportPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout reportPanel2Layout = new javax.swing.GroupLayout(reportPanel2);
        reportPanel2.setLayout(reportPanel2Layout);
        reportPanel2Layout.setHorizontalGroup(
            reportPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
        );
        reportPanel2Layout.setVerticalGroup(
            reportPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel5.add(reportPanel2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inventoryPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(urgentReqPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(titlePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(23, 23, 23))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(inventoryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(urgentReqPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(137, 137, 137))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void manageBloodBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageBloodBtnActionPerformed
        ViewBloodRequests viewBloodReq = new ViewBloodRequests(homeScreen, true);
        viewBloodReq.setLocationRelativeTo(this);
        viewBloodReq.setVisible(true);
    }//GEN-LAST:event_manageBloodBtnActionPerformed

    private void stockReportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockReportBtnActionPerformed
        
        String sample = "Hemas pvt. ltd.";
        
        try {
            InputStream filePath = getClass().getClassLoader().getResourceAsStream("com/dev/synapse/report/synapse_inventory_report.jasper");
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("Institute_Name", sample);
            Connection connection = MySQL.getConnection();
            JasperPrint fillReport = JasperFillManager.fillReport(filePath, parameters, connection);
            JasperViewer.viewReport(fillReport, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_stockReportBtnActionPerformed
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel availableUnitsLabel;
    private javax.swing.JLabel availableUnitsLabel1;
    private javax.swing.JLabel availableUnitsLabel2;
    private javax.swing.JLabel availableUnitsLabel3;
    private javax.swing.JLabel availableUnitsLabel4;
    private javax.swing.JLabel availableUnitsLabel5;
    private javax.swing.JLabel availableUnitsLabel6;
    private javax.swing.JLabel availableUnitsLabel7;
    private javax.swing.JLabel availableUnitsLabel8;
    private javax.swing.JLabel bloodTypeLabel;
    private javax.swing.JLabel bloodTypeLabel1;
    private javax.swing.JLabel bloodTypeLabel2;
    private javax.swing.JLabel bloodTypeLabel3;
    private javax.swing.JLabel bloodTypeLabel4;
    private javax.swing.JLabel bloodTypeLabel5;
    private javax.swing.JLabel bloodTypeLabel6;
    private javax.swing.JLabel bloodTypeLabel7;
    private javax.swing.JLabel bloodTypeLabel8;
    private javax.swing.JPanel cardPanel01;
    private javax.swing.JPanel cardPanel02;
    private javax.swing.JPanel cardPanel03;
    private javax.swing.JPanel cardPanel04;
    private javax.swing.JLabel iconLabel;
    private javax.swing.JLabel iconLabel1;
    private javax.swing.JLabel iconLabel2;
    private javax.swing.JLabel iconLabel3;
    private javax.swing.JPanel inventoryContainer;
    private javax.swing.JPanel inventoryPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JProgressBar jProgressBar2;
    private javax.swing.JProgressBar jProgressBar3;
    private javax.swing.JProgressBar jProgressBar4;
    private javax.swing.JProgressBar jProgressBar5;
    private javax.swing.JProgressBar jProgressBar6;
    private javax.swing.JProgressBar jProgressBar7;
    private javax.swing.JProgressBar jProgressBar8;
    private javax.swing.JProgressBar jProgressBar9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton manageBloodBtn;
    private javax.swing.JPanel panelA1;
    private javax.swing.JPanel panelA2;
    private javax.swing.JPanel panelAB1;
    private javax.swing.JPanel panelAB2;
    private javax.swing.JPanel panelB1;
    private javax.swing.JPanel panelB2;
    private javax.swing.JPanel panelO1;
    private javax.swing.JPanel panelO2;
    private javax.swing.JLabel recentBloodReqLabel;
    private javax.swing.JPanel reportPanel1;
    private javax.swing.JPanel reportPanel2;
    private javax.swing.JPanel requestContainer;
    private javax.swing.JButton stockReportBtn;
    private javax.swing.JPanel titlePanel;
    private javax.swing.JLabel totalBloodUnitsLabel;
    private javax.swing.JLabel urgentReq;
    private javax.swing.JPanel urgentReqPanel;
    // End of variables declaration//GEN-END:variables
}
