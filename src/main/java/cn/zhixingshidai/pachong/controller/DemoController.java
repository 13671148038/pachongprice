package cn.zhixingshidai.pachong.controller;

import cn.zhixingshidai.pachong.service.PriceMonitoring;
import cn.zhixingshidai.pachong.until.HttpClientUtil;
import cn.zhixingshidai.pachong.until.PricePaChongUntil;
import cn.zhixingshidai.pachong.until.WebDriver;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class DemoController {

    @Autowired
    private PriceMonitoring priceMonitoring;

    @RequestMapping("getp")
    public String getO() {
        ChromeDriver chromeDriver = WebDriver.getChromeDriver();
        chromeDriver.get("https://product.suning.com/0000000000/691178468.html?safp=d488778a_46602_0_834cc989bf");
        WebElement su = chromeDriver.findElementById("mainPrice");
        String text = su.getText();
        return text;
    }

    @RequestMapping("getp2")
    public String getO2() {
        System.setProperty("webdriver.firefox.driver", "C:/Users/13671/Downloads/geckodriver.exe");
       /* System.setProperty("webdriver.firefox.marionette", "false");
        System.setProperty("webdriver.gecko.marionette", "false");
        System.setProperty("webdriver.gecko.driver", "geckodriver");*/
        System.setProperty("webdriver.firefox.bin", "D:/install/ff/firefox.exe");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        FirefoxDriver firefoxDriver = new FirefoxDriver(firefoxOptions);
        firefoxDriver.get("https://product.suning.com/0000000000/691178468.html?safp=d488778a_46602_0_834cc989bf");
        WebElement su = firefoxDriver.findElementById("mainPrice");
        String text = su.getText();
        firefoxDriver.quit();
        return text;
    }

    @RequestMapping("getp3")
    public String getO3() {
        String s = HttpClientUtil.sendGet("https://product.suning.com/0000000000/691178468.html?safp=d488778a_46602_0_834cc989bf", "");
        return s;
    }

    @RequestMapping("getp4")
    public String getO4() throws IOException {
        String url = "http://you.163.com/item/detail?channel_type=1&from=out_cps_linktech_0&id=1690003&_stat_referer=index&_stat_area=mod_newItem_item_3";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpUriRequest httpUriRequest = new HttpGet(url);
        CloseableHttpResponse execute = httpClient.execute(httpUriRequest);
        HttpEntity entity = execute.getEntity();
        String s1 = EntityUtils.toString(entity, "utf-8");
        System.out.println(s1);
        StringBuilder stringBuilder = new StringBuilder(s1);
        String content = "\"counterPrice\":";
        int i = stringBuilder.indexOf(content);
        int i1 = stringBuilder.indexOf(",", i);
        String substring = stringBuilder.substring(i + content.length(), i1);
        return s1;
    }

    @RequestMapping("getp5")
    public String getO5() throws IOException {
        String url = "https://ksv-video-publish.cdn.bcebos.com/c86d9cfae5558d5786e263ae329a7f6ca183f28b.mp4?auth_key=1541039489-0-0-3e53b10e4bd274b8e122a1a7aded6d67";
        String userAgent = "Mozilla/5.0 (Windows NT 10.0;Win64;x64;rv:63.0) Gecko/20100101 Firefox/63.0";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpUriRequest httpUriRequest = new HttpGet(url);
        httpUriRequest.setHeader("User-Agent", userAgent);
        CloseableHttpResponse execute = httpClient.execute(httpUriRequest);
        HttpEntity entity = execute.getEntity();
        byte[] bytes = EntityUtils.toByteArray(entity);

        OutputStream outputStream = new FileOutputStream("C:/Users/13671/Desktop/sp.mp4");
        WritableByteChannel writableByteChannel = Channels.newChannel(outputStream);
        ByteBuffer wrap = ByteBuffer.wrap(bytes);
        writableByteChannel.write(wrap);
        return "success";
    }

    @RequestMapping("getp6")
    public String getO6() throws IOException {
        //String url = "https://item.taobao.com/item.htm?id=537330014675&ali_trackid=2:mm_167860039_81800429_15620700198:1539757821_144_254978455&pvid=10_222.128.44.223_931_1539053262245";
        //String url = "https://detailskip.taobao.com/service/getData/1/p1/item/detail/sib.htm?itemId=537330014675&sellerId=2776190336&modules=dynStock,qrcode,viewer,price,duty,xmpPromotion,delivery,upp,activity,fqg,zjys,amountRestriction,couponActivity,soldQuantity,originalPrice,tradeContract&callback=onSibRequestSuccess";
        //String url = "https://detailskip.taobao.com/service/getData/1/p1/item/detail/sib.htm?itemId=537330014675&modules=price,xmpPromotion,originalPrice";
        String url = "https://www.vmall.com/product/10086471621596.html";
        //String url = "http://product.dangdang.com/1100051270.html";
        String userAgent = "Mozilla/5.0 (Windows NT 10.0;Win64;x64;rv:63.0) Gecko/20100101 Firefox/63.0";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpUriRequest = new HttpGet(url);
        httpUriRequest.setHeader("User-Agent", userAgent);
//        httpUriRequest.setHeader("Accept","*/*");
//        httpUriRequest.setHeader("Referer","https://item.taobao.com/item.htm?id=537330014675&ali_trackid=2:mm_167860039_81800429_15620700198:1539757821_144_254978455&pvid=10_222.128.44.223_931_1539053262245");
//        httpUriRequest.removeHeaders("Upgrade-Insecure-Requests");

        CloseableHttpResponse execute = httpClient.execute(httpUriRequest);
        HttpEntity entity = execute.getEntity();
        String s = EntityUtils.toString(entity, "utf-8");
        System.out.print(s);
        return "";
    }

    //监控价格
    @RequestMapping("priceMonitoringCompare")
    public String priceMonitoringCompare() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                priceMonitoring.test1();
            }
        });
