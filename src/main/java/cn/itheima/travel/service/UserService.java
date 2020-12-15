package cn.itheima.travel.service;

import cn.itheima.travel.domain.User;

public interface UserService {
    boolean regist(User user);
    User active(String code,String username);
    User login(User user);
}
