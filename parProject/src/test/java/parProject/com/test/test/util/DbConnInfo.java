package parProject.com.test.test.util;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;

import org.slf4j.Logger;

import com.mysql.jdbc.Connection;

public class DbConnInfo  implements Serializable{
	private static long connNum;
    private static long applyNum;
    private static long freeNum;
    private static long usedNum;
    private static Hashtable htConn;
    private static int errNo;
    private static long alertTime;
    private static DbConnInfo instance;
    private static boolean isDebug;
    private Logger log;
    
    static {
        DbConnInfo.errNo = 0;
        DbConnInfo.alertTime = 0L;
        DbConnInfo.isDebug = false;
    }
    
    public DbConnInfo() {
    }
    
    public void setIsDebug(final boolean is) {
        DbConnInfo.isDebug = is;
    }
    
    public static synchronized DbConnInfo getInstance() {
        if (DbConnInfo.instance == null) {
            DbConnInfo.instance = new DbConnInfo();
            DbConnInfo.htConn = new Hashtable();
            DbConnInfo.connNum = 0L;
            DbConnInfo.freeNum = 0L;
            DbConnInfo.usedNum = 0L;
            DbConnInfo.applyNum = 0L;
            System.out.println("DbConnInfo init ");
        }
        return DbConnInfo.instance;
    }
    
    public synchronized void add(final Connection conn) {
        if (conn == null) {
            System.out.println("DbConnInfo add conn is null");
            ++DbConnInfo.errNo;
            if (DbConnInfo.errNo % 10 == 1) {
                this.alertMsg();
            }
            return;
        }
        if ((DbConnInfo.applyNum / 10000L > 0L && DbConnInfo.applyNum % 10000L == 0L) || DbConnInfo.htConn.size() > 128) {
            System.out.println("htConn clear");
            if (DbConnInfo.isDebug) {
                this.restore();
            }
            DbConnInfo.htConn.clear();
        }
        try {
            DbConnInfo.errNo = 0;
            ++DbConnInfo.applyNum;
            DbConnInfo.usedNum = DbConnInfo.applyNum - DbConnInfo.freeNum;
            String connStr = conn.toString().toLowerCase();
            final int len = connStr.lastIndexOf("@");
            if (len > 0) {
                connStr = connStr.substring(connStr.lastIndexOf("@"));
            }
            final boolean isNewConn = !DbConnInfo.htConn.containsKey(connStr);
            if (isNewConn) {
                ++DbConnInfo.connNum;
            }
            Properties prop = null;
            if (isNewConn) {
                prop = new Properties();
                prop.put("addTimes", 1);
                prop.put("freeTimes", 0);
                prop.put("startDate", System.currentTimeMillis());
                prop.put("endDate", System.currentTimeMillis());
            }
            else {
                prop = (Properties) DbConnInfo.htConn.get(connStr);
                final int addTimes = (int) prop.get("addTimes");
              prop.put("addTimes", addTimes + 1);
            }
            DbConnInfo.htConn.put(connStr, prop);
            if (DbConnInfo.isDebug) {
                System.out.print("Connect add ");
                if (isNewConn) {
                    System.out.print("new conn =" + connStr + "\n");
                }
                System.out.print("Act connNum=" + DbConnInfo.connNum);
                System.out.print(" usedNum=" + DbConnInfo.usedNum);
                System.out.print(" HtUsedNum=" + DbConnInfo.htConn.size());
                System.out.print(" applyNum=" + DbConnInfo.applyNum);
                System.out.println(" freeNum=" + DbConnInfo.freeNum);
            }
        }
        catch (Exception e) {
            System.out.println("DbConnInfo.add() error=" + e);
        }
    }
    
    public synchronized void free(final Connection conn) {
        if (conn == null) {
            return;
        }
        ++DbConnInfo.freeNum;
        DbConnInfo.usedNum = DbConnInfo.applyNum - DbConnInfo.freeNum;
        try {
            String connStr = conn.toString().toLowerCase();
            final int len = connStr.lastIndexOf("@");
            if (len > 0) {
                connStr = connStr.substring(connStr.lastIndexOf("@"));
            }
            Properties prop = null;
            if (DbConnInfo.htConn.containsKey(connStr)) {
                prop = (Properties) DbConnInfo.htConn.get(connStr);
                final int freeTimes =  (int) prop.get("freeTimes");
                 prop .put("freeTimes", freeTimes + 1);
                 prop .put("endDate", System.currentTimeMillis());
                DbConnInfo.htConn.put(connStr, prop);
            }
        }
        catch (Exception e) {
            System.out.println("DbConnInfo.free() error=" + e);
        }
        if (DbConnInfo.isDebug) {
            System.out.print("Connect free ");
            System.out.print("Act connNum=" + DbConnInfo.connNum);
            System.out.print(" usedNum=" + DbConnInfo.usedNum);
            System.out.print(" HtUsedNum=" + DbConnInfo.htConn.size());
            System.out.print(" applyNum=" + DbConnInfo.applyNum);
            System.out.println(" freeNum=" + DbConnInfo.freeNum);
        }
    }
    
    public void restore() {
        try {
            final Enumeration e = DbConnInfo.htConn.keys();
            final StringBuffer sb = new StringBuffer();
            sb.append("applyNum=" + DbConnInfo.applyNum + "\n");
            sb.append("freeNum=" + DbConnInfo.freeNum + "\n");
            sb.append("connNum=" + DbConnInfo.connNum + "\n");
            sb.append("usedNum=" + DbConnInfo.usedNum + "\n");
            while (e.hasMoreElements()) {
                final Object key = e.nextElement();
                final Object value = DbConnInfo.htConn.get(key);
                sb.append("conn=" + key + " value=" + value + "\n");
            }
            final long no = System.currentTimeMillis();
            this.log.info((String)(String.valueOf(no) + "==start"));
            this.log.info((String)(String.valueOf(no) + "==end"));
        }
        catch (Exception e2) {
            System.out.println("restore fail=" + e2);
        }
    }
    
    public void alertMsg() {
        if (System.currentTimeMillis() - DbConnInfo.alertTime > 300000L) {
            DbConnInfo.alertTime = System.currentTimeMillis();
            System.out.println("send alert Msg begin");
            System.out.println("alertMsg end");
        }
    }
    
    public long getApplyNum() {
        return DbConnInfo.applyNum;
    }
    
    public long getFreeNum() {
        return DbConnInfo.freeNum;
    }
    
    public long getConnNum() {
        return DbConnInfo.connNum;
    }
    
    public long getUsedNum() {
        return DbConnInfo.usedNum;
    }
    
    public int getErrNo() {
        return DbConnInfo.errNo;
    }
    
    public long getAlertTime() {
        return DbConnInfo.alertTime;
    }
    
    public Hashtable getHtConn() {
        return DbConnInfo.htConn;
    }
    
    public static void main(final String[] args) {
        getInstance().alertMsg();
    }
}
