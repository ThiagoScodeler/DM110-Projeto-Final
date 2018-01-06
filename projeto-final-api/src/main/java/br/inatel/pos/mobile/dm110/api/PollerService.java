package br.inatel.pos.mobile.dm110.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.inatel.pos.mobile.dm110.to.PollerEquipamentoTO;

@Path("/poller")
public interface PollerService {
	
	@GET
	@Path("/start/{IP}/{Mask}")
	@Produces(MediaType.APPLICATION_JSON)
	void iniciarVarredura(@PathParam("IP") String ip, @PathParam("Mask") String mask);

	@GET
	@Path("/status/{IP}")
	@Produces(MediaType.APPLICATION_JSON)
	PollerEquipamentoTO checarStatus(@PathParam("IP") String ip);

}