package org.tinder.app.entities;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Message  {
    private int id;
    private int senderId;
    private int receiverId;
    private String text;
    private String status;

    public Message(int id, int senderId, int receiverId, String text) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.text = text;
    }

    public Message(int senderId, int receiverId, String text) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.text = text;
    }

    public Message(int id, int senderId, int receiverId, String text, String status) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.text = text;
        this.status = status;
    }

    public int getSenderId() {
        return senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public String getText() {
        return text;
    }
}
