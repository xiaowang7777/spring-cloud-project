package com.springcloud.userCenter.controller.mo;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginModel implements Serializable {
	private static final long serialVersionUID = 6648732090626673723L;
	@NotNull
	@NotEmpty
	private String userName;        //用户名
	@NotNull
	@NotEmpty
	private String password;        //用户密码
}
