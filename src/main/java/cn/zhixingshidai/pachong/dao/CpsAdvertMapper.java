package cn.zhixingshidai.pachong.dao;

import cn.zhixingshidai.pachong.pojo.CodeAttrModel;
import cn.zhixingshidai.pachong.pojo.CpsAdvert;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CpsAdvertMapper {
    List<CpsAdvert> getCpsAdvert();

    void updateGoodsClassIdsByAdvertId(CpsAdvert cpsAdvert);

    Map<String, Object> getGoodsClassIdByClassId(@Param("classId") String classId);
}
