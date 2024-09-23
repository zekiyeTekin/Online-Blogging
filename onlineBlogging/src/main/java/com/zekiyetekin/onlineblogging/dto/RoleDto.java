package com.zekiyetekin.onlineblogging.dto;

import com.zekiyetekin.onlineblogging.enumuration.RoleEnum;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {

    private RoleEnum name;
}
