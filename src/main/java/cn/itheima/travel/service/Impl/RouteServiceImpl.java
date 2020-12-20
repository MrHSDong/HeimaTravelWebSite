package cn.itheima.travel.service.Impl;

import cn.itheima.travel.dao.Impl.RouteDaoImpl;
import cn.itheima.travel.dao.RouteDao;
import cn.itheima.travel.domain.PageBean;
import cn.itheima.travel.domain.Route;
import cn.itheima.travel.service.RouteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RouteServiceImpl implements RouteService {
    RouteDao dao = new RouteDaoImpl();
    ObjectMapper mapper = new ObjectMapper();
    @Override
    public PageBean getPageBean(int cid, int pageIndex, int itemCount) throws JsonProcessingException {
        //cid，pageIndex，itemCount都是从前台传过来的，因此是已知的，可以直接传入
        PageBean pageBean = new PageBean(cid, pageIndex, itemCount);
        //查询当前cid的总条目数
        int totalCount = dao.queryTotalByCid(cid);
        //分页查询Route对象列表
        List<Route> routeList = dao.queryByPage(cid, pageIndex, itemCount);
        //根据总条目数和每页条数计算总页数
        int totalPage = (int) Math.ceil((double)totalCount/itemCount);
        pageBean.setTotalCount(totalCount);
        //将Route列表序列化为String列表
        List<String> strRouteList = new ArrayList<String>();
        for (Route r :routeList) {
            strRouteList.add(mapper.writeValueAsString(r));
        }
        pageBean.setRouteList(strRouteList);
        pageBean.setTotalPage(totalPage);
        //返回pageBean对象
        return pageBean;
    }
}
