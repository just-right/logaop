package com.example.logaop.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.example.logaop.entity.LogAopInfo;

import java.util.List;

/**
 * (Logaopinfo)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-21 20:47:01
 */
@Mapper
public interface LogAopInfoDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    LogAopInfo queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<LogAopInfo> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param logAopInfo 实例对象
     * @return 对象列表
     */
    List<LogAopInfo> queryAll(LogAopInfo logAopInfo);

    /**
     * 新增数据
     *
     * @param logAopInfo 实例对象
     * @return 影响行数
     */
    int insert(LogAopInfo logAopInfo);

    /**
     * 修改数据
     *
     * @param logAopInfo 实例对象
     * @return 影响行数
     */
    int update(LogAopInfo logAopInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}