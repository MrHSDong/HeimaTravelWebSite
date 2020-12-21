package cn.itheima.travel.service.Impl;

import cn.itheima.travel.dao.CategoryDao;
import cn.itheima.travel.dao.Impl.CategoryDaoImpl;
import cn.itheima.travel.dao.Impl.RouteDaoImpl;
import cn.itheima.travel.dao.Impl.RouteImgDaoImpl;
import cn.itheima.travel.dao.Impl.SellerDaoImpl;
import cn.itheima.travel.dao.RouteDao;
import cn.itheima.travel.dao.RouteImgDao;
import cn.itheima.travel.dao.SellerDao;
import cn.itheima.travel.domain.*;
import cn.itheima.travel.service.RouteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RouteServiceImpl implements RouteService {
    RouteDao dao = new RouteDaoImpl();
    RouteImgDao imgDao = new RouteImgDaoImpl();
    SellerDao sellerDao = new SellerDaoImpl();
    ObjectMapper mapper = new ObjectMapper();
    CategoryDao cDao = new CategoryDaoImpl();
    @Override
    public PageBean getPageBean(int cid, int pageIndex, int itemCount,String searchName) throws JsonProcessingException {
        //cid，pageIndex，itemCount都是从前台传过来的，因此是已知的，可以直接传入
        PageBean pageBean = new PageBean(cid, pageIndex, itemCount);
        //查询当前cid的总条目数
        int totalCount = dao.queryTotal(cid, searchName);
        //分页查询Route对象列表
        List<Route> routeList = dao.queryByPage(cid, pageIndex, itemCount, searchName);
        //根据总条目数和每页条数计算总页数
        int totalPage = (int) Math.ceil((double)totalCount/itemCount);
        pageBean.setTotalCount(totalCount);
        //将Route列表序列化为String列表
        List<String> strRouteList = new ArrayList<String>();
        if( routeList!=null && routeList.size()>0){
            for (Route r :routeList) {
                strRouteList.add(mapper.writeValueAsString(r));
            }
        }
        pageBean.setRouteList(strRouteList);
        pageBean.setTotalPage(totalPage);
        //返回pageBean对象
        return pageBean;
    }
    //根据rid获得详细的route对象
    public Route getDetailRouteByRid(int rid){
        Route route = dao.queryByRid(rid);
        if(route != null){
            //route和route_img通过rid实现1对多的映射
            List<RouteImg> imgList = imgDao.getImgsByRid(rid);
            //route和seller通过sid实现映射
            Seller seller = sellerDao.getSellerBySid(route.getSid());
            //根据cid设置category
            Category category = cDao.getCategoryByCid(route.getCid());
            route.setCategory(category);
            //给route设置seller和img_list
            route.setRouteImgs(imgList);
            route.setSeller(seller);
        }
        return route;
    }
}
