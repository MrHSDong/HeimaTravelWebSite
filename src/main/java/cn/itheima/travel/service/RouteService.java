package cn.itheima.travel.service;

import cn.itheima.travel.domain.PageBean;
import cn.itheima.travel.domain.Route;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface RouteService {
    //根据cid，页码数，每页条数, searchName获得一个pageBean对象
    PageBean getPageBean(int cid, int pageIndex, int itemCount, String searchName) throws JsonProcessingException;
    public Route getDetailRouteByRid(int rid);
}
