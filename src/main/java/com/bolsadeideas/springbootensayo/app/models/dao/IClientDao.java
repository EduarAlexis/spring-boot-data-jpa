package com.bolsadeideas.springbootensayo.app.models.dao;

import java.util.List;

import com.bolsadeideas.springbootensayo.app.models.entity.Cliente;

public interface IClientDao {
	public List<Cliente> findAll();
}
