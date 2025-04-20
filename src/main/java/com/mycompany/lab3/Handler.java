package com.mycompany.lab3;

import java.util.List;

public interface Handler {
    void setNext(Handler h);
    void handle(String request);
    List<Monster> getMonsterList();
}
