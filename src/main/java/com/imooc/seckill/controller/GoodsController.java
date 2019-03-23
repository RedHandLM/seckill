package com.imooc.seckill.controller;

import com.imooc.seckill.domain.MiaoshaUser;
import com.imooc.seckill.result.Result;
import com.imooc.seckill.vo.GoodsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 登陆控制
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    private static final Logger log = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    private com.imooc.seckill.service.GoodsService goodsService;


    @RequestMapping("/to_list")
    public String toGoodsList(HttpServletResponse response, Model model, MiaoshaUser miaoshaUser) {
        model.addAttribute("user", miaoshaUser);
        List<GoodsVo> goodsList = goodsService.listGoodsVo();
        model.addAttribute("goodsList", goodsList);
        return "goods_list";
    }


    @RequestMapping("/to_detail/{goodsId}")
    public String toGoodDetail(HttpServletResponse response, Model model, MiaoshaUser miaoshaUser,
                               @PathVariable("goodsId") long goodsId) {
        GoodsVo goodsVo = goodsService.getGoodsVoByGoodsId(goodsId);
        model.addAttribute("goods", goodsVo);


        long beginTime = goodsVo.getStartDate().getTime();
        long endTime = goodsVo.getEndDate().getTime();
        long now = System.currentTimeMillis();

        int miaoshaStatus = 0;
        int remainSeconds = 0;//秒杀剩余时间 单位:s

        if (now < beginTime) {//秒杀没开始  倒计时
            miaoshaStatus = 0;
            remainSeconds = (int) ((beginTime - now) / 1000);
        } else if (now > endTime) {//秒杀已结束
            miaoshaStatus = 2;
            remainSeconds = -1;
        } else {//秒杀进行中
            miaoshaStatus = 1;
            remainSeconds = 0;
        }
        model.addAttribute("miaoshaStatus", miaoshaStatus);
        model.addAttribute("remainSeconds", remainSeconds);
        model.addAttribute("user", miaoshaUser);
        return "goods_detail";
    }


}
