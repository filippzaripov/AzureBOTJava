package com.fujitsu.azurebot;

import com.fujitsu.azurebot.youtube.YouTubeSubscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class BotRunner {
    private static String channelId;
    private static volatile BotRunner botRunner;
    private Logger log = LoggerFactory.getLogger(Main.class);

    private BotRunner(){
        channelId = "@filippschannel";
    }

    public void run() {
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
                log.error("Exception in BotRunner",e);
            }
        } catch (TelegramApiException e) {
            log.error("Telegram API Exception in BotRunner",e);
        }

    }

    public static BotRunner getBotRunner(){
        BotRunner localBotRunner  = botRunner;
        if(localBotRunner == null){
            synchronized (BotRunner.class){
                localBotRunner = botRunner;
                if(localBotRunner == null){
                    botRunner = localBotRunner = new BotRunner();
                }
            }
        }
        return localBotRunner;
    }
}
