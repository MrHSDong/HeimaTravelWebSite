package cn.itheima.travel.dao.Impl;

import cn.itheima.travel.dao.CategoryDao;
import cn.itheima.travel.domain.Category;
import cn.itheima.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<Category> findAll() {
        List<Category> list = null;
        String sql = " select * from tab_category ";
        try{
            list = template.query(sql, new BeanPropertyRowMapper<Category>(Category.class));
//            list.sort(new Comparator<Category>() {
//                @Override
//                public int compare(Category o1, Category o2) {
//                    return o1.getCid()-o2.getCid();
//                }
//            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Category getCategoryByCid(int cid) {
        String sql = " select * from tab_category where cid = ? ";
        Category c = null;
        try{
            c = template.queryForObject(sql, new BeanPropertyRowMapper<Category>(Category.class), cid);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("category: "+ c);
        return c;
    }
}
