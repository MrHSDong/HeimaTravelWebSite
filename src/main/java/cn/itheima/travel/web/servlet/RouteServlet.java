package cn.itheima.travel.web.servlet;

import cn.itheima.travel.domain.PageBean;
import cn.itheima.travel.service.Impl.RouteServiceImpl;
import cn.itheima.travel.service.RouteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
    private RouteService service = new RouteServiceImpl();
    private ObjectMapper mapper = new ObjectMapper();
    public void queryByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取ajax通过post请求传过来的三个参数
        int pageIndex =Integer.parseInt(request.getParameter("pageIndex"));
        int itemCount =Integer.parseInt(request.getParameter("itemCount"));
        int cid =Integer.parseInt(request.getParameter("cid"));
        //根据参数获得一个pageBean对象
        PageBean pageBean = service.getPageBean(cid, pageIndex,itemCount);
        //将pageBean对象序列化并返回给前台
        returnJson(response,pageBean);

    }
    //将一个object对象序列化并返回
    private void returnJson(HttpServletResponse response, Object obj) throws IOException {
        String s = mapper.writeValueAsString(obj);
        System.out.println(s);
        response.getWriter().write(s);
    }


}
