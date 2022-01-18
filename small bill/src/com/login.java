package com;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import org.apache.log4j.Logger;
import small.bill.DBClass;
import small.bill.SaveFilePath;
import static small.bill.SystemLogger.initLogger;

public class login extends javax.swing.JFrame {
            
       private Logger log;
        
    static String u_name = "lili";
    static String pass = "lili";

    private login() {
        initComponents();
         setLocationRelativeTo(null);
       // setExtendedState(JFrame.MAXIMIZED_BOTH);
        dateTime();
        imagei();
        jProgressBar1.setVisible(false);
        initLogger();
        log = Logger.getLogger("myLoger");
        log.info(massage);
    }

//---------########---------*********************---------########---------*********************---------########---------\\ 
//---------########---------*********************---------########---------*********************---------########---------\\    
//---------########---------*********************---------########---------*********************---------########---------\\     
//---------########---------*********************---------########---------*********************---------########---------\\ 
//---------########---------*********************---------########---------*********************---------########---------\\    
//---------########---------*********************---------########---------*********************---------########---------\\ 
    /* *** >>> Date ------------>>> 
     * *** >>> What ------------>>> Singltan class 
     * *** >>> Why ------------->>> ek object ekk hdnn
     * *** >>> Parameters ------>>>
     * *** >>> Status ---------->>>
     */
//>>>>>>>>> Get Instance <<<<<<<<<<<  
    private static login logn = new login();

    public static login getCallPopup() {
        return logn;
    }
//>>>>>>>>> Get Instance <<<<<<<<<<<         

//---------########---------*********************---------########---------*********************---------########---------\\ 
//---------########---------*********************---------########---------*********************---------########---------\\    
//---------########---------*********************---------########---------*********************---------########---------\\     
//---------########---------*********************---------########---------*********************---------########---------\\ 
//---------########---------*********************---------########---------*********************---------########---------\\    
//---------########---------*********************---------########---------*********************---------########---------\\ 
    /* *** >>> Date ------------>>> mon 6 September
     * *** >>> What ------------>>> image2()
     * *** >>> Why ------------->>>
     * *** >>> Parameters ------>>> no
     * *** >>> Status ---------->>> image ek load wenn
     */
//>>>>>>>>> set image <<<<<<<<<<<
    private void imagei()  {
        try {
           File f = new File(SaveFilePath.REPORT_BASE_URL+"logo.jpg");
           Image image2 = ImageIO.read(f);
       // ImageIcon ii = new ImageIcon(getClass().getResource("/img/logo.jpg")); 
          Image image = image2.getScaledInstance(loging_image.getWidth(), loging_image.getHeight(), Image.SCALE_SMOOTH);
          loging_image.setIcon(new ImageIcon(image));
        } catch (Exception e) {
           // e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Image Not Loadded imagei!!!!"+e);
        }

    }
//>>>>>>>>> set image <<<<<<<<<<<

//---------########---------*********************---------########---------*********************---------########---------\\ 
//---------########---------*********************---------########---------*********************---------########---------\\    
//---------########---------*********************---------########---------*********************---------########---------\\     
//---------########---------*********************---------########---------*********************---------########---------\\ 
//---------########---------*********************---------########---------*********************---------########---------\\    
//---------########---------*********************---------########---------*********************---------########---------\\ 
    /* *** >>> Date ------------>>> mon 6 September
     * *** >>> What ------------>>> SystemClose()
     * *** >>> Why ------------->>>
     * *** >>> Parameters ------>>> no
     * *** >>> Status ---------->>>
     */
    //>>>>>>>>> System cloce <<<<<<<<<<<   
    void SystemClose() {
        int x = JOptionPane.showConfirmDialog(this, "Do you sure close the System ?", "Exit System", JOptionPane.YES_NO_OPTION);
        if (JOptionPane.YES_OPTION == x) {
            // setDefaultCloseOperation(EXIT_ON_CLOSE);
            System.exit(0);
        } else if (JOptionPane.NO_OPTION == x) {
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            close_LA.setBackground(new Color(153, 204, 255));
        }
    }
    //>>>>>>>>> System cloce <<<<<<<<<<<       

//---------########---------*********************---------########---------*********************---------########---------\\ 
//---------########---------*********************---------########---------*********************---------########---------\\    
//---------########---------*********************---------########---------*********************---------########---------\\     
//---------########---------*********************---------########---------*********************---------########---------\\ 
//---------########---------*********************---------########---------*********************---------########---------\\    
//---------########---------*********************---------########---------*********************---------########---------\\ 

