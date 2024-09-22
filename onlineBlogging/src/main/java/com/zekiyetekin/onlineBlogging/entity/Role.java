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

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;


    @Enumerated(EnumType.STRING)
    private RoleEnum name;
}
