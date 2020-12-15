package cn.itheima.travel.web.servlet;

import cn.itheima.travel.domain.PageInfo;
import cn.itheima.travel.domain.User;
import cn.itheima.travel.service.Impl.UserServiceImpl;
import cn.itheima.travel.service.UserService;
import cn.itheima.travel.util.MailUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;
import org.graalvm.compiler.lir.LIRInstruction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.http.HttpRequest;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {

    private final ObjectMapper mapper = new ObjectMapper();
    private final UserService service = new UserServiceImpl();
    private final PageInfo pageInfo = new PageInfo();

    public HttpSession getSession(HttpServletRequest request){
        return request.getSession();
    }

    public void returnJson(Object obj, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(mapper.writeValueAsString(obj));
    }

    public void regist(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException, IOException {
        //获得注册用户信息
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        BeanUtils.populate(user, map);
        HttpSession session = request.getSession();
        String sCode = (String)session.getAttribute("CHECKCODE_SERVER");
        //将生成的验证码无效化
        session.invalidate();
        String code = request.getParameter("check");

        boolean flag;
        //验证码正确
        if(code!=null && !code.equals("") && code.equalsIgnoreCase(sCode)){
            //验证登录是否正确
            flag = service.regist(user);
        }else{
            flag =false;
        }
        //注册成功
        if(flag){
            pageInfo.setFlag(true);
        }else{
            pageInfo.setFlag(false);
            pageInfo.setMsg("用户名已存在");
        }
        //返回注册结果信息pageInfo
        returnJson(pageInfo, response);
    }
    public void active(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        String code = request.getParameter("code");
        String username = request.getParameter("username");
        //校验链接的code字段在数据库中是否存在
        if(code !=null && !code.equals("")){
            User user = service.active(code,username);
            if(user!=null){
                response.getWriter().write("<p>点击此处以<a href=http://localhost:80/travel/login.html>登录</a></p>");
            }
        }
    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException, IOException {
        //获得表单提交的验证码
        String code = request.getParameter("check");
        String imgCode = (String)request.getSession().getAttribute("CHECKCODE_SERVER");
        request.getSession().invalidate();
        //比较提交的验证码和生成的验证码是否一致
        if(code != null && !code.equals("") && code.equalsIgnoreCase(imgCode)){

            Map<String, String[]> map = request.getParameterMap();
            User user = new User();
            BeanUtils.populate(user, map);
            User loginUser =  service.login(user);
            //登陆成功
            if(loginUser !=null){
                pageInfo.setFlag(true);
                request.getSession().invalidate();
                request.getSession().setAttribute("user", user);
            }else{
                pageInfo.setFlag(false);
                pageInfo.setMsg("用户名或密码错误");
            }
        }else{
            pageInfo.setFlag(false);
            pageInfo.setMsg("验证码错误");
        }
        returnJson(pageInfo, response);
    }
}
