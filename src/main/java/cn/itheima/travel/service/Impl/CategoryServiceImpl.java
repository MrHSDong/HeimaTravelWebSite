package cn.itheima.travel.service.Impl;

import cn.itheima.travel.dao.CategoryDao;
import cn.itheima.travel.dao.Impl.CategoryDaoImpl;
import cn.itheima.travel.domain.Category;
import cn.itheima.travel.service.CategoryService;
import cn.itheima.travel.util.JedisUtil;
import com.alibaba.druid.support.json.JSONUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao dao = new CategoryDaoImpl();
    private Jedis jedis = JedisUtil.getJedis();
    private ObjectMapper mapper = new ObjectMapper();
    @Override
    public List<Category> getSortedCategorys() throws IOException {
            //从redis中获取category
        List<String> categorys = jedis.lrange("category", 0, -1);

        //如果redis中没有category
        if(categorys == null || categorys.size()==0){
            //从mysql中查询category
            List<Category> categoryList = dao.findAll();
            if(categoryList!=null){
                //将查询结果排序
                categoryList.sort((o1, o2) -> o1.getCid()-o2.getCid());
                //遍历结果列表，存入redis
                for (Category c:
                        categoryList) {
                    //将category序列化存入redis
                    jedis.rpush("category", mapper.writeValueAsString(c));
                }
            }
            //直接返回从mysql中查询得到的结果
            return categoryList;
            //jedis中查询category成功
        }else{
            List<Category> categoryList = new ArrayList<>();
            for (String c:
                 categorys) {
                //将redis中查询到的结果封装成category对象存入list
                Category c2 = mapper.readValue(c, Category.class);
                categoryList.add(c2);
            }
            categoryList.sort((o1, o2) -> o1.getCid()-o2.getCid());
            //返回排序后的list
            return categoryList;
        }
    }
}
