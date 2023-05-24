package com.codingdojo.nathaly.servicios;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.nathaly.modelos.Usuario;
import com.codingdojo.nathaly.repositorios.RepositorioUsuarios;

@Service
public class Servicios {

	@Autowired //para invocar todas las funciones del repo
	private RepositorioUsuarios repoUsuarios;
	
	/* Metodo que registra un nuevo usuario
	 * va a recibir un usuario, recibir posibles errores
	 * regresar usuario creado o null*/
	
	public Usuario registrar(Usuario nuevoUsuario, BindingResult result) {
		
		//revisamos que el correo que recibimos no exista en la tabal de usuarios
		String email= nuevoUsuario.getEmail();
		Usuario existeUsuario= repoUsuarios.findByEmail(email); //regresa null u objeto de suuario
		if(existeUsuario!=null) {//el correo ya esta registrado
			result.rejectValue("email", "Unique", "El correo ingresado ya esta en uso"); //campo en el que se equivoco= email, tipo de error unique=correo no es unico, lo ultimo es el mensaje de error
		}
		
		//comparamos contraseñas
		String contrasena= nuevoUsuario.getContrasena();
		String confirmacion= nuevoUsuario.getConfirmacion();
		if(!contrasena.equals(confirmacion)) {// si contraseña no es igual a confirmacion
			result.rejectValue("confirmacion", "Matches", "La contraseña no coincide"); //campo en el que se equivoco=confirmacion
		}
		//si no existe error guadamos
		if(result.hasErrors()) {
			return null;
		}else {
			//encriptamos contraseña
			String contra_encriptada= BCrypt.hashpw(contrasena, BCrypt.gensalt());
			nuevoUsuario.setContrasena(contra_encriptada);
			return repoUsuarios.save(nuevoUsuario);
		}
	}
	
	public Usuario login(String email, String password) {
		//buscamos que el correo recibido este en la base de datos
		Usuario existeUsuario= repoUsuarios.findByEmail(email);
		if(existeUsuario==null) {
			return null;
		}
		
		//comparamos contraseñans
		//BCrypt.checkpw(Contraseña no encriptada, contraseña encriptada)
		if(BCrypt.checkpw(password, existeUsuario.getContrasena())) {
			return existeUsuario;
		}else {
			return null;
		}
	}
}
