package cn.zhixingshidai.pachong;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Type;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PachongApplicationTests {

    @Test
    public void contextLoads() {
        Gson gson = new Gson();
        /*String ddd="\"data\": {\n" +
                "        \"activity\": { }, \n" +
                "        \"originalPrice\": {\n" +
                "            \";1627207:756525392;\": {\n" +
                "                \"price\": \"29.80\"\n" +
                "            }, \n" +
                "            \";1627207:761843687;\": {\n" +
                "                \"price\": \"32.40\"\n" +
                "            }, \n" +
                "            \"def\": {\n" +
                "                \"price\": \"25.80-32.40\"\n" +
                "            }, \n" +
                "            \";1627207:761843688;\": {\n" +
                "                \"price\": \"32.40\"\n" +
                "            }, \n" +
                "            \";1627207:369457976;\": {\n" +
                "                \"price\": \"29.80\"\n" +
                "            }, \n" +
                "            \";1627207:756525394;\": {\n" +
                "                \"price\": \"31.40\"\n" +
                "            }, \n" +
                "            \";1627207:2568954067;\": {\n" +
                "                \"price\": \"25.80\"\n" +
                "            }, \n" +
                "            \";1627207:2568428930;\": {\n" +
                "                \"price\": \"25.80\"\n" +
                "            }, \n" +
                "            \";1627207:1569920452;\": {\n" +
                "                \"price\": \"29.80\"\n" +
                "            }, \n" +
                "            \";1627207:1519787775;\": {\n" +
                "                \"price\": \"29.80\"\n" +
                "            }\n" +
                "        }";*/
        String ddd="{\"sdcsdc\":{\"sdcsdc\":false}}";
        Type type = new TypeToken<Map<String,Object>>(){}.getType();
        Map<String,String> jdMap = gson.fromJson(ddd, type);
        System.out.println(jdMap);
    }

    @Test
    public  void sdc(){
        String sd="78";
        Double ds= 78.0;
        System.out.println(Double.valueOf(sd)==(ds));
    }

}
