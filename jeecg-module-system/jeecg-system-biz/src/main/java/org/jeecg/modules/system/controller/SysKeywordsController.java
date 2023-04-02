package org.jeecg.modules.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
import org.jeecg.modules.system.entity.*;
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
@RequestMapping("/sys/keywords")
@Api(tags="关键词")
public class SysKeywordsController {
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
     * @param sysKeywords
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    //@RequiresPermissions("system:user:listAll")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @AutoLog(value = "查询全部关键词")
    @ApiOperation(value = "查询全部关键词", notes = "查询全部关键词")
    public Result<IPage<SysKeywords>> queryAllPageList(SysKeywords sysKeywords, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize, HttpServletRequest req) {
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        sysKeywords.setUserId(sysUser.getUsername());
        log.info("当前用户是:{}",sysUser.getUsername());
        QueryWrapper<SysKeywords> queryWrapper = QueryGenerator.initQueryWrapper(sysKeywords, req.getParameterMap());
        return sysKeywordsService.queryPageList(req, queryWrapper, pageSize, pageNo);
    }

    @RequestMapping(value = "/searchKeywords", method = RequestMethod.POST)
    @AutoLog(value = "搜索关键词")
    @ApiOperation(value = "搜索关键词", notes = "搜索关键词")
    public Result<IPage<SysKeywords>> searchKeywords(@RequestBody SysKeywordsReqVo sysKeywordsReqVo, HttpServletRequest req) {
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        //插入搜索记录
        if(StringUtils.isNotBlank(sysKeywordsReqVo.getKeywords())){
            SysKeywordsRecords sysKeywordsRecords=new SysKeywordsRecords();
            sysKeywordsRecords.setKeywords(sysKeywordsReqVo.getKeywords());
            sysKeywordsRecords.setCreateTime(new Date());
            sysKeywordsRecordsMapper.insert(sysKeywordsRecords);
        }
        //sysKeywords.setUserId(sysUser.getUsername());
        log.info("当前用户是:{}",sysUser.getUsername());
        QueryWrapper<SysKeywords> queryWrapper=new QueryWrapper<>();
        queryWrapper.like("keywords",sysKeywordsReqVo.getKeywords());
        //QueryWrapper<SysKeywords> queryWrapper = QueryGenerator.initQueryWrapper(sysKeywords, req.getParameterMap());
        return sysKeywordsService.queryPageListByKeywords(req, queryWrapper, sysKeywordsReqVo.getPageSize(), sysKeywordsReqVo.getPageNo(),sysKeywordsReqVo);
    }

    @RequestMapping(value = "/keywordsDetail", method = RequestMethod.POST)
    @AutoLog(value = "关键词详情（客户）")
    @ApiOperation(value = "关键词详情（客户）", notes = "关键词详情（客户）")
    public Result<SysKeywords> keywordsDetail(@RequestBody SysKeywordsReqVo sysKeywordsReqVo, HttpServletRequest req) {
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        //QueryWrapper<SysKeywords> queryWrapper = QueryGenerator.initQueryWrapper(sysKeywords, req.getParameterMap());
        return sysKeywordsService.keywordsDetail(sysUser,sysKeywordsReqVo);
    }


    /**
     *   添加
     * @param sysKeywords
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @AutoLog(value = "新增关键词")
    @ApiOperation(value = "新增关键词", notes = "新增关键词")
    public Result<SysKeywords> add(@RequestBody SysKeywords sysKeywords) {
        Result<SysKeywords> result = new Result<SysKeywords>();
        try {
            sysKeywords.setCreateTime(new Date());
            sysKeywordsMapper.insert(sysKeywords);
            result.success("添加成功！");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.error500("操作失败");
        }
        return result;
    }

    /**
     *  编辑
     * @param sysKeywords
     * @return
     */

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @AutoLog(value = "编辑关键词")
    @ApiOperation(value = "编辑关键词", notes = "编辑关键词")
    public Result<SysKeywords> edit(@RequestBody SysKeywords sysKeywords) {
        Result<SysKeywords> result = new Result<SysKeywords>();
        sysKeywordsMapper.updateById(sysKeywords);
        result.success("修改成功!");
        return result;
    }

    /**
     *   通过id删除
     * @param id
     * @return
     */

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @AutoLog(value = "删除关键词")
    @ApiOperation(value = "删除关键词", notes = "删除关键词")
    public Result<?> delete(@RequestParam(name="id",required=true) String id) {
        sysKeywordsMapper.deleteById(id);
        return Result.ok("删除关键词");
    }


    /**
     *  用户查询自己的关键词
     * @param sysKeywordsReqVo
     * @return
     */

    @RequestMapping(value = "/queryKeywordsByUserId", method = RequestMethod.POST)
    @AutoLog(value = "用户查询自己的关键词")
    @ApiOperation(value = "用户查询自己的关键词", notes = "用户查询自己的关键词")
    public Result<IPage<SysKeywords>> queryKeywordsByUserId(@RequestBody SysKeywordsReqVo sysKeywordsReqVo,HttpServletRequest request) {
        Result<IPage<SysKeywords>> result = new Result<IPage<SysKeywords>>();
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        LambdaQueryWrapper<SysKeywords> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(SysKeywords::getUserId,sysUser.getId());
        //先判断用户身份，如果只是买家，不允许查询，要先去申请变成买家身份
        if(2!=sysUser.getUserIdentity()){
           return Result.error("目前您还不是卖家身份！");
        }
        Page<SysKeywords> page = new Page<SysKeywords>(sysKeywordsReqVo.getPageNo(), sysKeywordsReqVo.getPageSize());
        Page<SysKeywords> keywordsPage = sysKeywordsMapper.selectPage(page, wrapper);
        result.setResult(keywordsPage);
        result.setSuccess(true);
        return result;
    }



}
