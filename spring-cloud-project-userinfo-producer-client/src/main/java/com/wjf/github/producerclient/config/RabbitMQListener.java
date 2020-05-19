package com.wjf.github.producerclient.config;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RabbitMQListener implements ChannelAwareMessageListener {

	@Override
	public void onMessage(Message message, Channel channel) throws Exception {
		final long deliveryTag = message.getMessageProperties().getDeliveryTag();
		try {
			final String msg = message.toString();

			log.info("***消费消息:" + msg + "***");

			//肯定确认
			//true表示确认所有消息，包括提供的传送标签；false表示仅确认提供的传送标签。
			channel.basicAck(deliveryTag, true);

			//否定确认，第二个参数为true时会重新将信息重新投递到队列中
			//channel.basicReject(deliveryTag,true);

			//第一个参数依然是当前消息到的数据的唯一id;
			//第二个参数是指是否针对多条消息；如果是true，也就是说一次性针对当前通道的消息的tagID小于当前这条消息的，都拒绝确认。
			//第三个参数是指是否重新入列，也就是指不确认的消息是否重新丢回到队列里面去。
			//channel.basicNack(deliveryTag,false,false);
		} catch (Exception e) {
			channel.basicReject(deliveryTag, true);
		}
	}
}
