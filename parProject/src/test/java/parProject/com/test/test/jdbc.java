package parProject.com.test.test;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;
import com.payProject.application;
import com.payProject.manage.contorller.BankCardContorller;

import parProject.com.test.test.util.DbConnInfo;
import parProject.com.test.test.util.DbConnect;
import parProject.com.test.test.util.DbPoolList;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = application.class)
public class jdbc {
	  	private Logger log;
	    private Logger error;
	    private Logger alert;
	    private static final Character UNDERLINE;
	    private int jndiPoolNo;
	    private String poolName;
	    private String poolSelf;
	    private Connection conn;
	    private ResultSet rs;
	    private Statement stmt;
	    private java.sql.CallableStatement cs;
	    private PreparedStatement ps;
	    private ResultSetMetaData rsmd;
	    
	    static {
	        UNDERLINE = '_';
	    }
	
	  public jdbc() {
	        this.log =  LoggerFactory.getLogger(jdbc.class);
	        this.error = LoggerFactory.getLogger(jdbc.class);
	        this.alert = LoggerFactory.getLogger(jdbc.class);
	        this.jndiPoolNo = 0;
	        this.poolName = "";
	        this.poolSelf = "";
	        this.conn = null;
	        this.rs = null;
	        this.stmt = null;
	        this.cs = null;
	        this.ps = null;
	        this.rsmd = null;
	    }
	    
	    public Object[] execProcess(final String procExpression, final String[] inParam, final int inParamCount, final int outParamCount) {
	        if (this.getConnection() != 0) {
	            return null;
	        }
	        final long startTime = System.currentTimeMillis();
	        final Object[] result = new String[outParamCount];
	        int paramCount = 0;
	        try {
	            this.conn.setAutoCommit(false);
	            final StringTokenizer tok = new StringTokenizer(procExpression, "?");
	            paramCount = tok.countTokens() - 1;
	            if (paramCount != inParamCount + outParamCount) {
	                this.error.info((String)("Error in DBBean.execProcess(" + procExpression + ") is wrong in proc param."));
	                this.log(procExpression, inParam, result, startTime, this.error);
	            }
	            else {
	                if (inParam.length == inParamCount) {
	                    this.cs = this.conn.prepareCall(procExpression);
	                    if (inParamCount > 0) {
	                        for (int i = 0; i < inParamCount; ++i) {
	                            this.cs.setString(i + 1, inParam[i]);
	                        }
	                    }
	                    if (outParamCount > 0) {
	                        for (int i = 0; i < outParamCount; ++i) {
	                            this.cs.registerOutParameter(inParamCount + i + 1, 12);
	                        }
	                    }
	                    this.cs.execute();
	                    if (outParamCount > 0) {
	                        for (int i = 0; i < outParamCount; ++i) {
	                            result[i] = this.cs.getString(inParamCount + i + 1);
	                        }
	                    }
	                    if (outParamCount == 0 || "0".equals(result[0]) || "00000".equals(result[0])) {
	                        this.conn.commit();
	                    }
	                    else {
	                        this.rollback();
	                    }
	                    this.log(procExpression, inParam, result, startTime, this.log);
	                    return result;
	                }
	                this.error.info((String)("Error in DBBean.execProcess(" + procExpression + ") is wrong in proc param."));
	                this.log(procExpression, inParam, result, startTime, this.error);
	            }
	            return null;
	        }
	        catch (Exception e) {
	            this.error.info((String)("Error in DBBean.execProcess(" + procExpression + "): " + e), (Throwable)e);
	            this.log(procExpression, inParam, result, startTime, this.error);
	            this.alert.info((String)"errorcode=DB11111 mess=\"new dbbean con err\"");
	            this.rollback();
	            return null;
	        }
	        finally {
	            this.disConnection();
	        }
	    }
	    
