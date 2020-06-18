package com.study.studyrestfulservice.account;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Account {

    private Long id;
    @Size(min = 2, message = "Name은 2글자 이상 입력해 주세요.")
    private String name;
    @Past
    private LocalDateTime joinDate;
}
