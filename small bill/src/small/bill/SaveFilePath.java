/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package small.bill;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author CHETHANA
 */
public class SaveFilePath {
    
    public static final String REPORT_BASE_URL = "C:\\SIDDHABill\\";
    
    public static String CheckPortGetPort(){
        String port = "3322";
        try {
            InputStream input = new FileInputStream(REPORT_BASE_URL+"DDR.txt");
            Properties prop = new Properties();
            prop.load(input);
            port = prop.getProperty("DB.mp");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "File is missing !!! ","Error Massage",JOptionPane.ERROR_MESSAGE);
        }
        return port;
    }
}
