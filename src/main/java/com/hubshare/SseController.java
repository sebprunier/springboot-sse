package com.hubshare;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * Created by sebprunier on 30/03/16.
 */
@RestController
public class SseController {

    @Autowired
    private MessagesService service;

    @RequestMapping(path = "messages", method = RequestMethod.GET)
    public SseEmitter stream() throws Exception {
        SseEmitter emitter = new SseEmitter();

        CompletableFuture<List<Message>> messages1 = service.messages1();
        messages1.thenAcceptAsync(messages -> {
            try {
                emitter.send(messages, MediaType.APPLICATION_JSON);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        System.out.println("Messages 1");

        CompletableFuture<List<Message>> messages2 = service.messages2();
        messages2.thenAcceptAsync(messages -> {
            try {
                emitter.send(messages, MediaType.APPLICATION_JSON);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        System.out.println("Messages 2");

        CompletableFuture<List<Message>> messages3 = service.messages3();
        messages3.thenAcceptAsync(messages -> {
            try {
                emitter.send(messages, MediaType.APPLICATION_JSON);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        System.out.println("Messages 3");

        CompletableFuture.allOf(messages1, messages2, messages3).thenAcceptAsync(aVoid -> emitter.complete());

        return emitter;
    }



}
