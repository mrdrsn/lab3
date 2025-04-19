package com.mycompany.lab3;

public interface Handler {
    void setNext(Handler h);
    void handle(String request);
}
