package com.payProject.config.common;

public class AutocompleteResult<T> {
	private String name;
	private String pinyin;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPinyin() {
		return pinyin;
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	public AutocompleteResult() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AutocompleteResult(String name, String pinyin) {
		super();
		this.name = name;
		this.pinyin = pinyin;
	}
}
