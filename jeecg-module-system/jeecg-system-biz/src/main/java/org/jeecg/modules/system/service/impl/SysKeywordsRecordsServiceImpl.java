package org.jeecg.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.modules.system.entity.SysKeywords;
import org.jeecg.modules.system.entity.SysKeywordsRecords;
import org.jeecg.modules.system.mapper.SysKeywordsMapper;
import org.jeecg.modules.system.mapper.SysKeywordsRecordsMapper;
import org.jeecg.modules.system.service.ISysKeywordsRecordsService;
import org.jeecg.modules.system.service.ISysKeywordsService;
import org.jeecg.modules.system.vo.SysKeywordsReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@Slf4j
public class SysKeywordsRecordsServiceImpl extends ServiceImpl<SysKeywordsRecordsMapper, SysKeywordsRecords> implements ISysKeywordsRecordsService {

}
