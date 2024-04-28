package org.wgh.handshop.controller;

import org.springframework.web.bind.annotation.*;
import org.wgh.handshop.entity.Soldrecord;
import org.wgh.handshop.service.SoldrecordService;
import org.wgh.handshop.util.StatusCode;
import org.wgh.handshop.vo.LayuiPageVo;
import org.wgh.handshop.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  销售记录控制器
 * </p>
 *
 * @author hlt
 * @since 2019-12-21
 */
@RestController
public class SoldrecordController {
    @Autowired
    private SoldrecordService soldrecordService;

    /**
     * 删除售出记录
     * 1.前端传入需删除记录的id（id）
     * 2.判断是否是本人
     * */
    @ResponseBody
    @PutMapping("/soldrecord/delect/{id}")
    public ResultVo delectSold (@PathVariable("id") String id) {
        Integer i = soldrecordService.deleteSold(id);
        if (i == 1){
            return new ResultVo(true, StatusCode.OK,"删除记录成功");
        }
        return new ResultVo(false, StatusCode.ERROR,"删除记录失败");
    }

    /**
     * 分页查看用户所有售出记录
     * 1.前端传入页码、分页数量
     * 2.查询分页数据
     */
    @ResponseBody
    @GetMapping("/soldrecord/lookuser")
    public LayuiPageVo LookUserSold(int limit, int page, HttpSession session) {
        String userid = (String) session.getAttribute("userid");
        List<Soldrecord> soldrecordList = soldrecordService.queryAllSoldrecord((page - 1) * limit, limit, userid);
        Integer dataNumber = soldrecordService.querySoldCount(userid);
        return new LayuiPageVo("",0,dataNumber,soldrecordList);
    }

    /**
     * 分页查看全部的售出记录
     * 1.前端传入页码、分页数量
     * 2.查询分页数据
     */
    @ResponseBody
    @GetMapping("/soldrecord/queryall")
    public LayuiPageVo queryAllSold(int limit, int page) {
        List<Soldrecord> soldrecordList = soldrecordService.queryAllSoldrecord((page - 1) * limit, limit, null);
        Integer dataNumber = soldrecordService.querySoldCount(null);
        return new LayuiPageVo("",0,dataNumber,soldrecordList);
    }

}

