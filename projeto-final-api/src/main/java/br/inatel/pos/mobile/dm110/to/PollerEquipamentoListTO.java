package br.inatel.pos.mobile.dm110.to;

import java.io.Serializable;
import java.util.List;

public class PollerEquipamentoListTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<PollerEquipamentoTO> list;
	
	public PollerEquipamentoListTO(List<PollerEquipamentoTO> list) {
		// TODO Auto-generated constructor stub
		this.list = list;
	}

	public List<PollerEquipamentoTO> getList() {
		return list;
	}

	public void setList(List<PollerEquipamentoTO> list) {
		this.list = list;
	}
	
}
