package org.jeecg.modules.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.modules.system.entity.SysKeywords;
import org.jeecg.modules.system.entity.SysKeywordsRecords;
import org.jeecg.modules.system.mapper.SysDictMapper;
import org.jeecg.modules.system.mapper.SysKeywordsMapper;
import org.jeecg.modules.system.mapper.SysKeywordsRecordsMapper;
import org.jeecg.modules.system.security.DictQueryBlackListHandler;
import org.jeecg.modules.system.service.ISysKeywordsService;
import org.jeecg.modules.system.vo.SysKeywordsReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/sys/keywordsRecords")
@Api(tags="关键词搜索记录查询")
public class SysKeywordsRecordsController {
    @Autowired
    SysDictMapper sysDictMapper;

    @Autowired
    DictQueryBlackListHandler dictQueryBlackListHandler;

    @Autowired

    private ISysKeywordsService sysKeywordsService;

    @Autowired
    private SysKeywordsMapper sysKeywordsMapper;

    @Autowired
    private SysKeywordsRecordsMapper sysKeywordsRecordsMapper;
    /**
     * 获取系全部关键词（查询全部关键词）
     *
     * @param sysKeywordsRecords
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */

    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @AutoLog(value = "查询全部关键词搜索记录")
    @ApiOperation(value = "查询全部关键词搜索记录", notes = "查询全部关键词搜索记录")
    public Result<IPage<SysKeywordsRecords>> queryAllKeywordsRecords(SysKeywordsRecords sysKeywordsRecords, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize, HttpServletRequest req) {
        Result<IPage<SysKeywordsRecords>> result = new Result<IPage<SysKeywordsRecords>>();
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        //sysKeywords.setUserId(sysUser.getUsername());
        log.info("当前用户是:{}",sysUser.getUsername());
        QueryWrapper<SysKeywordsRecords> queryWrapper = QueryGenerator.initQueryWrapper(sysKeywordsRecords, req.getParameterMap());

        Page<SysKeywordsRecords> page = new Page<SysKeywordsRecords>(pageNo, pageSize);
        Page<SysKeywordsRecords> sysKeywordsRecordsPage = sysKeywordsRecordsMapper.selectPage(page, queryWrapper);
        result.setResult(sysKeywordsRecordsPage);
        result.setSuccess(true);
        return result;
    }


}
