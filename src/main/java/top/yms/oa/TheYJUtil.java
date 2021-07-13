package top.yms.oa;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TheYJUtil {

    private final static Log LOGGER = LogFactory
            .getLog(TheYJUtil.class);

    private static DataSet dsR;
    private static Connection conC ;

    static {
        try {
            dsR = new DataSet("项目立项数据");

        } catch (Exception e) {
            LOGGER.error("ds ==> dsR连接数据库（项目立项数据）失败");
        }
        try {
            conC = dsR.getConnection();
        } catch (Exception e) {
            LOGGER.error("ds ==> conC 连接数据库（项目立项数据）失败");
        }
    }

    /**
     * PreparedStatement 参数绑定方法。
     *
     * @param wpstmt PreparedStatement
     * @param params 传入的绑定参数。注意顺序一致
     * @throws Exception
     */
    private static TheYResult bindParams(PreparedStatement wpstmt, Object[] params)  {
        if (params == null || params.length == 0) return new TheYResult(false,"params is null", new Exception("params is null"));
        try {
            for (int i = 0; i < params.length; i++) {
                Object match = params[i];
                int idx = i + 1;

                if (match instanceof Integer) {
                    wpstmt.setInt(idx, (int) match);
                } else if (match instanceof String) {
                    wpstmt.setString(idx, match.toString());
                } else if (match instanceof Double) {
                    wpstmt.setDouble(idx, (double) match);
                } else if (match instanceof Long) {
                    wpstmt.setLong(idx, (long) match);
                } else if (match instanceof Byte) {
                    wpstmt.setByte(idx, (byte) match);
                } else if (match instanceof Short) {
                    wpstmt.setShort(idx, (Short) match);
                } else if (match instanceof Float) {
                    wpstmt.setFloat(idx, (Float) match);
                } else if (match instanceof java.sql.Date) {
                    wpstmt.setDate(idx, (java.sql.Date) match);
                } else if (match instanceof Boolean) {
                    wpstmt.setBoolean(idx, (boolean) match);
                } else if (match instanceof java.math.BigDecimal) {
                    wpstmt.setBigDecimal(idx, (java.math.BigDecimal) match);
                } else if (match instanceof Array) {
                    wpstmt.setArray(idx, (Array) match);
                } else {
                    wpstmt.setObject(idx, match);
                }
            }

        } catch (Exception e) {
            return new TheYResult(false,"bindParams异常",e);
        }

        return new TheYResult(true,"OK");
    }

    private static Connection getConn() {
        DataSet ds = new DataSet("项目立项数据");
        Connection connection = ds.getConnection();
        return connection;
    }

    public static ResultSet doGetResultSet(String sql, Object ...parms) throws Exception{
        DataSet ds = dsR;
        Connection connection = conC;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        TheYResult theYResult = bindParams(preparedStatement, parms);
        ResultSet rs = null;
        if (theYResult.isSuccess()) {
            rs = preparedStatement.executeQuery();
        } else {
            LOGGER.error("doGetResultSet: Error. \n"+theYResult.getDetail());
            throw new Exception((Exception)theYResult.getResult());
        }
       // preparedStatement.close();
        //connection.close();
        //ds.close();

        return rs;
    }


    public static int doUpdate(String sql, Object ...parms) throws Exception{
        DataSet ds = dsR;
        Connection connection = conC;
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        TheYResult theYResult = bindParams(preparedStatement, parms);
        int res = 0;
        if (theYResult.isSuccess()) {
            res = preparedStatement.executeUpdate();
            connection.commit();
        } else {
            connection.rollback();
            LOGGER.error("doGetResultSet: Error. \n"+theYResult.getDetail());
            throw new Exception((Exception)theYResult.getResult());
        }
        preparedStatement.close();
        connection.close();
        ds.close();

        return res;
    }

}
