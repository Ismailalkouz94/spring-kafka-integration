package com.example.springkafkaproducer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person  implements Serializable {
    private Long id;
    private String name;
    private String email;
}
