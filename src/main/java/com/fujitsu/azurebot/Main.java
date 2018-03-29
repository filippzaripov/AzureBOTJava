package com.fujitsu.azurebot;

import com.fujitsu.azurebot.youtube.YouTubeSubscriber;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class Main {
    public static void main(String[] args) throws Exception {
        String channelId = "@filippschannel";
        String lastSentVideoUrl = "";
        YouTubeSubscriber subscriber = new YouTubeSubscriber();
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        Bot bot = new Bot();
        try {
            botsApi.registerBot(bot);
            try {
                while (true) {
                    String currentVideoUrl = subscriber.getLastVideoUrl();
                    System.out.println(currentVideoUrl);
                    if (currentVideoUrl != null) {
                        if (!lastSentVideoUrl.equals(currentVideoUrl)) {
                            lastSentVideoUrl = currentVideoUrl;
                            bot.sendMessageToChannel(currentVideoUrl, channelId);
                        } else {
                            continue;
                        }
                    }
                    Thread.sleep(1000 * 3600);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }


    }
}
