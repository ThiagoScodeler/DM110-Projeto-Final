package br.inatel.pos.mobile.dm110.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.inatel.pos.mobile.dm110.dao.PollerDAO;
import br.inatel.pos.mobile.dm110.entities.PollerEquipamento;
import br.inatel.pos.mobile.dm110.interfaces.PollerLocal;
import br.inatel.pos.mobile.dm110.interfaces.PollerRemote;
import br.inatel.pos.mobile.dm110.to.PollerEquipamentoListTO;
import br.inatel.pos.mobile.dm110.to.PollerEquipamentoTO;
import br.inatel.pos.mobile.dm110.util.NetworkIpGen;


@Stateless
@Remote(PollerRemote.class)
@Local(PollerLocal.class)
public class PollerBean implements PollerLocal, PollerRemote {
	
	@EJB
	private PollerDAO dao;
	
	@EJB
	private PollerMessageSender messageSender;
	
	@Override
	public void iniciarVarredura(String enderecoIP, String mascaraRede) {
		// TODO Auto-generated method stub
		
		List<PollerEquipamentoTO> equipamentoTOs = new ArrayList<>();

		try {
			String[] networkIpGenerated = NetworkIpGen.generateIps(enderecoIP, Integer.parseInt(mascaraRede));
			for (int i = 0; i < networkIpGenerated.length; i++) {
				String ip = networkIpGenerated[i];
				if (ip.indexOf(".0") == -1 || ip.indexOf(".255") == -1) {
					PollerEquipamentoTO equipamentoTO = new PollerEquipamentoTO();
					equipamentoTO.setEnderecoIP(ip);
					equipamentoTOs.add(equipamentoTO);
					if (i != 0 && i % 10 == 0) {
						if(i ==  networkIpGenerated.length){
							PollerEquipamentoTO pollerEquipamentoTO = new PollerEquipamentoTO();
							pollerEquipamentoTO.setEnderecoIP(enderecoIP);
							equipamentoTOs.add(pollerEquipamentoTO);
						}
						PollerEquipamentoListTO listTO = new PollerEquipamentoListTO(equipamentoTOs);
						messageSender.sendMessage(listTO);
						equipamentoTOs = new ArrayList<>();
					}
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}

	@Override
	public PollerEquipamentoTO checarPorIP(String enderecoIP) {
		// TODO Auto-generated method stub
		PollerEquipamento equipamento = dao.checarPorIP(enderecoIP);
		PollerEquipamentoTO equipamentoTO = new PollerEquipamentoTO();
		equipamentoTO.setEnderecoIP(equipamento.getEnderecoIP());
		equipamentoTO.setStatus(equipamento.getStatus());
		return equipamentoTO;
	}

	@Override
	public void addEquipamento(String enderecoIP, String status) {
		// TODO Auto-generated method stub
		PollerEquipamento equipamento = new PollerEquipamento();
		equipamento.setEnderecoIP(enderecoIP);
		equipamento.setStatus(status);
		dao.insert(equipamento);
	}

}