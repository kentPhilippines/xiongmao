package com.payProject.manage.util;

import java.util.List;

public class HighcharBena {
	private String name;
	private List data;//理论上是一个集合
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List getData() {
		return data;
	}
	public void setData(List data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "HighcharBena [name=" + name + ", data=" + data + "]";
	}
}
