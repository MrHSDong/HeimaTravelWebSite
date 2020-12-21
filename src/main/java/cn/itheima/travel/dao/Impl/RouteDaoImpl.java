package cn.itheima.travel.dao.Impl;

import cn.itheima.travel.dao.RouteDao;
import cn.itheima.travel.domain.Route;
import cn.itheima.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements RouteDao{
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    //根据cid，页码，每页条数,searchName分页查询一个包含Route对象的列表
    @Override
    public List<Route> queryByPage(int cid, int pageIndex, int itemCount, String searchName) {
        String sql = " select * from tab_route where 1 = 1 ";
        List<Object> arr = new ArrayList<Object>();
        if(searchName == null || searchName.length()==0 || "null".equals(searchName)){
            sql += " and cid = ? limit ?, ? ";
            arr.add(cid);
            arr.add((pageIndex - 1) * itemCount);
            arr.add(itemCount);
        }else{
            sql += " and cid = ? and rname like '%"+searchName+"%' limit ?, ?";
            arr.add(cid);
            arr.add((pageIndex - 1) * itemCount);
            arr.add(itemCount);
        }
        List<Route> routeList = null;
        try{                                                                        //传入可变数组参数
           routeList = template.query(sql, new BeanPropertyRowMapper<Route>(Route.class), arr.toArray());
           System.out.println(routeList.size());
        }catch (Exception e){
            e.printStackTrace();
        }
        return routeList;
    }
    //根据cid和searchNAME查询Route总条目数
    @Override
    public int queryTotal(int cid, String searchName){
        int count = 0;
        String sql = " select count(*) from tab_route where 1=1 ";
        if(searchName == null || searchName.length()==0 || "null".equals(searchName)){
            sql += " and cid = ?";
        }else{
            sql+= "and cid = ? and rname like '%"+searchName+"%'";
        }
        try{
            count = template.queryForObject(sql, Integer.class,cid);
            System.out.println(count);
        }catch (Exception e){
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public Route queryByRid(int rid) {
        String sql = " select * from tab_route where rid = ? ";

        Route route = null;
        try{
            route = template.queryForObject(sql, new BeanPropertyRowMapper<Route>(Route.class), rid);
        }catch (Exception e){
            e.printStackTrace();
        }
        return route;
    }
}
