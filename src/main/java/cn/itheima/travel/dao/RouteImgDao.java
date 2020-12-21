package cn.itheima.travel.dao;

import cn.itheima.travel.domain.RouteImg;

import java.util.List;

public interface RouteImgDao {
    List<RouteImg> getImgsByRid(int rid);
}
