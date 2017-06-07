package cn.wjs;

import io.netty.channel.ChannelOption;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.IOException;

import static io.netty.channel.ChannelOption.valueOf;

/**
 * Created by wjs on 2017/1/9.
 */
public class Test {
    public static final ChannelOption<Integer> SO_BACKLOG = valueOf("SO_BACKLOG");

    public static void main(String[] args) {

        HttpClient httpClient = new HttpClient(); // 构造HttpClient的实例
        GetMethod getMethod = new GetMethod("http://192.168.70.4:8080/api/v1/topology/summary"); // 创建GET方法的实
        try {
            int statusCode = httpClient.executeMethod(getMethod); // 执行getMethod
            String response = getMethod.getResponseBodyAsString(); // 读取服务器返回的页面代码，这里用的是字符读法
            System.out.println(response);
        } catch (HttpException e) {
            System.out.println("Please check your provided http address!"); // 发生致命的异常，可能是协议不对或者返回的内容有问题
            e.printStackTrace();
        } catch (IOException e) { // 发生网络异常
            e.printStackTrace();
        } finally { // 释放连接
            getMethod.releaseConnection();
        }

    }

}
