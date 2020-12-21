package cn.itheima.travel.dao;

import cn.itheima.travel.domain.Seller;

public interface SellerDao{
    Seller getSellerBySid(int sid);
}
