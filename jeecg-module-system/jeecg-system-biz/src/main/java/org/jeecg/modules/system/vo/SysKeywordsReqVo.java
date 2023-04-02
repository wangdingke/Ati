package org.jeecg.modules.system.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysKeywordsReqVo implements Serializable {
    private String keywords;
    private Integer pageNo;
    private Integer pageSize;
    private String keywordsId;
    private String userIdentity; //用户身份   1买家 2卖家 3.管理员
    private String urlSource;//连接来源 1是买家搜索页面 2是 买家查询页面，3是后台页面

}
