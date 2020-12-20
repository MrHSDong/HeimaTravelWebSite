package cn.itheima.travel.dao.Impl;

import cn.itheima.travel.dao.RouteDao;
import cn.itheima.travel.domain.Route;
import cn.itheima.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class RouteDaoImpl implements RouteDao{
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    //根据cid，页码，每页条数分页查询一个包含Route对象的列表
    @Override
    public List<Route> queryByPage(int cid, int pageIndex, int itemCount) {
        String sql = " select * from tab_route where cid = ? limit ?, ?";
        List<Route> routeList = null;
        try{
           routeList = template.query(sql, new BeanPropertyRowMapper<Route>(Route.class), cid, (pageIndex - 1) * itemCount, itemCount);
            System.out.println(routeList.size());
        }catch (Exception e){
        }
        return routeList;
    }
    //根据cid查询Route总条目数
    @Override
    public int queryTotalByCid(int cid){
        int count = 0;
        String sql = " select count(*) from tab_route where cid = ? ";
        try{
            count = template.queryForObject(sql, Integer.class,cid);
            System.out.println(count);
        }catch (Exception e){
            e.printStackTrace();
        }
        return count;
    }

}
