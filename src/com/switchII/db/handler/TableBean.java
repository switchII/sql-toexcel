package com.switchII.db.handler;

import java.util.List;

/**
 * 表数据结构实体类
 * @author Anton
 *
 */
public class TableBean {
	
	//表名称
	private String tableName ; 
 
	//所有列名
	List<ColumnBean> columns ; 
	
	public List<ColumnBean> getColumns() {
		return columns;
	}
	public void setColumns(List<ColumnBean> columns) {
		this.columns = columns;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
}
