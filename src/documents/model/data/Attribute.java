package documents.model.data;

import java.sql.Timestamp;

public class Attribute {
	
	private int attribute_type; 
	private int data_type;
	private String name;
	private boolean req;
	private int orderby;
		
	private Timestamp date;
	
	private Selection[] selections;
	
	private String text;
	
	private int value;
	
	private String defaultValue;
	
	

	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Selection[] getSelections() {
		return selections;
	}
	public void setSelections(Selection[] selections) {
		this.selections = selections;
	}
	public Timestamp getDate() {
		return date;
	}
	public int getOrderby() {
		return orderby;
	}
	public void setOrderby(int orderby) {
		this.orderby = orderby;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public int getAttribute_type() {
		return attribute_type;
	}
	public void setAttribute_type(int attribute_type) {
		this.attribute_type = attribute_type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isReq() {
		return req;
	}
	public void setReq(boolean req) {
		this.req = req;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	public int getData_type() {
		return data_type;
	}
	public void setData_type(int data_type) {
		this.data_type = data_type;
	}
	

	
}
