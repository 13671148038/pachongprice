package cn.zhixingshidai.pachong.service.impl;

import cn.zhixingshidai.pachong.dao.Goodclass2TypeMapper;
import cn.zhixingshidai.pachong.dao.WordDeclareMapper;
import cn.zhixingshidai.pachong.dao.WordMapper;
import cn.zhixingshidai.pachong.pojo.Goodclass2Type;
import cn.zhixingshidai.pachong.pojo.TbWordDeclare;
import cn.zhixingshidai.pachong.pojo.TbWordFace;
import cn.zhixingshidai.pachong.service.WordService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordServiceImpl implements WordService {

    @Autowired
    private WordMapper wordMapper;
    @Autowired
    private WordDeclareMapper wordDeclareMapper;
    @Autowired
    private Goodclass2TypeMapper goodclass2TypeMapper;

    /**
     * tb_word_face表
     */
    @Override
    public void asyncWordFaceTypeId() {
        List<TbWordFace> wordFaceList = wordMapper.listWordFace();
        int size = wordFaceList.size();
        List<Goodclass2Type> goodclass2Types = goodclass2TypeMapper.listGoodClass2Type();
        int index =0;
        for (TbWordFace wordFace:wordFaceList) {
            index++;
            String goodsClassId = wordFace.getGoodsClassId();
            if(StringUtils.isNotBlank(goodsClassId)){
                for (Goodclass2Type goodclass2Type:goodclass2Types) {
                    String goodsClassId1 = goodclass2Type.getGoodsClassId();
                    if(goodsClassId1.equals(goodsClassId)){
                        wordFace.setTypeId(goodclass2Type.getTypeId());
                        wordMapper.update(wordFace);
                        System.out.println("size+=====:"+size);
                        System.out.println("剩余+=====:"+(size-index));
                    }
                }
            }
        }
    }

    /**
     * tb_word表
     */
    @Override
    public void asyncWordTypeId() {
        List<TbWordFace> wordList = wordMapper.listWord();
        int size = wordList.size();
        List<Goodclass2Type> goodclass2Types = goodclass2TypeMapper.listGoodClass2Type();
        int index =0;
        for (TbWordFace wordFace:wordList) {
            index++;
            String goodsClassId = wordFace.getGoodsClassId();
            if(StringUtils.isNotBlank(goodsClassId)){
                for (Goodclass2Type goodclass2Type:goodclass2Types) {
                    String goodsClassId1 = goodclass2Type.getGoodsClassId();
                    if(goodsClassId1.equals(goodsClassId)){
                        wordFace.setTypeId(goodclass2Type.getTypeId());
                        wordMapper.updateWord(wordFace);
                        System.out.println("size+=====:"+size);
                        System.out.println("剩余+=====:"+(size-index));
                    }
                }
            }
        }
    }

    /**
     * tb_word_declare表
     */
    @Override
    public void asyncWordDeclareTypeId() {
        List<TbWordDeclare> wordDeclareList = wordDeclareMapper.listWordDeclare();
        int size = wordDeclareList.size();
        List<Goodclass2Type> goodclass2Types = goodclass2TypeMapper.listGoodClass2Type();
        int index =0;
        for (TbWordDeclare wordDeclare:wordDeclareList) {
            index++;
            String goodsClassId = wordDeclare.getGoodsClassId();
            if(StringUtils.isNotBlank(goodsClassId)){
                for (Goodclass2Type goodclass2Type:goodclass2Types) {
                    String goodsClassId1 = goodclass2Type.getGoodsClassId();
                    if(goodsClassId1.equals(goodsClassId)){
                        wordDeclare.setTypeId(goodclass2Type.getTypeId());
                        wordDeclareMapper.update(wordDeclare);
                        System.out.println("size+=====:"+size);
                        System.out.println("剩余+=====:"+(size-index));
                    }
                }
            }
        }
    }
}
