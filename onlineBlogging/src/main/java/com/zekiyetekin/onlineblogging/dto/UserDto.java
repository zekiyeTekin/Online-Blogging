package com.zekiyetekin.onlineblogging.dto;

import com.zekiyetekin.onlineblogging.enumuration.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Integer id;

    private String name;

    private String mail;

    private String password;

    private RoleEnum role;


    public UserDto(Builder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.mail = builder.mail;
        this.password = builder.password;
        this.role = builder.role;
    }

    public static class Builder{
        private Integer id;
        private String name;
        private String mail;
        private String password;
        private RoleEnum role;

        public Builder(){ }

        public Builder(Integer id, String name, String mail, String password, RoleEnum role ){
            this.id = id;
            this.name = name;
            this.mail = mail;
            this.password = password;
            this.role = role;
        }

        public Builder id(Integer id){
            this.id = id;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder mail(String mail){
            this.mail = mail;
            return this;
        }

        public Builder password(String password){
            this.password = password;
            return this;
        }

        public Builder role(RoleEnum role){
            this.role = role;
            return this;
        }

        public UserDto build(){
            return new UserDto(this);
        }

    }
}
