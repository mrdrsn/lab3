package com.mycompany.lab3;

import java.util.List;

/**
 *
 * @author nsoko
 */
public class BaseHandler implements Handler{
    private Handler next;
    private List<Monster> monsterList;
    
    @Override
    public void setNext(Handler h) {
        this.next = h;
    }
    @Override
    public void handle(String request) {
        if(next != null){
            next.handle(request);
        } else{
            System.out.println("невозможно обработать файл");
        }
    }

    @Override
    public List<Monster> getMonsterList() {
        return this.monsterList;
    }

    
}
