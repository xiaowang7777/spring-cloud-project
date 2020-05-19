package com.wjf.github.commons.domain.producer;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo implements Serializable {

	private static final long serialVersionUID = -6823336902674742412L;

	private Integer userId;

	private String dingdingId;

	private String userName;

	private String readCard;

	private String password;

	private String userStatus;

	private Date addTime;

	private Date updateTime;

}
