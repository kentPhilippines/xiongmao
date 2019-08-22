package parProject.com.test.test.util;

import java.sql.DriverManager;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Driver;

public class DbConnect {
	 private static String dbURL;
	    private static String username;
	    private static String password;
	    private static DbConnect instance;
	    
	    static {
	        DbConnect.dbURL = "jdbc:oracle:thin:@192.168.60.13:1521:JXDB";
	        DbConnect.username = "pay";
	        DbConnect.password = "pay";
	        DbConnect.instance = null;
	    }
	    
	    public static DbConnect getInstance() {
	        return new DbConnect();
	    }
	    
	    public Connection getConnection() {
	        final Connection conn = null;
	        try {
	            return this.getConnection(DbConnect.dbURL, DbConnect.username, DbConnect.password, 0);
	        }
	        catch (Exception e) {
	            System.out.println("e=" + e);
	            return null;
	        }
	    }
	    
	    public Connection getConnection(final String dbURL, final String username, final String password, final int timeout) throws Exception {
	        final Driver driver = (Driver)Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
	        DriverManager.registerDriver(driver);
	        if (timeout > 0) {
	            DriverManager.setLoginTimeout(timeout);
	        }
	        final Connection conn = (Connection) DriverManager.getConnection(dbURL, username, password);
	        return conn;
	    }
	    
	    public static void main(final String[] args) {
	        try {
	            final Connection conn1 = getInstance().getConnection();
	            if (conn1 != null) {
	                System.out.println("ok\n" + conn1);
	            }
	            conn1.close();
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}
