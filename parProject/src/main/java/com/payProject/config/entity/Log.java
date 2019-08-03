package com.payProject.config.entity;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.payProject.system.entity.User;
import com.payProject.system.entity.base.BaseEntity;

/**
 * <p>系统日志记录类</>
 * @author K
 * 2019-08-02 12:34:56
 *
 */
public class Log  extends BaseEntity<Log>{

	/**
	 * <p>客户端ip</p>
	 */
    private String clientIp;
    /**
	 * <p>请求路径</p>
	 */
    private String requertUrl;
    /**
	 * <p>请求方法</p>
	 */
    private String metHod;
    /**
	 * <p>参数</p>
	 */
    private String param;
    /**
	 * <p>操作人</p>
	 */
    private String operator;
    /**
	 * <p>请求开始时间</p>
	 */
    private String startTime;
    /**
	 * <p>请求结束时间</p>
	 */
    private String endTime;
    /**
	 * <p>操作用时</p>
	 */
    private String totalTime;
    /**
     * <p>保留字段</p>
     */
    private String retain1;
    /**
     * <p>保留字段</p>
     */
    private String retain2;
    /**
     * <p>保留字段</p>
     */
    private String retain3;
    /**
     * <p>保留字段</p>
     */
    private String retain4;
    /**
     * <p>保留字段</p>
     */
    private String retain5;
    /**
     * <p>保留字段</p>
     */
    private String retain6;
    /**
     * <p>保留字段</p>
     */
    private String retain7;
    /**
     * <p>保留字段</p>
     */
    private String retain8;
    /**
     * <p>返回数据</p>
     */
    private String returnData;


    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp == null ? null : clientIp.trim();
    }

    public String getRequertUrl() {
        return requertUrl;
    }

    public void setRequertUrl(String requertUrl) {
        this.requertUrl = requertUrl == null ? null : requertUrl.trim();
    }

    public String getMetHod() {
        return metHod;
    }

    public void setMetHod(String metHod) {
        this.metHod = metHod == null ? null : metHod.trim();
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param == null ? null : param.trim();
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime == null ? null : startTime.trim();
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime == null ? null : endTime.trim();
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime == null ? null : totalTime.trim();
    }

    public String getRetain1() {
        return retain1;
    }

    public void setRetain1(String retain1) {
        this.retain1 = retain1 == null ? null : retain1.trim();
    }

    public String getRetain2() {
        return retain2;
    }

    public void setRetain2(String retain2) {
        this.retain2 = retain2 == null ? null : retain2.trim();
    }

    public String getRetain3() {
        return retain3;
    }

    public void setRetain3(String retain3) {
        this.retain3 = retain3 == null ? null : retain3.trim();
    }

    public String getRetain4() {
        return retain4;
    }

    public void setRetain4(String retain4) {
        this.retain4 = retain4 == null ? null : retain4.trim();
    }

    public String getRetain5() {
        return retain5;
    }

    public void setRetain5(String retain5) {
        this.retain5 = retain5 == null ? null : retain5.trim();
    }

    public String getRetain6() {
        return retain6;
    }

    public void setRetain6(String retain6) {
        this.retain6 = retain6 == null ? null : retain6.trim();
    }

    public String getRetain7() {
        return retain7;
    }

    public void setRetain7(String retain7) {
        this.retain7 = retain7 == null ? null : retain7.trim();
    }

    public String getRetain8() {
        return retain8;
    }

    public void setRetain8(String retain8) {
        this.retain8 = retain8 == null ? null : retain8.trim();
    }

    public String getReturnData() {
        return returnData;
    }

    public void setReturnData(String returnData) {
        this.returnData = returnData == null ? null : returnData.trim();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}