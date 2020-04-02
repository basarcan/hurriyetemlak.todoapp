package com.hurriyetemlak.todoapp.todoapp.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;

import java.util.Date;

@Document
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationStrategy.UNIQUE, delimiter = "__")
    private String id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Date createdDate;
    private Date lastModifiedDate;
    private String token;
}
