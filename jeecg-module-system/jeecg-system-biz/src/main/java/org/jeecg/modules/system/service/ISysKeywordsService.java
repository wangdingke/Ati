package org.jeecg.modules.system.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.modules.system.entity.SysKeywords;
import org.jeecg.modules.system.entity.SysUser;
import org.jeecg.modules.system.vo.SysKeywordsReqVo;

import javax.servlet.http.HttpServletRequest;

public interface ISysKeywordsService extends IService<SysKeywords> {
    Result<IPage<SysKeywords>> queryPageList(HttpServletRequest req, QueryWrapper<SysKeywords> queryWrapper, Integer pageSize, Integer pageNo);

    Result<IPage<SysKeywords>> queryPageListByKeywords(HttpServletRequest req, QueryWrapper<SysKeywords> queryWrapper, Integer pageSize, Integer pageNo,SysKeywordsReqVo sysKeywordsReqVo);

    Result<SysKeywords> keywordsDetail(LoginUser sysUser, SysKeywordsReqVo sysKeywordsReqVo);
}
