package com.dev.synapse.panel;

import com.dev.synapse.classes.BloodRequest;
import com.dev.synapse.connection.MySQL;
import com.dev.synapse.dialogs.CreateBloodRequest;
import com.dev.synapse.dialogs.ViewBloodRequests;
import com.dev.synapse.gui.HomeScreen;
import com.dev.synapse.subpanels.BloodRequestSubPanel;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.SwingConstants;

public class HospitalPanel extends javax.swing.JPanel {

    private HomeScreen homeScreen;

    public HospitalPanel() {
        initComponents();
        styles();
        loadRequests();
    }

    public void loadRequests() {
        requestContainer.removeAll();

        try {
            ResultSet rs = MySQL.executeSearch("SELECT * "
                    + "FROM blood_requests a "
                    + "INNER JOIN blood_types p ON a.blood_type_id = p.blood_type_id "
                    + "INNER JOIN urgency_level ad ON a.urgency_level_id = ad.urgency_level_id "
                    + "INNER JOIN institutions at ON a.requesting_hospital_id= at.institution_id "
                    + "INNER JOIN request_status ast ON a.request_status_id = ast.request_status_id "
            );

            while (rs.next()) {
                BloodRequest bloodRequest = new BloodRequest(
                        rs.getString("blood_type"),
                        rs.getString("status"),
                        rs.getString("urgency_level"),
                        rs.getString("request_id"),
                        rs.getString("units_required"),
                        LocalDate.now()
                );

                BloodRequestSubPanel bloodRequestPanel = new BloodRequestSubPanel(bloodRequest, this);
                bloodRequestPanel.populateData();

                // Set alignment for each panel
                bloodRequestPanel.setAlignmentX(bloodRequestPanel.LEFT_ALIGNMENT);
                bloodRequestPanel.setAlignmentY(bloodRequestPanel.TOP_ALIGNMENT);

                requestContainer.add(bloodRequestPanel);
                requestContainer.add(Box.createVerticalStrut(5));
            }

            // CRUCIAL: Add glue at the end to push everything to top
            requestContainer.add(Box.createVerticalGlue());

        } catch (SQLException ex) {
            Logger.getLogger(BloodRequest.class.getName()).log(Level.SEVERE, null, ex);
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
        mainPanel01.putClientProperty(FlatClientProperties.STYLE, "arc:15");
        quickActionPanel.putClientProperty(FlatClientProperties.STYLE, "arc:15");
        emergencyNoticesPanel.putClientProperty(FlatClientProperties.STYLE, "arc:15");

        FlatSVGIcon clockIcon = new FlatSVGIcon("com/dev/synapse/assets/heart-icon.svg", 30, 30);
        clockIcon.setColorFilter(new FlatSVGIcon.ColorFilter(color -> Color.red));
        iconLabel.putClientProperty(FlatClientProperties.STYLE, "arc:15");
        iconLabel.setIcon(clockIcon);
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        iconLabel.setVerticalAlignment(SwingConstants.CENTER);

        FlatSVGIcon peopleIcon = new FlatSVGIcon("com/dev/synapse/assets/clock-icon.svg", 30, 30);
        peopleIcon.setColorFilter(new FlatSVGIcon.ColorFilter(color -> Color.red));
        iconLabel1.putClientProperty(FlatClientProperties.STYLE, "arc:15");
        iconLabel1.setIcon(peopleIcon);
        iconLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        iconLabel1.setVerticalAlignment(SwingConstants.CENTER);

        FlatSVGIcon heartIcon = new FlatSVGIcon("com/dev/synapse/assets/verify-icon.svg", 28, 28);
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

        FlatSVGIcon heartIcon2 = new FlatSVGIcon("com/dev/synapse/assets/heart-icon.svg", 28, 28);
        heartIcon2.setColorFilter(new FlatSVGIcon.ColorFilter(color -> Color.red));
        recentBloodReqLabel.setIcon(heartIcon2);

        newRequestBtn.putClientProperty(FlatClientProperties.STYLE, "arc:15");
        requestHistoryBtn.putClientProperty(FlatClientProperties.STYLE, "arc:15");

        jScrollPane1.setViewportBorder(null);
        jScrollPane1.setBorder(BorderFactory.createEmptyBorder());

        jScrollPane1.getVerticalScrollBar().setUnitIncrement(10);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        cardPanel01 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
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
        jPanel5 = new javax.swing.JPanel();
        mainPanel01 = new javax.swing.JPanel();
        recentBloodReqLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        requestContainer = new javax.swing.JPanel();
        refreshBtn = new javax.swing.JButton();
        titlePanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        quickActionPanel = new javax.swing.JPanel();
        recentReqLabel1 = new javax.swing.JLabel();
        newRequestBtn = new javax.swing.JButton();
        requestHistoryBtn = new javax.swing.JButton();
        emergencyNoticesPanel = new javax.swing.JPanel();
        recentReqLabel2 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(244, 244, 244));

        jPanel4.setBackground(new java.awt.Color(242, 242, 242));
        jPanel4.setLayout(new java.awt.GridLayout(1, 4, 15, 0));

        cardPanel01.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Total Requests");

        jLabel4.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("18");

        jLabel5.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("All Time");

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
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 121, Short.MAX_VALUE)
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
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)))
                .addContainerGap())
        );

        jPanel4.add(cardPanel01);

        cardPanel02.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Pending Requests");

        jLabel10.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("3");

        jLabel11.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("Awaiting Response");

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
                        .addContainerGap(101, Short.MAX_VALUE))
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
        jLabel12.setText("Fullfilled Requests");

        jLabel13.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("24");

        jLabel14.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("Successfull");

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
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
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
        jLabel6.setText("Response Rate ");

        jLabel7.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("89%");

        jLabel8.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Avg. Fullfillment");

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
                        .addContainerGap(117, Short.MAX_VALUE))
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

        jPanel5.setBackground(new java.awt.Color(242, 242, 242));

        mainPanel01.setBackground(new java.awt.Color(255, 255, 255));

        recentBloodReqLabel.setFont(new java.awt.Font("Poppins SemiBold", 0, 20)); // NOI18N
        recentBloodReqLabel.setForeground(new java.awt.Color(0, 0, 0));
        recentBloodReqLabel.setText("Recent Blood Requests");

        requestContainer.setBackground(new java.awt.Color(255, 255, 255));
        requestContainer.setLayout(new javax.swing.BoxLayout(requestContainer, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(requestContainer);

        refreshBtn.setBackground(new java.awt.Color(244, 244, 244));
        refreshBtn.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        refreshBtn.setForeground(new java.awt.Color(102, 102, 102));
        refreshBtn.setText("Refresh");
        refreshBtn.setBorder(null);
        refreshBtn.setBorderPainted(false);
        refreshBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainPanel01Layout = new javax.swing.GroupLayout(mainPanel01);
        mainPanel01.setLayout(mainPanel01Layout);
        mainPanel01Layout.setHorizontalGroup(
            mainPanel01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanel01Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(mainPanel01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(mainPanel01Layout.createSequentialGroup()
                        .addComponent(recentBloodReqLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(refreshBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16))
        );
        mainPanel01Layout.setVerticalGroup(
            mainPanel01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanel01Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(mainPanel01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(recentBloodReqLabel)
                    .addComponent(refreshBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel01, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel01, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        titlePanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Hospital Dashboard");

        jLabel2.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Manage tyour Blood requests and view request history");

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

        quickActionPanel.setBackground(new java.awt.Color(255, 255, 255));
        quickActionPanel.setPreferredSize(new java.awt.Dimension(213, 117));

        recentReqLabel1.setFont(new java.awt.Font("Poppins SemiBold", 0, 20)); // NOI18N
        recentReqLabel1.setForeground(new java.awt.Color(0, 0, 0));
        recentReqLabel1.setText("Quick Actions");

        newRequestBtn.setBackground(new java.awt.Color(204, 0, 0));
        newRequestBtn.setFont(new java.awt.Font("Poppins Medium", 0, 15)); // NOI18N
        newRequestBtn.setForeground(new java.awt.Color(255, 255, 255));
        newRequestBtn.setText("Create New Request");
        newRequestBtn.setBorder(null);
        newRequestBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newRequestBtnActionPerformed(evt);
            }
        });

        requestHistoryBtn.setBackground(new java.awt.Color(244, 244, 244));
        requestHistoryBtn.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        requestHistoryBtn.setForeground(new java.awt.Color(51, 51, 51));
        requestHistoryBtn.setText("View Request History");
        requestHistoryBtn.setBorder(null);
        requestHistoryBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requestHistoryBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout quickActionPanelLayout = new javax.swing.GroupLayout(quickActionPanel);
        quickActionPanel.setLayout(quickActionPanelLayout);
        quickActionPanelLayout.setHorizontalGroup(
            quickActionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(quickActionPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(quickActionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(recentReqLabel1)
                    .addComponent(newRequestBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
                    .addComponent(requestHistoryBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        quickActionPanelLayout.setVerticalGroup(
            quickActionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(quickActionPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(recentReqLabel1)
                .addGap(18, 18, 18)
                .addComponent(newRequestBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(requestHistoryBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        emergencyNoticesPanel.setBackground(new java.awt.Color(255, 204, 204));
        emergencyNoticesPanel.setPreferredSize(new java.awt.Dimension(213, 117));

        recentReqLabel2.setFont(new java.awt.Font("Poppins SemiBold", 0, 20)); // NOI18N
        recentReqLabel2.setForeground(new java.awt.Color(255, 0, 0));
        recentReqLabel2.setText("Emergency Guidelines");

        javax.swing.GroupLayout emergencyNoticesPanelLayout = new javax.swing.GroupLayout(emergencyNoticesPanel);
        emergencyNoticesPanel.setLayout(emergencyNoticesPanelLayout);
        emergencyNoticesPanelLayout.setHorizontalGroup(
            emergencyNoticesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(emergencyNoticesPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(recentReqLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        emergencyNoticesPanelLayout.setVerticalGroup(
            emergencyNoticesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(emergencyNoticesPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(recentReqLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(titlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1011, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(quickActionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
                            .addComponent(emergencyNoticesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE))))
                .addGap(23, 23, 23))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(quickActionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(emergencyNoticesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(137, 137, 137)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(137, 137, 137))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void newRequestBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newRequestBtnActionPerformed
        CreateBloodRequest createBloodRequest = new CreateBloodRequest(homeScreen, true);
        createBloodRequest.setLocationRelativeTo(this);
        createBloodRequest.setVisible(true);
    }//GEN-LAST:event_newRequestBtnActionPerformed

    private void refreshBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshBtnActionPerformed
        loadRequests();
    }//GEN-LAST:event_refreshBtnActionPerformed

    private void requestHistoryBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requestHistoryBtnActionPerformed
        ViewBloodRequests viewBloodReq = new ViewBloodRequests(homeScreen, true);
        viewBloodReq.setLocationRelativeTo(this);
        viewBloodReq.setVisible(true);
    }//GEN-LAST:event_requestHistoryBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel cardPanel01;
    private javax.swing.JPanel cardPanel02;
    private javax.swing.JPanel cardPanel03;
    private javax.swing.JPanel cardPanel04;
    private javax.swing.JPanel emergencyNoticesPanel;
    private javax.swing.JLabel iconLabel;
    private javax.swing.JLabel iconLabel1;
    private javax.swing.JLabel iconLabel2;
    private javax.swing.JLabel iconLabel3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mainPanel01;
    private javax.swing.JButton newRequestBtn;
    private javax.swing.JPanel quickActionPanel;
    private javax.swing.JLabel recentBloodReqLabel;
    private javax.swing.JLabel recentReqLabel1;
    private javax.swing.JLabel recentReqLabel2;
    private javax.swing.JButton refreshBtn;
    private javax.swing.JPanel requestContainer;
    private javax.swing.JButton requestHistoryBtn;
    private javax.swing.JPanel titlePanel;
    // End of variables declaration//GEN-END:variables
}