	    public Object[] execProcessQuery(final String procExpression, final String className, final String[] inParam, final int inParamCount, final int outParamCount, final int rsPosition) {
	        if (this.getConnection() != 0) {
	            return null;
	        }
	        final long startTime = System.currentTimeMillis();
	        Object[] result = null;
	        int paramCount = 0;
	        try {
	            final StringTokenizer tok = new StringTokenizer(procExpression, "?");
	            paramCount = tok.countTokens() - 1;
	            if (paramCount != inParamCount + outParamCount) {
	                this.log.info((String)("Error in DBBean.execProcess(" + procExpression + ") is wrong in proc param."));
	                this.log(procExpression, inParam, result, startTime, this.error);
	            }
	            else {
	                if (inParam.length == inParamCount) {
	                    this.cs = this.conn.prepareCall(procExpression);
	                    if (inParamCount > 0) {
	                        for (int i = 0; i < inParamCount; ++i) {
	                            this.cs.setString(i + 1, inParam[i]);
	                        }
	                    }
	                    if (outParamCount > 0) {
	                        for (int i = 0; i < outParamCount; ++i) {
	                            if (rsPosition != -1 && rsPosition == i) {
	                                this.cs.registerOutParameter(inParamCount + i + 1, -10);
	                            }
	                            else {
	                                this.cs.registerOutParameter(inParamCount + i + 1, 12);
	                            }
	                        }
	                    }
	                    this.cs.execute();
	                    if (outParamCount > 0) {
	                        result = new Object[outParamCount];
	                        for (int i = 0; i < outParamCount; ++i) {
	                            if (rsPosition != -1 && rsPosition == i) {
	                                this.rs = (ResultSet)this.cs.getObject(inParamCount + i + 1);
	                                result[i] = this.rs2List(this.rs, className);
	                            }
	                            else {
	                                result[i] = this.cs.getString(inParamCount + i + 1);
	                            }
	                        }
	                    }
	                    this.log(procExpression, inParam, result, startTime, this.log);
	                    return result;
	                }
	                this.log.info((String)("Error in DBBean.execProcess(" + procExpression + ") is wrong in proc param."));
	                this.log(procExpression, inParam, result, startTime, this.error);
	            }
	            return null;
	        }
	        catch (Exception e) {
	            this.error.info((String)("Error in DBBean.exec ProcessQuery(" + procExpression + "): " + e), (Throwable)e);
	            this.log(procExpression, inParam, result, startTime, this.error);
	            this.alert.info((String)"errorcode=DB11111 mess=\"new dbbean con err\"");
	            return null;
	        }
	        finally {
	            this.disConnection();
	        }
	    }
	    
	    public List execQuerySQLgetObjects(final String SQL, final String classname, final Object[] param) {
	        List list = null;
	        final int inParamCount = param.length;
	        if (this.getConnection() != 0) {
	            return null;
	        }
	        final long startTime = System.currentTimeMillis();
	        try {
	            this.conn.setAutoCommit(true);
	            this.cs = this.conn.prepareCall(SQL);
	            if (inParamCount > 0) {
	                for (int i = 0; i < inParamCount; ++i) {
	                    this.cs.setObject(i + 1, param[i]);
	                }
	            }
	            this.cs.execute();
	            this.rs = this.cs.getResultSet();
	            list = this.rs2List(this.rs, classname);
	            if (param instanceof String[]) {
	                this.log(SQL, (String[])param, null, startTime, this.log);
	            }
	            else {
	                this.log(SQL, null, null, startTime, this.log);
	            }
	            return list;
	        }
	        catch (Exception e) {
	            System.err.println("Error in DBBean.execQuerySQLgetObjects(" + SQL + "): " + e);
	            return null;
	        }
	        finally {
	            this.disConnection();
	        }
	    }
	    
	    public List execQuerySQLgetObjects(final String SQL, final String classname) {
	        List list = null;
	        if (this.getConnection() != 0) {
	            return null;
	        }
	        final long startTime = System.currentTimeMillis();
	        try {
	            this.conn.setAutoCommit(true);
	            (this.cs = this.conn.prepareCall(SQL)).execute();
	            this.rs = this.cs.getResultSet();
	            list = this.rs2List(this.rs, classname);
	            this.log(SQL, null, null, startTime, this.log);
	            return list;
	        }
	        catch (Exception e) {
	            System.err.println("Error in DBBean.execQuerySQLgetResultSet(" + SQL + "): " + e);
	            return null;
	        }
	        finally {
	            this.disConnection();
	        }
	    }
	    
