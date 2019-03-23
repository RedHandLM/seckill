package com.imooc.seckill.service;

import com.imooc.seckill.dao.UserDao;
import com.imooc.seckill.domain.MiaoshaUser;
import com.imooc.seckill.domain.User;
import com.imooc.seckill.redis.MiaoShaUserKey;
import com.imooc.seckill.redis.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by liushichang on 2019/3/20.
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RedisService redisService;


    /**
     * 测试获取数据
     *
     * @param id
     * @return
     */
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }


    /**
     * 测试DB事务
     *
     * @return
     */
    @Transactional
    public boolean tx() {
        User user1 = new User();
        user1.setId(2);
        user1.setName("222");
        userDao.insert(user1);

        User user2 = new User();
        user2.setId(1);
        user2.setName("111111");
        userDao.insert(user2);
        return true;
    }

}
