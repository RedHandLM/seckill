package com.imooc.seckill.service;

import com.imooc.seckill.dao.GoodsDao;
import com.imooc.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liushichang on 2019/3/21.
 */
@Service
public class GoodsService {
    @Autowired
    private GoodsDao goodsDao;


    /**
     * 查询商品列表
     *
     * @return
     */
    public List<GoodsVo> listGoodsVo() {
        return goodsDao.listGoodsVo();
    }

    /**
     * 查询商品详情
     *
     * @param goodsId
     * @return
     */
    public GoodsVo getGoodsVoByGoodsId(long goodsId) {
        return goodsDao.getGoodsVoByGoodsId(goodsId);
    }
}
