package com.wjf.github.springcloudproject.mqstream.service.impl;

import com.wjf.github.springcloudproject.mqstream.service.MassageApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;

@EnableBinding(Source.class)
public class MessageApiImpl implements MassageApi {

	@Resource
	private MessageChannel channel;

	@Override
	public boolean send() {

		channel.send(MessageBuilder.withPayload("测试信息").build());

		return false;
	}
}
