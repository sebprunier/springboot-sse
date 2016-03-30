package com.hubshare;

/**
 * Created by sebprunier on 30/03/16.
 */
public class Message {

    public String text;

    public static Message of(String text) {
        Message m = new Message();
        m.text = text;
        return m;
    }
}
