package com.wjf.github.commons.vo.gateway;

import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo implements Serializable {

	private static final long serialVersionUID = -5450957935932905721L;

	private String userName;

	private String password;

}
