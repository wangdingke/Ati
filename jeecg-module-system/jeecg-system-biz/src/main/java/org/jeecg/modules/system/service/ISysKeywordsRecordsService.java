package org.jeecg.modules.system.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.modules.system.entity.SysKeywords;
import org.jeecg.modules.system.entity.SysKeywordsRecords;
import org.jeecg.modules.system.vo.SysKeywordsReqVo;

import javax.servlet.http.HttpServletRequest;

public interface ISysKeywordsRecordsService extends IService<SysKeywordsRecords> {

}
