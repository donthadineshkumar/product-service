package com.contentserv.model;

import lombok.Data;

import java.util.List;

@Data
public class Attribute {
    String name;
    List<String> options;
}
