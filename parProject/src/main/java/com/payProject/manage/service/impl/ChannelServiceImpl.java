package com.payProject.manage.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payProject.manage.entity.Channel;
import com.payProject.manage.entity.ChannelExample;
import com.payProject.manage.entity.ChannelExample.Criteria;
import com.payProject.manage.entity.ChannelFee;
import com.payProject.manage.entity.ChannelFeeExample;
import com.payProject.manage.entity.PayType;
import com.payProject.manage.entity.PayTypeExample;
import com.payProject.manage.mapper.ChannelFeeMapper;
import com.payProject.manage.mapper.ChannelMapper;
import com.payProject.manage.mapper.PayTypeMapper;
import com.payProject.manage.service.ChannelService;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
@Service
public class ChannelServiceImpl implements ChannelService {
	@Autowired
	PayTypeMapper PayTypeDao;
	@Autowired
	ChannelFeeMapper ChannelFeeDao;
	@Autowired
	ChannelMapper ChannelDao;
	@Override
	public List<Channel> findPageChannelByChannel(Channel channel) {
		ChannelExample example  = new ChannelExample();
		Criteria criteria = example.createCriteria();
		if(StrUtil.isNotBlank(channel.getChannelNo()))
			criteria.andChannelNoEqualTo(channel.getChannelNo());
		if(StrUtil.isNotBlank(channel.getChannelName()))
			criteria.andChannelNameEqualTo(channel.getChannelName());
		List<Channel> selectByExample = ChannelDao.selectByExample(example);
		return selectByExample;
	}
	@Override
	public List<ChannelFee> findPageChannelFeeByChannelFee(ChannelFee channelFee) {
		ChannelFeeExample example  = new ChannelFeeExample();
		com.payProject.manage.entity.ChannelFeeExample.Criteria criteria = example.createCriteria();
		if(StrUtil.isNotBlank(channelFee.getChannelNo()))
			criteria.andChannelNoEqualTo(channelFee.getChannelNo());
		if(StrUtil.isNotBlank(channelFee.getChannelName()))
			criteria.andChannelNameEqualTo(channelFee.getChannelName());
		if(StrUtil.isNotBlank(channelFee.getPayType()))  
			criteria.andPayTypeEqualTo(channelFee.getPayType());
		List<ChannelFee> selectByExample = ChannelFeeDao.selectByExample(example);
		return selectByExample;
	}
	@Override
	public List<PayType> findPagePayTypeByPayType(PayType payType) {
		PayTypeExample example = new PayTypeExample();
		com.payProject.manage.entity.PayTypeExample.Criteria criteria = example.createCriteria();
		if(StrUtil.isNotBlank(payType.getPayTypeNo()))
			criteria.andPayTypeNoEqualTo(payType.getPayTypeNo());
		if(StrUtil.isNotBlank(payType.getPayTypeName()))
			criteria.andPayTypeNameEqualTo(payType.getPayTypeName());
		List<PayType> selectByExample = PayTypeDao.selectByExample(example);
		return selectByExample;
	}
	@Override
	public Channel findChannelFirst() {
		ChannelExample example  = new ChannelExample();
		Criteria criteria = example.createCriteria();
		example.setOrderByClause("createTime DESC");
		List<Channel> selectByExample = ChannelDao.selectByExample(example);
		if(CollUtil.isEmpty(selectByExample)) {
			return null;
		}
		Channel first = CollUtil.getFirst(selectByExample);
		return first;
	}
	@Override
	public Boolean addChannel(Channel channel) {
		int insertSelective = ChannelDao.insertSelective(channel);
		return insertSelective > 0 && insertSelective < 2;
	}
	@Override
	public List<Channel> findChannelByChannelId(String channelNo) {
		ChannelExample example  = new ChannelExample();
		Criteria criteria = example.createCriteria();
		if(StrUtil.isNotBlank(channelNo))
			criteria.andChannelNoEqualTo(channelNo);
		return ChannelDao.selectByExample(example);
	}
	@Override
	public boolean deleteChannelByNO(String channelNo) {
		ChannelExample example  = new ChannelExample();
		Criteria criteria = example.createCriteria();
		if(StrUtil.isNotBlank(channelNo))
			criteria.andChannelNoEqualTo(channelNo);
		int deleteByExample = ChannelDao.deleteByExample(example);
		return deleteByExample > 0 && deleteByExample < 2;
	}
	@Override
	public PayType findPayTypeFirst() {
		PayTypeExample example  = new PayTypeExample();
		com.payProject.manage.entity.PayTypeExample.Criteria criteria = example.createCriteria();
		example.setOrderByClause("createTime DESC");
		List<PayType> selectByExample = PayTypeDao.selectByExample(example);
		if(CollUtil.isEmpty(selectByExample)) {
			return null;
		}
		PayType first = CollUtil.getFirst(selectByExample);
		return first;
	}
	@Override
	public List<PayType> findPayTypeByNo(String payTypeNo) {
		PayTypeExample example  = new PayTypeExample();
		com.payProject.manage.entity.PayTypeExample.Criteria criteria = example.createCriteria();
		if(StrUtil.isNotBlank(payTypeNo))
			criteria.andPayTypeNoEqualTo(payTypeNo);
		List<PayType> selectByExample = PayTypeDao.selectByExample(example);
		return selectByExample;
	}
	@Override
	public boolean deletePayTypeByNO(String payTypeNo) {
		PayTypeExample example  = new PayTypeExample();
		com.payProject.manage.entity.PayTypeExample.Criteria criteria = example.createCriteria();
		if(StrUtil.isNotBlank(payTypeNo))
			criteria.andPayTypeNoEqualTo(payTypeNo);
		int deleteByExample = PayTypeDao.deleteByExample(example);
		return deleteByExample > 0 && deleteByExample < 2;
	}
	@Override
	public Boolean addPayType(PayType payType) {
		int insertSelective = PayTypeDao.insertSelective(payType);
		return insertSelective > 0 && insertSelective <2;
	}
	@Override
	public List<Channel> findChannelByAll() {
		ChannelExample example  = new ChannelExample();
		Criteria criteria = example.createCriteria();
		List<Channel> selectByExample = ChannelDao.selectByExample(example);
		return selectByExample;
	}
	@Override
	public List<PayType> findPayTypeByAll() {
		PayTypeExample example = new PayTypeExample();
		com.payProject.manage.entity.PayTypeExample.Criteria criteria = example.createCriteria();
		List<PayType> selectByExample = PayTypeDao.selectByExample(example);
		return selectByExample;
	}
	@Override
	public ChannelFee findChannelFee(String channelNo, String payType) {
		ChannelFeeExample example  = new ChannelFeeExample();
		com.payProject.manage.entity.ChannelFeeExample.Criteria criteria = example.createCriteria();
		if(StrUtil.isNotBlank(channelNo))
			criteria.andChannelNoEqualTo(channelNo);
		if(StrUtil.isNotBlank(payType))  
			criteria.andPayTypeEqualTo(payType);
		List<ChannelFee> selectByExample = ChannelFeeDao.selectByExample(example);
		if(CollUtil.isEmpty(selectByExample))
			return null;
		return CollUtil.getFirst(selectByExample);
	}
	@Override
	public Boolean addChannelFee(ChannelFee channelFee) {
		ChannelExample example  = new ChannelExample();
		Criteria criteria = example.createCriteria();
		if(StrUtil.isNotBlank(channelFee.getChannelNo()))
			criteria.andChannelNoEqualTo(channelFee.getChannelNo());
		  List<Channel> selectByExample = ChannelDao.selectByExample(example);
		  Channel first = CollUtil.getFirst(selectByExample);
		  channelFee.setChannelName(first.getChannelName());
		  channelFee.setChannelAccount(first.getChannelAccount());
		int insertSelective = ChannelFeeDao.insertSelective(channelFee);
		return insertSelective > 0 && insertSelective < 2;
	}
	@Override
	public boolean deleteChannelByI(Integer id) {
		int deleteByPrimaryKey = ChannelFeeDao.deleteByPrimaryKey(id);
		return deleteByPrimaryKey > 0 && deleteByPrimaryKey < 2;
	}
	@Override
	public boolean updataChannel(Channel first) {
		ChannelExample example  = new ChannelExample();
		Criteria criteria = example.createCriteria();
		criteria.andChannelNoEqualTo(first.getChannelNo());
		int updateByExampleSelective = ChannelDao.updateByExampleSelective(first, example);
		return updateByExampleSelective > 0 && updateByExampleSelective < 2;
	}
	@Override
	public List<ChannelFee> findChannelFeeById(Integer id) {
		ChannelFeeExample example  = new ChannelFeeExample();
		com.payProject.manage.entity.ChannelFeeExample.Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(id);
		List<ChannelFee> selectByExample = ChannelFeeDao.selectByExample(example);
		return selectByExample;
	}
	@Override
	public boolean updataChannelFee(ChannelFee first) {
		ChannelFeeExample example  = new ChannelFeeExample();
		com.payProject.manage.entity.ChannelFeeExample.Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(first.getId());
		int updateByExample = ChannelFeeDao.updateByExample(first, example);
		return updateByExample > 0 && updateByExample < 2;
	}
	@Override
	public List<ChannelFee> findChannelByAccountList(List<String> accountList) {
		ChannelFeeExample example  = new ChannelFeeExample();
		com.payProject.manage.entity.ChannelFeeExample.Criteria criteria = example.createCriteria();
		criteria.andChannelAccountListEqualTo(accountList);
		List<ChannelFee> selectByExample = ChannelFeeDao.selectByExample(example);
		return selectByExample;
	}
	@Override
	public List<PayType> findPayTypeByListNo(List<String> payList) {
		PayTypeExample example = new PayTypeExample();
		com.payProject.manage.entity.PayTypeExample.Criteria criteria = example.createCriteria();
		criteria.andPayTypeNoListEqualTo(payList);
		List<PayType> selectByExample = PayTypeDao.selectByExample(example);
		return selectByExample;
	}
	@Override
	public List<PayType> findProductToChannel(String channelNo) {
		ChannelFeeExample example  = new ChannelFeeExample();
		com.payProject.manage.entity.ChannelFeeExample.Criteria criteria = example.createCriteria();
		criteria.andChannelNoEqualTo(channelNo);
		List<ChannelFee> selectByExample = ChannelFeeDao.selectByExample(example);
		List<String> list  = new ArrayList<String>();
		for(ChannelFee  cf : selectByExample) {
			list.add(cf.getPayType());
		};
		PayTypeExample example1 = new PayTypeExample();
		com.payProject.manage.entity.PayTypeExample.Criteria criteria1 = example1.createCriteria();
		criteria1.andPayTypeNoListEqualTo(list);
		List<PayType> selectByExample1 = PayTypeDao.selectByExample(example1);
		return selectByExample1;
	}
}
