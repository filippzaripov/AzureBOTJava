package com.fujitsu.azurebot;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.Constants;
import org.telegram.telegrambots.bots.DefaultBotOptions;

import java.io.IOException;
import java.util.Properties;

public class MyBotOptions extends DefaultBotOptions {
    private Logger log = LoggerFactory.getLogger(MyBotOptions.class);

    public MyBotOptions() {
        Properties properties = new Properties();
        try {
            properties.load(MyBotOptions.class.getResourceAsStream("/youtube.properties"));
        } catch (IOException e) {
            log.error("Error while reading properties for MyBotOptions class", e);
        }

        setRequestConfig(RequestConfig.copy(RequestConfig.custom().build())
                .setProxy(new HttpHost(properties.getProperty("https.proxyHost"), Integer.parseInt(properties.getProperty("https.proxyPort"))))
                .setSocketTimeout(Constants.SOCKET_TIMEOUT)
                .setConnectTimeout(Constants.SOCKET_TIMEOUT)
                .setConnectionRequestTimeout(Constants.SOCKET_TIMEOUT)
                .build());
    }
}
