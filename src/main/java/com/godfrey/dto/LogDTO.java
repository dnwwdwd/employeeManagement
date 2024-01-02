package com.godfrey.dto;

import lombok.Data;

@Data
public class LogDTO {
    private int id;
    private int roleId;
    private String user_name;
    private String operation;
}
