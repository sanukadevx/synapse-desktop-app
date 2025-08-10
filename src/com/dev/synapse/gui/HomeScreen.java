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
        logo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        userProfilePanel = new javax.swing.JPanel();
        profileLogo = new javax.swing.JLabel();
        homeUserName = new javax.swing.JLabel();
        homeUserRole = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        contentPanel = new javax.swing.JPanel();
        logoutBtn = new javax.swing.JButton();

        label1.setText("label1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        logo.setOpaque(true);

        jLabel2.setFont(new java.awt.Font("Poppins", 1, 19)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Synapse");

        jLabel3.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        jLabel3.setText("Blood Donation Management");

        userProfilePanel.setBackground(new java.awt.Color(240, 240, 240));
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

        jSeparator1.setForeground(new java.awt.Color(200, 200, 200));

        jScrollPane1.setBorder(null);

        contentPanel.setBackground(new java.awt.Color(204, 204, 204));
        contentPanel.setLayout(new java.awt.CardLayout());
        jScrollPane1.setViewportView(contentPanel);

        logoutBtn.setBackground(new java.awt.Color(255, 255, 255));
        logoutBtn.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        logoutBtn.setForeground(new java.awt.Color(102, 102, 102));
        logoutBtn.setText("Logout");
        logoutBtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        logoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(147, 147, 147)
                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 291, Short.MAX_VALUE)
                .addComponent(userProfilePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(logoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(168, 168, 168))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
            .addComponent(jScrollPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel3))
                            .addComponent(logo, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                            .addComponent(userProfilePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(logoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
    private javax.swing.JPanel userProfilePanel;
    // End of variables declaration//GEN-END:variables
}
