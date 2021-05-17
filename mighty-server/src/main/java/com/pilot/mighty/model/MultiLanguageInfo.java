package com.pilot.mighty.model;

public class MultiLanguageInfo {
	
	private int no;
	private String langCode;
	private String langTyp;
	private String langData;
	
	private String langKo;
	private String langEn;
	private String langCn;
	private String langVn;
	
	public MultiLanguageInfo() {}
	
	public MultiLanguageInfo(int no, String langCode, String langTyp, String langData, String langKo, String langEn, String langCn, String langVn) {
		super();
		this.no = no;								// 카운트 번호
		this.langCode = langCode;				// 다국어 코드
		this.langTyp = langTyp;					// 다국어 타입
		this.langData = langData;				// 다국어 데이터
		
		this.langKo = langKo;
		this.langEn = langEn;
		this.langCn = langCn;
		this.langVn = langVn;
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
	
	public String getLangKo() {
		return langKo;
	}
		
	public void setLangKo(String langKo) {
		this.langKo = langKo;
	}
	
	public String getLangEn() {
		return langEn;
	}
	
	public void setLangEn(String langEn) {
		this.langEn = langEn;
	}
	
	public String getLangCn() {
		return langCn;
	}
	
	public void setLangCn(String langCn) {
		this.langCn = langCn;
	}
	
	public String getLangVn() {
		return langVn;
	}
	
	public void setLangVn(String langVn) {
		this.langVn = langVn;
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
