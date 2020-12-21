package cn.itheima.travel.dao;

import cn.itheima.travel.domain.Route;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public interface RouteDao {
    List<Route> queryByPage(int cid, int pageIndex, int itemCount, String searchName);
    int queryTotal(int cid,String searchName);
    Route queryByRid(int rid);
}
