package org.jeecg.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_keywords")
public class SysKeywords implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 客户号
     */
    private String userId;

    /**
     * 关键字
     */
    private String keywords;

    /**
     * 关键字价格
     */
    private String keywordsPrice;

    /**
     * 关键字类型（也可以叫标签）
     */
    private String keywordsType;


    /**
     * 关键字状态（0 待审核 1审核通过 2审核拒绝 4上架 5下架）
     */
    private String keywordsStatus;

    /**
     * 关键字审核原因
     */
    private String keywordsReason;


    /**
     * 关键字售出数量
     */
    private String keywordsSoldCount;


    /**
     * 关键字搜索数量
     */
    private String keywordsSearchCount;

    /**
     * 关键字介绍
     */
    private String keywordsIntroduce;

    /**
     * 关键字图片存放地址
     */
    private String keywordsFilePath;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;




}
