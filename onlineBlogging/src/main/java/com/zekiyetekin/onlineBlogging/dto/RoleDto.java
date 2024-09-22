package com.zekiyetekin.onlineBlogging.dto;

import com.zekiyetekin.onlineBlogging.enumuration.RoleEnum;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {

    private RoleEnum name;
}
