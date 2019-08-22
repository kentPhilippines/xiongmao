package parProject.com.test.test.util;

import java.util.HashMap;

import javax.sql.DataSource;

public class DbPoolList {
    private static HashMap hpool;
    public DbPoolList() {
        if (DbPoolList.hpool == null) {
            DbPoolList.hpool = new HashMap();
            System.out.println("DbPoolList init");
        }
    }
    
    public void setPool(final String poolName, final DataSource ds) {
        DbPoolList.hpool.put(poolName, ds);
    }
    
    public static DataSource getPool(final int jndiPoolNo) {
        if (DbPoolList.hpool == null) {
            return null;
        }
        return (DataSource) DbPoolList.hpool.get(new StringBuilder(String.valueOf(jndiPoolNo)).toString());
    }
    
    public static void main(final String[] args) {
    }

}
