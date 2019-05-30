package com.springboot.o2o.service;

import com.springboot.o2o.dto.UserShopMapExecution;
import com.springboot.o2o.domain.UserShopMap;

public interface UserShopMapService {

	UserShopMapExecution listUserShopMap(UserShopMap userShopMapCondition,
                                         int pageIndex, int pageSize);

}
