package com.switchII.db.handler;

/**
 * 字段属性
 * @author Anton
 *
 */
public class ColumnBean {
	//字段
	private String field ; 
	
	//类型
	private String type ; 
	
	//是否为空
	private String isNull ; 
	
	//是否为主键
	private String isKey ; 
	
	//默认值 
	private String defaultVal ; 
	
	//注释 
	private String comments ;
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIsNull() {
		return isNull;
	}
	public void setIsNull(String isNull) {
		this.isNull = isNull;
	}
	public String getIsKey() {
		return isKey;
	}
	public void setIsKey(String isKey) {
		this.isKey = isKey;
	}
	public String getDefaultVal() {
		return defaultVal;
	}
	public void setDefaultVal(String defaultVal) {
		this.defaultVal = defaultVal;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	} 
	
}
