package br.inatel.pos.mobile.dm110.impl;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

import br.inatel.pos.mobile.dm110.api.PollerService;
import br.inatel.pos.mobile.dm110.interfaces.PollerRemote;
import br.inatel.pos.mobile.dm110.to.PollerEquipamentoTO;


@RequestScoped
public class PollerServiceImpl implements PollerService {
	
	@EJB(lookup="java:app/projeto-final-ejb-1.0.0-SNAPSHOT/PollerBean!br.inatel.pos.mobile.dm110.interfaces.PollerRemote")
	private PollerRemote pollerRemote;

	@Override
	public void iniciarVarredura(String ip, String mask) {
		// TODO Auto-generated method stub
		pollerRemote.iniciarVarredura(ip, mask);
	}

	@Override
	public PollerEquipamentoTO checarStatus(String ip) {
		// TODO Auto-generated method stub
		PollerEquipamentoTO equipamentoTO = pollerRemote.checarPorIP(ip);
		return equipamentoTO;
	}

}