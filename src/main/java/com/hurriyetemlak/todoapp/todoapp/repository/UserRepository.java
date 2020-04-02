package com.hurriyetemlak.todoapp.todoapp.repository;

import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryRow;
import com.couchbase.client.java.query.ParameterizedN1qlQuery;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hurriyetemlak.todoapp.todoapp.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.data.couchbase.repository.support.N1qlCouchbaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
@Slf4j
public class UserRepository {
    private final CouchbaseTemplate couchbaseTemplate;
    private final ObjectMapper objectMapper;

    public UserRepository(CouchbaseTemplate couchbaseTemplate, ObjectMapper objectMapper) {
        this.couchbaseTemplate = couchbaseTemplate;
        this.objectMapper = objectMapper;
    }

    public void save(User user) {
        couchbaseTemplate.save(user);
    }

    public User signIn(String email, String encryptedPassword) {
        String query = "SELECT meta(f).id, f.* FROM `user-bucket` AS f WHERE f.email= $email";
        ParameterizedN1qlQuery parameterizedN1qlQuery = N1qlQuery.parameterized(query, JsonObject.create().put("email", email));
        Stream<User> userStream = couchbaseTemplate.queryN1QL(parameterizedN1qlQuery).allRows().stream().map(this::map);
        List<User> collect = userStream.collect(Collectors.toList());
        return collect.get(0);
    }

    private User map(N1qlQueryRow n1qlQueryRow) {
        try {
            return objectMapper.readValue(n1qlQueryRow.byteValue(), User.class);
        } catch (Exception ex) {
            log.error("Could not parsed");
        }
        return null;
    }
}
