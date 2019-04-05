package com.rondoiq.event.event;

import com.fasterxml.jackson.annotation.JsonCreator;

public class StudentCreatedEvent {

    private final String id;
    private final String name;
    private final String age;

    public StudentCreatedEvent(String id, String name, String age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }
}
