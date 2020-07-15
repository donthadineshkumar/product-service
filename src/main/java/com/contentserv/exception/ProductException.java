package com.contentserv.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;


@AllArgsConstructor
@Data
public class ProductException {
    Integer status;
    String error;
    Date timestamp;
}