    /* *** >>> Date ------------>>> mon 6 September
     * *** >>> What ------------>>> loginCheck()
     * *** >>> Why ------------->>>
     * *** >>> Parameters ------>>> no
     * *** >>> Status ---------->>>
     */
    //>>>>>>>>> System loginCheck <<<<<<<<<<<   
    void loginCheck() {

        if ((txt_u_name.getText().equals(u_name)) & (txt_pass.getText().equals(pass))) {
            lodingProgresBar(1);

        } else if (!((txt_u_name.getText().isEmpty()) & (txt_pass.getText().isEmpty()))) {
            searchEmployee();
        } else {
            JOptionPane.showMessageDialog(this, "Check the username or password");
            txt_u_name.grabFocus();
        }
    }
    //>>>>>>>>> System loginCheck <<<<<<<<<<<       

//---------########---------*********************---------########---------*********************---------########---------\\ 
//---------########---------*********************---------########---------*********************---------########---------\\    
//---------########---------*********************---------########---------*********************---------########---------\\     
//---------########---------*********************---------########---------*********************---------########---------\\ 
//---------########---------*********************---------########---------*********************---------########---------\\    
//---------########---------*********************---------########---------*********************---------########---------\\ 
    /* *** >>> Date ------------>>> mon 6 September
     * *** >>> What ------------>>> searchEmployee()
     * *** >>> Why ------------->>>
     * *** >>> Parameters ------>>> no
     * *** >>> Status ---------->>>
     */
    //>>>>>>>>>  searchEmployee <<<<<<<<<<<   
    public void searchEmployee() {
        try {
            String uname = txt_u_name.getText();
            String password = new String(txt_pass.getPassword());
            ResultSet search = DBClass.getData("select * from employee where uname ='" + uname + "' and password ='" + password + "' ");
            if (search.next()) {
                boolean isActive = search.getBoolean("isactive");
                if (isActive) {
                    lodingProgresBar(1);
                } else {
                    JOptionPane.showMessageDialog(this, "This User is Inactive");
                    txt_u_name.grabFocus();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
                txt_u_name.grabFocus();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Check the all inputs");
            txt_u_name.grabFocus();
        }

    }
    //>>>>>>>>>  searchEmployee <<<<<<<<<<<       

//---------########---------*********************---------########---------*********************---------########---------\\ 
//---------########---------*********************---------########---------*********************---------########---------\\    
//---------########---------*********************---------########---------*********************---------########---------\\     
//---------########---------*********************---------########---------*********************---------########---------\\ 
//---------########---------*********************---------########---------*********************---------########---------\\    
//---------########---------*********************---------########---------*********************---------########---------\\ 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        close_LA = new javax.swing.JLabel();
        loging_image = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        dateLA = new javax.swing.JLabel();
        txt_u_name = new javax.swing.JTextField();
        txt_pass = new javax.swing.JPasswordField();
        jButton2 = new javax.swing.JButton();
        timeLA = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        massage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(239, 251, 238));
        setLocation(new java.awt.Point(350, 200));
        setMinimumSize(new java.awt.Dimension(678, 263));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(678, 263));
        getContentPane().setLayout(null);

        jLayeredPane1.setBackground(new java.awt.Color(255, 255, 255));
        jLayeredPane1.setOpaque(true);

