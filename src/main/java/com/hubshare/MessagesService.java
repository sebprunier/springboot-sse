package com.hubshare;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Created by sebprunier on 30/03/16.
 */
@Service
public class MessagesService {

    @Async
    public CompletableFuture<List<Message>> messages1() throws Exception {
        ArrayList<Message> messages1 = new ArrayList<>();
        messages1.add(Message.of("toto"));
        messages1.add(Message.of("titi"));
        Thread.sleep(3000);
        return CompletableFuture.completedFuture(messages1);
    }

    @Async
    public CompletableFuture<List<Message>> messages2() throws Exception {
        ArrayList<Message> messages2 = new ArrayList<>();
        messages2.add(Message.of("tutu"));
        Thread.sleep(1000);
        return CompletableFuture.completedFuture(messages2);
    }

    @Async
    public CompletableFuture<List<Message>> messages3() throws Exception {
        ArrayList<Message> messages3 = new ArrayList<>();
        messages3.add(Message.of("sqdsqdsq"));
        messages3.add(Message.of("dsdsqdsddddddddd"));
        messages3.add(Message.of("tata"));
        Thread.sleep(2000);
        return CompletableFuture.completedFuture(messages3);
    }

}
