package org.jeecg.modules.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.modules.system.entity.SysKeywordsNonentity;
import org.jeecg.modules.system.entity.SysKeywordsRecords;
import org.jeecg.modules.system.mapper.SysDictMapper;
import org.jeecg.modules.system.mapper.SysKeywordsMapper;
import org.jeecg.modules.system.mapper.SysKeywordsNonentityMapper;
import org.jeecg.modules.system.mapper.SysKeywordsRecordsMapper;
import org.jeecg.modules.system.security.DictQueryBlackListHandler;
import org.jeecg.modules.system.service.ISysKeywordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/sys/keywordsNonentity")
@Api(tags="关键词不存在查询")
public class SysKeywordsNonentityController {
    @Autowired
    SysDictMapper sysDictMapper;

    @Autowired
    DictQueryBlackListHandler dictQueryBlackListHandler;

    @Autowired
    private SysKeywordsNonentityMapper sysKeywordsNonentityMapper;
    /**
     * 获取系全部关键词不存在查询（查询全部关键词）
     *
     * @param sysKeywordsNonentity
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */

    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @AutoLog(value = "获取系全部关键词不存在查询")
    @ApiOperation(value = "获取系全部关键词不存在查询", notes = "获取系全部关键词不存在查询")
    public Result<IPage<SysKeywordsNonentity>> queryAllKeywordsNonentity(SysKeywordsNonentity sysKeywordsNonentity, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                                       @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize, HttpServletRequest req) {
        Result<IPage<SysKeywordsNonentity>> result = new Result<IPage<SysKeywordsNonentity>>();
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        //sysKeywords.setUserId(sysUser.getUsername());
        log.info("当前用户是:{}",sysUser.getUsername());
        QueryWrapper<SysKeywordsNonentity> queryWrapper = QueryGenerator.initQueryWrapper(sysKeywordsNonentity, req.getParameterMap());

        Page<SysKeywordsNonentity> page = new Page<SysKeywordsNonentity>(pageNo, pageSize);
        Page<SysKeywordsNonentity> keywordsNonentityPage = sysKeywordsNonentityMapper.selectPage(page, queryWrapper);
        result.setResult(keywordsNonentityPage);
        result.setSuccess(true);
        return result;
    }


}
