
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.DefaultCaret;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import org.opencv.core.Core;

public class ROVController extends javax.swing.JFrame {

    Controller con;
    Thread pollThread;
    Thread streaming;

    public ROVController() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
        }

        initComponents();

        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        DefaultCaret caret = (DefaultCaret) log.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        findControllerButton = new javax.swing.JButton();
        controllerNameSpace = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        log = new javax.swing.JTextArea();
        buttonsLabel = new javax.swing.JLabel();
        startButton = new javax.swing.JButton();
        stopButton = new javax.swing.JButton();
        streamPanel = new javax.swing.JPanel();
        startStream = new javax.swing.JButton();
        stopStream = new javax.swing.JButton();
        gripperSlider = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        currentSliderValue = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel3 = new javax.swing.JLabel();
        waterButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        threshold = new javax.swing.JTextField();
        nonBlack = new javax.swing.JButton();
        cameraIndex = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        findControllerButton.setText("Find Controller");
        findControllerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findControllerButtonActionPerformed(evt);
            }
        });

        jScrollPane2.setAutoscrolls(true);

        log.setColumns(20);
        log.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        log.setRows(5);
        jScrollPane2.setViewportView(log);

        buttonsLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        buttonsLabel.setText("Logs from arduino");

        startButton.setText("Start Controller");
        startButton.setEnabled(false);
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        stopButton.setText("Stop Controller");
        stopButton.setEnabled(false);
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout streamPanelLayout = new javax.swing.GroupLayout(streamPanel);
        streamPanel.setLayout(streamPanelLayout);
        streamPanelLayout.setHorizontalGroup(
            streamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 987, Short.MAX_VALUE)
        );
        streamPanelLayout.setVerticalGroup(
            streamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 471, Short.MAX_VALUE)
        );

        startStream.setText("Start Streaming");
        startStream.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startStreamActionPerformed(evt);
            }
        });

        stopStream.setText("Stop Streaming");
        stopStream.setEnabled(false);
        stopStream.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopStreamActionPerformed(evt);
            }
        });

        gripperSlider.setMaximum(180);
        gripperSlider.setValue(0);
        gripperSlider.setEnabled(false);

        jLabel1.setText("0");

        jLabel2.setText("180");

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setText("Actions");

        waterButton1.setText("Recognize Image");
        waterButton1.setEnabled(false);
        waterButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                waterButton1ActionPerformed(evt);
            }
        });

        jLabel4.setText("Threshold:");

        threshold.setText("85");

        nonBlack.setText("Remvoe non-black");
        nonBlack.setEnabled(false);
        nonBlack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nonBlackActionPerformed(evt);
            }
        });

        jLayeredPane1.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(waterButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(threshold, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(nonBlack, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nonBlack, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                            .addComponent(waterButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(threshold, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(45, 45, 45)
                .addComponent(waterButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nonBlack)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(threshold, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        cameraIndex.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Camera 0", "Camera 1", "Camera 2", "Camera 3", "Camera 4" }));
        cameraIndex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cameraIndexActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(96, 96, 96)
                        .addComponent(startStream)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(gripperSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(99, 99, 99)
                                        .addComponent(currentSliderValue, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(49, 49, 49))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(stopStream)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(buttonsLabel)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(182, 182, 182)
                .addComponent(streamPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 181, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cameraIndex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(408, 408, 408))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(137, 137, 137)
                            .addComponent(findControllerButton)
                            .addGap(79, 79, 79)
                            .addComponent(controllerNameSpace, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(22, 22, 22)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(startButton, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                                .addComponent(stopButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addContainerGap(888, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(cameraIndex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(streamPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(startStream)
                            .addComponent(stopStream))
                        .addGap(94, 94, 94)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(gripperSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(currentSliderValue, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(findControllerButton)
                        .addComponent(controllerNameSpace, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(28, 28, 28)
                    .addComponent(startButton)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(stopButton)
                    .addContainerGap(670, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Controller", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nonBlackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nonBlackActionPerformed
        try {
            double thresholding = Double.parseDouble(threshold.getText());
            BufferedImage img = ((VideoStreaming) streaming).getImage();
            File outputfile = new File("test.png");
            ImageIO.write(img, "png", outputfile);
            Picture p = Picture.getPicture("test.png");
            p.removeNotBlack(thresholding);
            img = (BufferedImage) p.getImage();
            ImageIO.write(img, "png", outputfile);
            File nonBlackImage = new File("test.png");
            Desktop desktop = Desktop.getDesktop();
            if (nonBlackImage.exists()) {
                desktop.open(nonBlackImage);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "You have to put numbers only!");
        } catch (IOException ex) {
            Logger.getLogger(ROVController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_nonBlackActionPerformed

    private void waterButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_waterButton1ActionPerformed
        try {
            Process process = Runtime.getRuntime().exec("python Image-Recognition.py test.png");
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String s;
            // read the output from the command
            System.out.println("Image recognition output:");
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }
            File recognizedImage = new File("test-recognized.jpg");
            Desktop desktop = Desktop.getDesktop();
            if (recognizedImage.exists()) {
                desktop.open(recognizedImage);
            }
        } catch (IOException ex) {

        }
    }//GEN-LAST:event_waterButton1ActionPerformed

    private void stopStreamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopStreamActionPerformed
        streaming.interrupt();
        startStream.setEnabled(true);
        stopStream.setEnabled(false);
        waterButton1.setEnabled(false);
        nonBlack.setEnabled(false);
    }//GEN-LAST:event_stopStreamActionPerformed

    private void startStreamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startStreamActionPerformed
        streaming = new VideoStreaming(getWidth(), getHeight(), streamPanel, startStream, stopStream, cameraIndex.getSelectedIndex());
        streaming.start();
        startStream.setEnabled(false);
        stopStream.setEnabled(true);
        waterButton1.setEnabled(true);
        nonBlack.setEnabled(true);
    }//GEN-LAST:event_startStreamActionPerformed

    private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopButtonActionPerformed
        pollThread.interrupt();
        stopButton.setEnabled(false);
        startButton.setEnabled(true);
    }//GEN-LAST:event_stopButtonActionPerformed

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        pollThread = new PollingData(con, log, gripperSlider, currentSliderValue);
        pollThread.start();
        startButton.setEnabled(false);
        stopButton.setEnabled(true);
    }//GEN-LAST:event_startButtonActionPerformed

    private void findControllerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findControllerButtonActionPerformed

        Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
        try {
            for (Controller c : controllers) {
                if (c.getType().toString().equalsIgnoreCase("STICK")) {
                    con = c;
                    break;
                }
            }
            if (con == null) {
                throw new JoyStickNotFound();
            }
        } catch (JoyStickNotFound e) {
            System.out.println(e.getMessage());
            System.out.println("ROV's Controller is exiting..");
            System.exit(0);
        }
        controllerNameSpace.setText(con.getName());
        startButton.setEnabled(true);
    }//GEN-LAST:event_findControllerButtonActionPerformed

    private void cameraIndexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cameraIndexActionPerformed
        streaming.interrupt();
        streaming = new VideoStreaming(getWidth(), getHeight(), streamPanel, startStream, stopStream, cameraIndex.getSelectedIndex());
        streaming.start();
    }//GEN-LAST:event_cameraIndexActionPerformed

    public static void main(String args[]) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
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
            java.util.logging.Logger.getLogger(ROVController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ROVController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ROVController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ROVController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ROVController().setVisible(true);
            }
        });        
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel buttonsLabel;
    private javax.swing.JComboBox<String> cameraIndex;
    private javax.swing.JTextField controllerNameSpace;
    private javax.swing.JLabel currentSliderValue;
    private javax.swing.JButton findControllerButton;
    private javax.swing.JSlider gripperSlider;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea log;
    private javax.swing.JButton nonBlack;
    private javax.swing.JButton startButton;
    private javax.swing.JButton startStream;
    private javax.swing.JButton stopButton;
    private javax.swing.JButton stopStream;
    private javax.swing.JPanel streamPanel;
    private javax.swing.JTextField threshold;
    private javax.swing.JButton waterButton1;
    // End of variables declaration//GEN-END:variables
}
