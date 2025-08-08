package com.ortopunkt.medicalcrm.bot.screen;

import com.ortopunkt.medicalcrm.service.ApplicationService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import com.ortopunkt.medicalcrm.bot.button.ButtonFactory;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import java.util.List;

@Component
public class SmmScreen {

    public void handle(Update update, AbsSender sender, ApplicationService applicationService) {
        Long chatId = update.getMessage().getChatId();
        String text = update.getMessage().getText();
        SendMessage message = new SendMessage();
        message.setChatId(chatId.toString());

        message.setText("""
                    👩‍🎨 Привет! Вы вошли как SMM-специалист.

                    Я помогу вам:

                    • отслеживать охваты, вовлечённость, подписки и комментарии  
                    • видеть топ-посты и слабые публикации по VK и Instagram  
                    • анализировать видео по уровню досмотра
                    • смотреть, какие посты дали заявки и записи  
                    • формировать отчёты и делиться ими с врачом

                    Выберите действие:
                """);
        message.setReplyMarkup(new InlineKeyboardMarkup(
                ButtonFactory.smmMenuButtons()
        ));

        if (text.equalsIgnoreCase("/smm") || text.equalsIgnoreCase("/смм")) {
            message.setText("Отчет");
        }

        try {
            sender.execute(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
