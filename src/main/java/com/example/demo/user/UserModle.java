package com.example.demo.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user")
@Getter @Setter
public class UserModle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    @NotBlank(message = "帳號不可為空")
    private String username;

    @Column(unique = true)
    @Email(message = "信箱格式錯誤")
    @NotBlank(message = "信箱不可為空")
    private String email;

    @Column
    @Size(min = 8, message = "密碼不可少於8位")
    @NotBlank(message = "密碼不可為空")
    private String password;
}
