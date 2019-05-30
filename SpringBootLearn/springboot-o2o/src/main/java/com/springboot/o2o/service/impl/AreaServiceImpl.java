package com.springboot.o2o.service.impl;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.o2o.dao.AreaDao;
import com.springboot.o2o.domain.Area;
import com.springboot.o2o.dto.AreaExecution;
import com.springboot.o2o.enums.AreaStateEnum;
import com.springboot.o2o.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {
	//@Autowired
	//private JedisUtil.Strings jedisStrings;
	//@Autowired
	//private JedisUtil.Keys jedisKeys;
	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private AreaDao areaDao;

	private static String AREALISTKEY = "arealist";

	@Override
	public List<Area> getAreaList() throws JsonParseException,
        JsonMappingException, IOException {
		String key = AREALISTKEY;
		List<Area> areaList = null;
		ObjectMapper mapper = new ObjectMapper();
		if (redisTemplate.opsForValue().get(key) == null) {
			areaList = areaDao.queryArea();
			String jsonString = mapper.writeValueAsString(areaList);
			redisTemplate.opsForValue().set(key, jsonString);
		} else {
			String jsonString = (String) redisTemplate.opsForValue().get(key);
			JavaType javaType = mapper.getTypeFactory()
					.constructParametricType(ArrayList.class, Area.class);
			areaList = mapper.readValue(jsonString, javaType);
		}
		return areaList;
	}

	@Override
	@Transactional
	public AreaExecution addArea(Area area) {
		if (area.getAreaName() != null && !"".equals(area.getAreaName())) {
			area.setCreateTime(new Date());
			area.setLastEditTime(new Date());
			try {
				int effectedNum = areaDao.insertArea(area);
				if (effectedNum > 0) {
					String key = AREALISTKEY;
					if (redisTemplate.opsForValue().get(key) != null) {
						redisTemplate.delete(key);
					}
					return new AreaExecution(AreaStateEnum.SUCCESS, area);
				} else {
					return new AreaExecution(AreaStateEnum.INNER_ERROR);
				}
			} catch (Exception e) {
				throw new RuntimeException("添加区域信息失败:" + e.toString());
			}
		} else {
			return new AreaExecution(AreaStateEnum.EMPTY);
		}
	}

	@Override
	@Transactional
	public AreaExecution modifyArea(Area area) {
		if (area.getAreaId() != null && area.getAreaId() > 0) {
			area.setLastEditTime(new Date());
			try {
				int effectedNum = areaDao.updateArea(area);
				if (effectedNum > 0) {
					String key = AREALISTKEY;
					if (redisTemplate.opsForValue().get(key) != null) {
						redisTemplate.delete(key);
					}
					return new AreaExecution(AreaStateEnum.SUCCESS, area);
				} else {
					return new AreaExecution(AreaStateEnum.INNER_ERROR);
				}
			} catch (Exception e) {
				throw new RuntimeException("更新区域信息失败:" + e.toString());
			}
		} else {
			return new AreaExecution(AreaStateEnum.EMPTY);
		}
	}

	@Override
	@Transactional
	public AreaExecution removeArea(long areaId) {
		if (areaId > 0) {
			try {
				int effectedNum = areaDao.deleteArea(areaId);
				if (effectedNum > 0) {
					String key = AREALISTKEY;
					if (redisTemplate.opsForValue().get(key) != null) {
						redisTemplate.delete(key);
					}
					return new AreaExecution(AreaStateEnum.SUCCESS);
				} else {
					return new AreaExecution(AreaStateEnum.INNER_ERROR);
				}
			} catch (Exception e) {
				throw new RuntimeException("删除区域信息失败:" + e.toString());
			}
		} else {
			return new AreaExecution(AreaStateEnum.EMPTY);
		}
	}

	@Override
	@Transactional
	public AreaExecution removeAreaList(List<Long> areaIdList) {
		if (areaIdList != null && areaIdList.size() > 0) {
			try {
				int effectedNum = areaDao.batchDeleteArea(areaIdList);
				if (effectedNum > 0) {
					String key = AREALISTKEY;
					if (redisTemplate.opsForValue().get(key) != null) {
						redisTemplate.delete(key);
					}
					return new AreaExecution(AreaStateEnum.SUCCESS);
				} else {
					return new AreaExecution(AreaStateEnum.INNER_ERROR);
				}
			} catch (Exception e) {
				throw new RuntimeException("删除区域信息失败:" + e.toString());
			}
		} else {
			return new AreaExecution(AreaStateEnum.EMPTY);
		}
	}
}
