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
        List<String> barcode0 = new ArrayList<>();
        List<String> barcode1 = new ArrayList<>();
        List<String> barcode2 = new ArrayList<>();
        List<String> barcode3 = new ArrayList<>();
        List<String> barcode4 = new ArrayList<>();
        List<String> barcode5 = new ArrayList<>();
        List<String> barcode6 = new ArrayList<>();
        List<String> barcode7 = new ArrayList<>();
        List<String> barcode8 = new ArrayList<>();
        List<String> barcode9 = new ArrayList<>();
        int size = goodsCodeList.size();
        int num = 10000;
        LOGGER.info("总次数:" + size);
        for (int i = 0; i < size; i++) {
            String code = goodsCodeList.get(i);
            String lastChar = code.charAt(code.length() - 1) + "";
            switch (lastChar) {
                case "0":
                    barcode0.add(code);
                    if (barcode0.size() >= num) {
                        codeAttrModel.setGoodsCode(lastChar);
                        codeAttrModel.setGoodsCodeList(barcode0);
                        codeMapper.codeAttrCopyToSubmeter(codeAttrModel);
                        barcode0.clear();
                        LOGGER.info("剩余次数:" + (size - i - 1));
                    }
                    break;
                case "1":
                    barcode1.add(code);
                    if (barcode1.size() >= num) {
                        codeAttrModel.setGoodsCode(lastChar);
                        codeAttrModel.setGoodsCodeList(barcode1);
                        codeMapper.codeAttrCopyToSubmeter(codeAttrModel);
                        barcode1.clear();
                        LOGGER.info("剩余次数:" + (size - i - 1));
                    }
                    break;
                case "2":
                    barcode2.add(code);
                    if (barcode2.size() >= num) {
                        codeAttrModel.setGoodsCode(lastChar);
                        codeAttrModel.setGoodsCodeList(barcode2);
                        codeMapper.codeAttrCopyToSubmeter(codeAttrModel);
                        barcode2.clear();
                        LOGGER.info("剩余次数:" + (size - i - 1));
                    }
                    break;
                case "3":
                    barcode3.add(code);
                    if (barcode3.size() >= num) {
                        codeAttrModel.setGoodsCode(lastChar);
                        codeAttrModel.setGoodsCodeList(barcode3);
                        codeMapper.codeAttrCopyToSubmeter(codeAttrModel);
                        barcode3.clear();
                        LOGGER.info("剩余次数:" + (size - i - 1));
                    }
                    break;
                case "4":
                    barcode4.add(code);
                    if (barcode4.size() >= num) {
                        codeAttrModel.setGoodsCode(lastChar);
                        codeAttrModel.setGoodsCodeList(barcode4);
                        codeMapper.codeAttrCopyToSubmeter(codeAttrModel);
                        barcode4.clear();
                        LOGGER.info("剩余次数:" + (size - i - 1));
                    }
                    break;
                case "5":
                    barcode5.add(code);
                    if (barcode5.size() >= num) {
                        codeAttrModel.setGoodsCode(lastChar);
                        codeAttrModel.setGoodsCodeList(barcode5);
                        codeMapper.codeAttrCopyToSubmeter(codeAttrModel);
                        barcode5.clear();
                        LOGGER.info("剩余次数:" + (size - i - 1));
                    }
                    break;
                case "6":
                    barcode6.add(code);
                    if (barcode6.size() >= num) {
                        codeAttrModel.setGoodsCode(lastChar);
                        codeAttrModel.setGoodsCodeList(barcode6);
                        codeMapper.codeAttrCopyToSubmeter(codeAttrModel);
                        barcode6.clear();
                        LOGGER.info("剩余次数:" + (size - i - 1));
                    }
                    break;
                case "7":
                    barcode7.add(code);
                    if (barcode7.size() >= num) {
                        codeAttrModel.setGoodsCode(lastChar);
                        codeAttrModel.setGoodsCodeList(barcode7);
                        codeMapper.codeAttrCopyToSubmeter(codeAttrModel);
                        barcode7.clear();
                        LOGGER.info("剩余次数:" + (size - i - 1));
                    }
                    break;
                case "8":
                    barcode8.add(code);
                    if (barcode8.size() >= num) {
                        codeAttrModel.setGoodsCode(lastChar);
                        codeAttrModel.setGoodsCodeList(barcode8);
                        codeMapper.codeAttrCopyToSubmeter(codeAttrModel);
                        barcode8.clear();
                        LOGGER.info("剩余次数:" + (size - i - 1));
                    }
                    break;
                case "9":
                    barcode9.add(code);
                    if (barcode9.size() >= num) {
                        codeAttrModel.setGoodsCode(lastChar);
                        codeAttrModel.setGoodsCodeList(barcode9);
                        codeMapper.codeAttrCopyToSubmeter(codeAttrModel);
                        barcode9.clear();
                        LOGGER.info("剩余次数:" + (size - i - 1));
                    }
                    break;
            }
        }

        Map<String, List<String>> map = new HashMap<>();
        map.put("0", barcode0);
        map.put("1", barcode1);
        map.put("2", barcode2);
        map.put("3", barcode3);
        map.put("4", barcode4);
        map.put("5", barcode5);
        map.put("6", barcode6);
        map.put("7", barcode7);
        map.put("8", barcode8);
        map.put("9", barcode9);
        Set<String> submeters = map.keySet();
        for (String submeter : submeters) {
            List<String> list = map.get(submeter);
            if (list.size() > 0) {
                codeAttrModel.setGoodsCode(submeter);
                codeAttrModel.setGoodsCodeList(list);
                codeMapper.codeAttrCopyToSubmeter(codeAttrModel);
            }

        }
        LOGGER.info("执行完毕");


    }

    //将已经发布的条码添加到showSearch表表中
    @Override
    public void asyncShowSearch() {
        List<String> temp = new ArrayList<>();
        //查询已经发布的条形码
        List<String> publishCodeList = codeMapper.getPublishCode();
        temp.addAll(publishCodeList);
        List<String> showSearchCodeList = codeMapper.getShowSearchCode();
        publishCodeList.removeAll(showSearchCodeList);
        showSearchCodeList.removeAll(temp);

        if (publishCodeList.size() > 0) {
            codeMapper.addToShowSearch(publishCodeList);
        }
        if (showSearchCodeList.size() > 0) {
            codeMapper.deleteFromShowSearch(showSearchCodeList);
        }
        publishCodeList.forEach(s -> LOGGER.info("添加到showSearch的条码:" + s));
        System.out.println("添加到showSearch的条码数量:" + publishCodeList.size());
        publishCodeList.forEach(s -> LOGGER.info("从删除showSearch的条码:" + s));
        System.out.println("从删除showSearch的条码数量:" + publishCodeList.size());
    }

    @Override
    public String getBarcodeInto(String barcode) {
        CodeAttrModel codeAttrModel = new CodeAttrModel();
        codeAttrModel.setGoodsCode(barcode);
        StringBuilder stringBuilder = new StringBuilder();
        CodeAttrModel main = codeMapper.getCodeAttrByGoodsCode(codeAttrModel);
        stringBuilder.append(main.toString()).append("\n");
        CodeAttrModel submeter = codeMapper.getCodeAttrSubmeterByGoodsCode(codeAttrModel);
        stringBuilder.append(submeter.toString());
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }


}
