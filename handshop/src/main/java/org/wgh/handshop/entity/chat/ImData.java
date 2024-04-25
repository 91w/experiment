package org.wgh.handshop.entity.chat;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.wgh.handshop.entity.UserInfo;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class ImData {
    private UserInfo mine;
    private List<Friend> friend;
    private List<Groups> group;
}
