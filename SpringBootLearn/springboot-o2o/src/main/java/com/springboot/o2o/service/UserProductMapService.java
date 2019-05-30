package com.springboot.o2o.service;

import com.springboot.o2o.dto.UserProductMapExecution;
import com.springboot.o2o.domain.UserProductMap;

public interface UserProductMapService {
	/**
	 * 
	 * @param shopId
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	UserProductMapExecution listUserProductMap(
        UserProductMap userProductCondition, Integer pageIndex,
        Integer pageSize);

	/**
	 * 
	 * @param userProductMap
	 * @return
	 * @throws RuntimeException
	 */
	UserProductMapExecution addUserProductMap(UserProductMap userProductMap)
			throws RuntimeException;

}