        close_LA.setBackground(new java.awt.Color(0, 255, 140));
        close_LA.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        close_LA.setForeground(new java.awt.Color(0, 0, 0));
        close_LA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        close_LA.setText("X");
        close_LA.setOpaque(true);
        close_LA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                close_LAMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                close_LAMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                close_LAMouseExited(evt);
            }
        });
        jLayeredPane1.add(close_LA);
        close_LA.setBounds(650, 0, 28, 28);

        loging_image.setText("jLabel1");
        jLayeredPane1.add(loging_image);
        loging_image.setBounds(10, 10, 250, 240);

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Siddha.lk");
        jLayeredPane1.add(jLabel2);
        jLabel2.setBounds(280, 10, 200, 40);

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("User Name");
        jLayeredPane1.add(jLabel3);
        jLabel3.setBounds(280, 70, 130, 30);

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Password");
        jLayeredPane1.add(jLabel4);
        jLabel4.setBounds(280, 110, 130, 30);

        dateLA.setBackground(new java.awt.Color(0, 0, 0));
        dateLA.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        dateLA.setForeground(new java.awt.Color(0, 0, 0));
        dateLA.setText("Date");
        jLayeredPane1.add(dateLA);
        dateLA.setBounds(440, 220, 110, 30);

        txt_u_name.setBackground(new java.awt.Color(204, 204, 204));
        txt_u_name.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        txt_u_name.setForeground(new java.awt.Color(0, 0, 0));
        txt_u_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_u_nameActionPerformed(evt);
            }
        });
        txt_u_name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_u_nameKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_u_nameKeyReleased(evt);
            }
        });
        jLayeredPane1.add(txt_u_name);
        txt_u_name.setBounds(420, 70, 220, 30);

        txt_pass.setBackground(new java.awt.Color(204, 204, 204));
        txt_pass.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        txt_pass.setForeground(new java.awt.Color(0, 0, 0));
        txt_pass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_passKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_passKeyReleased(evt);
            }
        });
        jLayeredPane1.add(txt_pass);
        txt_pass.setBounds(420, 110, 220, 30);

        jButton2.setBackground(new java.awt.Color(65, 241, 241));
        jButton2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 0));
        jButton2.setText("Login");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jLayeredPane1.add(jButton2);
        jButton2.setBounds(420, 150, 220, 38);

        timeLA.setBackground(new java.awt.Color(0, 0, 0));
        timeLA.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        timeLA.setForeground(new java.awt.Color(0, 0, 0));
        timeLA.setText("Time");
        jLayeredPane1.add(timeLA);
        timeLA.setBounds(560, 220, 110, 30);

        jProgressBar1.setBackground(new java.awt.Color(102, 255, 51));
        jProgressBar1.setForeground(new java.awt.Color(0, 0, 0));
        jLayeredPane1.add(jProgressBar1);
        jProgressBar1.setBounds(280, 200, 380, 12);

        massage.setForeground(new java.awt.Color(255, 0, 0));
        jLayeredPane1.add(massage);
        massage.setBounds(260, 220, 160, 30);

        getContentPane().add(jLayeredPane1);
        jLayeredPane1.setBounds(0, 0, 680, 270);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void close_LAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_close_LAMouseClicked
        SystemClose();
    }//GEN-LAST:event_close_LAMouseClicked

    private void close_LAMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_close_LAMouseEntered
        close_LA.setBackground(Color.red);
    }//GEN-LAST:event_close_LAMouseEntered

    private void close_LAMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_close_LAMouseExited
        close_LA.setBackground(Color.green);
    }//GEN-LAST:event_close_LAMouseExited

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (!((txt_u_name.getText().isEmpty()) & (Arrays.toString(txt_pass.getPassword()).isEmpty()))) {
            loginCheck();
        } else if ((txt_u_name.getText().isEmpty())) {
            txt_u_name.grabFocus();
        } else if ((Arrays.toString(txt_pass.getPassword()).isEmpty())) {
            txt_pass.grabFocus();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txt_u_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_u_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_u_nameActionPerformed

    private void txt_u_nameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_u_nameKeyReleased
        char c = evt.getKeyChar();
        if ((Character.isDigit(c)) | (Character.isAlphabetic(c)) | (c == KeyEvent.VK_BACK_SPACE) | (c == KeyEvent.VK_SPACE)) {
            txt_u_name.setEditable(true);
        } else {
            txt_u_name.setEditable(false);
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!txt_u_name.getText().trim().equals("")) {
                txt_pass.grabFocus();
            } else {
                txt_u_name.grabFocus();
            }
        }

    }//GEN-LAST:event_txt_u_nameKeyReleased

    private void txt_passKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_passKeyReleased
        char c = evt.getKeyChar();
        if ((Character.isDigit(c)) | (Character.isAlphabetic(c)) | (c == KeyEvent.VK_BACK_SPACE) | (c == KeyEvent.VK_SPACE)) {
            txt_pass.setEditable(true);
        } else {
            txt_pass.setEditable(false);
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!txt_pass.getText().trim().equals("")) {
                jButton2.doClick();
            } else {
                txt_pass.grabFocus();
            }
        }
    }//GEN-LAST:event_txt_passKeyReleased

    private void txt_u_nameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_u_nameKeyPressed
        char c = evt.getKeyChar();
        if ((Character.isDigit(c)) | (Character.isAlphabetic(c)) | (c == KeyEvent.VK_BACK_SPACE) | (c == KeyEvent.VK_SPACE)) {
            txt_u_name.setEditable(true);
        } else {
            txt_u_name.setEditable(false);
        }
    }//GEN-LAST:event_txt_u_nameKeyPressed

    private void txt_passKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_passKeyPressed
        char c = evt.getKeyChar();
        if ((Character.isDigit(c)) | (Character.isAlphabetic(c)) | (c == KeyEvent.VK_BACK_SPACE) | (c == KeyEvent.VK_SPACE)) {
            txt_u_name.setEditable(true);
        } else {
            txt_u_name.setEditable(false);
        }
    }//GEN-LAST:event_txt_passKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (Exception ex) {
