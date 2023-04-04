package org.jeecg.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@TableName("sys_order")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Data
public class Sys_order implements Serializable {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * '订单编号
     */
    private String order_number;

    /**
     * 用户id
     */
    private String user_id;

    /**
     * 订单状态
     */
    private String order_statue;

    /**
     * 支付方式
     */
    private String payway;

    /**
     * 真实价格
     */
    private String real_price;

    /**
     * 关键字
     */
    private String keywords;
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
