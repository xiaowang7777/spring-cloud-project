package com.springcloud.userCenter.vo;

import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserNormalInfo implements Serializable {
	private static final long serialVersionUID = -54041171487323549L;
	private String userId;                  //用户编号，全局唯一的UUID
	private String userName;                //用户名
	private String userAccount;             //用户账号
	private String telephoneNumber;         //用户电话号码
	private String userToken;               //用户Token
}
