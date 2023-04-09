package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        //username bot - Example :"@ChatBot"
        return "here username";
    }

    @Override
    public String getBotToken() {
        //token bot - Example "9999:abcdefghijklmnqzzz3a33"
        return "here token";
    }

    //we handle the received update and capture the text and id of the conversation
    @Override
    public void onUpdateReceived(Update update) {
        String message = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();
        System.out.println("Message Received: " + message);
        int length = message.length();
        System.out.println("The Message have " + length + " characters");
        sendMessage(generateSendMessage(chatId, length));
    }

    //we create a SendMessage with the text we want to send to the chat
    private SendMessage generateSendMessage(Long chatId, int characterCount) {
        return new SendMessage(chatId.toString(), "The Message have " + characterCount + " characters");
    }

    //send the message to Telegram API
    private void sendMessage(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
