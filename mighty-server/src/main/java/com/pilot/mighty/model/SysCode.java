package com.pilot.mighty.model;

public class SysCode {
	
	private int no;
	private String name;
	private String value;
	private String use;
	
	public SysCode() {}	
	
	public SysCode(int no, String name, String value, String use) {
		super();
		this.no = no;
		this.name = name;
		this.value = value;
		this.use = use;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public String getUse() {
		return use;
	}

	public void setUse(String use) {
		this.use = use;
	}
	
}
