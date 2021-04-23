package com.pilot.mighty.model;

public class MultiLanguageInfo {
	
	private String langText;
	
	public MultiLanguageInfo() {}
	
	public MultiLanguageInfo(String langText) {
		super();
		this.langText = langText;			// 다국어 내용
	}

	public String getLangText() {
		return langText;
	}
	
	public void setLangText(String langText) {
		this.langText = langText;
	}
}
