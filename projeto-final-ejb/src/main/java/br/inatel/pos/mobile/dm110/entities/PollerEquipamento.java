package br.inatel.pos.mobile.dm110.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "equipamentos")
@SequenceGenerator(name = "seq_equipamentos", sequenceName = "seq_equipamentos", allocationSize = 1)
public class PollerEquipamento {
	
	@Id
	@Column(name = "endereco")
	private String enderecoIP;
	
	@Column(name = "status")
	private String status;

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
