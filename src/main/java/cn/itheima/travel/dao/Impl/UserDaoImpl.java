package cn.itheima.travel.dao.Impl;

import cn.itheima.travel.dao.UserDao;
import cn.itheima.travel.domain.User;
import cn.itheima.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public void updateStatus(User user, String y) {
        try {
            String sql = " update tab_user set status = ? where uid = ? ";
            template.update(sql, y, user.getUid());
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public User getUserByCodeAndUsername(String code, String username) {
        String sql = " select * from tab_user where code = ? and username = ? ";
        User user = null;
        try{
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), code, username);
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean addUser(User user) {
        boolean flag = false;
        try{
            String sql = " insert into tab_user (`uid`, `username`, `password`, `name`, `birthday`, `sex`, `telephone`, `email`, `status`, `code`) values ( ?, " +
                    " ?, ?, ?, ?, ?, ?, ?, ?, ? ) ";
            template.update(sql, user.getUid(),
                    user.getUsername(),
                    user.getPassword(),
                    user.getName(),
                    user.getBirthday(),
                    user.getSex(),
                    user.getTelephone(),
                    user.getEmail(),
                    user.getStatus(),
                    user.getCode());
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public User getUserByUsername(String username) {
        String sql = " select * from tab_user where username = ? ";
        User user = null;
        try{
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        }catch (Exception e){
        }
        return user;
    }
}
