package com.payProject.manage.service;

import java.util.List;

import com.payProject.config.entity.Dictionary;

public interface DictionaryService {

	/**
	 * <p>增加一个数据字典值</p>
	 * @param dictionary
	 * @return
	 */
	Boolean addDictionary(Dictionary dictionary);

	/**
	 * <p>分页查询数据字典数据</p>
	 * @param dictionary
	 * @return
	 */
	List<Dictionary> findDictionaryByDictionary(Dictionary dictionary);
	/**
	 * <p>删除一个数据</p>
	 * @param id
	 * @return
	 */
	boolean deleteDictionaryByDictionaryId(Integer id);

	/**
	 * <p>根据数据字典id查询数字典数据</p>
	 * @param id
	 * @return
	 */
	Dictionary findDictionaryById(Integer id);

	/**
	 * <p>根据字典id更新一个数据</p>
	 * @param dictionary
	 * @return
	 */
	boolean UpdateDictionaryById(Dictionary dictionary);

}
