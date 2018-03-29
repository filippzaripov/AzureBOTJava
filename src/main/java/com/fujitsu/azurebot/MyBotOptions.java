package com.fujitsu.azurebot;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.telegram.telegrambots.Constants;
import org.telegram.telegrambots.bots.DefaultBotOptions;

public class MyBotOptions extends DefaultBotOptions {

    public MyBotOptions(){
        setRequestConfig(RequestConfig.copy(RequestConfig.custom().build())
        .setProxy(new HttpHost("itp-proxy.russia.local", 3128))
        .setSocketTimeout(Constants.SOCKET_TIMEOUT)
        .setConnectTimeout(Constants.SOCKET_TIMEOUT)
        .setConnectionRequestTimeout(Constants.SOCKET_TIMEOUT)
        .build());
    }
}
