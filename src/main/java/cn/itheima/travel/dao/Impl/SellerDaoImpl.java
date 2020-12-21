package cn.itheima.travel.dao.Impl;

import cn.itheima.travel.dao.SellerDao;
import cn.itheima.travel.domain.Seller;
import cn.itheima.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class SellerDaoImpl implements SellerDao{
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public Seller getSellerBySid(int sid) {
        String sql = " select * from tab_seller where sid = ? ";
        Seller seller = null;
        try{
            seller = template.queryForObject(sql, new BeanPropertyRowMapper<Seller>(Seller.class), sid);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("seller: "+seller);
        return seller;
    }
}
