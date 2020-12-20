package cn.itheima.travel.service;

import cn.itheima.travel.domain.PageBean;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface RouteService {
    //根据cid，页码数，每页条数获得一个pageBean对象
    PageBean getPageBean(int cid, int pageIndex, int itemCount) throws JsonProcessingException;
}
