package cn.itheima.travel.service.Impl;

import cn.itheima.travel.dao.Impl.UserDaoImpl;
import cn.itheima.travel.dao.UserDao;
import cn.itheima.travel.domain.User;
import cn.itheima.travel.service.UserService;
import cn.itheima.travel.util.MailUtils;
import cn.itheima.travel.util.UuidUtil;

public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();

    @Override
    public User login(User user) {
        User u = null;
        try{
            u = dao.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
        }catch (Exception e){
        }
        return u;
    }

    @Override
    public User active(String code,String username) {
        User user = dao.getUserByCodeAndUsername(code, username);
        if(user!=null){
            dao.updateStatus(user, "Y");
        }
        return user;
    }

    @Override
    public boolean regist(User user) {
        User u = dao.getUserByUsername(user.getUsername());
        //用户名不存在返回true
        if(u==null)
        {
            user.setStatus("N");
            String code = UuidUtil.getUuid();
            user.setCode(code);
            dao.addUser(user);
            MailUtils.sendMail(user.getEmail(),"<p>登陆成功，点击这里以<a href=http://localhost:80/travel/user/active?code="+user.getCode()+ "&username="+user.getUsername() +">激活</a></p>","账户激活");
            return true;
        }
        return false;
    }
}
