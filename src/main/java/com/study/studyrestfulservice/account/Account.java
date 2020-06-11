package com.study.studyrestfulservice.account;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Account {

    private Long id;
    private String name;
    private LocalDateTime joinDate;
}
