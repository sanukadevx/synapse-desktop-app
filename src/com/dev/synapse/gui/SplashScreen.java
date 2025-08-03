package com.dev.synapse.gui;

import com.formdev.flatlaf.FlatIntelliJLaf;

public class SplashScreen extends javax.swing.JFrame {

    public SplashScreen() {
        initComponents();
        //loadingAnimation();
    }

//    private void loadingAnimation() {
//        Thread t = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i <= 100; i++) {
//                    if (i < 10) {
//                        loadingAnimText.setText("Loading Application .");
//                    } else if (i > 10 && i < 20) {
//                        loadingAnimText.setText("Loading Application ..");
//                    } else if (i > 10 && i < 20) {
//                        loadingAnimText.setText("Loading Application ..");
//                    } else if (i > 20 && i < 30) {
//                        loadingAnimText.setText("Loading Application ...");
//                    } else if (i > 30 && i < 40) {
//                        loadingAnimText.setText("Loading Application .");
//                    } else if (i > 40 && i < 50) {
//                        loadingAnimText.setText("Loading Application ..");
//                    } else if (i > 50 && i < 60) {
//                        loadingAnimText.setText("Loading Application ...");
//                    } else if (i > 60 && i < 70) {
//                        loadingAnimText.setText("Loading Application .");
//                    } else if (i > 70 && i < 80) {
//                        loadingAnimText.setText("Loading Application ..");
//                    } else if (i > 80 && i < 90) {
//                        loadingAnimText.setText("Loading Application ...");
//                    }
//
//                    try {
//                        Thread.sleep(20);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                new HomeScreen().setVisible(true);
//                splashScreen.dispose();
//            }
//        });
//
//        t.start();
//    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loadingPanelImage = new javax.swing.JPanel();
        loadingPanelInfo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        loadingAnimText = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        loadingPanelImage.setBackground(new java.awt.Color(255, 102, 102));

        javax.swing.GroupLayout loadingPanelImageLayout = new javax.swing.GroupLayout(loadingPanelImage);
        loadingPanelImage.setLayout(loadingPanelImageLayout);
        loadingPanelImageLayout.setHorizontalGroup(
            loadingPanelImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 407, Short.MAX_VALUE)
        );
        loadingPanelImageLayout.setVerticalGroup(
            loadingPanelImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        loadingPanelInfo.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(49, 151, 149));
        jLabel1.setText("Synapse");

        jLabel2.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Be the Missing Piece,");

        jLabel3.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("© 2025 Synapse. All Rights Reserved.");

        jLabel4.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("For more information, Go to");

        jLabel5.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("www.withsynapse.com");

        loadingAnimText.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        loadingAnimText.setForeground(new java.awt.Color(0, 0, 0));
        loadingAnimText.setText("Loading Application");

        jLabel7.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("in Someone's Story");

        javax.swing.GroupLayout loadingPanelInfoLayout = new javax.swing.GroupLayout(loadingPanelInfo);
        loadingPanelInfo.setLayout(loadingPanelInfoLayout);
        loadingPanelInfoLayout.setHorizontalGroup(
            loadingPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loadingPanelInfoLayout.createSequentialGroup()
                .addGroup(loadingPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(loadingPanelInfoLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(loadingPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel7)
                            .addComponent(jLabel2)))
                    .addGroup(loadingPanelInfoLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(loadingPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(loadingAnimText))))
                .addContainerGap(101, Short.MAX_VALUE))
        );
        loadingPanelInfoLayout.setVerticalGroup(
            loadingPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loadingPanelInfoLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addGap(3, 3, 3)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(109, 109, 109)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addGap(53, 53, 53)
                .addComponent(loadingAnimText)
                .addContainerGap(132, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(loadingPanelInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(loadingPanelImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(loadingPanelImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(loadingPanelInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    private static SplashScreen splashScreen;

    public static void main(String args[]) {

        FlatIntelliJLaf.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                splashScreen = new SplashScreen();
                splashScreen.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel loadingAnimText;
    private javax.swing.JPanel loadingPanelImage;
    private javax.swing.JPanel loadingPanelInfo;
    // End of variables declaration//GEN-END:variables
}
