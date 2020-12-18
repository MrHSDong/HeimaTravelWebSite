package cn.itheima.travel.service;

import cn.itheima.travel.dao.CategoryDao;
import cn.itheima.travel.dao.Impl.CategoryDaoImpl;
import cn.itheima.travel.domain.Category;

import java.io.IOException;
import java.util.List;

public interface CategoryService {
    public List<Category> getSortedCategorys() throws IOException;
}
