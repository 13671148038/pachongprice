package cn.zhixingshidai.pachong.until;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.lang.reflect.GenericSignatureFormatError;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.List;
import java.util.Map;

public class PricePaChongUntil {

    //2ge网优惠折扣地址前缀
    private final static String erGeDiscountAddress = "http://www.2ge.cn/discount/dis/";

    //浏览器头信息
    private final static String userAgent = "Mozilla/5.0 (Windows NT 10.0;Win64;x64;rv:63.0) Gecko/20100101 Firefox/63.0";


    private final static CloseableHttpClient httpClient = HttpClients.createDefault();

    //唯品会价格标识 这个预约的没有
    private final static String jgbs = "J_surpriseSprice_wrap";
    //唯品会价格标识 这个预约和没有预约都可以
    private final static String jgbs2 = "id=\"J-pi-price-box\"";

    private final static Type type = new TypeToken<Map<String, Object>>() {
    }.getType();

    /**
     * 拉取西集网价格信息
     *
     * @param item
     * @return
     */
    public static String xj(Map<String, Object> item) {
        String activity_address = (String) item.get("activity_address");
        Long discount_id = (Long) item.get("discount_id");
        Double original_price = (Double) item.get("original_price");
        Double discount_after_price = (Double) item.get("discount_after_price");
        String resultP =
                "         2哥链接:" + erGeDiscountAddress + discount_id +
                        "         库里面原价:" + original_price +
                        "            库里面优惠价:" + discount_after_price;
        String getPriceUrl = "http://www.xiji.com/product-ajax_product_price-48773.html";
        String xijiUrl = "http://www.xiji.com/product-95664.html";
        String xijiUrl2 = "https://www.linkhaitao.com/index.php?mod=lhdeal&track=46d6vnGHX0ZAfkP7T54zzb7JNM5lCz6jmpn6ylG7uErVdmuTy7W6vUlUAOPq_aLc_c&new=http%3A%2F%2Fwww.xiji.com%2Fproduct-48773.html";
        //定义商品的id
        String productId = "";
        StringBuilder stringBuilder = new StringBuilder();
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
        try {
            HttpGet httpGet = new HttpGet(getPriceUrl);
            httpGet.setHeader("User-Agent", userAgent);
            CloseableHttpResponse execute = httpClient.execute(httpGet);
            HttpEntity entity = execute.getEntity();
            String content = EntityUtils.toString(entity);
            if (StringUtils.isNotBlank(content)) {
                Gson gson = new Gson();
                Map<String, Object> jdMap = gson.fromJson(content, type);
                //售价
                String price = (String) jdMap.get("price");
                //原价
                String mktprice = (String) jdMap.get("mktprice");
                if (StringUtils.isBlank(price) && StringUtils.isBlank(mktprice)) {
                    resultP += "商品已下架或拼接的价格路径不对,价格都是空";
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
                resultP += "商品已下架或拼接的获取价格路径不正确 ";
            }

        } catch (Exception e) {
            e.printStackTrace();
            resultP += "路径错误";
        }
        return resultP;
    }

    /**
     * 拉取网易网价格信息
     *
     * @param item
     * @return
     */
    public static String wy(Map<String, Object> item) {
        String activity_address = (String) item.get("activity_address");
        Long discount_id = (Long) item.get("discount_id");
        Double original_price = (Double) item.get("original_price");
        Double discount_after_price = (Double) item.get("discount_after_price");
        String resultP =
                "         2哥链接:" + erGeDiscountAddress + discount_id +
                        "         库里面原价:" + original_price +
                        "            库里面优惠价:" + discount_after_price;
        //数据局原链接
        String r = "https://www.linkhaitao.com/index.php?mod=lhdeal&track=766eY15GnrSSf8bbskMOhKzGWatN7SFg0o4R_aTPZTHQ4TxjKkp1Q9We5XwRYrQ8m&new=http%3A%2F%2Fyou.163.com%2Fitem%2Fdetail%3Fid%3D1356010%26_stat_area%3Dmod_1_item_19%26_stat_id%3D1010000%26_stat_referer%3DitemList&tag=2ge";
        //原链接截取标识
        String bs = "&new=http%3A%2F%2Fyou.163.com%2Fitem%2Fdetail%3Fid%3D";
        //拼接后的链接
        String r2 = "http://you.163.com/item/detail?id=";
        StringBuilder stringBuilder = new StringBuilder(activity_address);
        try {
            if (!activity_address.startsWith("http://you.163.com/item/detail")) {
                String id = stringBuilder.delete(0, stringBuilder.indexOf(bs) + bs.length()).substring(0, stringBuilder.indexOf("%"));
                stringBuilder.setLength(0);
                stringBuilder.append(r2).append(id);
            }
            HttpGet httpGet = new HttpGet(stringBuilder.toString());
            httpGet.setHeader("User-Agent", userAgent);
            CloseableHttpResponse execute = httpClient.execute(httpGet);
            HttpEntity entity = execute.getEntity();
            String content = EntityUtils.toString(entity);
            stringBuilder.setLength(0);
            int index = stringBuilder.append(content).indexOf("JSON_DATA_FROMFTL");
            if (index == -1) {
                resultP += "商品已下架或者路径不对";
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
        } catch (Exception e) {
            resultP += "路径错误";
        }
        return resultP;
    }

    /**
     * 拉取苏宁易购商品的价格
     *
     * @param item
     * @return
     */
    public static String snyg(Map<String, Object> item) {
        String activity_address = (String) item.get("activity_address");
        Long discount_id = (Long) item.get("discount_id");
        Double original_price = (Double) item.get("original_price");
        Double discount_after_price = (Double) item.get("discount_after_price");
        String resultP =
                "         2哥链接:" + erGeDiscountAddress + discount_id +
                        "         库里面原价:" + original_price +
                        "            库里面优惠价:" + discount_after_price;
        String url1 = "https://product.suning.com/0070163466/10519124864.html";
        String url2 = "https://www.linkhaitao.com/index.php?mod=lhdeal&track=f6033OHSpXH2EOH5D6QG6WD1sQwDNWoOwHphIVJ_bfff0TSd0sq5M_apv_bQ02XcC4_c&new=https%3A%2F%2Fproduct.suning.com%2F0070089387%2F193495016.html%3Fclu%3D325574%26safp%3Dd488778a_46602_0_e2c08ea9a2&tag=2ge";
        // 获取价格的链接 https://pas.suning.com/nspcsale_0_000000000690098567_000000000690098567_0070073727_10_010_0100101.html?callback=pcData
        String priceUrlPrefix = "https://pas.suning.com/nspcsale_0_";
        String priceUrlSuffix = "_10_010_0100101.html?callback=pcData";
        String parameter1 = "";
        String parameter2 = "";
        try {
            StringBuilder stringBuilder = new StringBuilder(activity_address);
            if (activity_address.startsWith("https://product.suning.com/")) {
                parameter1 = stringBuilder.delete(0, stringBuilder.indexOf(".com/") + 5).substring(0, stringBuilder.indexOf("/"));
                parameter2 = stringBuilder.substring(stringBuilder.indexOf("/") + 1, stringBuilder.indexOf("."));
            } else if (activity_address.startsWith("https://www.linkhaitao.com/")) {
                String bs = "product.suning.com%2F";
                String[] split = stringBuilder.delete(0, stringBuilder.indexOf(bs) + bs.length()).delete(stringBuilder.indexOf(".html"), stringBuilder.length()).toString().split("%2F");
                parameter1 = split[0];
                parameter2 = split[1];
            }
            stringBuilder.setLength(0);
            int length = stringBuilder.append(parameter2).length();
            for (int i = 0; i < 18 - length; i++) {
                stringBuilder.insert(0, "0");
            }
            parameter2 = stringBuilder.toString();
            //拼接路径
            stringBuilder.setLength(0);
            String priceUrl = stringBuilder.append(priceUrlPrefix).append(parameter2).append("_").append(parameter2).append("_").append(parameter1).append(priceUrlSuffix).toString();
            HttpGet httpGet = new HttpGet(priceUrl);
            httpGet.setHeader("User-Agent", userAgent);
            CloseableHttpResponse execute = httpClient.execute(httpGet);
            HttpEntity entity = execute.getEntity();
            String content = EntityUtils.toString(entity);
            try {
                stringBuilder.setLength(0);
                stringBuilder.append(content);
                String contentPreFix = "pcData(";
                String contentSuffix = ")";
                stringBuilder.delete(0, contentPreFix.length()).deleteCharAt(stringBuilder.lastIndexOf(contentSuffix));
                Gson gson = new Gson();
                Map<String, Object> map = gson.fromJson(stringBuilder.toString(), type);
                Map<String, Object> data = (Map<String, Object>) map.get("data");
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
                }
            } catch (Exception e) {
                e.printStackTrace();
                resultP += "商品已下架或者该链接不存在";
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultP += "推广链接错错误";
        }
        return resultP;
    }

    /**
     * 拉取华为商城商品的价格
     *
     * @param item
     * @return
     */
    public static String hw(Map<String, Object> item) {
        String activity_address = (String) item.get("activity_address");
        Long discount_id = (Long) item.get("discount_id");
        Double original_price = (Double) item.get("original_price");
        Double discount_after_price = (Double) item.get("discount_after_price");
        String resultP =
                "         2哥链接:" + erGeDiscountAddress + discount_id +
                        "         库里面原价:" + original_price +
                        "            库里面优惠价:" + discount_after_price;
        //华为路径第一种情况
        String url1 = "https://www.vmall.com/product/2749.html#";
        //华为路径第二种情况
        String url2 = "https://www.linkhaitao.com/index.php?mod=lhdeal&track=cb751mjy925Akfy_ayAEYl7WTTuAAojfs_bbflWBLWqKwK_aaESDuF14_bnmywQnig_c_c&new=https%3A%2F%2Fwww.vmall.com%2Fproduct%2F3279.html";
        //拉取价格的路径和第一种路径一样,是从页面中一个长的json字符串中截取
        //路径前缀
        String getPriceUrlPreFix = "https://www.vmall.com/product/";
        //价格路径后缀
        String getPriceUrlSufFix = ".html";
        try {
            StringBuilder stringBuilder = new StringBuilder(activity_address);
            if (activity_address.startsWith("https://www.vmall.com/product/")) {
                activity_address = stringBuilder.substring(0, stringBuilder.indexOf("html") + 4).toString();
            } else if (activity_address.startsWith("https://www.linkhaitao.com/index")) {
                String productIdBs = "product%2F";
                String pruductId = stringBuilder.delete(stringBuilder.indexOf(".html"), stringBuilder.length()).substring(stringBuilder.indexOf(productIdBs) + productIdBs.length());
                activity_address = getPriceUrlPreFix + pruductId + getPriceUrlSufFix;
            }
            HttpGet httpGet = new HttpGet(activity_address);
            httpGet.setHeader("User-Agent", userAgent);
            CloseableHttpResponse execute = httpClient.execute(httpGet);
            HttpEntity entity = execute.getEntity();
            String content = EntityUtils.toString(entity);
            String proNameBs = "id=\"bread-pro-name\">";
            String bs = "ec.context = \"\";";
            String bs2 = "ec.product.gift.init";
            String priceBs = "price : \"";
            String originPriceBs = "originPrice : \"";
            stringBuilder.setLength(0);
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
                try {
                    if (!discount_after_price.equals(Double.valueOf(price)) || !original_price.equals(Double.valueOf(originPrice))) {
                        resultP += "           抓取优惠价:" + price + "       抓取原价" + originPrice;
                    } else {
                        resultP = "";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    resultP += "           抓取优惠价:" + price + "       抓取原价" + originPrice;
                }
            } catch (Exception e) {
                e.printStackTrace();
                resultP += "商品已下架";
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultP += "推广链接不正确或者无法访问";
        }

        return resultP;
    }

    /**
     * 当当网拉取价格
     *
     * @param item
     * @return
     */
    public static String dd(Map<String, Object> item) {
        String activity_address = (String) item.get("activity_address");
        Long discount_id = (Long) item.get("discount_id");
        Double original_price = (Double) item.get("original_price");
        Double discount_after_price = (Double) item.get("discount_after_price");
        String resultP =
                "         2哥链接:" + erGeDiscountAddress + discount_id +
                        "         库里面原价:" + original_price +
                        "            库里面优惠价:" + discount_after_price;
        String dd1 = "http://product.dangdang.com/1195142169.html?_ddclickunion=P-338121|ad_type=10|sys_id=1#dd_refer=";
        String dd2 = "http://union.dangdang.com/transfer.php?from=P-338121&ad_type=10&sys_id=1&backurl=http%3A%2F%2Fproduct.dangdang.com%2F1370187577.html";
        StringBuilder stringBuilder = new StringBuilder();
        try {
            if (activity_address.startsWith("http://product.dangdang.com/")) {
                activity_address = activity_address.substring(0, activity_address.indexOf("html") + 4);
            } else if (activity_address.startsWith("http://union.dangdang.com/")) {
                String productIdBs = ".dangdang.com%2F";
                stringBuilder.append(activity_address);
                String productId = stringBuilder.delete(0, stringBuilder.indexOf(productIdBs) + productIdBs.length()).substring(0, stringBuilder.indexOf("."));
                activity_address = "http://product.dangdang.com/" + productId + ".html";
            }
            HttpGet httpGet = new HttpGet(activity_address);
            httpGet.setHeader("User-Agent", userAgent);
            CloseableHttpResponse execute = httpClient.execute(httpGet);
            HttpEntity entity = execute.getEntity();
            String content = EntityUtils.toString(entity);
            //定义查询价格的路径
            //priceUrl = "http://product.dangdang.com/index.php?r=callback/product-info&productId=1100051270&isCatalog=1&shopId=18552&productType=0";
            stringBuilder.setLength(0);
            //{"productId":"1433237479","mainProductId":"1433237479","productName":"\u70ed\u98ce2018\u5e74\u6625\u65b0\u6b3e\u65f6\u5c1a\u6f6e\u6d41\u5973\u58eb\u95ea\u5e03\u7c97\u8ddf\u978b\u6df1\u53e3\u4f11\u95f2\u77ed\u9774H08W8106","productSubName":"\u95ea\u8000\u683c\u5229\u7279 \u6c14\u8d28\u7efd\u653e","url":"\/1433237479.html","shopId":"21531","isCatalog":"1","isOverSea":"0","isWireless":"0","isPresent":"0","isVirtualProduct":"0","isSoldOut":"0","displayStatus":"0","categoryPath":"58.65.02.13.00.00","describeMap":"","categoryId":"4010044","template":"cloth","productType":"0","templateId":"44263","mediumId":"12","supId":"","pathName":"\u4f11\u95f2\u978b","isGiftPackaging":"0","isSupportReturnPolicy":"1","shopType":"0","energyLevel":"-1","icon":[],"ddShopId":"0","isShowOriginalPrice":"1","isRecyclable":"0","isShowRobot":"1","isPod":"0","podId":"0","isJit":"0"}
            String json = stringBuilder.append(content).delete(0, stringBuilder.indexOf("prodSpuInfo")).substring(stringBuilder.indexOf("{"), stringBuilder.indexOf("};") + 1);
            Gson gson = new Gson();
            Map<String, Object> prodSpuInfoMap = gson.fromJson(json, type);
            String productId = (String) prodSpuInfoMap.get("productId");
            String isCatalog = (String) prodSpuInfoMap.get("isCatalog");
            String shopId = (String) prodSpuInfoMap.get("shopId");
            String productType = (String) prodSpuInfoMap.get("productType");
            stringBuilder.setLength(0);
            //拼接获取价格的路径
            stringBuilder.append("http://product.dangdang.com/index.php?r=callback/product-info&productId=").
                    append(productId).append("&shopId=").append(shopId).append("&isCatalog=").append(isCatalog).append("&productType=").append(productType);
            //获取价格路径
            String s = stringBuilder.toString();
            httpGet.setURI(new URI(s));
            execute = httpClient.execute(httpGet);
            entity = execute.getEntity();
            //获取返回价格信息的json
            content = EntityUtils.toString(entity);
            stringBuilder.setLength(0);
            stringBuilder.append(content);
            String originalPrice = "";
            String salePrice = "";
            if (content.contains("directPrice")) {
                String directPriceBs = "\"directPrice\":\"";
                //获取抢购价
                salePrice = stringBuilder.delete(0, stringBuilder.indexOf(directPriceBs) + directPriceBs.length()).substring(0, stringBuilder.indexOf("\""));
            } else {
                //原价标识
                String originalPriceBs = "\"originalPrice\":\"";
                //售价标识
                String salePriceBs = "\"salePrice\":\"";
                //获取原价
                originalPrice = stringBuilder.delete(0, stringBuilder.indexOf(originalPriceBs) + originalPriceBs.length()).substring(0, stringBuilder.indexOf("\""));
                //获取售价
                salePrice = stringBuilder.delete(0, stringBuilder.indexOf(salePriceBs) + salePriceBs.length()).substring(0, stringBuilder.indexOf("\""));
            }
            try {
                if (!discount_after_price.equals(Double.valueOf(salePrice)) || !original_price.equals(Double.valueOf(originalPrice))) {
                    resultP += "           抓取优惠价:" + salePrice + "       抓取原价" + originalPrice;
                } else {
                    resultP = "";
                }
            } catch (Exception e) {
                e.printStackTrace();
                resultP += "           抓取优惠价:" + salePrice + "       抓取原价" + originalPrice;
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultP += "该商品不存在或者路径不正确或者拉取价格有误";
        }
        return resultP;
    }

    /**
     * 网易考拉价格拉取
     *
     * @return
     */
    public static String wykl(Map<String, Object> item) {
        //数据库中网易考拉路径1
        String kl1 = "http://cps.kaola.com/cps/login?unionId=999684142642&uid=&trackingCode=&targetUrl=http%3A%2F%2Fwww.kaola.com%2Fproduct%2F1854995.html";
        //数据库中网易考拉路径2
        String kl2 = "https://goods.kaola.com/product/1854995.html";
        //价格拉取路径前缀后面是pruductId
        String url = "https://goods.kaola.com/product/getPcGoodsDetailDynamic.json?goodsId=";

        String activity_address = (String) item.get("activity_address");
        Long discount_id = (Long) item.get("discount_id");
        Double original_price = (Double) item.get("original_price");
        Double discount_after_price = (Double) item.get("discount_after_price");
        String resultP =
                "         2哥链接:" + erGeDiscountAddress + discount_id +
                        "         库里面原价:" + original_price +
                        "            库里面优惠价:" + discount_after_price;
        String priductId = "";
        StringBuilder stringBuilder = new StringBuilder();
        if (activity_address.startsWith("https://goods.kaola.com/product")) {
            priductId = stringBuilder.append(activity_address).
                    delete(stringBuilder.indexOf(".html"), stringBuilder.length()).
                    delete(0, stringBuilder.lastIndexOf("/") + 1).toString();
        } else if (activity_address.startsWith("http://cps.kaola.com/cps/login")) {
            String productIdBs = "product%2F";
            if (activity_address.contains(productIdBs)) {
                priductId = stringBuilder.append(activity_address).substring(stringBuilder.indexOf(productIdBs) + productIdBs.length(), stringBuilder.indexOf(".html"));
            } else {
                return resultP += "路径错误";
            }
        }
        try {
            HttpGet httpGet = new HttpGet(url + priductId);
            httpGet.setHeader("User-Agent", userAgent);
            CloseableHttpResponse execute = httpClient.execute(httpGet);
            HttpEntity entity = execute.getEntity();
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
                return resultP;
            } catch (Exception e) {
                e.printStackTrace();
                resultP += "           抓取优惠价:" + currentPrice + "       抓取原价" + marketPrice;
                return resultP;
            }

        } catch (Exception e) {
            return resultP += "路径有误";
        }
    }


    /**
     * 唯品会拉取价格
     *
     * @param item
     * @return https://detail.vip.com/detail-3559750-654889948.html
     */
    public static String vip(Map<String, Object> item) {
        //唯品会路径1
        String wph = "https://click.union.vip.com/deeplink/showGoodsDetail?pid=651061118&goodType=0&tra_from=tra%3A248ytswt%3Acha00000%3Amed00000%3Aad000004%3A%3Axwy33mj4%3A%3A&f=dx&cps_code=xX7Rh3X";
        //唯品会路径1
        String wph2 = "https://detail.vip.com/detail-3534252-651061118.html";
        //唯品会价格拉取路径前缀
        String urlFreFix = "https://detail.vip.com/detail-184552252-";

        //路径后缀
        String urlSufFix = ".html";

        String activity_address = (String) item.get("activity_address");
        Long discount_id = (Long) item.get("discount_id");
        Double original_price = (Double) item.get("original_price");
        Double discount_after_price = (Double) item.get("discount_after_price");
        String resultP =
                "         2哥链接:" + erGeDiscountAddress + discount_id +
                        "         库里面原价:" + original_price +
                        "            库里面优惠价:" + discount_after_price;
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(activity_address);
            //后面的字符串
            String intemid = "";
            if (activity_address.startsWith("https://click.union.vip.com/")) {
                //截取pid
                intemid = stringBuilder.delete(0, stringBuilder.indexOf("pid=") + 4).substring(0, stringBuilder.indexOf("&"));
                activity_address = urlFreFix + intemid + urlSufFix;
            } else if (activity_address.startsWith("https://detail.vip.com/detail-")) {
                //删除路径后的参数
                if (activity_address.contains("?")) {
                    activity_address = activity_address.substring(0, activity_address.indexOf("?"));
                    String substring = stringBuilder.substring(stringBuilder.indexOf("-") + 1);
                    stringBuilder.setLength(0);
                    intemid = stringBuilder.append(substring).substring(stringBuilder.indexOf("-") + 1, stringBuilder.indexOf("."));
                }
            }

            //截取字符串标识 预约的没有暂时不用这个
            String priceId = jgbs + intemid;
            HttpGet httpGet = new HttpGet(activity_address);
            httpGet.setHeader("User-Agent", userAgent);
            CloseableHttpResponse execute = httpClient.execute(httpGet);
            HttpEntity entity = execute.getEntity();
            String comment = EntityUtils.toString(entity);
            //清空容器
            stringBuilder.setLength(0);
            //添加从地址中爬去到的内容
            stringBuilder.append(comment);
            if (comment == null || comment.length() == 0) {
                resultP += "        路径执行无内容返回错误";
            } else if (stringBuilder.indexOf(jgbs2) == -1) {
                resultP += "          电脑端无法查看";
            } else {
                String jPrice = "J-price\">";
                stringBuilder.delete(0, stringBuilder.indexOf(jgbs2)).
                        delete(0, stringBuilder.indexOf(jPrice) + jPrice.length());
                //计算优惠后的价格
                String discountP = stringBuilder.substring(0, stringBuilder.indexOf("<"));
                //计算优惠前的价格
                String mPrice = "J-mPrice\">";
                String originalP = stringBuilder.delete(0, stringBuilder.indexOf(mPrice) + mPrice.length()).
                        substring(0, stringBuilder.indexOf("<"));
                //排除价格是90-90这样的情况,如果是就志向是90
                if (discountP.contains("~")) {
                    String[] split = discountP.split("~");
                    if (split[0].equals(split[1])) {
                        discountP = split[0];
                    }
                }
                //排除价格是90~90这样的情况,如果是就志向是90
                if (originalP.contains("~")) {
                    String[] split = originalP.split("~");
                    if (split[0].equals(split[1])) {
                        originalP = split[0];
                    }
                }
                //排除价格是90-90这样的情况,如果是就志向是90
                if (discountP.contains("-")) {
                    String[] split = discountP.split("-");
                    if (split[0].equals(split[1])) {
                        discountP = split[0];
                    }
                }
                //排除价格是90~90这样的情况,如果是就志向是90
                if (originalP.contains("-")) {
                    String[] split = originalP.split("-");
                    if (split[0].equals(split[1])) {
                        originalP = split[0];
                    }
                }
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
        } catch (Exception e) {
            resultP += "原链接错误或无发访问";
        }
        return resultP;
    }
}
