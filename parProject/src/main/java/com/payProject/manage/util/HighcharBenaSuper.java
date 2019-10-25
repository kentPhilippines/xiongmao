package com.payProject.manage.util;

import java.util.List;

public class HighcharBenaSuper<t> {
	private List timeList;
	private t obj;
	
	public t getObj() {
		return obj;
	}
	public void setObj(t obj) {
		this.obj = obj;
	}
	public List getTimeList() {
		return timeList;
	}

	public void setTimeList(List timeList) {
		this.timeList = timeList;
	}
	@Override
	public String toString() {
		return "HighcharBenaSuper [timeList=" + timeList + ", obj=" + obj + "]";
	}
}
