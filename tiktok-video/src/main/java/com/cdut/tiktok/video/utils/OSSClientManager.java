package com.cdut.tiktok.video.utils;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.common.comm.Protocol;
import com.aliyuncs.exceptions.ClientException;
import org.springframework.stereotype.Component;


@Component
public class OSSClientManager {


    public OSSClientManager() throws ClientException {
        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。


        // 从环境变量中获取访问凭证。运行本代码示例之前，请先配置环境变量。
        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();

        // 创建OSSClient实例。
        String endpoint = "https://oss-cn-chengdu.aliyuncs.com";
        OSS ossClient = new OSSClientBuilder().build(endpoint, credentialsProvider);

        // 创建ClientBuilderConfiguration。
// ClientBuilderConfiguration是OSSClient的配置类，可配置代理、连接超时、最大连接数等参数。
        ClientBuilderConfiguration conf = new ClientBuilderConfiguration();

// 设置OSSClient允许打开的最大HTTP连接数，默认为1024个。
        conf.setMaxConnections(200);
// 设置Socket层传输数据的超时时间，默认为50000毫秒。
        conf.setSocketTimeout(10000);
// 设置建立连接的超时时间，默认为50000毫秒。
        conf.setConnectionTimeout(10000);
// 设置从连接池中获取连接的超时时间（单位：毫秒），默认不超时。
        conf.setConnectionRequestTimeout(1000);
// 设置连接空闲超时时间。超时则关闭连接，默认为60000毫秒。
        conf.setIdleConnectionTime(10000);
// 设置失败请求重试次数，默认为3次。
        conf.setMaxErrorRetry(5);
// 设置是否支持将自定义域名作为Endpoint，默认支持。
        conf.setSupportCname(true);
// 设置是否开启二级域名的访问方式，默认不开启。
        conf.setSLDEnabled(true);
// 设置连接OSS所使用的协议（HTTP或HTTPS），默认为HTTP。
        conf.setProtocol(Protocol.HTTP);
// 设置用户代理，指HTTP的User-Agent头，默认为aliyun-sdk-java。
        conf.setUserAgent("aliyun-sdk-java");
// 设置代理服务器IP，请将"<yourProxyHost>"替换为代理服务器的IP地址（如"196.128.xxx.xxx"）。
        conf.setProxyHost("<yourProxyHost>");
// 设置代理服务器验证的用户名，请将"<yourProxyUserName>"替换为代理服务器的用户名（如"root"）。
        conf.setProxyUsername("<yourProxyUserName>");
// 设置代理服务器验证的密码，请将"<yourProxyPassword>"替换为该用户的验证密码。
        conf.setProxyPassword("<yourProxyPassword>");
// 设置是否开启HTTP重定向，默认开启。
        conf.setRedirectEnable(true);
// 设置是否开启SSL证书校验，默认开启。
        conf.setVerifySSLEnable(true);
        // 关闭OSSClient。
        ossClient.shutdown();
    }


}

