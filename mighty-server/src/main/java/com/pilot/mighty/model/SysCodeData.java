package com.pilot.mighty.model;

public class SysCodeData {
	
	private String name;
	private String value;
	private int seq;
	
	public SysCodeData() {}	
	
	public SysCodeData(String name, String value, int seq) {
		super();
		this.name = name;
		this.value = value;
		this.seq = seq;
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
	
	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}
	
}
