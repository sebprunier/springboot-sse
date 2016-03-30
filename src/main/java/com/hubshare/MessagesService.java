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
        messages3.add(Message.of(">>>> START sqdsqdsq sqdsqdsq sqdsqdsq sqdsqdsq sqdsqdsq"));

        for (int i = 0; i < 12000; i++) {
            messages3.add(Message.of("dsdsqdsddddddddd dsdsqdsddddddddd dsdsqdsddddddddddsdsqdsddddddddd dsdsqdsddddddddd"));
            messages3.add(Message.of("tata titi tutu tata titi tutu tata titi tutu tata titi tutu tata titi tutu tata titi tutu tata titi tutu tata titi tutu "));
        }
        messages3.add(Message.of("this is the END <<<<"));

        Thread.sleep(2000);
        return CompletableFuture.completedFuture(messages3);
    }

    @Async
    public CompletableFuture<List<Message>> messages4() throws Exception {
        ArrayList<Message> messages4 = new ArrayList<>();
        messages4.add(Message.of("avé dé accents û ù :-) :pig: \n et un emoji \uD83D\uDE03 ! "));
        Thread.sleep(1000);
        return CompletableFuture.completedFuture(messages4);
    }

}
