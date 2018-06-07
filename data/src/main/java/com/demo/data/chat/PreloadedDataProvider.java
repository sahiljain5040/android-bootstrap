package com.demo.data.chat;

import com.demo.domain.chat.models.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PreloadedDataProvider {

    public static List<Message> getDefaultMessages(){
        List<Message> messages = new ArrayList<>();
        Message message1 = new Message(UUID.randomUUID().toString(), "OUTGOING", "Hello",
                "2018-04-28T00:00:00.000Z");
        Message message2 = new Message(UUID.randomUUID().toString(), "INCOMING", "Hi",
                "2018-04-29T00:00:00.000Z");
        Message message3 = new Message(UUID.randomUUID().toString(), "OUTGOING", "What is Circles.Life?",
                "2018-05-01T00:00:00.000Z");
        Message message4 = new Message(UUID.randomUUID().toString(), "INCOMING", "The Best No-Contract Mobile plan is here starting at 6 GB/mo at $28. Get more data with Unlimited Bonus Data. Get the best phones at $0 upfront.",
                "2018-05-02T00:00:00.000Z");
        Message message5 = new Message(UUID.randomUUID().toString(), "OUTGOING", "I see, thanks!",
                "2018-05-03T00:00:00.000Z");
        Message message6 = new Message(UUID.randomUUID().toString(), "INCOMING", "No problem!",
                "2018-05-04T00:00:00.000Z");

        messages.add(message1);
        messages.add(message2);
        messages.add(message3);
        messages.add(message4);
        messages.add(message5);
        messages.add(message6);

        return messages;
    }
}
