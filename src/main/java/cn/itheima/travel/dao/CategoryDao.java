package cn.itheima.travel.dao;

import cn.itheima.travel.domain.Category;

import java.util.List;

public interface CategoryDao {
    public List<Category> findAll();
}
