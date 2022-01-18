/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package small.bill;

import org.apache.log4j.HTMLLayout;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

/**
 *
 * @author CHETHANA
 */
public class SystemLogger {

    public static void initLogger() {
        try {
        //    String path = "C:\\SIDDHABill\\Systemlogger\\Logger1.html";
            String path = "C:\\SIDDHABill\\Systemlogger\\Logger1.log";
        //    HTMLLayout htmlLayout = new HTMLLayout();
            PatternLayout pattenLayout = new PatternLayout("%-10p %-10d{yyyy-MMM-dd HH:mm:ss, SSS} %-10C %-10M %-10m %n");
        //    RollingFileAppender appender = new RollingFileAppender(htmlLayout, path);
            RollingFileAppender appender = new RollingFileAppender(pattenLayout, path);
            appender.setMaxFileSize("10MB");
            appender.setName("myLoger");
            appender.activateOptions();
            Logger.getRootLogger().addAppender(appender);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        initLogger();
        Logger log = Logger.getLogger("myLoger");
        log.info("Logger Initiate");
        log.error("Logger Error Found");
        log.warn("Logger Error Found 2");
        log.error("Logger Error Found 2");
    }
}
