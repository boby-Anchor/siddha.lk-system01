
package small.bill;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBClass {
    public static Connection con;
    public static ResultSet r;
    
    public static Connection con()throws Exception{
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        String port = SaveFilePath.CheckPortGetPort();
       con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:"+port+"/com_simplebill?useSSL=false", "root","1234");//?useSSL=false
        return con;
       
    }
    public static void putData(String sql)throws Exception{
        Statement s = (Statement) con().prepareStatement(sql);
        s.executeUpdate(sql);
    //    con.close();
    }
    public static ResultSet getData(String sql)throws Exception{
       r = con().createStatement().executeQuery(sql);
        return r;
    }
    
}
