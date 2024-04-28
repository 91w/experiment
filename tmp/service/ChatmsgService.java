package org.wgh.handshop.service;

//import com.entity.UserInfo;
//import com.entity.chat.ChatMsg;
//import com.mapper.ChatmsgMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.wgh.handshop.entity.UserInfo;
import org.wgh.handshop.entity.chat.ChatMsg;
import org.wgh.handshop.mapper.ChatmsgMapper;

import java.util.List;

@Service
public class ChatmsgService {

    @Resource
    ChatmsgMapper chatmsgMapper;


    /**插入发送的消息记录*/
    @Async
    public void insertChatmsg(ChatMsg chatmsg){
        chatmsgMapper.insertChatmsg(chatmsg);
    }

    /**查询聊天记录*/
    public List<UserInfo> LookChatMsg(ChatMsg chatMsg){
        return chatmsgMapper.LookChatMsg(chatMsg);
    }
}
