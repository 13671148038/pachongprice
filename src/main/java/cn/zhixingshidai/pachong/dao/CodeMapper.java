package cn.zhixingshidai.pachong.dao;

import java.util.List;
import java.util.Map;

public interface CodeMapper {
    List<Map<String, Object>> getCodeAttr(Map<String,Object> condition);

    String getGoodIdByWordId(Map<String, Object> item);

    void update(Map<String, Object> item);
}
