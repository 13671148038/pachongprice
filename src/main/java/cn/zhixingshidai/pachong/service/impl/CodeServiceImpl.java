package cn.zhixingshidai.pachong.service.impl;

import cn.zhixingshidai.pachong.dao.CodeMapper;
import cn.zhixingshidai.pachong.service.CodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CodeServiceImpl implements CodeService {
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
                List<Map<String, Object>> result = codeMapper.getCodeAttr(condition);
                int index = 0;
                for (Map<String, Object> item : result) {
                    //根据wordid查询分类id
                    String goodClassId = codeMapper.getGoodIdByWordId(item);
                    //根据分类id查找id
                    Integer id = codeMapper.getIdByGoodClassId(goodClassId);
                    if (id == null) {
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
                List<Map<String, Object>> result = codeMapper.getCodeAttr(condition);
                int index = 0;
                for (Map<String, Object> item : result) {
                    //根据wordid查询分类id
                    String goodClassId = codeMapper.getGoodIdByWordId(item);
                    //根据分类id查找id
                    Integer id = codeMapper.getIdByGoodClassId(goodClassId);
                    if (id == null) {
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
                List<Map<String, Object>> result = codeMapper.getCodeAttr(condition);
                int index = 0;
                for (Map<String, Object> item : result) {
                    //根据wordid查询分类id
                    String goodClassId = codeMapper.getGoodIdByWordId(item);
                    Integer id = codeMapper.getIdByGoodClassId(goodClassId);
                    if (id == null) {
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
                List<Map<String, Object>> result = codeMapper.getCodeAttr(condition);
                int index = 0;
                for (Map<String, Object> item : result) {
                    //根据wordid查询分类id
                    String goodClassId = codeMapper.getGoodIdByWordId(item);
                    Integer id = codeMapper.getIdByGoodClassId(goodClassId);
                    if (id == null) {
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
                List<Map<String, Object>> result = codeMapper.getCodeAttr(condition);
                int index = 0;
                for (Map<String, Object> item : result) {
                    //根据wordid查询分类id
                    String goodClassId = codeMapper.getGoodIdByWordId(item);
                    Integer id = codeMapper.getIdByGoodClassId(goodClassId);
                    if (id == null) {
                        continue;
                    }
                    item.put("goodsClassId", id);
                    item.put("tableName", "tb_barcode_3");
                    codeMapper.update(item);
                    logger.info("tb_barcode_3 wordId为:" + item.get("goodsWordId") + " 分类id为:" + id);
                    index++;
                    logger.info("更新tb_coded_3表 总数为:"+(result.size()+1)+" 当前执行数为:"+index);
                    System.out.println("更新tb_coded_3表 总数为:"+(result.size()+1)+" 当前执行数为:"+index);
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
                List<Map<String, Object>> result = codeMapper.getCodeAttr(condition);
                int index = 0;
                for (Map<String, Object> item : result) {
                    //根据wordid查询分类id
                    String goodClassId = codeMapper.getGoodIdByWordId(item);
                    Integer id = codeMapper.getIdByGoodClassId(goodClassId);
                    if (id == null) {
                        continue;
                    }
                    item.put("goodsClassId", id);
                    item.put("tableName", "tb_barcode_4");
                    codeMapper.update(item);
                    logger.info("tb_barcode_4 wordId为:" + item.get("goodsWordId") + " 分类id为:" + id);
                    index++;
                    logger.info("更新tb_coded_4表 总数为:"+(result.size()+1)+" 当前执行数为:"+index);
                    System.out.println("更新tb_coded_4表 总数为:"+(result.size()+1)+" 当前执行数为:"+index);
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
                List<Map<String, Object>> result = codeMapper.getCodeAttr(condition);
                int index = 0;
                for (Map<String, Object> item : result) {
                    //根据wordid查询分类id
                    String goodClassId = codeMapper.getGoodIdByWordId(item);
                    Integer id = codeMapper.getIdByGoodClassId(goodClassId);
                    if (id == null) {
                        continue;
                    }
                    item.put("goodsClassId", id);
                    item.put("tableName", "tb_barcode_5");
                    codeMapper.update(item);
                    logger.info("tb_barcode_5 wordId为:" + item.get("goodsWordId") + " 分类id为:" + id);
                    index++;
                    logger.info("更新tb_coded_5表 总数为:"+(result.size()+1)+" 当前执行数为:"+index);
                    System.out.println("更新tb_coded_5表 总数为:"+(result.size()+1)+" 当前执行数为:"+index);
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
                List<Map<String, Object>> result = codeMapper.getCodeAttr(condition);
                int index = 0;
                for (Map<String, Object> item : result) {
                    //根据wordid查询分类id
                    String goodClassId = codeMapper.getGoodIdByWordId(item);
                    Integer id = codeMapper.getIdByGoodClassId(goodClassId);
                    if (id == null) {
                        continue;
                    }
                    item.put("goodsClassId", id);
                    item.put("tableName", "tb_barcode_6");
                    codeMapper.update(item);
                    logger.info("tb_barcode_6 wordId为:" + item.get("goodsWordId") + " 分类id为:" + id);
                    index++;
                    logger.info("更新tb_coded_6表 总数为:"+(result.size()+1)+" 当前执行数为:"+index);
                    System.out.println("更新tb_coded_6表 总数为:"+(result.size()+1)+" 当前执行数为:"+index);
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
                List<Map<String, Object>> result = codeMapper.getCodeAttr(condition);
                int index = 0;
                for (Map<String, Object> item : result) {
                    //根据wordid查询分类id
                    String goodClassId = codeMapper.getGoodIdByWordId(item);
                    Integer id = codeMapper.getIdByGoodClassId(goodClassId);
                    if (id == null) {
                        continue;
                    }
                    item.put("goodsClassId", id);
                    item.put("tableName", "tb_barcode_7");
                    codeMapper.update(item);
                    logger.info("tb_barcode_7 wordId为:" + item.get("goodsWordId") + " 分类id为:" + id);
                    index++;
                    logger.info("更新tb_coded_7表 总数为:"+(result.size()+1)+" 当前执行数为:"+index);
                    System.out.println("更新tb_coded_7表 总数为:"+(result.size()+1)+" 当前执行数为:"+index);
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
                List<Map<String, Object>> result = codeMapper.getCodeAttr(condition);
                int index = 0;
                for (Map<String, Object> item : result) {
                    //根据wordid查询分类id
                    String goodClassId = codeMapper.getGoodIdByWordId(item);
                    Integer id = codeMapper.getIdByGoodClassId(goodClassId);
                    if (id == null) {
                        continue;
                    }
                    item.put("goodsClassId", id);
                    item.put("tableName", "tb_barcode_8");
                    codeMapper.update(item);
                    logger.info("tb_barcode_8 wordId为:" + item.get("goodsWordId") + " 分类id为:" + id);
                    index++;
                    logger.info("更新tb_barcode_8表 总数为:"+(result.size()+1)+" 当前执行数为:"+index);
                    System.out.println("更新tb_barcode_8表 总数为:"+(result.size()+1)+" 当前执行数为:"+index);
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
                List<Map<String, Object>> result = codeMapper.getCodeAttr(condition);
                int index = 0;
                for (Map<String, Object> item : result) {
                    //根据wordid查询分类id
                    String goodClassId = codeMapper.getGoodIdByWordId(item);
                    Integer id = codeMapper.getIdByGoodClassId(goodClassId);
                    if (id == null) {
                        continue;
                    }
                    item.put("goodsClassId", id);
                    item.put("tableName", "tb_barcode_9");
                    codeMapper.update(item);
                    logger.info("tb_barcode_9 wordId为:" + item.get("goodsWordId") + " 分类id为:" + id);
                    index++;
                    logger.info("更新tb_barcode_9表 总数为:"+(result.size()+1)+" 当前执行数为:"+index);
                    System.out.println("更新tb_barcode_9表 总数为:"+(result.size()+1)+" 当前执行数为:"+index);
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
}
