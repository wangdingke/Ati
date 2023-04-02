package org.jeecg.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.constant.SymbolConstant;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.system.entity.SysKeywords;
import org.jeecg.modules.system.entity.SysKeywordsNonentity;
import org.jeecg.modules.system.entity.SysUser;
import org.jeecg.modules.system.entity.SysUserDepart;
import org.jeecg.modules.system.mapper.SysKeywordsMapper;
import org.jeecg.modules.system.mapper.SysKeywordsNonentityMapper;
import org.jeecg.modules.system.mapper.SysUserMapper;
import org.jeecg.modules.system.service.ISysKeywordsService;
import org.jeecg.modules.system.service.ISysUserService;
import org.jeecg.modules.system.vo.SysKeywordsReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SysKeywordsServiceImpl extends ServiceImpl<SysKeywordsMapper, SysKeywords> implements ISysKeywordsService {
    @Autowired
    private SysKeywordsMapper sysKeywordsMapper;
    @Autowired
    private SysKeywordsNonentityMapper sysKeywordsNonentityMapper;
    @Override
    public Result<IPage<SysKeywords>> queryPageList(HttpServletRequest req, QueryWrapper<SysKeywords> queryWrapper, Integer pageSize, Integer pageNo) {

        Result<IPage<SysKeywords>> result = new Result<IPage<SysKeywords>>();
        Page<SysKeywords> page = new Page<SysKeywords>(pageNo, pageSize);
        IPage<SysKeywords> pageList = this.page(page, queryWrapper);
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }

    @Override
    public Result<IPage<SysKeywords>> queryPageListByKeywords(HttpServletRequest req, QueryWrapper<SysKeywords> queryWrapper, Integer pageSize, Integer pageNo,SysKeywordsReqVo sysKeywordsReqVo) {
        Result<IPage<SysKeywords>> result = new Result<IPage<SysKeywords>>();
        Page<SysKeywords> page = new Page<SysKeywords>(pageNo, pageSize);
        IPage<SysKeywords> pageList = this.page(page, queryWrapper);
        List<SysKeywords> records = pageList.getRecords();
        if(!records.isEmpty()){
            for (SysKeywords keywords:records) {
                keywords.setKeywords("*******");
            }
        }
        //如果搜索的关键词不存在，要记录在表里
        if(pageList.getTotal()==0 && StringUtils.isNotBlank(sysKeywordsReqVo.getKeywords())){
            SysKeywordsNonentity sysKeywordsNonentity=new SysKeywordsNonentity();
            sysKeywordsNonentity.setKeywords(sysKeywordsReqVo.getKeywords());
            sysKeywordsNonentity.setCreateTime(new Date());
            sysKeywordsNonentityMapper.insert(sysKeywordsNonentity);
        }
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }

    @Override
    public Result<SysKeywords> keywordsDetail(LoginUser sysUser, SysKeywordsReqVo sysKeywordsReqVo) {
        Result result=new Result();
        QueryWrapper<SysKeywords> queryWrapper=new QueryWrapper<>();
        SysKeywords keywords = sysKeywordsMapper.selectById(sysKeywordsReqVo.getKeywordsId());
        //如果是管理员身份，关键词次
        result.setResult(keywords);
        return result;
    }
}
