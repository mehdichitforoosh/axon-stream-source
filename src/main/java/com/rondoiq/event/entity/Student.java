package com.rondoiq.event.entity;

import com.rondoiq.event.command.CreateStudentCommand;
import com.rondoiq.event.event.StudentCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;


@Aggregate
public class Student {

    @AggregateIdentifier
    private String id;
    private String name;
    private String age;

    @CommandHandler
    public Student(CreateStudentCommand command) {
        apply(new StudentCreatedEvent(command.getId(), command.getName(), command.getAge()));
    }

    @EventSourcingHandler
    public void on(StudentCreatedEvent event) {
        this.id = event.getId();
        this.name = event.getName();
        this.age = event.getAge();
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
