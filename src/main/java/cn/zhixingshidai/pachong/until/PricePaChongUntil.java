package cn.zhixingshidai.pachong.until;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.lang.reflect.Type;
import java.net.URI;
import java.util.List;
import java.util.Map;

public class PricePaChongUntil {

    // 浏览器头信息
    private final static String userAgent = "Mozilla/5.0 (Windows NT 10.0;Win64;x64;rv:63.0) Gecko/20100101 Firefox/63.0";

    private final static CloseableHttpClient httpClient = HttpClients.createDefault();
    // 当前优惠后的价格
    private final static String currentDPrice = "currentDPrice";
    // 当前原价的价格
    private final static String currentOPrice = "currentOPrice";

    // 唯品会价格标识 这个预约的没有
    private final static String jgbs = "J_surpriseSprice_wrap";
    // 唯品会价格标识 这个预约和没有预约都可以
    private final static String jgbs2 = "id=\"J-pi-price-box\"";

    private final static Type type = new TypeToken<Map<String, Object>>() {
    }.getType();

    /**
     * 拉取西集网价格信息
     *
     * @param item
     * @return
     */
    public static Map<String, Object> xj(Map<String, Object> item) {
        try {
            String activity_address = (String) item.get("activity_address");
            Double original_price = (Double) item.get("original_price");
            Double discount_after_price = (Double) item.get("discount_after_price");
            String getPriceUrl = "http://www.xiji.com/product-ajax_product_price-48773.html";
            // 定义商品的id
            String productId = "";
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.setLength(0);
            stringBuilder.append(activity_address);
            if (activity_address.startsWith("http://www.xiji.com/product-")) {
                stringBuilder.substring(stringBuilder.indexOf("-") + 1, stringBuilder.indexOf(".html"));
            } else {
                stringBuilder.delete(stringBuilder.lastIndexOf(".html"), stringBuilder.length()).delete(0,
                        stringBuilder.lastIndexOf("-") + 1);
            }
            productId = stringBuilder.toString();
            // 拼接获取价格的路径
            stringBuilder.setLength(0);
            getPriceUrl = stringBuilder.append("http://www.xiji.com/product-ajax_product_price-").append(productId)
                    .append(".html").toString();
            HttpGet httpGet = new HttpGet(getPriceUrl);
            httpGet.setHeader("User-Agent", userAgent);
            CloseableHttpResponse execute = httpClient.execute(httpGet);
            HttpEntity entity = execute.getEntity();
            String content = EntityUtils.toString(entity);
            if (StringUtils.isNotBlank(content)) {
                Gson gson = new Gson();
                Map<String, Object> jdMap = gson.fromJson(content, type);
                // 售价
                String price = (String) jdMap.get("price");
                // 原价
                String mktprice = (String) jdMap.get("mktprice");
                if (StringUtils.isBlank(price) && StringUtils.isBlank(mktprice)) {
                    item.put(currentDPrice, "商品已下架或拼接的价格路径不对,价格都是空");
                    item.put(currentOPrice, "商品已下架或拼接的价格路径不对,价格都是空");
                } else {
                    try {
                        if (!discount_after_price.equals(Double.valueOf(price))
                                || !original_price.equals(Double.valueOf(mktprice))) {
                            item.put(currentDPrice, price);
                            item.put(currentOPrice, mktprice);
                        } else {
                            item = null;
                            // resultP = "";
                        }
                    } catch (Exception e) {
                        item.put(currentDPrice, price);
                        item.put(currentOPrice, mktprice);
                    }
                }
            } else {
                item.put(currentDPrice, "商品已下架或拼接的获取价格路径不正确");
                item.put(currentOPrice, "商品已下架或拼接的获取价格路径不正确");
            }

        } catch (Exception e) {
            item.put(currentDPrice, "路径错误");
            item.put(currentOPrice, "路径错误");
            // resultP += "路径错误";
        }
        return item;
    }

