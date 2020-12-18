package cn.itheima.travel.web.servlet;

import cn.itheima.travel.domain.Category;
import cn.itheima.travel.service.CategoryService;
import cn.itheima.travel.service.Impl.CategoryServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.Serializers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {
    private CategoryService service = new CategoryServiceImpl();
    public void listAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Category> sortedCategorys = service.getSortedCategorys();
        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(sortedCategorys);
        response.getWriter().write(s);
    }
}
