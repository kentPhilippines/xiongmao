package com.payProject.config.base.mapperBase;


import java.util.List;

import org.apache.ibatis.annotations.Param;

@org.apache.ibatis.annotations.Mapper
public interface MyMapper<T, E>   {
	 /**
		 * <p>增加数据</p>
		 * @param example		<strong>固定数据,存在值则有值,不存在则为null</strong>
		 * @return				<strong>数据条数</strong>
		 */
	int insert(T record);
	 /**
		 * <p>增加数据</p>
		 * @param example		<strong>动态查询类【统计条件】</strong>
		 * @return				<strong>数据条数</strong>
		 */
	int insertSelective(T record);
	/**
	 * <p>统计数据条数</p>
	 * @param example		<strong>动态查询类【统计条件】</strong>
	 * @return				<strong>数据条数</strong>
	 */
    int countByExample(E example);
    /**
     * <p>查询数据集合</p>
     * @param example		<strong>动态查询类【统计条件】</strong>
     * @return 				<strong>数据集合</strong>
     */
    List<T> selectByExample(E example);
    /**
     * <p>动态跟新实体类数据,以实体类数据id为索引</p>
     * @param record			实体类
     * @param example			动态条件
     * @return
     */
    int updateByExampleSelective(@Param("record") T record, @Param("example") E example);
    int updateByExample(@Param("record") T record, @Param("example") E example);
}
