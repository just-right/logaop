package com.example.logaop.service;

import com.example.logaop.entity.LogAopInfo;
import java.util.List;

/**
 * (logAopInfo)表服务接口
 *
 * @author makejava
 * @since 2020-04-21 20:47:02
 */
public interface LogAopInfoDaoService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    LogAopInfo queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<LogAopInfo> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param logAopInfo 实例对象
     * @return 实例对象
     */
    LogAopInfo insert(LogAopInfo logAopInfo);

    /**
     * 修改数据
     *
     * @param logAopInfo 实例对象
     * @return 实例对象
     */
    LogAopInfo update(LogAopInfo logAopInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}