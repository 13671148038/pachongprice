package cn.zhixingshidai.pachong.dao;

import cn.zhixingshidai.pachong.pojo.TbWordDeclare;
import cn.zhixingshidai.pachong.pojo.TbWordFace;

import java.util.List;

public interface WordDeclareMapper {

    List<TbWordDeclare> listWordDeclare();

    void update(TbWordDeclare wordDeclare);
}
