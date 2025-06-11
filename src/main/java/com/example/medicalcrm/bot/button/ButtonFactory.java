package com.example.medicalcrm.bot.button;

import com.example.medicalcrm.entity.Application;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public class ButtonFactory {

    // 🔹 1. Кнопки для меню по ролям
    public static List<InlineKeyboardButton> doctorMenuButtons() {
        return List.of(
                patientsButton(),
                InlineKeyboardButton.builder()
                        .text("📄 Отчёт")
                        .callbackData("DOCTOR_REPORT")
                        .build()
        );
    }

    public static List<InlineKeyboardButton> smmMenuButtons() {
        return List.of(
                patientsButton(),
                InlineKeyboardButton.builder()
                        .text("📄 Отчёт по соцсетям")
                        .callbackData("SMM_REPORT")
                        .build()
        );
    }

    public static List<InlineKeyboardButton> targetMenuButtons() {
        return List.of(
                patientsButton(),
                InlineKeyboardButton.builder()
                        .text("📄 Отчёт по платной рекламе")
                        .callbackData("TARGET_REPORT")
                        .build()
        );
    }

    private static InlineKeyboardButton patientsButton() {
        return InlineKeyboardButton.builder()
                .text("👥 Заявки")
                .callbackData("DOCTOR_PATIENTS")
                .build();
    }

    // 🔹 2. Кнопки для карточки заявки

    public static InlineKeyboardButton openChatButton(String username) {
        return InlineKeyboardButton.builder()
                .text("ИИ-анализ")
                .url("https://t.me/" + username)
                .build();
    }

    public static InlineKeyboardButton markButton(Long appId, boolean marked) {
        return InlineKeyboardButton.builder()
                .text(marked ? "✅ Записан" : "Записать")
                .callbackData("MARK_" + appId)
                .build();
    }

    public static InlineKeyboardButton quotaButton(Long appId, boolean marked) {
        return InlineKeyboardButton.builder()
                .text(marked ? "✅ По квоте" : "По квоте")
                .callbackData("FREE_" + appId)
                .build();
    }

    public static InlineKeyboardButton paidButton(Long appId, boolean marked) {
        return InlineKeyboardButton.builder()
                .text(marked ? "✅ Платно" : "Платно")
                .callbackData("PAID_" + appId)
                .build();
    }

    // 🔹 3. Обновление всей клавиатуры по статусу заявки

    public static InlineKeyboardMarkup updatedKeyboard(Application app) {
        return new InlineKeyboardMarkup(List.of(
                List.of(
                        openChatButton(app.getPatient().getUsername()),
                        markButton(app.getId(), "Записан".equals(app.getStatus()))
                ),
                List.of(
                        quotaButton(app.getId(), "По квоте".equals(app.getPaymentStatus())),
                        paidButton(app.getId(), "Платно".equals(app.getPaymentStatus()))
                )
        ));
    }
}