	    public jdbc(final int jndiPoolNo) {
	        this.jndiPoolNo = 0;
	        this.poolName = "";
	        this.poolSelf = "";
	        this.conn = null;
	        this.rs = null;
	        this.stmt = null;
	        this.cs = null;
	        this.ps = null;
	        this.rsmd = null;
	        this.jndiPoolNo = jndiPoolNo;
	    }
	    
	    int getConnection() {
	        try {
	            this.poolSelf ="false";
	            if ("true".equals(this.poolSelf)) {
	                final DataSource dataSource =	 DbPoolList.getPool(this.jndiPoolNo);
	                this.conn = (Connection) dataSource.getConnection();
	            }
	            else {
	                if (this.jndiPoolNo == 0) {
	                    this.poolName = "jdbc/local_gw_mysql_jdbc";
	                }
	                else {
	                    this.poolName = "jdbc/local_gw_mysql_jdbc";
	                }
	                if (!"".equals(this.poolName) && this.poolName != null) {
	                    final Context initContext = new InitialContext();
	                    final Context envContext = (Context)initContext.lookup("java:/comp/env");
	                    final DataSource dataSource2 = (DataSource)envContext.lookup(this.poolName);
	                    this.conn = (Connection) dataSource2.getConnection();
	                }
	                else {
	                    this.conn = DbConnect.getInstance().getConnection();
	                }
	            }
	            return 0;
	        }
	        catch (Exception e) {
	            this.error.info((String)("Get DbConnection error. ErrorMessage=" + e), (Throwable)e);
	            this.alert.info((String)"errorcode=DB11111 mess=\"new dbbean get dbpool err\"");
	            return 1;
	        }
	        finally {
	            DbConnInfo.getInstance().add(this.conn);
	        }
	    }
	    
	    public String getCount(final String sql, final Object[] param) {
	        return this.getT(sql, param, 0, "")[0];
	    }
	    
	    public String getSum(final String sql, final Object[] param, final String fieldName) {
	        return this.getT(sql, param, 1, fieldName)[0];
	    }
	    
	    public String[] getCountAndSum(final String sql, final Object[] param, final String fieldName) {
	        return this.getT(sql, param, 2, fieldName);
	    }
	    
	    private String[] getT(final String sql, final Object[] param, final int mode, final String fieldName) {
	        final String[] count = { "0", "0" };
	        String SQL = "";
	        if (mode == 0) {
	            SQL = countWrap(sql);
	        }
	        if (mode == 1) {
	            SQL = sumWrap(sql, fieldName);
	        }
	        if (mode == 2) {
	            SQL = countAndSumWrap(sql, fieldName);
	        }
	        if (this.getConnection() != 0) {
	            return null;
	        }
	        final int inParamCount = param.length;
	        try {
	            this.cs = this.conn.prepareCall(SQL);
	            if (inParamCount > 0) {
	                for (int i = 0; i < inParamCount; ++i) {
	                    if (param[i] == null) {
	                        this.cs.setObject(i + 1, "");
	                    }
	                    else {
	                        this.cs.setObject(i + 1, param[i]);
	                    }
	                }
	            }
	            this.cs.execute();
	            this.rs = this.cs.getResultSet();
	            if (this.rs.next()) {
	                count[0] = this.rs.getString(1);
	            }
	            if (mode == 2) {
	                count[1] = this.rs.getString(2);
	            }
	            return count;
	        }
	        catch (Exception e) {
	            System.err.println("Error in DBBean.execQuerySQL (" + SQL + ",param,inParamCount): " + e);
	            return count;
	        }
	        finally {
	            this.disConnection();
	        }
	    }
	    
	    private void log(final String procExpression, final String[] inParam, final Object[] result, final long startTime, final Logger logmsg) { }
	    
	    private List rs2List(final ResultSet rs, final String classname) {
	        final ArrayList<Object> result = new ArrayList<Object>();
	        try {
	            while (rs != null && rs.next()) {
	                final ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
	                final int columnCount = rsmd.getColumnCount();
	                Object c1 = null;
	                c1 = Class.forName(classname).newInstance();
	                for (int i = 1; i <= columnCount; ++i) {
	                    final String fieldName = rsmd.getColumnName(i);
	                    if (!"RN".equals(fieldName.toUpperCase())) {
	                        String feildValue;
	                        if (rs.getString(fieldName) != null) {
	                            feildValue = rs.getString(fieldName);
	                        }
	                        else {
	                            feildValue = "";
	                        }
	                        try {
	                            final Method m = c1.getClass().getMethod("set" + convertName(fieldName), feildValue.getClass());
	                            m.invoke(c1, feildValue);
	                        }
	                        catch (Exception ex2) {}
	                    }
	                }
	                result.add(c1);
	            }
	            result.trimToSize();
	            return result;
	        }
	        catch (Exception ex) {
	            this.error.info((String)("rs2List error:  " + ex), (Throwable)ex);
	            this.alert.info((String)"errorcode=DB11111 mess=\"new dbbean Result>list err\"");
	            return new ArrayList();
	        }
	    }
	    
