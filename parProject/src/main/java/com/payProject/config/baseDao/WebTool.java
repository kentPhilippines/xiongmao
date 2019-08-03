package com.payProject.config.baseDao;
/**
 * <p>这是一个分页工具 主要用于显示页码</p>
 *　<li>pagecode　要获得记录的开始索引　即　开始页码</li>
 * <li>pageNow 　当前页</li>
 *　<li>pageCount 总页数</li>
 *  <li>这个工具类　返回的是页索引　PageIndex</li>
 * @version 1.0v
 */
public class WebTool {
	  public static PageIndex getPageIndex(long pagecode, int pageNow, long pageCount){
			long startpage = pageNow-(pagecode%2==0? pagecode/2-1 : pagecode/2);
			long endpage = pageNow+pagecode/2;
			if(startpage<1){
				startpage = 1;
				if(pageCount>=pagecode) endpage = pagecode;
				else endpage = pageCount;
			}
			if(endpage>pageCount){
				endpage = pageCount;
				if((endpage-pagecode)>0) startpage = endpage-pagecode+1;
				else startpage = 1;
			}
			return new PageIndex(startpage, endpage);		
	  }

}
