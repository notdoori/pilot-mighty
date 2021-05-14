package com.pilot.mighty.model;

public class MultiLanguageInfo {
	
	private int no;
	private String langCode;
	private String langTyp;
	private String langData;
	
	public MultiLanguageInfo() {}
	
	public MultiLanguageInfo(int no, String langCode, String langTyp, String langData) {
		super();
		this.no = no;								// 카운트 번호
		this.langCode = langCode;				// 다국어 코드
		this.langTyp = langTyp;					// 다국어 타입
		this.langData = langData;				// 다국어 데이터
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}
	
	public String getLangCode() {
		return langCode;
	}
	
	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}
	
	public String getLangTyp() {
		return langTyp;
	}
	
	public void setLangTyp(String langTyp) {
		this.langTyp = langTyp;
	}
	
	public String getLangData() {
		return langData;
	}
	
	public void setLangData(String langData) {
		this.langData = langData;
	}
}

//public class MultiLanguageInfo {
//	
//	private int no;
//	private String langKeyword;
//	private String langCode;
//	private String langText;
//	
//	public MultiLanguageInfo() {}
//	
//	public MultiLanguageInfo(int no, String langKeyword, String langCode, String langText) {
//		super();
//		this.no = no;								// 카운트 번호
//		this.langKeyword = langKeyword;	// 다국어 키워드
//		this.langCode = langCode;				// 다국어 코드
//		this.langText = langText;				// 다국어 문자열
//	}
//
//	public int getNo() {
//		return no;
//	}
//
//	public void setNo(int no) {
//		this.no = no;
//	}
//	
//	public String getLangKeyword() {
//		return langKeyword;
//	}
//	
//	public void setLangKeyword(String langKeyword) {
//		this.langKeyword = langKeyword;
//	}
//	
//	public String getLangCode() {
//		return langCode;
//	}
//	
//	public void setLangCode(String langCode) {
//		this.langCode = langCode;
//	}
//	
//	public String getLangText() {
//		return langText;
//	}
//	
//	public void setLangText(String langText) {
//		this.langText = langText;
//	}
//}
