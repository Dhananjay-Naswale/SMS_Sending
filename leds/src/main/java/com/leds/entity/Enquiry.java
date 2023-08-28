package com.leds.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name="Enquirys",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})}
)
public class Enquiry {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    @Column(name="title" , nullable = false)
    private String title;
    @Column(name="description",nullable = false)
    private String description;
    @Column(name="Content",nullable = false)
    private  String Content;
}
