package com.example.demo.dao;

import com.example.demo.entity.Area;

import java.util.List;

/**
 * dao接口
 */
public interface AreaDao {
    /**
     * 查询所有数据
     * @return
     */
    public List<Area> queryArea();

    /**
     * 根据ID查询单条数据
     * @param areaId
     * @return
     */
    public Area  queryAreaById(int areaId);

    /**
     * 插入数据方法
     * @return
     */
    public int insertArea(Area area);

    /**
     * 更新数据方法
     * @return
     */
    public int updateArea(Area area);

    /**
     * 删除数据方法
     * @return
     */
    public int deleteArea(int areaId);

}
