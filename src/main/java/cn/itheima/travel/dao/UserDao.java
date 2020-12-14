package cn.itheima.travel.dao;

import cn.itheima.travel.domain.User;
import cn.itheima.travel.util.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;

public interface UserDao {
    public User getUserByUsername(String username);
    public boolean addUser(User user);
    public User getUserByCodeAndUsername(String code, String username);

    void updateStatus(User user, String y);
}
