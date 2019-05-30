/*
 * Copyright 2001-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.springboot.o2o.service;

import com.springboot.o2o.dao.ShopDao;
import com.springboot.o2o.dto.ShopExcution;
import com.springboot.o2o.entity.Shop;
import com.springboot.o2o.enums.ShopStateEnum;
import com.springboot.o2o.exception.ShopOperationException;
import com.springboot.o2o.util.ImageUtil;
import com.springboot.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.Date;

/**
 * <p> Title: </p>
 *
 * <p> Description: </p>
 *
 * @author: Guo.Weifeng
 * @version: 1.0
 * @create: 2019/5/7 11:19
 */
@Service
public class ShopService {
    @Autowired
    private ShopDao shopDao;

    @Transactional
    public ShopExcution addShop(Shop shop, CommonsMultipartFile shopImg){
        // 非空字段的验证
        if (shop == null || shop.getShopId() == null || shop.getArea() == null
            || shop.getShopCategory() == null || shop.getShopName() == null){
            return new ShopExcution(ShopStateEnum.NULL_SHOP);
        }

        try {
            // 存储图片，返回图片地址
            addShopImg(shop, shopImg);
            // 1. 设置初始的店铺信息--状态，创建时间，修改时间，权限等
            shop.setLastEditTime(new Date());
            shop.setCreateTime(new Date());
            shop.setEnableStatus(0);

            // 插入店铺
            int effectedNum = shopDao.insertShop(shop);
            if (effectedNum <= 0){
                throw new ShopOperationException("店铺创建失败");
            }
        } catch (Exception e){
            throw new ShopOperationException("addShop error:" + e.getMessage());
        }
        // 没有问题，则店铺创建成功，当前处于审核中
        return new ShopExcution(ShopStateEnum.CHECK, shop);
    }

    private void addShopImg(Shop shop, CommonsMultipartFile shopImg){
        String dest = PathUtil.getShopImagePath(shop.getShopId());
        String shopImgAddr = ImageUtil.generateThumbnail(shopImg, dest);

        shop.setShopImg(shopImgAddr);
    }
}