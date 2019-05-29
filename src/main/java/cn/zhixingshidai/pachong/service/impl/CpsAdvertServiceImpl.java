package cn.zhixingshidai.pachong.service.impl;

import cn.zhixingshidai.pachong.dao.CpsAdvertMapper;
import cn.zhixingshidai.pachong.pojo.CpsAdvert;
import cn.zhixingshidai.pachong.service.CpsAdvertService;
import cn.zhixingshidai.pachong.until.HttpClientUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CpsAdvertServiceImpl implements CpsAdvertService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtil.class);


    @Autowired
    private CpsAdvertMapper cpsAdvertMapper;

    @Override
    public void updateGoodsClassId() {
        //查询行业是空的数据
        List<CpsAdvert> rows = cpsAdvertMapper.getCpsAdvert();
        LOGGER.info("total:" + rows.size());
        StringBuilder classIdSb = new StringBuilder();
        StringBuilder goodsClassIdSb = new StringBuilder();
        int index = 0;
        for (CpsAdvert cpsAdvert : rows) {
            index++;
            LOGGER.info("剩余:" + (rows.size() - index));
            classIdSb.setLength(0);
            goodsClassIdSb.setLength(0);
            String goodIds = cpsAdvert.getGoodIds();
            if (StringUtils.isNotBlank(goodIds)) {
                List<String> list = Arrays.asList(goodIds.split(","));
                for (String classId : list) {
                    Map<String, Object> goodsClass = cpsAdvertMapper.getGoodsClassIdByClassId(classId);
                    if (goodsClass != null) {
                        Integer typeId = Integer.valueOf(goodsClass.get("typeId") + "");
                        System.out.println("typeId:" + typeId);
                        classIdSb.append(typeId).append(",");
                        String goodsClassId = (String) goodsClass.get("goodsClassId");
                        System.out.println("goodsClassId:" + goodsClassId);
                        goodsClassIdSb.append(goodsClassId).append(",");
                    }
                }
                String s = classIdSb.delete(classIdSb.length() - 1, classIdSb.length()).toString();
                String s1 = goodsClassIdSb.delete(goodsClassIdSb.length() - 1, goodsClassIdSb.length()).toString();
                if (StringUtils.isNotBlank(s) && StringUtils.isNotBlank(s1)) {
                    cpsAdvert.setGoodIds(s);
                    cpsAdvert.setGoodClassIds(s1);
                    cpsAdvertMapper.updateGoodsClassIdsByAdvertId(cpsAdvert);
                }
            }
        }

    }
}
