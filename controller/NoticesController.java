package org.wgh.handshop.controller;


import org.springframework.web.bind.annotation.*;
import org.wgh.handshop.entity.Notices;
import org.wgh.handshop.service.NoticesService;
import org.wgh.handshop.util.StatusCode;
import org.wgh.handshop.vo.LayuiPageVo;
import org.wgh.handshop.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  消息通知控制器
 * </p>
 *
 * @author hlt
 * @since 2019-12-25
 */
@RestController
public class NoticesController {
    @Autowired
    private NoticesService noticesService;

    /**
     * 用户查看通知消息后
     * 1.前端传入通知id（id）
     * 2.将其设置为已读
     * */
    @ResponseBody
    @PutMapping("/notices/look/{id}")
    public ResultVo LookNoticesById (@PathVariable("id") String id) {
        Integer i = noticesService.updateNoticesById(id);
        if (i == 1){
            return new ResultVo(true, StatusCode.OK,"设置成功");
        }
        return new ResultVo(true, StatusCode.ERROR,"设置失败");
    }

    /**
     *查询前10条公告
     * **/
    @ResponseBody
    @GetMapping("/notices/queryNotices")
    public ResultVo queryNotices (HttpSession session){
        String userid = (String) session.getAttribute("userid");
        List<Notices> noticesList = noticesService.queryNotices(userid);
        return new ResultVo(true,StatusCode.OK,"查询成功",noticesList);
    }

    /**
     * 取消新通知标志
     * 用户点击查看最新通知后会将所有通知设置为非最新通知
     * */
    @ResponseBody
    @GetMapping("/notices/cancelLatest")
    public ResultVo CancelLatest (HttpSession session){
        String userid = (String) session.getAttribute("userid");
        Integer i = noticesService.CancelLatest(userid);
        if (i == 1){
            return new ResultVo(true,StatusCode.OK,"设置成功");
        }
        return new ResultVo(true,StatusCode.ERROR,"设置失败");
    }

    /**
     * 分类分页查询用户所有通知消息
     * 1.前端传入消息通知类型（tpname）
     * 2.session中获取用户id（userid）
     * 3.返回分页数据
     * */
    @ResponseBody
    @GetMapping("/notices/queryall")
    public LayuiPageVo queryallSold(int limit, int page, HttpSession session) {
        String userid = (String) session.getAttribute("userid");
        List<Notices> noticesList = noticesService.queryAllNotices((page - 1) * limit, limit, userid);
        Integer dataNumber = noticesService.queryNoticesCount(userid);
        return new LayuiPageVo("", 0,dataNumber,noticesList);
    }

}

