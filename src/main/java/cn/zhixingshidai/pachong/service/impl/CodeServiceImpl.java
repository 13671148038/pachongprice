package cn.zhixingshidai.pachong.service.impl;

import cn.zhixingshidai.pachong.dao.CodeMapper;
import cn.zhixingshidai.pachong.pojo.CodeAttrModel;
import cn.zhixingshidai.pachong.service.CodeService;
import cn.zhixingshidai.pachong.until.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CodeServiceImpl implements CodeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtil.class);

    @Autowired
    private CodeMapper codeMapper;

    @Override
    public void start() throws InterruptedException {
        Logger logger = LoggerFactory.getLogger(CodeServiceImpl.class);
        List<Thread> list = new ArrayList<>();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Map<String, Object> condition = new HashMap<>();
                condition.put("tableName", "tb_coded_attr");
                Set<Integer> result = (Set<Integer>) codeMapper.getCodeAttr(condition);
                Map<String, Object> item = new HashMap<>();
                int index = 0;
                for (Integer wid : result) {
                    item.put("goodsWordId", wid);
                    //根据wordid查询分类id
                    String goodClassId = codeMapper.getGoodIdByWordId(item);
                    //根据分类id查找id
                    Integer id = codeMapper.getIdByGoodClassId(goodClassId);
                    if (id == null) {
                        logger.info("tb_coded_attr11 分类:" + goodClassId + "在数据库中为空================================================================");
                        continue;
                    }
                    item.put("goodsClassId", id);
                    item.put("tableName", "tb_coded_attr");
                    codeMapper.update(item);
                    logger.info("更新tb_coded_attr表 wordId为:" + item.get("goodsWordId") + " 分类id为:" + id);
                    index++;
                    logger.info("更新tb_coded_attr表 总数为:" + (result.size() + 1) + " 当前执行数为:" + index);
                    System.out.println("更新tb_coded_attr表 总数为:" + (result.size() + 1) + " 当前执行数为:" + index);
                }
                System.out.println("tb_coded_attr 执行完了");
            }
        });
        thread.start();
        list.add(thread);
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {//tb_coded_attr
                Map<String, Object> condition = new HashMap<>();
                condition.put("tableName", "tb_barcode_0");
                Set<Integer> result = (Set<Integer>) codeMapper.getCodeAttr(condition);
                Map<String, Object> item = new HashMap<>();
                int index = 0;
                for (Integer wid : result) {
                    item.put("goodsWordId", wid);
                    //根据wordid查询分类id
                    String goodClassId = codeMapper.getGoodIdByWordId(item);
                    //根据分类id查找id
                    Integer id = codeMapper.getIdByGoodClassId(goodClassId);
                    if (id == null) {
                        logger.info("tb_barcode_0 分类:" + goodClassId + "在数据库中为空================================================================");
                        continue;
                    }
                    item.put("goodsClassId", id);
                    item.put("tableName", "tb_barcode_0");
                    codeMapper.update(item);
                    logger.info("tb_barcode_0 wordId为:" + item.get("goodsWordId") + " 分类id为:" + id);
                    index++;
                    logger.info("更新tb_coded_0表 总数为:" + (result.size() + 1) + " 当前执行数为:" + index);
                    System.out.println("更新tb_coded_0表 总数为:" + (result.size() + 1) + " 当前执行数为:" + index);
                }
                System.out.println("tb_barcode_0 执行完了");
            }
        });
        thread2.start();
        list.add(thread2);
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {//tb_coded_attr
                Map<String, Object> condition = new HashMap<>();
                condition.put("tableName", "tb_barcode_1");
                Set<Integer> result = (Set<Integer>) codeMapper.getCodeAttr(condition);
                Map<String, Object> item = new HashMap<>();
                int index = 0;
                for (Integer wid : result) {
                    item.put("goodsWordId", wid);
                    //根据wordid查询分类id
                    String goodClassId = codeMapper.getGoodIdByWordId(item);
                    Integer id = codeMapper.getIdByGoodClassId(goodClassId);
                    if (id == null) {
                        logger.info("tb_barcode_1 分类:" + goodClassId + "在数据库中为空================================================================");
                        continue;
                    }
                    item.put("goodsClassId", id);
                    item.put("tableName", "tb_barcode_1");
                    codeMapper.update(item);
                    logger.info("tb_barcode_1 wordId为:" + item.get("goodsWordId") + " 分类id为:" + id);
                    index++;
                    logger.info("更新tb_coded_1表 总数为:" + (result.size() + 1) + " 当前执行数为:" + index);
                    System.out.println("更新tb_coded_1表 总数为:" + (result.size() + 1) + " 当前执行数为:" + index);
                }
                System.out.println("tb_barcode_1 执行完了");
            }
        });
        thread3.start();
        list.add(thread3);
        Thread thread4 = new Thread(new Runnable() {
            @Override
            public void run() {//tb_coded_attr
                Map<String, Object> condition = new HashMap<>();
                condition.put("tableName", "tb_barcode_2");
                Set<Integer> result = (Set<Integer>) codeMapper.getCodeAttr(condition);
                Map<String, Object> item = new HashMap<>();
                int index = 0;
                for (Integer wid : result) {
                    item.put("goodsWordId", wid);
                    //根据wordid查询分类id
                    String goodClassId = codeMapper.getGoodIdByWordId(item);
                    Integer id = codeMapper.getIdByGoodClassId(goodClassId);
                    if (id == null) {
                        logger.info("tb_barcode_2 分类:" + goodClassId + "在数据库中为空================================================================");
                        continue;
                    }
                    item.put("goodsClassId", id);
                    item.put("tableName", "tb_barcode_2");
                    codeMapper.update(item);
                    logger.info("tb_barcode_2 wordId为:" + item.get("goodsWordId") + " 分类id为:" + id);
                    index++;
                    logger.info("更新tb_coded_2表 总数为:" + (result.size() + 1) + " 当前执行数为:" + index);
                    System.out.println("更新tb_coded_2表 总数为:" + (result.size() + 1) + " 当前执行数为:" + index);
                }
                System.out.println("tb_barcode_2 执行完了");
            }
        });
        thread4.start();
        list.add(thread4);
        Thread thread5 = new Thread(new Runnable() {
            @Override
            public void run() {//tb_coded_attr
                Map<String, Object> condition = new HashMap<>();
                condition.put("tableName", "tb_barcode_3");
                Set<Integer> result = (Set<Integer>) codeMapper.getCodeAttr(condition);
                Map<String, Object> item = new HashMap<>();
                int index = 0;
                for (Integer wid : result) {
                    item.put("goodsWordId", wid);
                    //根据wordid查询分类id
                    String goodClassId = codeMapper.getGoodIdByWordId(item);
                    Integer id = codeMapper.getIdByGoodClassId(goodClassId);
                    if (id == null) {
                        logger.info("tb_barcode_3 分类:" + goodClassId + "在数据库中为空================================================================");
                        continue;
                    }
                    item.put("goodsClassId", id);
                    item.put("tableName", "tb_barcode_3");
                    codeMapper.update(item);
                    logger.info("tb_barcode_3 wordId为:" + item.get("goodsWordId") + " 分类id为:" + id);
                    index++;
                    logger.info("更新tb_coded_3表 总数为:" + (result.size()) + " 当前执行数为:" + index);
                    System.out.println("更新tb_coded_3表 总数为:" + (result.size()) + " 当前执行数为:" + index);
                }
                System.out.println("tb_barcode_3 执行完了");
            }
        });
        thread5.start();
        list.add(thread5);
        Thread thread6 = new Thread(new Runnable() {
            @Override
            public void run() {//tb_coded_attr
                Map<String, Object> condition = new HashMap<>();
                condition.put("tableName", "tb_barcode_4");
                Set<Integer> result = (Set<Integer>) codeMapper.getCodeAttr(condition);
                Map<String, Object> item = new HashMap<>();
                int index = 0;
                for (Integer wid : result) {
                    item.put("goodsWordId", wid);
                    //根据wordid查询分类id
                    String goodClassId = codeMapper.getGoodIdByWordId(item);
                    Integer id = codeMapper.getIdByGoodClassId(goodClassId);
                    if (id == null) {
                        logger.info("tb_barcode_4 分类:" + goodClassId + "在数据库中为空================================================================");
                        continue;
                    }
                    item.put("goodsClassId", id);
                    item.put("tableName", "tb_barcode_4");
                    codeMapper.update(item);
                    logger.info("tb_barcode_4 wordId为:" + item.get("goodsWordId") + " 分类id为:" + id);
                    index++;
                    logger.info("更新tb_coded_4表 总数为:" + (result.size()) + " 当前执行数为:" + index);
                    System.out.println("更新tb_coded_4表 总数为:" + (result.size()) + " 当前执行数为:" + index);
                }
                System.out.println("tb_barcode_4 执行完了");
            }
        });
        thread6.start();
        list.add(thread6);
        Thread thread7 = new Thread(new Runnable() {
            @Override
            public void run() {//tb_coded_attr
                Map<String, Object> condition = new HashMap<>();
                condition.put("tableName", "tb_barcode_5");
                Set<Integer> result = (Set<Integer>) codeMapper.getCodeAttr(condition);
                Map<String, Object> item = new HashMap<>();
                int index = 0;
                for (Integer wid : result) {
                    item.put("goodsWordId", wid);
                    //根据wordid查询分类id
                    String goodClassId = codeMapper.getGoodIdByWordId(item);
                    Integer id = codeMapper.getIdByGoodClassId(goodClassId);
                    if (id == null) {
                        logger.info("tb_barcode_5 分类:" + goodClassId + "在数据库中为空================================================================");
                        continue;
                    }
                    item.put("goodsClassId", id);
                    item.put("tableName", "tb_barcode_5");
                    codeMapper.update(item);
                    logger.info("tb_barcode_5 wordId为:" + item.get("goodsWordId") + " 分类id为:" + id);
                    index++;
                    logger.info("更新tb_coded_5表 总数为:" + (result.size()) + " 当前执行数为:" + index);
                    System.out.println("更新tb_coded_5表 总数为:" + (result.size()) + " 当前执行数为:" + index);
                }
                System.out.println("tb_barcode_5 执行完了");
            }
        });
        thread7.start();
        list.add(thread7);
        Thread thread8 = new Thread(new Runnable() {
            @Override
            public void run() {//tb_coded_attr
                Map<String, Object> condition = new HashMap<>();
                condition.put("tableName", "tb_barcode_6");
                Set<Integer> result = (Set<Integer>) codeMapper.getCodeAttr(condition);
                Map<String, Object> item = new HashMap<>();
                int index = 0;
                for (Integer wid : result) {
                    item.put("goodsWordId", wid);
                    //根据wordid查询分类id
                    String goodClassId = codeMapper.getGoodIdByWordId(item);
                    Integer id = codeMapper.getIdByGoodClassId(goodClassId);
                    if (id == null) {
                        logger.info("tb_barcode_6 分类:" + goodClassId + "在数据库中为空================================================================");
                        continue;
                    }
                    item.put("goodsClassId", id);
                    item.put("tableName", "tb_barcode_6");
                    codeMapper.update(item);
                    logger.info("tb_barcode_6 wordId为:" + item.get("goodsWordId") + " 分类id为:" + id);
                    index++;
                    logger.info("更新tb_coded_6表 总数为:" + (result.size()) + " 当前执行数为:" + index);
                    System.out.println("更新tb_coded_6表 总数为:" + (result.size()) + " 当前执行数为:" + index);
                }
                System.out.println("tb_barcode_6 执行完了");
            }
        });
        thread8.start();
        list.add(thread8);
        Thread thread9 = new Thread(new Runnable() {
            @Override
            public void run() {//tb_coded_attr
                Map<String, Object> condition = new HashMap<>();
                condition.put("tableName", "tb_barcode_7");
//                List<Map<String, Object>> result = codeMapper.getCodeAttr(condition);
                Set<Integer> result = (Set<Integer>) codeMapper.getCodeAttr(condition);
                Map<String, Object> item = new HashMap<>();
                int index = 0;
                for (Integer wid : result) {
                    item.put("goodsWordId", wid);
                    //根据wordid查询分类id
                    String goodClassId = codeMapper.getGoodIdByWordId(item);
                    Integer id = codeMapper.getIdByGoodClassId(goodClassId);
                    if (id == null) {
                        logger.info("tb_barcode_7 分类:" + goodClassId + "在数据库中为空================================================================");
                        continue;
                    }
                    item.put("goodsClassId", id);
                    item.put("tableName", "tb_barcode_7");
                    codeMapper.update(item);
                    logger.info("tb_barcode_7 wordId为:" + item.get("goodsWordId") + " 分类id为:" + id);
                    index++;
                    logger.info("更新tb_coded_7表 总数为:" + (result.size()) + " 当前执行数为:" + index);
                    System.out.println("更新tb_coded_7表 总数为:" + (result.size()) + " 当前执行数为:" + index);
                }
                System.out.println("tb_barcode_7 执行完了");
            }
        });
        thread9.start();
        list.add(thread9);
        Thread thread10 = new Thread(new Runnable() {
            @Override
            public void run() {//tb_coded_attr
                Map<String, Object> condition = new HashMap<>();
                condition.put("tableName", "tb_barcode_8");
                Set<Integer> result = (Set<Integer>) codeMapper.getCodeAttr(condition);
                Map<String, Object> item = new HashMap<>();
                int index = 0;
                for (Integer wid : result) {
                    item.put("goodsWordId", wid);
                    //根据wordid查询分类id
                    String goodClassId = codeMapper.getGoodIdByWordId(item);
                    Integer id = codeMapper.getIdByGoodClassId(goodClassId);
                    if (id == null) {
                        logger.info("tb_barcode_8 分类:" + goodClassId + "在数据库中为空================================================================");
                        continue;
                    }
                    item.put("goodsClassId", id);
                    item.put("tableName", "tb_barcode_8");
                    codeMapper.update(item);
                    logger.info("tb_barcode_8 wordId为:" + item.get("goodsWordId") + " 分类id为:" + id);
                    index++;
                    logger.info("更新tb_barcode_8表 总数为:" + (result.size()) + " 当前执行数为:" + index);
                    System.out.println("更新tb_barcode_8表 总数为:" + (result.size()) + " 当前执行数为:" + index);
                }
                System.out.println("tb_barcode_8 执行完了");
            }
        });
        thread10.start();
        list.add(thread10);

        Thread thread11 = new Thread(new Runnable() {
            @Override
            public void run() {//tb_coded_attr
                Map<String, Object> condition = new HashMap<>();
                condition.put("tableName", "tb_barcode_9");
                Set<Integer> result = (Set<Integer>) codeMapper.getCodeAttr(condition);
                Map<String, Object> item = new HashMap<>();
                int index = 0;
                for (Integer wid : result) {
                    item.put("goodsWordId", wid);
                    //根据wordid查询分类id
                    String goodClassId = codeMapper.getGoodIdByWordId(item);
                    Integer id = codeMapper.getIdByGoodClassId(goodClassId);
                    if (id == null) {
                        logger.info("更新tb_barcode_9表 分类:" + goodClassId + "在数据库中为空================================================================");
                        continue;
                    }
                    item.put("goodsClassId", id);
                    item.put("tableName", "tb_barcode_9");
                    codeMapper.update(item);
                    logger.info("tb_barcode_9 wordId为:" + item.get("goodsWordId") + " 分类id为:" + id);
                    index++;
                    logger.info("更新tb_barcode_9表 总数为:" + (result.size()) + " 当前执行数为:" + index);
                    System.out.println("更新tb_barcode_9表 总数为:" + (result.size()) + " 当前执行数为:" + index);
                }
                System.out.println("tb_barcode_9 执行完了");
            }
        });
        thread11.start();
        list.add(thread11);
        for (Thread t : list) {
            t.join();
        }
        System.out.println("全部执行完了");
    }

    @Override
    public void startUpdateWordDataisNull() {
        LOGGER.info("开始执行............");
        List<Integer> idList = codeMapper.getUpdateWordDateIsNotNull();
        int size = idList.size();
        LOGGER.info("总条数:" + size);
        Collections.sort(idList, (o1, o2) -> o1.compareTo(o2));
        int index = 0;
        int num = 1;
        List<Integer> tempList = new ArrayList<>();
        Integer idStart = null;
        Integer idEnd = null;
        Map<String, Integer> parm = new HashMap<>();
        for (Integer i : idList) {
            if (index == 0) {
                idStart = i;
            }
            if (index == 20000 || num == size) {
                LOGGER.info("当前执行个数:" + num);
                idEnd = i;
                index = -1;
                parm.put("idStart", idStart);
                parm.put("idEnd", idEnd);
                LOGGER.info("idStart:" + idStart);
                LOGGER.info("idEnd:" + idEnd);
                LOGGER.info("------------------------------------------------");
                codeMapper.setUpdateWordDateIsNull(parm);
            }
            index++;
            num++;
        }
        LOGGER.info("执行结束");
    }

    @Override
    public void startUpdateIsUpdateIs0UpdateDateIsNullAndUpdateisUpdateIs3() {
        List<Integer> ids = codeMapper.getIds();
    }

    @Override
    public void asyncCodeAttrToSubmeter() {
        CodeAttrModel codeAttrModel = new CodeAttrModel();
        List<String> goodsCodeList = codeMapper.getGoodsCodeList();
        for (int i = 0; i < goodsCodeList.size(); i++) {
            System.out.println(i);
            try {
                codeAttrModel.setGoodsCode(goodsCodeList.get(i));
                codeMapper.codeAttrCopyToSubmeter(codeAttrModel);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


}
