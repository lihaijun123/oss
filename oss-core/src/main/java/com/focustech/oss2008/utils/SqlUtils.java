package com.focustech.oss2008.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.Hibernate;

/**
 * <li>處理JDBC的工具類</li>
 *
 * @author yangpeng 2008-5-30 上午09:37:19
 */
public class SqlUtils {
    /**
     * 根據傳入的輸入流，構造Blob字段
     *
     * @throws IOException
     */
    public static Blob createBlob(InputStream in) throws IOException {
        return Hibernate.createBlob(in);
    }

    public static void close(ResultSet res) {
        if (res != null) {
            try {
                res.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String formatClobToString(Clob clob) throws SQLException {
        String content = "";
        char ac[] = new char[200];
        Reader reader;
        reader = clob.getCharacterStream();
        int i;
        try {
            while ((i = reader.read(ac, 0, 200)) != -1)
                content = content + new String(ac, 0, i);
        }
        catch (Exception e) {
            throw new SQLException(e.getMessage());
        }
        finally {
            try {
                reader.close();
            }
            catch (Exception _ex) {
            }
        }
        return content;
    }
}