//        priceMonitoring.priceMonitoringCompare();
        return "Hello 2ge";
    }

    @RequestMapping("JmsTest")
    public String sendMessageTest() {
        priceMonitoring.sendMessageTest();
        return "sdcsd";
    }

    @RequestMapping("getData")
    public String sdcsd() {
        String activity_address = "https://www.linkhaitao.com/index.php?mod=lhdeal&track=5478SqdudoFco0EtmHE4FfhC225I0cX_b3i_awrbUVVR4tivobfBkYf_by5Wvf1ys4_c&new=https%3A%2F%2Fproduct.suning.com%2F0000000000%2F103703461.html%3Fsafp%3Dd488778a.46602.productWrap.2%26safc%3Dprd.3.ssdln_pic01-1_jz";
        //https://pas.suning.com/nspcsale_0_000000000103703461_000000000103703461_0000000000_10_010_0100101_500353_1000000_9017_10106_Z001___R9006826_4.0_0___00027E125___.html?callback=pcData&_=1562317708358
        //https://pas.suning.com/nspcsale_0_000000000103703461_000000000103703461_0000000000_10_010_0100101_500353_1000000_9017_10106_Z001___R9006826_4.0_0___00027E125___.html?callback=pcData&_=1562317708358
        //https://pas.suning.com/nspcsale_0_000000000103703461_000000000103703461_0000000000_10_010_0100101.html?callback=pcData
        //https://pas.suning.com/nspcsale_1_000000010941825161_000000010941825161_0070135207_10_010_0100101_315053_1000000_9017_10106_Z001___R9003091_1.0_1___0001909D0___.html?callback=pcData&_=1562318082446
        //        http://you.163.com/item/detail?channel_type=1_11111110&from=out_cps_linktech_0&id=3459032                                                       %3A%2F%2Fyou.163.com%2Fitem%2Fdetail%3Fid%3D
//        Double original_price = (Double) item.get("original_price");
//        Double discount_after_price = (Double) item.get("discount_after_price");
        Map<String, Object> map = new HashMap<>();
        map.put("activity_address", activity_address);
        map.put("discount_id", 5555L);
        map.put("original_price", 22.2D);
        map.put("discount_after_price", 8585.00d);
        Map<String, Object> wy = PricePaChongUntil.snyg(map);
        Set<Map.Entry<String, Object>> entries = wy.entrySet();
        for (Map.Entry<String, Object> item : entries) {
            System.out.println(item.getKey() + ":" + item.getValue());
        }
        return "success";
    }

}