	    private static String convertName(final String _s) {
	        final char[] c = _s.toCharArray();
	        final StringBuffer sb = new StringBuffer("");
	        sb.append(Character.toUpperCase(c[0]));
	        for (int i = 1; i < c.length; ++i) {
	            if (jdbc.UNDERLINE.equals(c[i])) {
	                if (i + 1 < c.length && !jdbc.UNDERLINE.equals(c[i + 1])) {
	                    sb.append(Character.toUpperCase(c[i + 1]));
	                    ++i;
	                }
	            }
	            else {
	                sb.append(Character.toLowerCase(c[i]));
	            }
	        }
	        return sb.toString();
	    }
	    
	    private static String countWrap(String sql) {
	        final String _s = sql.toLowerCase();
	        final int _start = _s.indexOf("from");
	        final int _end = _s.lastIndexOf("order");
	        final int _end2 = _s.lastIndexOf("by");
	        if (_end >= 0 && _end2 >= 0 && _end2 - _end > 5) {
	            sql = "select count(*) " + sql.substring(_start, _end);
	        }
	        else {
	            sql = "select count(*) " + sql.substring(_start);
	        }
	        return sql;
	    }
	    
	    private static String countAndSumWrap(String sql, final String filedName) {
	        final String _s = sql.toLowerCase();
	        final int _start = _s.indexOf("from");
	        final int _end = _s.lastIndexOf("order");
	        final int _end2 = _s.lastIndexOf("by");
	        if (_end >= 0 && _end2 >= 0 && _end2 - _end > 5) {
	            sql = "select count(*),nvl(sum(" + filedName + "),0)  " + sql.substring(_start, _end);
	        }
	        else {
	            sql = "select  count(*),nvl(sum(" + filedName + "),0) " + sql.substring(_start);
	        }
	        return sql;
	    }
	    
	    private static String sumWrap(String sql, final String filedName) {
	        final String _s = sql.toLowerCase();
	        final int _start = _s.indexOf("from");
	        final int _end = _s.lastIndexOf("order");
	        final int _end2 = _s.lastIndexOf("by");
	        if (_end >= 0 && _end2 >= 0 && _end2 - _end > 5) {
	            sql = "select nvl(sum(" + filedName + "),0) " + sql.substring(_start, _end);
	        }
	        else {
	            sql = "select nvl(sum(" + filedName + "),0) " + sql.substring(_start);
	        }
	        return sql;
	    }
	    
	    void disConnection() {
	        this.rsmd = null;
	        if (this.cs != null) {
	            try {
	                this.cs.close();
	            }
	            catch (Exception ex) {}
	            this.cs = null;
	        }
	        if (this.rs != null) {
	            try {
	                this.rs.close();
	            }
	            catch (Exception ex2) {}
	            this.rs = null;
	        }
	        if (this.stmt != null) {
	            try {
	                this.stmt.close();
	            }
	            catch (Exception ex3) {}
	            this.stmt = null;
	        }
	        DbConnInfo.getInstance().free(this.conn);
	        if (this.conn != null) {
	            try {
	                this.conn.close();
	            }
	            catch (Exception e) {
	                this.error.info((String)("conn close Error" + e), (Throwable)e);
	            }
	            this.conn = null;
	        }
	    }
	    
	    private void rollback() {
	        try {
	            this.conn.rollback();
	            this.conn.setAutoCommit(true);
	        }
	        catch (Exception ex) {
	            this.error.info((String)("Error in DBBean.rollback: " + ex.getMessage()), (Throwable)ex);
	            return;
	        }
	        finally {
	            this.disConnection();
	        }
	        this.disConnection();
	    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
