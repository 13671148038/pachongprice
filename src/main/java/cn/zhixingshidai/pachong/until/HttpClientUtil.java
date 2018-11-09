package cn.zhixingshidai.pachong.until;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;


/**  
* @Title: HttpClientUtil.java
* @Package com.zxsd.util
* @Description: TODO(发送post，get请求工具类)
* @author 徐腾 
* @date 2016年12月22日 上午10:56:58
* @version V1.0  
*/ 
public class HttpClientUtil {
	
	private static final Logger LOGGER = LogManager.getLogger(HttpClientUtil.class);
	
	  /**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
    	
        String result = "";
        try {
        	URLConnection connection = createConnection(url, param);
           result = doGet(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public static String sendGetWithRetry(String url, String param) {
    	return sendGetWithRetry(url, param,3,2000,3000);
    }
    public static String sendGetWithRetry(String url, String param,int retryNum,int conTimeout,int readTimeout) {
    	 String result = "";
         try {
         	URLConnection connection = createConnection(url, param);
         	connection.setConnectTimeout(conTimeout);
         	connection.setReadTimeout(readTimeout);
            result = doGet(connection);
         } catch(SocketTimeoutException timeoutException) {
        	 if(retryNum > 0) {
        		 LOGGER.warn("timeout..",timeoutException);
        		 return sendGetWithRetry(url, param, --retryNum, conTimeout, readTimeout);
        	 }
        	 LOGGER.error("timeout!!",timeoutException);
         } catch (Exception e) {
             e.printStackTrace();
         }
         return result;
    }
    
    private static String doGet(URLConnection connection)throws IOException {
    	String result = "";
        BufferedReader in = null;
        try {
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
//            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
//            for (String key : map.keySet()) {
//            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        }finally {// 使用finally块来关闭输入流
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    
    private static URLConnection createConnection(String url, String queryString) throws IOException {
    	String urlNameString = url;
    	if(queryString != null) {
    		urlNameString += "?" + queryString;
    	}
        URL realUrl = new URL(urlNameString);
        // 打开和URL之间的连接
        URLConnection connection = realUrl.openConnection();
        // 设置通用的请求属性
        connection.setRequestProperty("accept", "*/*");
        connection.setRequestProperty("connection", "Keep-Alive");
        connection.setRequestProperty("user-agent",
                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        return connection;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            // 打开和URL之间的连接
            URLConnection conn = createConnection(url, null);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }
 

}
