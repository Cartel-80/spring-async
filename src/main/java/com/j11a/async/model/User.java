package com.j11a.async.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown=true)
@Data
public class User {

    private String name;
    private String blog;

    @Override
    public String toString() {
        return "User [name=" + name + ", blog=" + blog + "]";
    }

}
