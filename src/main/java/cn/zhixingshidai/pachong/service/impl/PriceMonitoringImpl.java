package cn.zhixingshidai.pachong.service.impl;

import cn.zhixingshidai.pachong.dao.DiscountDao;
import cn.zhixingshidai.pachong.service.PriceMonitoring;
import cn.zhixingshidai.pachong.until.MailUtil;
import cn.zhixingshidai.pachong.until.PricePaChongUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.List;
import java.util.Map;

@Service
public class PriceMonitoringImpl implements PriceMonitoring {
    @Autowired
    private DiscountDao discountDao;

    @Override
    public void test1() {
        List<Map<String, Object>> result = discountDao.getAllInto();
        //2ge网优惠折扣地址前缀
        String erGeDiscountAddress = "http://www.2ge.cn/discount/dis/";
        //唯品会
        String wph = ".vip.com/";
//        网易考拉
        String wykl = ".kaola.com/";
        //当当
        String dd = ".dangdang.com/";
        //华为
        String hw = "www.vmall.com";
        //苏宁
        String snyg = "product.suning.com";
        //西集
        String xj = "www.xiji.com";
        //网易
        String wy = "you.163.com";
        //总共扫描条数
        int total = 0;
        //价格变动条数
        int bTotal = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (Map<String, Object> item : result) {
            String activity_address = (String) item.get("activity_address");
            if (StringUtils.isBlank(activity_address)) {
                continue;
            }
            Map<String, Object> resultItem = null;
            if (activity_address.contains(wph)) {
                resultItem = PricePaChongUtil.vip(item);
                total++;
            }
            if (activity_address.contains(wykl)) {
                resultItem = PricePaChongUtil.wykl(item);
                total++;
            }
            if (activity_address.contains(dd)) {
                resultItem = PricePaChongUtil.dd(item);
                total++;
            }
            if (activity_address.contains(hw)) {
                resultItem = PricePaChongUtil.hw(item);
                total++;
            }
            if (activity_address.contains(snyg)) {
                resultItem = PricePaChongUtil.snyg(item);
                total++;
            }
            if (activity_address.contains(xj)) {
                resultItem = PricePaChongUtil.xj(item);
                total++;
            }
            if (activity_address.contains(wy)) {
                resultItem = PricePaChongUtil.wy(item);
                total++;
            }
            if (resultItem != null) {
                bTotal++;
                String title = (String) resultItem.get("title");
                Long discount_id = (Long) resultItem.get("discount_id");
                //2哥链接
                String ergeAddress = erGeDiscountAddress + discount_id;
                //原现价
                Double original_price = (Double) resultItem.get("original_price");
                //原优惠
                Double discount_after_price = (Double) resultItem.get("discount_after_price");
                //现优惠价格
                String currentDPrice = (String) resultItem.get("currentDPrice");
                //现原价
                String currentOPrice = (String) resultItem.get("currentOPrice");
                stringBuilder.append("原始价格：").
                        append(original_price).append("  原始折扣价: ").
                        append(discount_after_price).
                        append("<br/>").
                        append("现原价: ").append(currentOPrice).append("  现折扣价:").append(currentDPrice).
                        append("<br/>").
                        append("标题： ").append(title).
                        append("<br/>").
                        append("链接：").append(ergeAddress).
                        append("<br/>").
                        append("商品详情页链接： ").
                        append(activity_address).
                        append("<br/>").append("").
                        append("<br/>");
            }
        }
        try {
            stringBuilder.append("共扫描 ").append(total).append(" 条记录, 价格变动的数量为").append(bTotal).append(" 条");
            MailUtil.sendMail("zhushaopeng@zhixingshidai.com", "优惠折扣非cps商品价格监控结果", stringBuilder.toString());
           /* MailUtil.sendMail("qiaowenbo@zhixingshidai.com", "优惠折扣非cps商品价格监控结果", stringBuilder.toString());
            MailUtil.sendMail("liuyongming@zhixingshidai.com", "优惠折扣非cps商品价格监控结果", stringBuilder.toString());
            MailUtil.sendMail("zhangchi@zhixingshidai.com", "优惠折扣非cps商品价格监控结果", stringBuilder.toString());
            MailUtil.sendMail("zhangheyi@zhixingshidai.com", "优惠折扣非cps商品价格监控结果", stringBuilder.toString());
            MailUtil.sendMail("wangfangfang@zhixingshidai.com", "优惠折扣非cps商品价格监控结果", stringBuilder.toString());*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void priceMonitoringCompare() throws Exception {
        Gson gson = new Gson();
        FileWriter fileWriter = new FileWriter("C:/Users/13671/Desktop/pppp.txt", true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        List<Map<String, Object>> result = discountDao.getAllInto();
        String userAgent = "Mozilla/5.0 (Windows NT 10.0;Win64;x64;rv:63.0) Gecko/20100101 Firefox/63.0";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpEntity entity = null;
        HttpGet httpGet = new HttpGet();
        httpGet.setHeader("User-Agent", userAgent);
        CloseableHttpResponse execute = null;
        Map<String, Object> jdMap = null;
        String resultP = "";
        StringBuilder sb = new StringBuilder();
        String erGeAfddress = "http://www.2ge.cn/discount/dis/";
        //京东
        String jd = "3https://item.jd.com/";
        //淘宝
        String tb = "3https://item.taobao.com/item.htm?id=";
        //淘宝专用请求头
        httpGet.setHeader("Accept", "*/*");
        httpGet.setHeader("Referer", "https://item.taobao.com/item.htm?id=537330014675&ali_trackid=2:mm_167860039_81800429_15620700198:1539757821_144_254978455&pvid=10_222.128.44.223_931_1539053262245");
        httpGet.removeHeaders("Upgrade-Insecure-Requests");
        //天猫
        String tm = "https:r//detail.tmall.com/item.htm?id=";
        //唯品会
        String wph = "https://detail.vip.com/detail-";
        String jgbs = "J_surpriseSprice_wrap";
        //网易考拉
        String kl = "https://goods.kaola.com/product/";
        //当当网
        String dd = "http://product.dangdang.com/";
        //华为商场
        String hw = "https://www.vmall.com/product/";
        //苏宁易购
        String snyg = "https://product.suning.com/";
        //西集
        String xj = "www.xiji.com";
        //网易
        String wy = "you.163.com";
        Type type = new TypeToken<Map<String, Object>>() {
        }.getType();
        for (Map<String, Object> item : result) {
            try {
                String activity_address = (String) item.get("activity_address");
                Long discount_id = (Long) item.get("discount_id");
                System.out.println(discount_id);
                Double original_price = (Double) item.get("original_price");
                Double discount_after_price = (Double) item.get("discount_after_price");
                StringBuilder stringBuilder = new StringBuilder();
                //京东联盟
                if (activity_address.startsWith(jd) && activity_address.endsWith(".html")) {
                    //获取商品价格
                    stringBuilder.setLength(0);
                    String itemId = stringBuilder.append(activity_address).
                            substring(jd.length(), stringBuilder.length() - 5);
                    //价格url https://p.3.cn/prices/mgets?skuIds=J_4609662
                    httpGet.setURI(URI.create("https://p.3.cn/prices/mgets?skuIds=J_" + itemId));
                    entity = httpClient.execute(httpGet).getEntity();
                    String json = EntityUtils.toString(entity);
                    if (json != null && !"".equals(json)) {
                        jdMap = gson.fromJson(json.substring(1, json.lastIndexOf("]")), type);
                        //现价
                        String p = (String) jdMap.get("p");
                        String m = (String) jdMap.get("m");
                        String op = (String) jdMap.get("op");
                        resultP =
                                "         2哥链接:" + erGeAfddress + discount_id +
                                        "         库里面原价:" + original_price +
                                        "            库里面优惠价:" + discount_after_price;
                        resultP += "           抓取优惠价:" + p + "       抓取原价" + op + "    抓取定价" + m;
                    }

                    //淘宝联盟
                } else if (activity_address.startsWith(tb)) {
                    stringBuilder.setLength(0);
                    stringBuilder.append(activity_address);
                    int endLength = stringBuilder.length();
                    if (activity_address.contains("&")) {
                        endLength = stringBuilder.indexOf("&");
                    }
                    String id = stringBuilder.append(activity_address).substring(tb.length(), endLength);
                    String url = "https://detailskip.taobao.com/service/getData/1/p1/item/detail/sib.htm?itemId=" + id + "&modules=price,xmpPromotion,originalPrice";
                    httpGet.setURI(URI.create(url));
                    execute = httpClient.execute(httpGet);
                    entity = execute.getEntity();
                    String content = EntityUtils.toString(entity).trim();
                    //获取原价价格
                    String price = "";
                    //定义推广价
                    String promoPirce = "";
                    if (content != null && !"".equals(content)) {
                        jdMap = gson.fromJson(content, type);
                        Map<String, Object> data = (Map<String, Object>) jdMap.get("data");
                        if (data != null) {
                            price = (String) data.get("price");
                            price = (String) data.get("price");
                            if (price == null) {
                                price = "";
                            }
                            Map<String, Object> promotion = (Map<String, Object>) data.get("promotion");
                            Map<String, Object> promoData = (Map<String, Object>) promotion.get("promoData");
                            if (promoData != null) {
                                List<Object> def = (List<Object>) promoData.get("def");
                                if (def != null && def.size() > 0) {
                                    Map<String, Object> o = (Map<String, Object>) def.get(0);
                                    promoPirce = (String) o.get("price");
                                }
                            }
                        }
                        resultP =
                                "         2哥链接:" + erGeAfddress + discount_id +
                                        "         库里面原价:" + original_price +
                                        "            库里面优惠价:" + discount_after_price;
                        resultP += "           抓取优惠价:" + promoPirce + "       抓取原价" + price + "    抓取定价";
                    }
                    //唯品会 https://detail.vip.com/detail-3559750-654889948.html==========================================
                } else if (activity_address.startsWith(wph)) {
                    resultP =
                            "         2哥链接:" + erGeAfddress + discount_id +
                                    "         库里面原价:" + original_price +
                                    "            库里面优惠价:" + discount_after_price;
                    stringBuilder.setLength(0);
                    stringBuilder.append(activity_address);
                    String substring = stringBuilder.substring(stringBuilder.indexOf("-") + 1);
                    stringBuilder.setLength(0);
                    String intemid = stringBuilder.append(substring).substring(stringBuilder.indexOf("-") + 1, stringBuilder.indexOf("."));
                    String priceId = jgbs + intemid;
                    if (activity_address.contains("?")) {
                        activity_address = activity_address.substring(0, activity_address.indexOf("?"));
                    }
                    httpGet.setURI(URI.create(activity_address));
                    execute = httpClient.execute(httpGet);
                    entity = execute.getEntity();
                    String s = EntityUtils.toString(entity);
                    stringBuilder.setLength(0);
                    stringBuilder.append(s);
                    if (s == null || s.length() == 0) {
                        resultP += "        路径错误";
                    } else if (stringBuilder.indexOf(priceId) == -1) {
                        resultP += "          电脑端无法查看";
                    } else {
                        String jPrice = "J-price\">";
                        stringBuilder.delete(0, stringBuilder.indexOf(priceId)).
                                delete(0, stringBuilder.indexOf(jPrice) + jPrice.length());
                        //计算优惠后的价格
                        String discountP = stringBuilder.substring(0, stringBuilder.indexOf("<"));
                        //计算优惠前的价格
                        String mPrice = "J-mPrice\">";
                        String originalP = stringBuilder.delete(0, stringBuilder.indexOf(mPrice) + mPrice.length()).
                                substring(0, stringBuilder.indexOf("<"));
                        try {
                            if (!Double.valueOf(discountP).equals(discount_after_price) || !Double.valueOf(originalP).equals(original_price)) {
                                resultP += "           抓取优惠价:" + discountP + "       抓取原价" + originalP;
                            } else {
                                resultP = "";
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            resultP += "           抓取优惠价:" + discountP + "       抓取原价" + originalP;
                        }
                    }
                    //网易考拉监控 String kl = "https://goods.kaola.com/product/";
                } else if (activity_address.startsWith(kl)) {
                    resultP =
                            "         2哥链接:" + erGeAfddress + discount_id +
                                    "         库里面原价:" + original_price +
                                    "            库里面优惠价:" + discount_after_price;
                    String url = "https://goods.kaola.com/product/getPcGoodsDetailDynamic.json?goodsId=";
                    stringBuilder.setLength(0);
                    String priductId = stringBuilder.append(activity_address).
                            delete(stringBuilder.indexOf(".html"), stringBuilder.length()).
                            delete(0, stringBuilder.lastIndexOf("/") + 1).toString();
                    httpGet.setURI(new URI(url + priductId));
                    execute = httpClient.execute(httpGet);
                    entity = execute.getEntity();
                    String json = EntityUtils.toString(entity);
                    stringBuilder.setLength(0);
                    stringBuilder.append(json);
                    String marketPrice = "marketPrice\":";
                    String currentPrice = "currentPrice\":";
                    //市场价
                    marketPrice = stringBuilder.delete(0, stringBuilder.indexOf(marketPrice) + marketPrice.length()).substring(0, stringBuilder.indexOf(","));
                    //当前价格
                    currentPrice = stringBuilder.delete(0, stringBuilder.indexOf(currentPrice) + currentPrice.length()).substring(0, stringBuilder.indexOf(","));
                    try {
                        if (!discount_after_price.equals(Double.valueOf(currentPrice)) || !original_price.equals(Double.valueOf(marketPrice))) {
                            resultP += "           抓取优惠价:" + currentPrice + "       抓取原价" + marketPrice;
                        } else {
                            resultP = "";
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        resultP += "           抓取优惠价:" + currentPrice + "       抓取原价" + marketPrice;
                    }
                    //当当网
                } else if (activity_address.startsWith(dd)) {
                    resultP =
                            "         2哥链接:" + erGeAfddress + discount_id +
                                    "         库里面原价:" + original_price +
                                    "            库里面优惠价:" + discount_after_price;
                    httpGet.setURI(new URI(activity_address.substring(0, activity_address.indexOf(".html") + 5)));
                    execute = httpClient.execute(httpGet);
                    entity = execute.getEntity();
                    String content = EntityUtils.toString(entity);
                    if (content.contains("id=\"dd-price\"")) {
                        stringBuilder.setLength(0);
                        stringBuilder.append(content).
                                delete(0, stringBuilder.indexOf("id=\"dd-price\"")).
                                delete(0, stringBuilder.indexOf("</span>") + 7);
                        //获取当当价格
                        String ddPrice = stringBuilder.substring(0, stringBuilder.indexOf("<"));
                        //获取原价
                        String originalPrice = "";
                        String bs = "id=\"original-price\"";
                        int i = stringBuilder.indexOf(bs);
                        if (i != -1) {
                            stringBuilder.delete(0, stringBuilder.indexOf(bs)).
                                    delete(0, stringBuilder.indexOf("</span>") + 7);
                            originalPrice = stringBuilder.substring(0, stringBuilder.indexOf("<"));
                        }
                        try {
                            if (!discount_after_price.equals(Double.valueOf(ddPrice)) || !original_price.equals(Double.valueOf(originalPrice))) {
                                resultP += "           抓取优惠价:" + ddPrice + "       抓取原价" + originalPrice;
                            } else {
                                resultP = "";
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            resultP += "           抓取优惠价:" + ddPrice + "       抓取原价" + originalPrice;
                        }

                    } else {
                        resultP = "";
                    }
                    //华为是商场 id="pro-name">
                } else if (activity_address.startsWith(hw)) {
                    resultP =
                            "         2哥链接:" + erGeAfddress + discount_id +
                                    "         库里面原价:" + original_price +
                                    "            库里面优惠价:" + discount_after_price;
                    httpGet.setURI(new URI(activity_address));
                    execute = httpClient.execute(httpGet);
                    entity = execute.getEntity();
                    String content = EntityUtils.toString(entity);
                    stringBuilder.setLength(0);
                    String proNameBs = "id=\"pro-name\">";
                    String bs = "ec.context = \"\";";
                    String bs2 = "ec.product.gift.init";
                    String priceBs = "price : \"";
                    String originPriceBs = "originPrice : \"";
                    try {
                        //查询商品的名字
                        String proName = stringBuilder.append(content).delete(0, stringBuilder.indexOf(proNameBs) + proNameBs.length()).
                                substring(0, stringBuilder.indexOf("<"));
                        stringBuilder.delete(0, stringBuilder.indexOf(bs)).delete(0, stringBuilder.indexOf(bs2));

                        String content2 = stringBuilder.substring(0, stringBuilder.indexOf(proName));
                        stringBuilder.setLength(0);
                        //获取优惠价
                        String price = stringBuilder.append(content2).delete(0, stringBuilder.indexOf(priceBs) + priceBs.length()).
                                substring(0, stringBuilder.indexOf("\""));
                        String originPrice = stringBuilder.delete(0, stringBuilder.indexOf(originPriceBs) + originPriceBs.length()).
                                substring(0, stringBuilder.indexOf("\""));
                        if (!discount_after_price.equals(Double.valueOf(price)) || !original_price.equals(Double.valueOf(originPrice))) {
                            resultP += "           抓取优惠价:" + price + "       抓取原价" + originPrice;
                        } else {
                            resultP = "";
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        resultP += "商品已下架";
                    }
                    //苏宁易购
                    // 原链接 https://product.suning.com/0070079092/690098567.html
                    // 获取价格的链接 https://pas.suning.com/nspcsale_0_000000000690098567_000000000690098567_0070073727_10_010_0100101.html?callback=pcData
                } else if (activity_address.startsWith(snyg)) {
                    resultP =
                            "         2哥链接:" + erGeAfddress + discount_id +
                                    "         库里面原价:" + original_price +
                                    "            库里面优惠价:" + discount_after_price;
                    String priceUrlPrefix = "https://pas.suning.com/nspcsale_0_";
                    String priceUrlSuffix = "_10_010_0100101.html?callback=pcData";
                    stringBuilder.setLength(0);
                    String parameter1 = stringBuilder.append(activity_address).delete(0, stringBuilder.indexOf(".com/") + 5).substring(0, stringBuilder.indexOf("/"));
                    String parameter2 = stringBuilder.substring(stringBuilder.indexOf("/") + 1, stringBuilder.indexOf("."));
                    stringBuilder.setLength(0);
                    int length = stringBuilder.append(parameter2).length();
                    for (int i = 0; i < 18 - length; i++) {
                        stringBuilder.insert(0, "0");
                    }
                    parameter2 = stringBuilder.toString();
                    //拼接路径
                    stringBuilder.setLength(0);
                    String priceUrl = stringBuilder.append(priceUrlPrefix).append(parameter2).append("_").append(parameter2).append("_").append(parameter1).append(priceUrlSuffix).toString();
                    httpGet.setURI(new URI(priceUrl));
                    execute = httpClient.execute(httpGet);
                    entity = execute.getEntity();
                    String content = EntityUtils.toString(entity);
                    stringBuilder.setLength(0);
                    stringBuilder.append(content);
                    String contentPreFix = "pcData(";
                    String contentSuffix = ")";
                    stringBuilder.delete(0, contentPreFix.length()).deleteCharAt(stringBuilder.lastIndexOf(contentSuffix));
                    jdMap = gson.fromJson(stringBuilder.toString(), type);
                    Map<String, Object> data = (Map<String, Object>) jdMap.get("data");
                    if (data != null) {
                        Map<String, Object> price = (Map<String, Object>) data.get("price");
                        if (price != null) {
                            List<Object> saleInfo = (List<Object>) price.get("saleInfo");
                            if (saleInfo.size() > 0) {
                                Map<String, Object> productInto = (Map<String, Object>) saleInfo.get(0);
                                String noPriceCausation = (String) productInto.get("noPriceCausation");
                                if (noPriceCausation == null || !"".equals(noPriceCausation.trim())) {
                                    resultP += "该商品已下架";
                                } else {
                                    //原价 如果refPrice不为空 就是用refPrice,如果为空就使用netPrice
                                    String refPrice = (String) productInto.get("refPrice");
                                    if ("".equals(refPrice)) {
                                        refPrice = (String) productInto.get("netPrice");
                                    }
                                    //促销价
                                    String promotionPrice = (String) productInto.get("promotionPrice");
                                    try {
                                        if (!discount_after_price.equals(Double.valueOf(promotionPrice)) || !original_price.equals(Double.valueOf(refPrice))) {
                                            resultP += "           抓取优惠价:" + promotionPrice + "       抓取原价" + refPrice;
                                        } else {
                                            resultP = "";
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        if ("".equals(promotionPrice)) {
                                            resultP += "该商品已下架";
                                        } else {
                                            resultP += "           抓取优惠价:" + promotionPrice + "       抓取原价" + refPrice;
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        resultP += "价格路径错误, 错误路径为:" + priceUrl;
                    }

                    //西集网
                } else if (activity_address.contains(xj)) {
                    resultP =
                            "         2哥链接:" + erGeAfddress + discount_id +
                                    "         库里面原价:" + original_price +
                                    "            库里面优惠价:" + discount_after_price;
                    String getPriceUrl = "http://www.xiji.com/product-ajax_product_price-48773.html";
                    String xijiUrl = "http://www.xiji.com/product-95664.html";
                    String xijiUrl2 = "https://www.linkhaitao.com/index.php?mod=lhdeal&track=46d6vnGHX0ZAfkP7T54zzb7JNM5lCz6jmpn6ylG7uErVdmuTy7W6vUlUAOPq_aLc_c&new=http%3A%2F%2Fwww.xiji.com%2Fproduct-48773.html";
                    //定义商品的id
                    String productId = "";
                    stringBuilder.setLength(0);
                    stringBuilder.append(activity_address);
                    if (activity_address.startsWith("http://www.xiji.com/product-")) {
                        stringBuilder.substring(stringBuilder.indexOf("-") + 1, stringBuilder.indexOf(".html"));
                    } else {
                        stringBuilder.delete(stringBuilder.lastIndexOf(".html"), stringBuilder.length()).delete(0, stringBuilder.lastIndexOf("-") + 1);
                    }
                    productId = stringBuilder.toString();
                    //拼接获取价格的路径
                    stringBuilder.setLength(0);
                    getPriceUrl = stringBuilder.append("http://www.xiji.com/product-ajax_product_price-").append(productId).append(".html").toString();
                    httpGet.setURI(new URI(getPriceUrl));
                    execute = httpClient.execute(httpGet);
                    entity = execute.getEntity();
                    String content = EntityUtils.toString(entity);
                    if (StringUtils.isNotBlank(content)) {
                        jdMap = gson.fromJson(content, type);
                        //售价
                        String price = (String) jdMap.get("price");
                        //原价
                        String mktprice = (String) jdMap.get("mktprice");
                        if (StringUtils.isBlank(price) && StringUtils.isBlank(mktprice)) {
                            resultP += "商品已下架或路径不对";
                        } else {

                            try {
                                if (!discount_after_price.equals(Double.valueOf(price)) || !original_price.equals(Double.valueOf(mktprice))) {
                                    resultP += "           抓取优惠价:" + price + "       抓取原价" + mktprice;
                                } else {
                                    resultP = "";
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                resultP += "           抓取优惠价:" + price + "       抓取原价" + mktprice;
                            }
                        }
                    } else {
                        resultP += "商品已下架或路径不对 ";
                    }

                    //网易
                } else if (activity_address.contains(wy)) {
                    resultP =
                            "         2哥链接:" + erGeAfddress + discount_id +
                                    "         库里面原价:" + original_price +
                                    "            库里面优惠价:" + discount_after_price;
                    //数据局原链接
                    String r = "https://www.linkhaitao.com/index.php?mod=lhdeal&track=766eY15GnrSSf8bbskMOhKzGWatN7SFg0o4R_aTPZTHQ4TxjKkp1Q9We5XwRYrQ8m&new=http%3A%2F%2Fyou.163.com%2Fitem%2Fdetail%3Fid%3D1356010%26_stat_area%3Dmod_1_item_19%26_stat_id%3D1010000%26_stat_referer%3DitemList&tag=2ge";
                    //原链接截取标识
                    String bs = "&new=http%3A%2F%2Fyou.163.com%2Fitem%2Fdetail%3Fid%3D";
                    //拼接后的链接
                    String r2 = "http://you.163.com/item/detail?id=";
                    stringBuilder.setLength(0);
                    stringBuilder.append(activity_address);
                    if (!activity_address.startsWith("http://you.163.com/item/detail")) {
                        String id = stringBuilder.delete(0, stringBuilder.indexOf(bs) + bs.length()).substring(0, stringBuilder.indexOf("%"));
                        stringBuilder.setLength(0);
                        stringBuilder.append(r2).append(id);
                    }
                    httpGet.setURI(new URI(stringBuilder.toString()));
                    execute = httpClient.execute(httpGet);
                    entity = execute.getEntity();
                    String content = EntityUtils.toString(entity);
                    stringBuilder.setLength(0);
                    int index = stringBuilder.append(content).indexOf("JSON_DATA_FROMFTL");
                    if (index == -1) {
                        resultP += "路径不对";
                    } else {
                        String retailPricebs = "\"retailPrice\":";
                        //获取零售价
                        String retailPrice = stringBuilder.delete(0, index).
                                delete(stringBuilder.indexOf("};"), stringBuilder.length()).
                                delete(0, stringBuilder.indexOf(retailPricebs) + retailPricebs.length()).
                                substring(0, stringBuilder.indexOf(","));
                        //获取柜台价
                        String counterPricebs = "\"counterPrice\":";
                        stringBuilder.delete(0, stringBuilder.indexOf(counterPricebs) + counterPricebs.length());
                        String counterPrice = stringBuilder.substring(0, stringBuilder.indexOf(","));
                        try {
                            if (!discount_after_price.equals(Double.valueOf(retailPrice)) || !original_price.equals(Double.valueOf(counterPrice))) {
                                resultP += "           抓取优惠价:" + retailPrice + "       抓取原价" + counterPrice;
                            } else {
                                resultP = "";
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            resultP += "           抓取优惠价:" + retailPrice + "       抓取原价" + counterPrice;
                        }

                    }
                }

                if (!"".equals(resultP)) {
                    bufferedWriter.write(resultP);
                    bufferedWriter.newLine();
                }
                resultP = "";
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    private String PaChongJd(String content, String original_price, String discount_after_price, String activity_address) {
        Gson gson = new Gson();
        Map map = gson.fromJson(content, Map.class);
        //现价
        String p = (String) map.get("p");
        String m = (String) map.get("m");
        String op = (String) map.get("op");
        return ("链接:" + activity_address + "  原价:" + original_price + "现在价格:" + discount_after_price + "抓取价格:" + p + "抓取原价格" + op + "buzhi" + m);
    }
}
