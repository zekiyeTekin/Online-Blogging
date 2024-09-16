package com.zekiyetekin.onlineBlogging.entity;


import com.zekiyetekin.onlineBlogging.enumuration.RoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer Id;


    @Enumerated(EnumType.STRING)
    private RoleEnum name;
}
