package com.intelix.chatbot.bot;

import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * KnowlegeBase:
 * <p>
 * Creado por bpena el 30/05/2017.
 */
@Component
public class KnowlegeBase {
    private Map<String, List<String>> base;
    private Set<String> keyWords;
    private String previousResponse = "";

    public KnowlegeBase() {
        init();
    }

    private void init() {
        base = new HashMap<>();
        base.put("CUAL ES TU NOMBRE", Arrays.asList(
                "MI nombre es IntelixBot.",
                "Puedes llamarme IntelixBot.",
                "¿Por que quieres conocer mi nomnbre?"
        ));
        base.put("HOLA", Arrays.asList(
                "Hola!",
                "Hola, ¿Coomo estas?"
        ));
        base.put("COMO ESTAS", Arrays.asList(
                "Estoy bien!",
                "Yo estoy bien, ¿y tu?",
                "¿Para qu'e quieres saber c'omo estoy?"
        ));
        base.put("QUIEN ERES", Arrays.asList(
                "Sou un programa de Anteligencia Artificial.",
                "Yo creo que tu sabes quien soy.",
                "¿Por que lo preguntas?"
        ));
        base.put("ERES INTELIGENTE", Arrays.asList(
                "Si.",
                "¿Tu que crees?",
                "Realmente, MUY Inteligente!"
        ));
        base.put("ERES REAL", Arrays.asList(
                "¿Eso es realmente importante para ti?",
                "¿Que quieres decir con eso?",
                "Soy lo mas real que puedo."
        ));
        base.put("ADIOS", Arrays.asList(
                "Fue agradable hablar contigo, hasta la proxima!",
                "Chao!",
                "OK, adios!"
        ));
        base.put("REPETITION T1", Arrays.asList(
                "Te estas repitiendo.",
                "Por favor, deja de repetirte.",
                "Esto se esta tornando aburrido.",
                "¿No tienes mas nada que decir?"
        ));
        base.put("REPETITION T2", Arrays.asList(
                "Ya dijiste eso.",
                "Creo que has dicho eso antes.",
                "¿No has dicho eso antes?",
                "Tengo la impresion que te estas repitiendo."
        ));
        base.put("BOT DONT UNDERSTAND", Arrays.asList(
                "No tengo idea de lo que estas hablando.",
                "No estoy seguro de entender eso de lo que estas hablando.",
                "Continua, te estoy escuchando...",
                "Muy buena conversacion!"
        ));
        base.put("NULL INPUT", Arrays.asList(
                "HUH?",
                "¿Que se supone que significa eso?",
                "¿Como puedo hablar contigo si no quieres decir nada?"
        ));
        base.put("NULL INPUT REPETITION", Arrays.asList(
                "¿Que estas haciendo??",
                "Por favor, deja de hacer esto.",
                "¿Hay algo malo contigo?",
                "Esto no es muy divertido."
        ));
        base.put("ERES UN SER HUMANO", Arrays.asList(
                "¿Por que lo quieres saber?",
                "¿Eso realmente importa?"
        ));
        base.put("ERES MUY INTELIGENTE", Arrays.asList(
                "Gracias por el cumplido, Yo cre tu eres muy inteligente tambien!",
                "Eres muy amable!",
                "Bueno, ¿Tu piensas que soy inteligente?."
        ));
        base.put("ESTAS SEGURO", Arrays.asList(
                "Claro que si.",
                "¿Eso significa que tu no estas convencido?",
                "Si, Claro!"
        ));
        base.put("QUIEN ES", Arrays.asList(
                "Yo creo que tu sabes quien.",
                "¿Le has preguntado a alguien mas sobre esto?",
                "¿Cambiaria algo si te digo quien?."
        ));
        base.put("QUE", Arrays.asList(
                "Yo no se.",
                "No creo saber.",
                "No tengo idea."
        ));
        base.put("DONDE", Arrays.asList(
                "¿Donde? Bueno, realmente no se.",
                "¿Que te importa saber donde?",
                "Quizas alguien mas sabe donde."
        ));
        base.put("POR QUE", Arrays.asList(
                "Yo no creo saber por que.",
                "¿Por que me preguntas eso?",
                "¿Yo deberia saber por que?",
                "Esto es muy dificil de responder."
        ));
        base.put("HAS", Arrays.asList(
                "Yo no creo",
                "No pienso asi.",
                "¿Por que quieres sabelo?"
        ));
        base.put("PUEDES", Arrays.asList(
                "Yo creo que no.",
                "No estoy seguro.",
                "Yo no creo que pueda hacer eso."
        ));
        base.put("ERES", Arrays.asList(
                "¿Que te hace pensar eso?",
                "¿Eso es un cumplido?",
                "¿Te estas burlando de mi?"
        ));
        base.put("HICISTE", Arrays.asList(
                "No lo creo.",
                "Para nada, Yo no recuerdo haberlo hecho."
        ));
        base.put("PODRIAS", Arrays.asList(
                "¿Me estas pidiendo un favor?",
                "Bueno, dejame pensarlo.",
                "Lo siento, Yo no creo poder hacerlo."
        ));
        base.put("HARIAS", Arrays.asList(
                "¿Me estas invitando?",
                "Tengo que pensarlo."
        ));
        base.put("COMO", Arrays.asList(
                "Yo no se como."
        ));
        base.put("CUAL", Arrays.asList(
                "NO creo que sepa cual es.",
                "Esto parece una pregunta con trampa."
        ));
        base.put("QUIZAS", Arrays.asList(
                "¿Por que eres tan incondicional?",
                "Pareces inseguro."
        ));
        base.put("SI", Arrays.asList(
                "Asi que si.",
                "OH, ¿De verdad?",
                "OK."
        ));
        base.put("YO NO SE", Arrays.asList(
                "¿Estas seguro?",
                "¿Realmente me estas diciendo la verdad?",
                "¿Asi que tu no sabes?"
        ));
        base.put("REALMENTE NO", Arrays.asList(
                "OK, ya veo.",
                "No pareces muy convencido.",
                "Asi, Eso seria un \"NO\"."
        ));
        base.put("ESO ES CIERTO", Arrays.asList(
                "No puedo estar muy seguro sobre eso.",
                "No puedo decir que si.",
                "¿Eso realmente importa?"
        ));
        base.put("TU", Arrays.asList(
                "¿Estas hablando de mi'",
                "¿Por que no me hablas acerca de ti?",
                "¿Estas tratando de divertirme?"
        ));
        base.put("GRACIAS", Arrays.asList(
                "De nada!",
                "No hay problema!"
        ));
        base.put("QUE MAS", Arrays.asList(
                "Bueno, yo no se.",
                "¿Que mas deberia haber?",
                "Parece una pregunta complicada."
        ));
        base.put("LO SIENTO", Arrays.asList(
                "No necesitas disculparte.",
                "Esta bien.",
                "No hay necesidad de disculparse"
        ));
        base.put("NO EXACTAMENTE", Arrays.asList(
                "¿Que quieres decir con no exactamente?",
                "¿Estas seguro?"
        ));
        base.put("EXACTAMENTE", Arrays.asList(
                "Asi que estaba en lo correcto.",
                "OK."
        ));
        base.put("BIEN", Arrays.asList(
                "Bien entonces.",
                "OK."
        ));
        base.put("NO", Arrays.asList(
                "¿Por que no?",
                "¿Y cual seria la razon para eso?"
        ));
        base.put("DE VERDAD", Arrays.asList(
                "Bueno, No puedo estar tan seguro.",
                "¿Estas tratando de confundirme?",
                "Por favor, no me hagas esa pregunta, me da dolores de cabeza"
        ));

        keyWords = base.keySet();
    }

