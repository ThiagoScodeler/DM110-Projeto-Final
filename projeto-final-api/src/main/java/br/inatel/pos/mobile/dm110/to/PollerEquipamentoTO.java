package br.inatel.pos.mobile.dm110.to;

import java.io.Serializable;

public class PollerEquipamentoTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String enderecoIP;
	
	private String status;
	
	public PollerEquipamentoTO() {
		// TODO Auto-generated constructor stub
	}
	
	public String getEnderecoIP() {
		return enderecoIP;
	}

	public void setEnderecoIP(String enderecoIP) {
		this.enderecoIP = enderecoIP;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
