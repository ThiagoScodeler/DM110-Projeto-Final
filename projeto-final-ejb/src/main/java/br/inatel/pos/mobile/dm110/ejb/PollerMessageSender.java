package br.inatel.pos.mobile.dm110.ejb;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

import br.inatel.pos.mobile.dm110.to.PollerEquipamentoListTO;

@Stateless
public class PollerMessageSender {
	
	@Resource(lookup = "java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;
	
	@Resource(lookup = "java:/jms/queue/EquipamentoQueue")
	private Queue queue;
	
	public void sendMessage(PollerEquipamentoListTO listTO) {
		try (
				Connection connection = connectionFactory.createConnection();
				Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
				MessageProducer producer = session.createProducer(queue);
		) {
			ObjectMessage message = session.createObjectMessage(listTO);
			producer.send(message);
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}
}
