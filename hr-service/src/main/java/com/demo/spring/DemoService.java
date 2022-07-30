package com.demo.spring;

import org.springframework.stereotype.Service;

@Service
public class DemoService {
    public String longProcess(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "sucess";
    }

    public String produceFailure(){
        System.out.printf("calling remote app....");
        throw new RuntimeException("Server Error");
    }

}