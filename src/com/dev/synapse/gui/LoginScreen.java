package com.dev.synapse.gui;

import com.dev.synapse.connection.MySQL;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class LoginScreen extends javax.swing.JFrame {

    private final HomeScreen homeScreen = new HomeScreen();

    private Properties props;
    private final String FILE_NAME = "credentials.properties";

    public static int institutionId;

    public LoginScreen() {
        initComponents();
        styles();
        loadCredentials();
    }

    private void loadCredentials() {
        props = new Properties();
        try (FileInputStream in = new FileInputStream(FILE_NAME)) {
            props.load(in);
            userInput.setText(props.getProperty("username", ""));
            passwordInput.setText(props.getProperty("password", ""));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveCredentials(String username, String password) {
        try (FileOutputStream out = new FileOutputStream(FILE_NAME)) {
            props.setProperty("username", username);
            props.setProperty("password", password);
            props.store(out, "Saved Login Credentials");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void styles() {
        userInput.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        passwordInput.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        loginImage.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        loginImage.setIcon(new FlatSVGIcon("com/dev/synapse/assets/login-banner.svg",
                loginImage.getPreferredSize().width,
                loginImage.getPreferredSize().height
        ));
    }

    private int signInProcess() {

        String username = userInput.getText().trim();
        String password = String.valueOf(passwordInput.getPassword());

        String userSql = "SELECT user_id, password_hash FROM users WHERE username = ?";
        String roleSql = "SELECT role_id FROM user_assignments WHERE user_id = ?";
        String insSql = "SELECT institution_id FROM user_assignments WHERE user_id = ?";

        saveCredentials(username, password);

        try (Connection conn = MySQL.getConnection(); PreparedStatement userPS = conn.prepareStatement(userSql)) {

            userPS.setString(1, username);
            ResultSet rsUser = userPS.executeQuery();

            if (rsUser.next()) {
                int userId = rsUser.getInt("user_id");
                String storedPassword = rsUser.getString("password_hash");

                if (password.equals(storedPassword)) {

                    try (PreparedStatement insPS = conn.prepareStatement(insSql)) {
                        insPS.setInt(1, userId);
                        ResultSet rsIns = insPS.executeQuery();
                        if (rsIns.next()) {
                            this.institutionId = rsIns.getInt(1);
                        } else {
                            System.out.println("No institution found for this user.");
                            return 0;
                        }
                    }

                    try (PreparedStatement rolePstmt = conn.prepareStatement(roleSql)) {
                        rolePstmt.setInt(1, userId);
                        ResultSet rsRole = rolePstmt.executeQuery();

                        if (rsRole.next()) {
                            int roleId = rsRole.getInt("role_id");
                            switch (roleId) {
                                case 1:
                                    System.out.println("Permission Denied! Your request hasn't been approved yet.");
                                    break;
                                case 2:
                                    this.homeScreen.getHospitalPanel();
                                    homeScreen.setVisible(true);
                                    this.dispose();
                                    break;
                                case 3:
                                    this.homeScreen.getBankPanel();
                                    homeScreen.setVisible(true);
                                    this.dispose();
                                    break;
                                case 4:
                                    this.homeScreen.getAdminPanel();
                                    homeScreen.setVisible(true);
                                    this.dispose();
                                    break;
                                default:
                                    System.out.println("Unknown role.");
                                    break;
                            }
                        }
                    }

                    return institutionId; 

                } else {
                    System.out.println("Invalid username or password.");
                }

            } else {
                System.out.println("Invalid username or password.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        userInput = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        registerHereBtn = new javax.swing.JLabel();
        passwordInput = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        signInBtn = new com.dev.synapse.components.PrimaryButton();
        loginImage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Poppins", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Welcome Back !");

        jLabel2.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Username / Email");

        userInput.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Password");

        registerHereBtn.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        registerHereBtn.setForeground(new java.awt.Color(0, 102, 204));
        registerHereBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        registerHereBtn.setText("New to Synapse? Register Here");
        registerHereBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                registerHereBtnMouseClicked(evt);
            }
        });

        passwordInput.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 153, 153));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Sign in to access Synapse system");

        signInBtn.setText("Sign in");
        signInBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signInBtnActionPerformed(evt);
            }
        });

        loginImage.setOpaque(true);
        loginImage.setPreferredSize(new java.awt.Dimension(349, 424));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(loginImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(271, 271, 271))
                            .addComponent(passwordInput)
                            .addComponent(userInput)
                            .addComponent(jLabel2)
                            .addComponent(signInBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(registerHereBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(72, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addGap(29, 29, 29)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(userInput, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(passwordInput, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(signInBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(registerHereBtn))
                    .addComponent(loginImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void registerHereBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerHereBtnMouseClicked
        new RegisterScreen().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_registerHereBtnMouseClicked

    private void signInBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signInBtnActionPerformed
        signInProcess();
    }//GEN-LAST:event_signInBtnActionPerformed

//    public static int getInstitutionId() {
//        return institutionId;
//    }
    
    public static void main(String args[]) {

        FlatIntelliJLaf.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel loginImage;
    private javax.swing.JPasswordField passwordInput;
    private javax.swing.JLabel registerHereBtn;
    private com.dev.synapse.components.PrimaryButton signInBtn;
    private javax.swing.JTextField userInput;
    // End of variables declaration//GEN-END:variables
}
