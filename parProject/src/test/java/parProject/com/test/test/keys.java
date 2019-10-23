package parProject.com.test.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;

public class keys {
	public static void main(String[] args) {
		List<String> clist = new ArrayList();
		List<String> plist = new ArrayList();
		List<String> alist = new ArrayList();
		clist.add("CH10000");
		clist.add("CH10001");
		plist.add("PAY1000");
		plist.add("PAY1001");
		alist.add("AC1000");
		alist.add("AC1001");
		List<List> kets = new ArrayList();
		kets.add(clist);
		kets.add(plist);
		kets.add(alist);
		List<String> k = new ArrayList();
		List<String> kets2 = getKets(kets,k);
		System.out.println(kets2.toString());
	}
	private static List<String> getKets(List<? extends Collection> list , List<String> lists) {
		List<String> keyList = new ArrayList();
			for(int i = 0 ; i<list.size(); i++) {
				Collection<String> collection = list.get(i);
				for(int j = list.size()-1; j>i; j--) {
					Collection<String> key = list.get(j);
					for(String keyi : collection) {
						for(String keyj : key) {
								lists.add(keyi+keyj);
						}
					}
				}
			}
			 for(int i = 0 ; i<list.size(); i++ ) {
				 Collection<String> collection = list.get(i);
				 for(String keyi : collection) {
					 for(String key : lists) {
						 if(!key.contains(keyi)) {
							 keyList.add(key+keyi);
						 }else {
							 keyList.add(key);
						 }
					 }
				 }
			 }
			 for(String str : keyList) {
				 str = str.replaceAll("[^a-z^A-Z]", "");
				 int length = str.length();
				 int a =  length/2;
				 System.out.println(str);
				 String subPre = StrUtil.subPre(str,a);
				 String subSuf = StrUtil.subSuf(str,a);
				 System.out.println(subPre);
				 System.out.println(subSuf);
				 System.out.println("---------");
			 }
		return keyList;
	}

}
