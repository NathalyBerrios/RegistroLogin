package com.codingdojo.nathaly.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.nathaly.modelos.Usuario;

@Repository
public interface RepositorioUsuarios extends CrudRepository<Usuario,Long>{

	List<Usuario>findAll(); //findAll=SELECT*FROM usuarios
	
	Usuario findByEmail(String email); //SELECT*FROM usuarios WHERE email=<email>
}
