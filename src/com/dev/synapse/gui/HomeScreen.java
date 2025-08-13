package com.dev.synapse.gui;

import com.dev.synapse.dialogs.UserProfile;
import com.dev.synapse.panel.AdminPanel;
import com.dev.synapse.panel.BankPanel;
import com.dev.synapse.panel.HospitalPanel;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class HomeScreen extends javax.swing.JFrame {

    private HomeScreen homeScreen;

    private CardLayout contentPanelLayout;

    private AdminPanel adminPanel;
    private HospitalPanel hospitalPanel;
    private BankPanel bankPanel;

    public HomeScreen() {
        initComponents();
        init();
        styles();
        loadPanels();
        loadAdminPanel();
    }

    private void styles() {
        userProfilePanel.putClientProperty(FlatClientProperties.STYLE, "arc:15");
        logoutBtn.putClientProperty(FlatClientProperties.STYLE, "arc:15");
        logo.putClientProperty(FlatClientProperties.STYLE, "arc:15");
        logo.setIcon(new FlatSVGIcon("com/dev/synapse/assets/synapse-logo.svg", logo.getWidth(), logo.getHeight()));
        profileLogo.setIcon(new FlatSVGIcon("com/dev/synapse/assets/user-profile.svg", profileLogo.getWidth(), profileLogo.getHeight()));
    }

    private void init() {

    }
    
    private void loadPanels() {
        if (contentPanelLayout == null && contentPanel.getLayout() instanceof CardLayout) {
            this.contentPanelLayout = (CardLayout) contentPanel.getLayout();
        }
    }

    private void loadAdminPanel() {
        this.adminPanel = new AdminPanel();

        this.adminPanel.putClientProperty(FlatClientProperties.STYLE, "arc:20");

        this.contentPanel.add(adminPanel, "admin_dashboard_panel");
        SwingUtilities.updateComponentTreeUI(contentPanel);

        this.contentPanelLayout.show(contentPanel, "admin_dashboard_panel");
    }

    private void loadHospitalPanel() {
        this.hospitalPanel = new HospitalPanel();

        this.hospitalPanel.putClientProperty(FlatClientProperties.STYLE, "arc:20");

        this.contentPanel.add(hospitalPanel, "hospital_panel");
        SwingUtilities.updateComponentTreeUI(contentPanel);

        this.contentPanelLayout.show(contentPanel, "hospital_panel");
    }

    private void loadBankPanel() {
        this.bankPanel = new BankPanel();

        this.bankPanel.putClientProperty(FlatClientProperties.STYLE, "arc:20");

        this.contentPanel.add(bankPanel, "bank_panel");
        SwingUtilities.updateComponentTreeUI(contentPanel);

        this.contentPanelLayout.show(contentPanel, "bank_panel");
    }

//    private void loadUserProfile() {
//        try {
//            ResultSet rs1 = MySQL.executeSearch("SELECT `username` FROM `users`");
//            ResultSet rs2 = MySQL.executeSearch("SELECT `role_id` FROM `user_assignments`");
//            
//            homeUserName.setText(rs1.toString());
//            
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label1 = new java.awt.Label();
        jPanel2 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        contentPanel = new javax.swing.JPanel();
        subHeaderPanel = new javax.swing.JPanel();
        userProfilePanel = new javax.swing.JPanel();
        profileLogo = new javax.swing.JLabel();
        homeUserName = new javax.swing.JLabel();
        homeUserRole = new javax.swing.JLabel();
        logoutBtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        logo = new javax.swing.JLabel();

        label1.setText("label1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jSeparator1.setForeground(new java.awt.Color(200, 200, 200));

        jScrollPane1.setBorder(null);

        contentPanel.setBackground(new java.awt.Color(244, 244, 244));
        contentPanel.setLayout(new java.awt.CardLayout());
        jScrollPane1.setViewportView(contentPanel);

        subHeaderPanel.setOpaque(false);

        userProfilePanel.setBackground(new java.awt.Color(244, 244, 244));
        userProfilePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                userProfilePanelMouseClicked(evt);
            }
        });

        profileLogo.setOpaque(true);

        homeUserName.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        homeUserName.setForeground(new java.awt.Color(51, 51, 51));
        homeUserName.setText("User Name");

        homeUserRole.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        homeUserRole.setForeground(new java.awt.Color(153, 153, 153));
        homeUserRole.setText("User Role");

        javax.swing.GroupLayout userProfilePanelLayout = new javax.swing.GroupLayout(userProfilePanel);
        userProfilePanel.setLayout(userProfilePanelLayout);
        userProfilePanelLayout.setHorizontalGroup(
            userProfilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userProfilePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(profileLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(userProfilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(homeUserName)
                    .addComponent(homeUserRole))
                .addContainerGap(95, Short.MAX_VALUE))
        );
        userProfilePanelLayout.setVerticalGroup(
            userProfilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userProfilePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(userProfilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(userProfilePanelLayout.createSequentialGroup()
                        .addComponent(homeUserName)
                        .addGap(0, 0, 0)
                        .addComponent(homeUserRole)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(profileLogo, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
                .addContainerGap())
        );

        logoutBtn.setBackground(new java.awt.Color(244, 244, 244));
        logoutBtn.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        logoutBtn.setForeground(new java.awt.Color(102, 102, 102));
        logoutBtn.setText("Logout");
        logoutBtn.setBorder(null);
        logoutBtn.setBorderPainted(false);
        logoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtnActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        jLabel3.setText("Blood Donation Management");

        jLabel2.setFont(new java.awt.Font("Poppins", 1, 19)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Synapse");

        logo.setOpaque(true);

        javax.swing.GroupLayout subHeaderPanelLayout = new javax.swing.GroupLayout(subHeaderPanel);
        subHeaderPanel.setLayout(subHeaderPanelLayout);
        subHeaderPanelLayout.setHorizontalGroup(
            subHeaderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subHeaderPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(subHeaderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 354, Short.MAX_VALUE)
                .addComponent(userProfilePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(logoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        subHeaderPanelLayout.setVerticalGroup(
            subHeaderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subHeaderPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(subHeaderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(subHeaderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(subHeaderPanelLayout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(jLabel3))
                        .addComponent(logo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(userProfilePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(subHeaderPanelLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(logoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(148, 148, 148)
                .addComponent(subHeaderPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(158, 158, 158))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(subHeaderPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 634, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }//GEN-LAST:event_formWindowOpened

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed
        new LoginScreen().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_logoutBtnActionPerformed

    private void userProfilePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userProfilePanelMouseClicked
        UserProfile userProfile = new UserProfile(homeScreen, true);
        userProfile.setLocationRelativeTo(this);
        userProfile.setVisible(true);
    }//GEN-LAST:event_userProfilePanelMouseClicked

    public AdminPanel getAdminPanel() {
        loadAdminPanel();
        return adminPanel;
    }

    public HospitalPanel getHospitalPanel() {
        loadHospitalPanel();
        return hospitalPanel;
    }

    public BankPanel getBankPanel() {
        loadBankPanel();
        return bankPanel;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        FlatIntelliJLaf.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel contentPanel;
    private javax.swing.JLabel homeUserName;
    private javax.swing.JLabel homeUserRole;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private java.awt.Label label1;
    private javax.swing.JLabel logo;
    private javax.swing.JButton logoutBtn;
    private javax.swing.JLabel profileLogo;
    private javax.swing.JPanel subHeaderPanel;
    private javax.swing.JPanel userProfilePanel;
    // End of variables declaration//GEN-END:variables
}
