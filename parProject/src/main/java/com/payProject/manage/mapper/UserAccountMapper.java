package com.payProject.manage.mapper;

import com.payProject.manage.entity.UserAccount;
import com.payProject.manage.entity.UserAccountExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface UserAccountMapper {
    int countByExample(UserAccountExample example);
    int deleteByExample(UserAccountExample example);
    int deleteByPrimaryKey(Integer id);
    int insert(UserAccount record);
    int insertSelective(UserAccount record);
    List<UserAccount> selectByExample(UserAccountExample example);
    UserAccount selectByPrimaryKey(Integer id);
    int updateByExampleSelective(@Param("record") UserAccount record, @Param("example") UserAccountExample example);
    int updateByExample(@Param("record") UserAccount record, @Param("example") UserAccountExample example);
    int updateByPrimaryKeySelective(UserAccount record);
    int updateByPrimaryKey(UserAccount record);
    List<UserAccount>  selectByExampleAnd(UserAccount record);
    /**
     * <p>根据代理商账号查询所有商户号</p>
     * @param account
     * @return
     */
	List<UserAccount> findUserAccountByUserId(UserAccount account);
    
    
}