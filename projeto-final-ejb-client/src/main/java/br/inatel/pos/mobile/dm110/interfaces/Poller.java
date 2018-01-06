package br.inatel.pos.mobile.dm110.interfaces;

import br.inatel.pos.mobile.dm110.to.PollerEquipamentoTO;

public interface Poller {
	
	void iniciarVarredura(String enderecoIP, String mascaraRede);
	
	PollerEquipamentoTO checarPorIP(String enderecoIP);
	
	void addEquipamento(String enderecoIP, String status);
	
}