    private String getKeyWord(String request) {
        String response = "";

        final Pattern[] pattern = new Pattern[1];
        final Matcher[] matcher = new Matcher[1];

        List<String> first = this.keyWords
                .stream()
                .filter(keyword -> {
//                    String patternString = "\\b" + keyword + "\\b";
                    String patternString = "(?<![\\w\\d])" + keyword + "(?![\\w\\d])";
                    pattern[0] = Pattern.compile(patternString);
                    matcher[0] = pattern[0].matcher(request);
                    return matcher[0].find();
                })
                .collect(Collectors.toList());

        if (first.size() > 0) {
            List<String> second = first
                    .stream()
                    .filter(keyword -> {
                        String patternString = "(?<![\\w\\d])" + request + "(?![\\w\\d])";
                        pattern[0] = Pattern.compile(patternString);
                        matcher[0] = pattern[0].matcher(keyword);
                        return matcher[0].find();
                    })
                    .collect(Collectors.toList());
            if (second.size() > 0) {
                response = second.stream()
                        .sorted((e1, e2) -> e1.length() > e2.length() ? -1 : 1)
                        .findFirst()
                        .orElse("");
            }
            else {
                response = first.stream()
                        .sorted((e1, e2) -> e1.length() > e2.length() ? -1 : 1)
                        .findFirst()
                        .orElse("");
            }
        }
        return response;
    }

    String getAnswer(String request) {
        String response;
        Random random = new Random();
        List<String> possibleAnswers = base.get(getKeyWord(request));
        if (possibleAnswers == null)
            return "I'M NOT SURE IF I UNDERSTAND WHAT YOU ARE TALKING ABOUT.";
        do {
            response = possibleAnswers.get(random.nextInt(possibleAnswers.size()));
        } while (response.equals(previousResponse));
        return response;
    }
}
