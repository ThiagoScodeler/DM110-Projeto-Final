package br.inatel.pos.mobile.dm110.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.inatel.pos.mobile.dm110.entities.PollerEquipamento;

@Stateless
public class PollerDAO {
	
	@PersistenceContext(unitName = "projeto_final")
	private EntityManager em;

	public PollerEquipamento insert(PollerEquipamento equipamento) {
		em.persist(equipamento);
		return equipamento;
	}
	
	public List<PollerEquipamento> consultar() {
		List<PollerEquipamento> equipamentos = null;
		equipamentos = em.createQuery("from Equipamentos e", PollerEquipamento.class).getResultList();
		return equipamentos;
	}
	
	public PollerEquipamento checarPorIP(String enderecoIP) {
		PollerEquipamento equipamento = null;
		equipamento = em.find(PollerEquipamento.class, enderecoIP);
		return equipamento;
	}
	
}