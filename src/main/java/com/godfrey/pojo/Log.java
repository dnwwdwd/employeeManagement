package com.godfrey.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Log {
    private int roleId;
    private String user_name;
    private String operation;
}
