package com.fujitsu.azurebot;

import com.fujitsu.azurebot.youtube.YouTubeSubscriber;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.Properties;

public class Bot extends TelegramLongPollingBot {

    private Properties properties = new Properties();


    public Bot() {
        super(new MyBotOptions());
        try {
            properties.load(Bot.class.getResourceAsStream("/bot.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getBotUsername() {
        return properties.getProperty("bot.username");
    }

    public String getBotToken() {
        return properties.getProperty("bot.token");
    }


    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            SendMessage sendMessage = new SendMessage()
                    .setChatId(message.getChatId().toString())
                    .setReplyToMessageId(message.getMessageId())
                    .setText("I won't tell you anything, dude");
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessageToChannel(String message, String channelId) {

        if (message != null && channelId != null) {
            SendMessage sendMessage = new SendMessage()
                    .setChatId(channelId)
                    .setText(message);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else if (message == null && channelId == null) {
            System.err.println("You try to send null message to null channel");
        } else if (message == null && channelId != null) {
            System.err.println("You try to send null message to channel");
        } else if (message != null && channelId == null) {
            System.err.println("You try to send message to null channel");
        } else {
            System.err.println("Unexpected error in Bot class");
        }
    }


}