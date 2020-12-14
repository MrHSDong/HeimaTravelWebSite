package cn.itheima.travel.service;

import cn.itheima.travel.domain.User;

public interface UserService {
    public boolean regist(User user);
    public User active(String code,String username);
}
