package com.sebprunier;

import com.github.javafaker.Faker;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class MessagesService {

    @Async
    public CompletableFuture<List<Message>> messages(int n, long millis) {
        Faker faker = new Faker();
        List<Message> messages = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            messages.add(new Message(UUID.randomUUID().toString(), faker.lorem().sentence(10)));
        }
        sleep(millis);
        return CompletableFuture.completedFuture(messages);
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
