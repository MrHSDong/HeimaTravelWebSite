package cn.itheima.travel.dao.Impl;

import cn.itheima.travel.dao.RouteImgDao;
import cn.itheima.travel.domain.RouteImg;
import cn.itheima.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class RouteImgDaoImpl implements RouteImgDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<RouteImg> getImgsByRid(int rid) {
        String sql = " select * from tab_route_img where rid = ? ";
        List<RouteImg> list = null;
        try{
            list = template.query(sql,new BeanPropertyRowMapper<RouteImg>(RouteImg.class), rid);
//            template.queryForList()
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("img list: "+ list);
        return list;
    }
}
