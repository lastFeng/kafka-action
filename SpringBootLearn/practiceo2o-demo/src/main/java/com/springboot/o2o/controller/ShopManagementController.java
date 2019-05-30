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
package com.springboot.o2o.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.o2o.dto.ShopExcution;
import com.springboot.o2o.entity.Area;
import com.springboot.o2o.entity.PersonInfo;
import com.springboot.o2o.entity.Shop;
import com.springboot.o2o.entity.ShopCategory;
import com.springboot.o2o.enums.ShopStateEnum;
import com.springboot.o2o.service.AreaService;
import com.springboot.o2o.service.ShopCategoryService;
import com.springboot.o2o.service.ShopService;
import com.springboot.o2o.util.CodeUtil;
import com.springboot.o2o.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> Title: </p>
 *
 * <p> Description: </p>
 *
 * @author: Guo.Weifeng
 * @version: 1.0
 * @create: 2019/5/7 13:26
 */
@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {
    @Autowired
    private ShopService shopService;

    @Autowired
    private ShopCategoryService shopCategoryService;

    @Autowired
    private AreaService areaService;

    @PostMapping("/registershop")
    @ResponseBody
    private Map<String, Object> registerShop(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<String, Object>();

        // 验证码
        if (!CodeUtil.checkVerifyCode(request)){
            errorModelMap(modelMap, false, "输入了错误的验证码");
            return modelMap;
        }

        // 接收信息
        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;

        try {
            shop = mapper.readValue(shopStr, Shop.class);
        }catch (Exception e){
            errorModelMap(modelMap, false, e.getMessage());
        }

        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (commonsMultipartResolver.isMultipart(request)){
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
        } else {
            errorModelMap(modelMap, false, "上传图片不能为空");
            return modelMap;
        }
        // 注册店铺
        if (shop != null && shopImg != null){
            PersonInfo owner = new PersonInfo();
            owner.setUserId(1L);
            shop.setOwner(owner);
            ShopExcution shopExcution = shopService.addShop(shop, shopImg);

            if (shopExcution.getState() == ShopStateEnum.CHECK.getState()){
                modelMap.put("success", true);
            } else {
                errorModelMap(modelMap, false, shopExcution.getStateInfo());
            }

        } else {
            errorModelMap(modelMap, false, "请输入店铺信息");
        }
        // 返回结果
        return modelMap;
    }

    private void errorModelMap(Map<String, Object> modelMap, boolean success, String errMsg){
        modelMap.put("success", success);
        modelMap.put("errMsg", errMsg);
    }

    @GetMapping("/getshopinitinfo")
    @ResponseBody
    private Map<String, Object> getShopInitInfo(){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        List<ShopCategory> shopCategoryList = new ArrayList<ShopCategory>();
        List<Area> areaList = new ArrayList<Area>();

        try {
            // 店铺都存储在二级类别之下
            shopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
            areaList = areaService.getAreaList();
            modelMap.put("success", true);
            modelMap.put("shopCategoryList", shopCategoryList);
            modelMap.put("areaList", areaList);
        } catch (Exception e){
            errorModelMap(modelMap, false, e.getMessage());
        }
        return modelMap;
    }
}