//            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel close_LA;
    private javax.swing.JLabel dateLA;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JLabel loging_image;
    private javax.swing.JLabel massage;
    private javax.swing.JLabel timeLA;
    private javax.swing.JPasswordField txt_pass;
    private javax.swing.JTextField txt_u_name;
    // End of variables declaration//GEN-END:variables

    private void dateTime() {
        new Thread(() -> {
            StringBuffer sb, sb2;
            sb2 = new StringBuffer(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            dateLA.setText("" + sb2);
            while (true) {
                sb = new StringBuffer(new SimpleDateFormat("HH:mm:ss").format(new Date()));
                timeLA.setText("" + sb);
            }
        }).start();
    }

    synchronized private void lodingProgresBar(int x) {
       jProgressBar1.setVisible(true);
        Thread t = new Thread(new Runnable() {
            invoice n ;
            @Override
            public void run() {
                try {
                    for (int i = 1; i < 101; i++) {
                        jProgressBar1.setValue(i);
                        if (i == 5) {
                            massage.setText("Database checking...!");
                            Connection newConnection = DBClass.con();
                            if (newConnection == null) {
                                break;
                            }
                        }
                        if (i >= 20 && i <= 30) {
                            massage.setText("DB Connected");
                            Thread.sleep(100);
                            continue;

                        }
                        if (i == 90) {
                            massage.setText("Loging in user");
                            n = new invoice();
                        }if(i==100){
                            n.setVisible(true);
                           login.this.dispose();
                           break;
                        }

                        Thread.sleep(20);

                    }
                } catch (Exception e) {
                }
            }
        });
        t.start();

    }

    
}
