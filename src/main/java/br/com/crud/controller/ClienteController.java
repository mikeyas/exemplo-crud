package br.com.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.crud.model.Cliente;
import br.com.crud.service.ClienteService;

@Controller
public class ClienteController {
	@Autowired
	private ClienteService service;
	
	public void save(Cliente cliente) {
		service.save(cliente);
	}
	
	public void remover(Long idCliente) {
		service.remover(idCliente);
	}
	
	public List<Cliente> listar(){
		return service.listar();
	}
	
	public Cliente buscaPorID(Long idCliente) {
		return service.buscaPorID(idCliente);
	}
	
	public void update(Cliente cliente) {
		service.update(cliente);
	}

}
