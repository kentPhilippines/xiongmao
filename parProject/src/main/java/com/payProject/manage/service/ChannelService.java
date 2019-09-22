package com.payProject.manage.service;

import java.util.List;

import com.payProject.manage.entity.Channel;
import com.payProject.manage.entity.ChannelFee;
import com.payProject.manage.entity.PayType;

/**
 * <p>关于渠道业务</p>
 * @author K
 */
public interface ChannelService {
	/**
	 * <p>分页展示渠道信息</p>
	 * @param channel
	 * @return
	 */
	List<Channel> findPageChannelByChannel(Channel channel);
	/**
	 * <p>分页展示渠道费率信息</p>
	 * @param channel
	 * @return
	 */
	List<ChannelFee> findPageChannelFeeByChannelFee(ChannelFee channelFee);
	/**
	 * <p>分页产品信息</p>
	 * @param channel
	 * @return
	 */
	List<PayType> findPagePayTypeByPayType(PayType payType);
	/**
	 * <p>获取最新的渠道创建</p>
	 * @return
	 */
	Channel findChannelFirst();
	/**
	 * <p>增加一个渠道</p>
	 * @param channel
	 * @return
	 */
	Boolean addChannel(Channel channel);
	/**
	 * <p>根据渠道号查询渠道信息</p>
	 * @param channelNo
	 * @return
	 */
	List<Channel> findChannelByChannelId(String channelNo);
	/**
	 * <p>根据渠道编号阐述渠道</p>
	 * @param channelNo
	 * @return
	 */
	boolean deleteChannelByNO(String channelNo);
	/**
	 * <p>获取最新的渠支付方式创建</p>
	 * @return
	 */
	PayType findPayTypeFirst();
	/**
	 * <p>根据支付编号查询支付方式</p>
	 * @param payTypeNo		系统支付编号唯一
	 * @return
	 */
	List<PayType> findPayTypeByNo(String payTypeNo);
	/**
	 * <p>根据支付编号删除一个支付方式</p>
	 * @param payTypeNo
	 * @return
	 */
	boolean deletePayTypeByNO(String payTypeNo);
	/**
	 * <p>增加一个支付方式</p>
	 * @param payType
	 * @return
	 */
	Boolean addPayType(PayType payType);
	/**
	 * </p>查询所有渠道信息</p>
	 * @return
	 */
	List<Channel> findChannelByAll();
	/**
	 * <p>查询所有的支付方式</p>
	 * @return
	 */
	List<PayType> findPayTypeByAll();
	/**
	 * <p>根据渠道编号和产品类型查询渠道成本费率</p>
	 * @param channelNo
	 * @param payType
	 * @return
	 */
	ChannelFee findChannelFee(String channelNo, String payType);
	/**
	 * <p>增加一个渠道费率</p>
	 * @param channelFee
	 * @return
	 */
	Boolean addChannelFee(ChannelFee channelFee);
	/**
	 * <p>删除一个渠道成本费率</p>
	 * @param id
	 * @return
	 */
	boolean deleteChannelByI(Integer id);
	/**
	 * <p>根据渠道id修改渠道</p>
	 * @param first
	 * @return
	 */
	boolean updataChannel(Channel first);
	/**
	 * <p>根据渠道费率ID查询渠道费率</p>
	 * @param id
	 * @return
	 */
	List<ChannelFee> findChannelFeeById(Integer id);
	/**
	 * <p>修改渠道费率，根据渠道费率Id</p>
	 * @param first
	 * @return
	 */
	boolean updataChannelFee(ChannelFee first);
	/**
	 * <p>根据账户集合查询集合里面账户所对应的所有费率集合</p>
	 * @param accountList
	 * @return
	 */
	List<ChannelFee> findChannelByAccountList(List<String> accountList);
	/**
	 * <p>根据支付产品编号集合查询产婆名</p>
	 * @param payList
	 * @return
	 */
	List<PayType> findPayTypeByListNo(List<String> payList);

}
