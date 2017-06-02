package com.intelix.chatbot.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * BotController:
 * <p>
 * Creado por bpena el 30/05/2017.
 */
@RestController
@RequestMapping("/bot")
public class BotController {
    @Autowired
    BotService botService;

    @GetMapping("/talk/{message}")
    public ResponseEntity<String> talk(@PathVariable String message) {
        return new ResponseEntity<>(botService.respond(message), HttpStatus.OK);
    }
}
