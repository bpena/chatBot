package com.intelix.chatbot.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * BotService:
 * <p>
 * Creado por bpena el 31/05/2017.
 */
@Service
public class BotService {
    @Autowired
    private KnowlegeBase knowlegeBase;
    private String previousInput = "";

    String respond(String input) {
        input = cleanInput(input);
        if (previousInput.equals(input)) {
            return "YOU ARE REPEATING YOURSELF.";
        }
        previousInput = input;
        return knowlegeBase.getAnswer(input.toUpperCase());
    }

    private String cleanInput(String input) {
        return input.trim().replaceAll("[?!.;,]+", "").replaceAll(" +", " ").toUpperCase();
    }
}
