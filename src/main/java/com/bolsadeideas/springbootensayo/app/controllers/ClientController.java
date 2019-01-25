package com.bolsadeideas.springbootensayo.app.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bolsadeideas.springbootensayo.app.models.dao.IClientDao;
import com.bolsadeideas.springbootensayo.app.models.entity.Cliente;

@Controller
public class ClientController {

	/*
	 * Cuando se manejan bastantes bean o Daos (en este caso se puede omitir, lo
	 * dejo por explicación), se utiliza la anotación @Qualifier("clienteDaoJPA")
	 * indicando el nombre expesificado en la implementación del
	 * dao @Repository("clienteDaoJPA"), para este caso ClienteDaoImpl
	 */

	@Autowired
	@Qualifier("clienteDaoJPA")
	private IClientDao clienteDao;

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de clientes");
		model.addAttribute("clientes", clienteDao.findAll());
		return "listar";
	}

	/*
	 * Para leer los clientes y mostrarlos en la interfaz web
	 */
	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model) {
		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		model.put("Titulo", "Formulario cliente");
		return "form";
	}

	/*
	 * Para guardar en la base de datos los datos recolectados en la 
	 * interfaz web
	 * */
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(Cliente cliente) {
		clienteDao.save(cliente);
		return "redirect:listar";
	}
}
