package br.com.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.crud.model.Cliente;
import br.com.crud.service.ClienteService;

@Controller
public class ClienteController {
	
	@Autowired
	private ClienteService service;
	
	@RequestMapping(value="/salvar", method=RequestMethod.PUT)
	public ResponseEntity<List<Cliente>> save(@RequestBody Cliente cliente) {
		
		if(cliente == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		service.save(cliente);
		List<Cliente> clientes = service.listar();
		return new ResponseEntity<List<Cliente>>(clientes,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/editar/{id}", method=RequestMethod.PUT)
	public ResponseEntity<List<Cliente>> update(@RequestBody Cliente cliente, @PathVariable("id")Long id) {
		Cliente cli = service.buscaPorID(id);
		if(cliente == null || cli == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		if(cliente.getNome()!=null) {
			cli.setNome(cliente.getNome());
		}
		
		if(cliente.getEmail()!=null) {
			cli.setEmail(cliente.getEmail());
		}
		
		List<Cliente> clientes = service.listar();
		return new ResponseEntity<List<Cliente>>(clientes,HttpStatus.OK);
		
	}

	@GetMapping("/remover/{id}")
	public ResponseEntity<List<Cliente>> remover(@PathVariable("id")Long id){
		
		Cliente cliente = service.buscaPorID(id);
		if(cliente == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		service.remover(id);
		List<Cliente> clientes = service.listar();
		return new ResponseEntity<List<Cliente>>(clientes,HttpStatus.OK);

	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity <Cliente> buscaPorID(@PathVariable("id")Long id){
		Cliente cliente = service.buscaPorID(id);
		if(cliente == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Cliente>(cliente,HttpStatus.OK);
	}
	
	@GetMapping("listar")
	public ResponseEntity<List<Cliente>> Listar(){
		List<Cliente> clientes = service.listar();
		if(clientes.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Cliente>>(clientes,HttpStatus.OK);
	}

}
