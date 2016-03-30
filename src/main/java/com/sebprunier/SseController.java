package com.sebprunier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
public class SseController {

    @Autowired
    private MessagesService service;

    @RequestMapping(path = "messages", method = RequestMethod.GET)
    public SseEmitter stream() throws Exception {
        SseEmitter emitter = new SseEmitter();

        CompletableFuture<List<Message>> messagesChunk1 = sendMessages(emitter, 3, 2000);
        CompletableFuture<List<Message>> messagesChunk2 = sendMessages(emitter, 1, 1000);
        CompletableFuture<List<Message>> messagesChunk3 = sendMessages(emitter, 42, 3000);
        CompletableFuture<List<Message>> messagesChunk4 = sendMessages(emitter, 5, 1000);

        CompletableFuture.allOf(
                messagesChunk1,
                messagesChunk2,
                messagesChunk3,
                messagesChunk4
        ).thenAcceptAsync(nothing -> {
            try {
                emitter.send(SseEmitter.event().name("finished").data(new ArrayList<>(), MediaType.APPLICATION_JSON));
                emitter.complete();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return emitter;
    }

    private CompletableFuture<List<Message>> sendMessages(SseEmitter emitter, int n, long millis) {
        System.out.println(String.format("Sending %d messages...", n));
        CompletableFuture<List<Message>> futureMessages = service.messages(n, millis);
        futureMessages.thenAccept(messages -> {
            try {
                emitter.send(SseEmitter.event().name("messages").data(messages, MediaType.APPLICATION_JSON));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return futureMessages;
    }

}
