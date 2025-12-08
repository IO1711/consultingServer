package com.bilolbek.ConsultingWebsite.components;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class TelegramBot extends TelegramLongPollingBot{

    @Value("${telegram.bot.username}")
    private String botUsername;

    @Value("${telegram.bot.token}")
    private String botToken;

    @Override
    public String getBotUsername(){
        return botUsername;
    }

    @Override
    public String getBotToken(){
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update){
        if(update.hasMessage() && update.getMessage().hasText()){
            long chatId = update.getMessage().getChatId();
            String text = update.getMessage().getText();

            if(text.equals("/start")){
                System.out.println("User started the bot, chatId = " + chatId);

                sendMessage(chatId, "Welcome! You will now receive notifications.");
            }
        }
    }

    public void sendMessage(Long chatId, String message) {
        try {
            execute(new SendMessage(chatId.toString(), message));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
