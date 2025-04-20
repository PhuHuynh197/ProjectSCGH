/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Tuan3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author THIS PC
 */
public class frmRC4 extends javax.swing.JFrame {

    private RC4Cipher rc4;
    
    public frmRC4() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnOpenFile1 = new javax.swing.JButton();
        txtKey = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtCiphertext = new javax.swing.JTextArea();
        btnEncrypt2 = new javax.swing.JButton();
        btnDecrypt = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnOpenFile = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtPlaintext = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        btnSaveFile = new javax.swing.JButton();

        btnOpenFile1.setText("Open CipherFile Text");
        btnOpenFile1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenFile1ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtCiphertext.setColumns(20);
        txtCiphertext.setRows(5);
        jScrollPane2.setViewportView(txtCiphertext);

        btnEncrypt2.setText("Encrypt");
        btnEncrypt2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEncrypt2ActionPerformed(evt);
            }
        });

        btnDecrypt.setText("Decrypt");
        btnDecrypt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDecryptActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("RC4 Cipher");

        btnOpenFile.setText("Open File");
        btnOpenFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenFileActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel2.setText("Plain Text");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel3.setText("Key");

        txtPlaintext.setColumns(20);
        txtPlaintext.setRows(5);
        jScrollPane1.setViewportView(txtPlaintext);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel4.setText("Cipher Text");

        btnSaveFile.setText("Save To File");
        btnSaveFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveFileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(btnEncrypt2)
                        .addGap(28, 28, 28)
                        .addComponent(btnDecrypt)
                        .addGap(36, 36, 36)
                        .addComponent(btnSaveFile)
                        .addGap(32, 32, 32)
                        .addComponent(btnOpenFile))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1)
                                .addComponent(txtKey)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(101, 101, 101)
                                    .addComponent(jLabel1)
                                    .addGap(0, 0, Short.MAX_VALUE)))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtKey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(jLabel2)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addGap(60, 60, 60)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnDecrypt)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSaveFile)
                            .addComponent(btnEncrypt2)))
                    .addComponent(btnOpenFile))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEncrypt2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEncrypt2ActionPerformed
        String plaintext = txtPlaintext.getText();
        byte[] plaintextBytes = plaintext.getBytes(StandardCharsets.UTF_8);
        String key = txtKey.getText();
        rc4 = new RC4Cipher(key.getBytes());
        byte[] ciphertextBytes = rc4.encrypt(plaintextBytes);
        String ciphertext = bytesToHexString(ciphertextBytes);
        txtCiphertext.setText(ciphertext);
    }//GEN-LAST:event_btnEncrypt2ActionPerformed

    private void btnDecryptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDecryptActionPerformed
        String ciphertext = txtCiphertext.getText();
        byte[] ciphertextBytes = hexStringToByteArray(ciphertext);
        String key = txtKey.getText();
        rc4 = new RC4Cipher(key.getBytes());
        byte[] decryptedBytes = rc4.decrypt(ciphertextBytes);
        String decryptedText = new String(decryptedBytes, StandardCharsets.UTF_8);
        txtPlaintext.setText(decryptedText);
    }//GEN-LAST:event_btnDecryptActionPerformed

    private void btnOpenFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenFileActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Open File containing Ciphertext");
        int userSelection = fileChooser.showOpenDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToOpen = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(fileToOpen))){
                StringBuilder ciphertextBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    ciphertextBuilder.append(line);
                    }
                String ciphertext = ciphertextBuilder.toString().trim();
                txtCiphertext.setText(ciphertext);
            }catch (IOException e){
                JOptionPane.showMessageDialog(this, "Error opening file:" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnOpenFileActionPerformed

    private void btnOpenFile1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenFile1ActionPerformed

    }//GEN-LAST:event_btnOpenFile1ActionPerformed

    private void btnSaveFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveFileActionPerformed
        String ciphertext = txtCiphertext.getText();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Ciphertext to File");
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            try (FileWriter writer = new FileWriter(fileToSave.getAbsolutePath() + ".txt")) {
                writer.write(ciphertext);
                JOptionPane.showMessageDialog(this,
                        "Ciphertext saved to file successfully.",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error saving file: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnSaveFileActionPerformed
    private static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }
    private static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmRC4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmRC4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmRC4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmRC4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmRC4().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDecrypt;
    private javax.swing.JButton btnEncrypt2;
    private javax.swing.JButton btnOpenFile;
    private javax.swing.JButton btnOpenFile1;
    private javax.swing.JButton btnSaveFile;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea txtCiphertext;
    private javax.swing.JTextField txtKey;
    private javax.swing.JTextArea txtPlaintext;
    // End of variables declaration//GEN-END:variables
}