    /**
     * 拉取网易网价格信息
     *
     * @param item
     * @return
     */
    public static Map<String, Object> wy(Map<String, Object> item) {
        try {
            String activity_address = (String) item.get("activity_address");
            Double original_price = (Double) item.get("original_price");
            Double discount_after_price = (Double) item.get("discount_after_price");
            // 数据局原链接
            // 原链接截取标识
            String bs = "%3A%2F%2Fyou.163.com%2Fitem%2Fdetail%3Fid%3D";
            // 拼接后的链接
            String r2 = "http://you.163.com/item/detail?id=";
            StringBuilder stringBuilder = new StringBuilder(activity_address);
            if (!activity_address.startsWith("http://you.163.com/item/detail")) {
                stringBuilder.delete(0, stringBuilder.indexOf(bs) + bs.length());
                int i = stringBuilder.indexOf("%");
                if (i == -1) {
                    i = stringBuilder.length();
                }
                String id = stringBuilder.substring(0, i);
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
            if (index != -1) {
                String retailPricebs = "\"retailPrice\":";
                // 获取零售价
                String retailPrice = stringBuilder.delete(0, index)
                        .delete(stringBuilder.indexOf("};"), stringBuilder.length())
                        .delete(0, stringBuilder.indexOf(retailPricebs) + retailPricebs.length())
                        .substring(0, stringBuilder.indexOf(","));
                // 获取柜台价
                String counterPricebs = "\"counterPrice\":";
                stringBuilder.delete(0, stringBuilder.indexOf(counterPricebs) + counterPricebs.length());
                String counterPrice = stringBuilder.substring(0, stringBuilder.indexOf(","));
                try {
                    if (!discount_after_price.equals(Double.valueOf(retailPrice))
                            || !original_price.equals(Double.valueOf(counterPrice))) {
                        item.put(currentDPrice, retailPrice);
                        item.put(currentOPrice, counterPrice);
                    } else {
                        item = null;
                    }
                } catch (Exception e) {
                    item.put(currentDPrice, retailPrice);
                    item.put(currentOPrice, counterPrice);
                }
            }
        } catch (Exception e) {
            item.put(currentDPrice, "路径错误");
            item.put(currentOPrice, "路径错误");
        }
        return item;
    }

    /**
     * 拉取苏宁易购商品的价格
     *
     * @param item
     * @return
     */
    public static Map<String, Object> snyg(Map<String, Object> item) {
        try {
            String activity_address = (String) item.get("activity_address");
            Double original_price = (Double) item.get("original_price");
            Double discount_after_price = (Double) item.get("discount_after_price");
            // 获取价格的链接
            String priceUrlPrefix = "https://pas.suning.com/nspcsale_0_";
            String priceUrlSuffix = "_10_010_0100101.html?callback=pcData";
            String parameter1 = "";
            String parameter2 = "";
            StringBuilder stringBuilder = new StringBuilder(activity_address);
            if (activity_address.startsWith("https://product.suning.com/")) {
                parameter1 = stringBuilder.delete(0, stringBuilder.indexOf(".com/") + 5).substring(0,
                        stringBuilder.indexOf("/"));
                parameter2 = stringBuilder.substring(stringBuilder.indexOf("/") + 1, stringBuilder.indexOf("."));
            } else if (activity_address.startsWith("https://www.linkhaitao.com/")) {
                String bs = "product.suning.com%2F";
                String[] split = stringBuilder.delete(0, stringBuilder.indexOf(bs) + bs.length())
                        .delete(stringBuilder.indexOf(".html"), stringBuilder.length()).toString().split("%2F");
                parameter1 = split[0];
                parameter2 = split[1];
            }
            stringBuilder.setLength(0);
            int length = stringBuilder.append(parameter2).length();
            for (int i = 0; i < 18 - length; i++) {
                stringBuilder.insert(0, "0");
            }
            parameter2 = stringBuilder.toString();
            // 拼接路径
            stringBuilder.setLength(0);
            String priceUrl = stringBuilder.append(priceUrlPrefix).append(parameter2).append("_").append(parameter2)
                    .append("_").append(parameter1).append(priceUrlSuffix).toString();
            HttpGet httpGet = new HttpGet(priceUrl);
            System.out.println(priceUrl);
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
                            } else {
                                // 原价 如果refPrice不为空 就是用refPrice,如果为空就使用netPrice
                                String refPrice = (String) productInto.get("refPrice");
                                if ("".equals(refPrice)) {
                                    refPrice = (String) productInto.get("netPrice");
                                }
                                // 促销价
                                String promotionPrice = (String) productInto.get("promotionPrice");
                                try {
                                    promotionPrice = promotionPrice.split("-")[0];
                                    if (!discount_after_price.equals(Double.valueOf(promotionPrice))
                                            || !original_price.equals(Double.valueOf(refPrice))) {
                                        item.put(currentDPrice, promotionPrice);
                                        item.put(currentOPrice, refPrice);
                                    } else {
                                        item = null;
                                    }
                                } catch (Exception e) {
                                    item.put(currentDPrice, promotionPrice);
                                    item.put(currentOPrice, refPrice);
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                item.put(currentDPrice, "商品已下架或者该链接不存在");
                item.put(currentOPrice, "商品已下架或者该链接不存在");
            }
        } catch (Exception e) {
            item.put(currentDPrice, "推广链接错错误");
            item.put(currentOPrice, "推广链接错错误");
        }
        return item;
    }

    /**
     * 拉取华为商城商品的价格
     *
     * @param item
     * @return
     */
    public static Map<String, Object> hw(Map<String, Object> item) {
        try {
            String activity_address = (String) item.get("activity_address");
            Double original_price = (Double) item.get("original_price");
            Double discount_after_price = (Double) item.get("discount_after_price");
            // 华为路径第一种情况
            String url1 = "https://www.vmall.com/product/2749.html#";
            // 华为路径第二种情况
            String url2 = "https://www.linkhaitao.com/index.php?mod=lhdeal&track=cb751mjy925Akfy_ayAEYl7WTTuAAojfs_bbflWBLWqKwK_aaESDuF14_bnmywQnig_c_c&new=https%3A%2F%2Fwww.vmall.com%2Fproduct%2F3279.html";
            // 拉取价格的路径和第一种路径一样,是从页面中一个长的json字符串中截取
            // 路径前缀
            String getPriceUrlPreFix = "https://www.vmall.com/product/";
            // 价格路径后缀
            String getPriceUrlSufFix = ".html";
            StringBuilder stringBuilder = new StringBuilder(activity_address);
            if (activity_address.startsWith("https://www.vmall.com/product/")) {
                activity_address = stringBuilder.substring(0, stringBuilder.indexOf("html") + 4).toString();
            } else if (activity_address.startsWith("https://www.linkhaitao.com/index")) {
                String productIdBs = "product%2F";
                String pruductId = stringBuilder.delete(stringBuilder.indexOf(".html"), stringBuilder.length())
                        .substring(stringBuilder.indexOf(productIdBs) + productIdBs.length());
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
                // 查询商品的名字
                String proName = stringBuilder.append(content)
                        .delete(0, stringBuilder.indexOf(proNameBs) + proNameBs.length())
                        .substring(0, stringBuilder.indexOf("<"));
                stringBuilder.delete(0, stringBuilder.indexOf(bs)).delete(0, stringBuilder.indexOf(bs2));

                String content2 = stringBuilder.substring(0, stringBuilder.indexOf(proName));
                stringBuilder.setLength(0);
                // 获取优惠价
                String price = stringBuilder.append(content2)
                        .delete(0, stringBuilder.indexOf(priceBs) + priceBs.length())
                        .substring(0, stringBuilder.indexOf("\""));
                String originPrice = stringBuilder
                        .delete(0, stringBuilder.indexOf(originPriceBs) + originPriceBs.length())
                        .substring(0, stringBuilder.indexOf("\""));
                try {
                    if (!discount_after_price.equals(Double.valueOf(price))
                            || !original_price.equals(Double.valueOf(originPrice))) {
                        item.put(currentDPrice, price);
                        item.put(currentOPrice, originPrice);
                    } else {
                        item = null;
                        // resultP = "";
                    }
                } catch (Exception e) {
                    item.put(currentDPrice, price);
                    item.put(currentOPrice, originPrice);
                }
            } catch (Exception e) {
                item.put(currentDPrice, "商品已下架");
                item.put(currentOPrice, "商品已下架");
            }
        } catch (Exception e) {
            item.put(currentDPrice, "推广链接不正确或者无法访问");
            item.put(currentOPrice, "推广链接不正确或者无法访问");
        }

        return item;
    }

    /**
     * 当当网拉取价格
     *
     * @param item
     * @return
     */
    public static Map<String, Object> dd(Map<String, Object> item) {
        try {
            String activity_address = (String) item.get("activity_address");
            Double original_price = (Double) item.get("original_price");
            Double discount_after_price = (Double) item.get("discount_after_price");
            StringBuilder stringBuilder = new StringBuilder();
            if (activity_address.startsWith("http://product.dangdang.com/")) {
                activity_address = activity_address.substring(0, activity_address.indexOf("html") + 4);
            } else if (activity_address.startsWith("http://union.dangdang.com/")) {
                String productIdBs = ".dangdang.com%2F";
                stringBuilder.append(activity_address);
                String productId = stringBuilder.delete(0, stringBuilder.indexOf(productIdBs) + productIdBs.length())
                        .substring(0, stringBuilder.indexOf("."));
                activity_address = "http://product.dangdang.com/" + productId + ".html";
            }
            HttpGet httpGet = new HttpGet(activity_address);
            httpGet.setHeader("User-Agent", userAgent);
            CloseableHttpResponse execute = httpClient.execute(httpGet);
            HttpEntity entity = execute.getEntity();
            String content = EntityUtils.toString(entity);
            // 定义查询价格的路径
            stringBuilder.setLength(0);
            String json = stringBuilder.append(content).delete(0, stringBuilder.indexOf("prodSpuInfo"))
                    .substring(stringBuilder.indexOf("{"), stringBuilder.indexOf("};") + 1);
            Gson gson = new Gson();
            Map<String, Object> prodSpuInfoMap = gson.fromJson(json, type);
            String productId = (String) prodSpuInfoMap.get("productId");
            String isCatalog = (String) prodSpuInfoMap.get("isCatalog");
            String shopId = (String) prodSpuInfoMap.get("shopId");
            String productType = (String) prodSpuInfoMap.get("productType");
            stringBuilder.setLength(0);
            // 拼接获取价格的路径
            stringBuilder.append("http://product.dangdang.com/index.php?r=callback/product-info&productId=")
                    .append(productId).append("&shopId=").append(shopId).append("&isCatalog=").append(isCatalog)
                    .append("&productType=").append(productType);
            // 获取价格路径
            String s = stringBuilder.toString();
            httpGet.setURI(new URI(s));
            execute = httpClient.execute(httpGet);
            entity = execute.getEntity();
            // 获取返回价格信息的json
            content = EntityUtils.toString(entity);
            stringBuilder.setLength(0);
            stringBuilder.append(content);
            String originalPrice = "";
            String salePrice = "";
            if (content.contains("directPrice")) {
                String directPriceBs = "\"directPrice\":\"";
                // 获取抢购价
                salePrice = stringBuilder.delete(0, stringBuilder.indexOf(directPriceBs) + directPriceBs.length())
                        .substring(0, stringBuilder.indexOf("\""));
            } else {
                // 原价标识
                String originalPriceBs = "\"originalPrice\":\"";
                // 售价标识
                String salePriceBs = "\"salePrice\":\"";
                // 获取原价
                originalPrice = stringBuilder
                        .delete(0, stringBuilder.indexOf(originalPriceBs) + originalPriceBs.length())
                        .substring(0, stringBuilder.indexOf("\""));
                // 获取售价
                salePrice = stringBuilder.delete(0, stringBuilder.indexOf(salePriceBs) + salePriceBs.length())
                        .substring(0, stringBuilder.indexOf("\""));
            }
            try {
                if (!discount_after_price.equals(Double.valueOf(salePrice))
                        || !original_price.equals(Double.valueOf(originalPrice))) {
                    item.put(currentDPrice, salePrice);
                    item.put(currentOPrice, originalPrice);
                } else {
                    item = null;
                }
            } catch (Exception e) {
                item.put(currentDPrice, salePrice);
                item.put(currentOPrice, originalPrice);
            }
        } catch (Exception e) {
            item.put(currentDPrice, "该商品不存在或者路径不正确或者拉取价格有误");
            item.put(currentOPrice, "该商品不存在或者路径不正确或者拉取价格有误");
        }
        return item;
    }

    /**
     * 网易考拉价格拉取
     *
     * @return
     */
    public static Map<String, Object> wykl(Map<String, Object> item) {
        try {
            // 价格拉取路径前缀后面是pruductId
            String url = "https://goods.kaola.com/product/getPcGoodsDetailDynamic.json?goodsId=";
            String activity_address = (String) item.get("activity_address");
            Double original_price = (Double) item.get("original_price");
            Double discount_after_price = (Double) item.get("discount_after_price");
            String priductId = "";
            StringBuilder stringBuilder = new StringBuilder();
            if (activity_address.startsWith("https://goods.kaola.com/product")) {
                priductId = stringBuilder.append(activity_address)
                        .delete(stringBuilder.indexOf(".html"), stringBuilder.length())
                        .delete(0, stringBuilder.lastIndexOf("/") + 1).toString();
            } else if (activity_address.startsWith("http://cps.kaola.com/cps/login")) {
                String productIdBs = "product%2F";
                if (activity_address.contains(productIdBs)) {
                    priductId = stringBuilder.append(activity_address).substring(
                            stringBuilder.indexOf(productIdBs) + productIdBs.length(), stringBuilder.indexOf(".html"));
                }
            }
            HttpGet httpGet = new HttpGet(url + priductId);
            httpGet.setHeader("User-Agent", userAgent);
            CloseableHttpResponse execute = httpClient.execute(httpGet);
            HttpEntity entity = execute.getEntity();
            String json = EntityUtils.toString(entity);
            stringBuilder.setLength(0);
            stringBuilder.append(json);
            String marketPrice = "marketPrice\":";
            String currentPrice = "currentPrice\":";
            // 市场价
            marketPrice = stringBuilder.delete(0, stringBuilder.indexOf(marketPrice) + marketPrice.length())
                    .substring(0, stringBuilder.indexOf(","));
            // 当前价格
            currentPrice = stringBuilder.delete(0, stringBuilder.indexOf(currentPrice) + currentPrice.length())
                    .substring(0, stringBuilder.indexOf(","));
            try {
                if (!discount_after_price.equals(Double.valueOf(currentPrice))
                        || !original_price.equals(Double.valueOf(marketPrice))) {
                    item.put(currentDPrice, currentPrice);
                    item.put(currentOPrice, marketPrice);
                } else {
                    item = null;
                }
            } catch (Exception e) {
                item.put(currentDPrice, currentPrice);
                item.put(currentOPrice, marketPrice);
            }

        } catch (Exception e) {
            item.put(currentDPrice, "路径有误");
            item.put(currentOPrice, "路径有误");
        }
        return item;
    }

    /**
     * 唯品会拉取价格
     *
     * @param item
     * @return https://detail.vip.com/detail-3559750-654889948.html
     */
    public static Map<String, Object> vip(Map<String, Object> item) {
        try {
            // 唯品会价格拉取路径前缀
            String urlFreFix = "https://detail.vip.com/detail-184552252-";
            // 路径后缀
            String urlSufFix = ".html";
            String activity_address = (String) item.get("activity_address");
            Double original_price = (Double) item.get("original_price");
            Double discount_after_price = (Double) item.get("discount_after_price");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(activity_address);
            // 后面的字符串
            String intemid = "";
            if (activity_address.startsWith("https://click.union.vip.com/")) {
                // 截取pid
                intemid = stringBuilder.delete(0, stringBuilder.indexOf("pid=") + 4).substring(0,
                        stringBuilder.indexOf("&"));
                activity_address = urlFreFix + intemid + urlSufFix;
            } else if (activity_address.startsWith("https://detail.vip.com/detail-")) {
                // 删除路径后的参数
                if (activity_address.contains("?")) {
                    activity_address = activity_address.substring(0, activity_address.indexOf("?"));
                    stringBuilder.setLength(0);
                }
            }

            // 截取字符串标识 预约的没有暂时不用这个
            HttpGet httpGet = new HttpGet(activity_address);
            httpGet.setHeader("User-Agent", userAgent);
            CloseableHttpResponse execute = httpClient.execute(httpGet);
            HttpEntity entity = execute.getEntity();
            String comment = EntityUtils.toString(entity);
            // 清空容器
            stringBuilder.setLength(0);
            // 添加从地址中爬去到的内容
            stringBuilder.append(comment);
            if (comment == null || comment.length() == 0) {
            } else if (stringBuilder.indexOf(jgbs2) == -1) {
            } else {
                String jPrice = "J-price\">";
                stringBuilder.delete(0, stringBuilder.indexOf(jgbs2)).delete(0,
                        stringBuilder.indexOf(jPrice) + jPrice.length());
                // 计算优惠后的价格
                String discountP = stringBuilder.substring(0, stringBuilder.indexOf("<"));
                // 计算优惠前的价格
                String mPrice = "J-mPrice\">";
                String originalP = stringBuilder.delete(0, stringBuilder.indexOf(mPrice) + mPrice.length()).substring(0,
                        stringBuilder.indexOf("<"));
                // 排除价格是90-90这样的情况,如果是就是90
                if (discountP.contains("~")) {
                    String[] split = discountP.split("~");
                    if (split[0].equals(split[1])) {
                        discountP = split[0];
                    }
                }
                // 排除价格是90~90这样的情况,如果是就是90
                if (originalP.contains("~")) {
                    String[] split = originalP.split("~");
                    if (split[0].equals(split[1])) {
                        originalP = split[0];
                    }
                }
                // 排除价格是90-90这样的情况,如果是就是90
                if (discountP.contains("-")) {
                    String[] split = discountP.split("-");
                    if (split[0].equals(split[1])) {
                        discountP = split[0];
                    }
                }
                // 排除价格是90~90这样的情况,如果是就是90
                if (originalP.contains("-")) {
                    String[] split = originalP.split("-");
                    if (split[0].equals(split[1])) {
                        originalP = split[0];
                    }
                }
                try {
                    if (!Double.valueOf(discountP).equals(discount_after_price)
                            || !Double.valueOf(originalP).equals(original_price)) {
                        item.put(currentDPrice, discountP);
                        item.put(currentOPrice, originalP);
                    } else {
                        item = null;
                    }
                } catch (Exception e) {
                    item.put(currentDPrice, discountP);
                    item.put(currentOPrice, originalP);
                }
            }
        } catch (Exception e) {
            item.put(currentDPrice, "原链接错误或无发访问");
            item.put(currentOPrice, "原链接错误或无发访问");
        }
        return item;
    }
}
