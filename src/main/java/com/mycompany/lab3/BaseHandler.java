package com.mycompany.lab3;

/**
 *
 * @author nsoko
 */
public class BaseHandler implements Handler{
    private Handler next;
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
    
}
