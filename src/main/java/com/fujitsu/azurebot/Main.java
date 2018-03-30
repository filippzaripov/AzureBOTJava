package com.fujitsu.azurebot;

import com.fujitsu.azurebot.youtube.YouTubeSubscriber;

public class Main {

    private static BotRunner botRunner = BotRunner.getBotRunner();

    public static void main(String[] args) throws Exception {
        botRunner.run();
    }
}
