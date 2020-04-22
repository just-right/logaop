package com.example.logaop.service.impl;

import com.example.logaop.dao.LogAopInfoDao;
import com.example.logaop.entity.LogAopInfo;
import com.example.logaop.service.LogAopInfoDaoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (LogAopInfoDaoService)表服务实现类
 *
 * @author makejava
 * @since 2020-04-21 20:47:02
 */
@Service("LogAopInfoDaoService")
public class LogAopInfoDaoServiceImpl implements LogAopInfoDaoService {
    @Resource
    private LogAopInfoDao logAopInfoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public LogAopInfo queryById(Integer id) {
        return this.logAopInfoDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<LogAopInfo> queryAllByLimit(int offset, int limit) {
        return this.logAopInfoDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param logAopInfo 实例对象
     * @return 实例对象
     */
    @Override
    public LogAopInfo insert(LogAopInfo logAopInfo) {
        this.logAopInfoDao.insert(logAopInfo);
        return logAopInfo;
    }

    /**
     * 修改数据
     *
     * @param logAopInfo 实例对象
     * @return 实例对象
     */
    @Override
    public LogAopInfo update(LogAopInfo logAopInfo) {
        this.logAopInfoDao.update(logAopInfo);
        return this.queryById(logAopInfo.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.logAopInfoDao.deleteById(id) > 0;
    }
}