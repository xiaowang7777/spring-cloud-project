package com.springcloud.userCenter.vo;

import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RolePathInfo implements Serializable {
	private static final long serialVersionUID = -7362630688600603179L;
	private Integer roleId;             //权限编号
	private String rolePath;            //权限地址
}
