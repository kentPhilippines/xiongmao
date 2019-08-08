package com.payProject.system.util;

import java.util.ArrayList;
import java.util.List;

import com.payProject.system.entity.Role;

/**
 * <p>layui转换框组件工具类转换</p>
 * @author K
 * 2019-08-08
 */
public class TransferUtil {
	/**
	 * <p>所有的角色集合</p>
	 * @param list			所有角色集合
	 * @return
	 */
	public static List<TransferBean> getTransferBeanList(List<Role> list){ 
		List<TransferBean> listBean  = new ArrayList<TransferBean>();
		for(Role role : list) {
			TransferBean bean = new TransferBean();
			bean.setTitle(role.getRoleName());
			bean.setValue(String.valueOf(role.getRoleId()));
			bean.setDisabled(false);
			listBean.add(bean);
		}
		return listBean;
	}
	
	/**
	 * <p>拿到自己关注的角色</p>
	 * @param list			所有角色集合
	 * @param checkedList	自己关注的角色Id
	 * @return
	 */
	public  static List<TransferBean> getTransferBeanList(List<Role> list, List<Integer> checkedList){
		List<TransferBean> listBean  = new ArrayList<TransferBean>();
		for(Role role : list) {
			TransferBean bean = new TransferBean();
			if(checkedList.contains(role.getRoleId())){
				bean.setTitle(role.getRoleName());
				bean.setValue(String.valueOf(role.getRoleId()));
				bean.setDisabled(false);
				listBean.add(bean);
			}
		}
		return listBean;
	}
	
	/**
	 * <p>拿到自己没有关注的角色</p>
	 * @param list			所有角色集合
	 * @param checkedList	自己关注的角色Id
	 * @return
	 */
	public static List<TransferBean> getReduceTransferBean(List<Role> list, List<Integer> checkedList){
		List<TransferBean> listBean  = new ArrayList<TransferBean>();
		for(Role role : list) {
			TransferBean bean = new TransferBean();
			if(!checkedList.contains(role.getRoleId())){
				bean.setTitle(role.getRoleName());
				bean.setValue(String.valueOf(role.getRoleId()));
				bean.setDisabled(false);
				listBean.add(bean);
			}
		}
		return listBean;
	}

}
