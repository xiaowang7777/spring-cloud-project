package com.springcloud.userCenter.domain;

import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDo implements Serializable {
	private static final long serialVersionUID = -5008192980907875957L;
	private String userAccount;         //用户账号
	private String telephoneNumber;     //用户电话
	private String password;            //密码
}
