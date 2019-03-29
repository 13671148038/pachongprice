package cn.zhixingshidai.pachong.dao;

import cn.zhixingshidai.pachong.pojo.TbWordFace;

import java.util.List;

public interface WordMapper {

    List<TbWordFace> listWordFace();

    void update(TbWordFace wordFace);

    void updateWord(TbWordFace wordFace);

    List<TbWordFace> listWord();
}
