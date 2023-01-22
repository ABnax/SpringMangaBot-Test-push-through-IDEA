package ru.trick.springmangabot.makerKeyBord;


import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import ru.trick.springmangabot.ENUM.CallbackDataPartsEnum;
import ru.trick.springmangabot.ENUM.DictionaryResourcePathEnum;


import java.util.ArrayList;
import java.util.List;

@Component
public class InlineKeyboardMaker {

//    public InlineKeyboardMarkup getInlineMessageButtonsWithTemplate(String prefix, boolean isUserDictionaryNeed) {
//        InlineKeyboardMarkup inlineKeyboardMarkup = getInlineMessageButtons(prefix, isUserDictionaryNeed);
//        inlineKeyboardMarkup.getKeyboard().add(getButton(
//                "Шаблон",
//                prefix + CallbackDataPartsEnum.TEMPLATE.name()
//        ));
//        return inlineKeyboardMarkup;
//    }

    public InlineKeyboardMarkup getInlineMessageButtons(String prefix, boolean isUserDictionaryNeed) {

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        for (DictionaryResourcePathEnum dictionary : DictionaryResourcePathEnum.values()) {
            rowList.add(getButton(dictionary.getButtonName(), prefix + dictionary.name()
            ));}

        if (!rowList.isEmpty()) {
            rowList.add(getButton("Следующая старница ->", prefix + CallbackDataPartsEnum.NEXT.name()
            ));}

        if (isUserDictionaryNeed) {
            rowList.add(getButton("<- Вернуться назад", prefix + CallbackDataPartsEnum.BACK.name()
            ));}

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

    private List<InlineKeyboardButton> getButton(String buttonName, String buttonCallBackData) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(buttonName);
        button.setCallbackData(buttonCallBackData);

        List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
        keyboardButtonsRow.add(button);
        return keyboardButtonsRow;
    }



}



