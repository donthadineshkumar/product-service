package com.contentserv.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@Document(value = "sequences")
public class Sequence {
    @Id
    String id;
    private BigInteger seq;
}
