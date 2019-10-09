package parProject.com.test.test;

import cn.hutool.core.util.StrUtil;

public class Test {
	public static void main(String[] args) {
		String url  = "http://tinyurl.com/y56v9bw7";
		String removePrefixIgnoreCase = StrUtil.removePrefixIgnoreCase(url,"http://");
		System.out.println(removePrefixIgnoreCase);
	}

}
