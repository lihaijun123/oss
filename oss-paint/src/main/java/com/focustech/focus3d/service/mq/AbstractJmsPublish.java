package com.focustech.focus3d.service.mq;

import java.util.Map;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
/**
 *
 * *
 * @author lihaijun
 *
 */
public class AbstractJmsPublish {

	private JmsTemplate jmsTemplate;

	public void publish(final String message){
		jmsTemplate.send(new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}
		});
	}
	public void publish(final Map<String, String> message){

		jmsTemplate.send(new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				MapMessage createMapMessage = session.createMapMessage();
				for(Map.Entry<String, String> m : message.entrySet()){
					createMapMessage.setObject(m.getKey(), m.getValue());
				}
				return createMapMessage;
			}

		});
	}

	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

}
