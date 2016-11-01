package com.switchII.db.handler;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.switchII.db.datasource.DbConnect;

/**
 * 操作数据库
 * 
 * @author Anton
 * 
 */
public class MySqlHandler {

	/**
	 * 得到所有的数据库表
	 * 
	 * @return
	 */
	public List<String> queryAllTableName() {
		try {
			List<String> tableList = new ArrayList<String>();
			DatabaseMetaData dmd = (DatabaseMetaData) DbConnect.getInstance()
					.getMetaData();
			ResultSet rs = dmd.getTables(null, null, "%", null);
			while (rs.next()) {
				String tableName = rs.getString("TABLE_NAME");
				tableList.add(tableName);
			}
			rs.close();
			return tableList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 得到所有的实体类
	 * 
	 * @return
	 */
	public List<TableBean> queryTables() {
		List<TableBean> tableBeans = new ArrayList<TableBean>();
		List<String> tables = this.queryAllTableName();
		for (String table : tables) {
			TableBean t = queryBeanByTable(table);
			tableBeans.add(t);
		}
		return tableBeans;
	}

	/**
	 * 根据表名查询表的基本信息
	 * 
	 * @param table
	 * @return
	 */
	private TableBean queryBeanByTable(String table) {
		Connection conn = DbConnect.getInstance();
		List<ColumnBean> columns = new ArrayList<ColumnBean>();
		TableBean t = new TableBean();
		t.setTableName(table) ; 
		String sql = "SHOW FULL FIELDS FROM " + table;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()){
			    String field = rs.getString("Field") ; 
			    String type = rs.getString("Type") ; 
			    String isNull = rs.getString("Null") ; 
			    String isKey = rs.getString("Key") ; 
			    String isDefault = rs.getString("Default") ; 
			    String comments = rs.getString("Comment") ; 
			    
			    ColumnBean c = new ColumnBean() ; 
			    
			    c.setComments(comments) ; 
			    c.setDefaultVal(isDefault) ; 
			    c.setField(field) ; 
			    c.setIsKey(isKey) ; 
			    c.setIsNull(isNull) ; 
			    c.setType(type) ; 
			    
			    columns.add(c) ; 
			    
			}
			t.setColumns(columns) ; 
			rs.close();
			stmt.close();
			return t ; 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null ; 
	}

}
