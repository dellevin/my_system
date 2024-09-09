/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package io.dellevin.service;

import io.dellevin.commons.dynamic.datasource.annotation.DataSource;
import io.dellevin.modules.sys.dao.SysUserDao;
import io.dellevin.modules.sys.entity.SysUserEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 测试多数据源
 *
 * @author Mark sunlightcs@gmail.com
 */
@Service
//@DataSource("slave1")
public class DynamicDataSourceTestService {
    @Resource
    private SysUserDao sysUserDao;

    //@Transactional
    public void updateUser(Long id) {
        SysUserEntity user = new SysUserEntity();
        user.setId(id);
        user.setMobile("13500000000");
        //sysUserDao.updateById(user);
        System.out.println(sysUserDao.selectById(id));
    }

    @DataSource("slave1")
    @Transactional
    public void updateUserBySlave1(Long id) {
        SysUserEntity user = new SysUserEntity();
        user.setId(id);
        user.setMobile("13500000001");
        //sysUserDao.updateById(user);
        System.out.println(sysUserDao.selectById(id));
    }

//    @DataSource("slave2")
//    @Transactional
//    public void updateUserBySlave2(Long id){
//        SysUserEntity user = new SysUserEntity();
//        user.setId(id);
//        user.setMobile("13500000002");
//        sysUserDao.updateById(user);
//
//        //测试事物
//        int i = 1/0;
//    }
}