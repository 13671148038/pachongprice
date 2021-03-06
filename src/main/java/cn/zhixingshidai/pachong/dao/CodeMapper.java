package cn.zhixingshidai.pachong.dao;

import cn.zhixingshidai.pachong.pojo.CodeAttrModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CodeMapper {
//    List<Map<String, Object>> getCodeAttr(Map<String,Object> condition);
    Set<Integer> getCodeAttr(Map<String,Object> condition);

    String getGoodIdByWordId(Map<String, Object> item);

    void update(Map<String, Object> item);

    Integer getIdByGoodClassId(String goodClassId);

    List<Integer> getUpdateWordDateIsNotNull();

    void setUpdateWordDateIsNull(Map<String, Integer> parm);

    List<Integer> getIds();

    List<String> getGoodsCodeList();

    void codeAttrCopyToSubmeter(CodeAttrModel codeAttrModel);

    CodeAttrModel getCodeAttrByGoodsCode(CodeAttrModel codeAttrModel);

    CodeAttrModel getCodeAttrSubmeterByGoodsCode(CodeAttrModel codeAttrModel);

    List<String> getPublishCode();

    List<String> getShowSearchCode();

    void addToShowSearch(@Param("publishCodeList") List<String> publishCodeList);

    void deleteFromShowSearch(@Param("showSearchCodeList") List<String> showSearchCodeList);
}
