package br.inatel.pos.mobile.dm110.mdb;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import br.inatel.pos.mobile.dm110.interfaces.PollerLocal;
import br.inatel.pos.mobile.dm110.to.PollerEquipamentoListTO;
import br.inatel.pos.mobile.dm110.to.PollerEquipamentoTO;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType",
								  propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination",
								  propertyValue = "java:/jms/queue/EquipamentoQueue")
})
public class PollerMDB implements MessageListener {

	@EJB
	private PollerLocal pollerLocal;

	@Override
	public void onMessage(Message message) {
		try {
			if (message instanceof ObjectMessage) {
				ObjectMessage objMessage = (ObjectMessage) message;
				
				PollerEquipamentoListTO listTO = (PollerEquipamentoListTO) objMessage.getObject();
				
				for (PollerEquipamentoTO equipamentoTO : listTO.getList()) {

					try {

						boolean ping = execPing(equipamentoTO.getEnderecoIP());
						String status = ping ? "Ativo" : "Inativo";
						pollerLocal.addEquipamento(equipamentoTO.getEnderecoIP(), status);
						
					} catch (Exception e1) {
						e1.printStackTrace();
					}

				}
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	public static boolean execPing(String address) {
		try {
			Runtime runtime = Runtime.getRuntime();
			Process process = runtime.exec("ping -n 1 " + address);
			InputStream is = process.getInputStream();
			InputStream es = process.getErrorStream();
			String input = processStream(is);
			String error = processStream(es);
			int code = process.waitFor();
			if (code != 0) {
				return false;
			}
			if (error.length() > 0) {
				return false;
			}
			if (input.contains("Host de destino inacess")) {
				return false;
			}
			return true;
		} catch (IOException | InterruptedException e) {
			return false;
		}
	}

	public static String processStream(InputStream is) {
		StringBuilder input = new StringBuilder();
		try (Scanner scanner = new Scanner(is)) {
			while (scanner.hasNextLine()) {
				input.append(scanner.nextLine()).append("\n");
			}
		}
		return input.toString();
	}

}
