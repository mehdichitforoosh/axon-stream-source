package com.rondoiq.event.handler;

import com.rondoiq.event.event.StudentCreatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class StudentEventHandler {

    @EventHandler
    public void on(StudentCreatedEvent event) {
        System.out.println("Id in source : " + event.getId());
        System.out.println("Name in source : " + event.getName());
        System.out.println("Age in source: " + event.getAge());
    }
}
