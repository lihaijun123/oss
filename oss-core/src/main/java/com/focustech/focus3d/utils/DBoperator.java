package com.focustech.focus3d.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import com.focustech.cief.filemanage.core.FileManage;
import com.focustech.common.utils.SpringUtil;
import com.focustech.common.utils.TCUtil;

public class DBoperator {
	private DataSource ciefDataSource;

	public DBoperator() {
        ciefDataSource = (DataSource) SpringUtil.getBean("ossDataSource");
	}

    /**
     * 通过文件编号获取文件信息
     *
     * @param id
     * @return
     */
	@Deprecated
    public Map<String, String> getFileInfo(int id) {
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection con = null;
        Map<String, String> returnMap = new HashMap<String, String>();
        String sql = "SELECT NAME,SIZE,TYPE,HEIGHT,WIDTH,LENGTH,VISIT_ADDR,SN,EXT  FROM com_file_data WHERE SN = ?";
		try {
            con = ciefDataSource.getConnection();
            stmt = con.prepareStatement(sql);
            stmt.setLong(1, id);
            rs = stmt.executeQuery();
			while (rs.next()) {
                returnMap.put(FileManage.NAME, TCUtil.sv(rs.getString(1)));
                returnMap.put(FileManage.SIZE, TCUtil.sv(rs.getFloat(2)));
                returnMap.put(FileManage.TYPE, TCUtil.sv(rs.getString(3)));
                returnMap.put(FileManage.HEIGHT, TCUtil.sv(rs.getInt(4)));
                returnMap.put(FileManage.WIDTH, TCUtil.sv(rs.getInt(5)));
                returnMap.put(FileManage.LENGTH, TCUtil.sv(rs.getString(6)));
                returnMap.put(FileManage.VISIT_ADDR, TCUtil.sv(rs.getString(7)));
                returnMap.put(FileManage.SN, TCUtil.sv(rs.getLong(8)));
                returnMap.put(FileManage.EXT, TCUtil.sv(rs.getString(9)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
        }
        finally {
            try {
                if (null != rs) {
                    rs.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (null != stmt) {
                    stmt.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (null != con) {
                    con.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
		}
        return returnMap;
	}
}
