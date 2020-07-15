package com.contentserv.model;

import lombok.Data;

@Data
public class Category {
    String name;
    Category category;
}
