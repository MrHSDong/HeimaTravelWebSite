import cn.itheima.travel.domain.PageInfo;
import cn.itheima.travel.domain.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class JacksonTest {
    public static void main(String[] args) throws JsonProcessingException {
        ArrayList<Integer> l = new ArrayList<>();
        l.add(2);
        l.add(5);
        l.add(7);
        l.add(1);
        l.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });
        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(l);
        System.out.println(s);
    }
}
