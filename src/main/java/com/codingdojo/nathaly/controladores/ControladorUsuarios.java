package com.codingdojo.nathaly.controladores;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.nathaly.modelos.Usuario;
import com.codingdojo.nathaly.servicios.Servicios;

@Controller
public class ControladorUsuarios {

	@Autowired
	private Servicios servicios;
	
	@GetMapping("/")
	public String index(@ModelAttribute("nuevoUsuario")Usuario nuevoUsuario) {
		return "index.jsp";
	}
	
	@PostMapping("/registro")
	public String registro(@Valid @ModelAttribute("nuevoUsuario")Usuario nuevoUsuario, BindingResult result, HttpSession session) {
		//metodo en servicio
		servicios.registrar(nuevoUsuario, result);
		
		if(result.hasErrors()) {
			return "index.jsp";
		}else {
			session.setAttribute("usuarioEnSesion", nuevoUsuario);//guardamos en sesion esto para poder desplegar en mas de una pagina el mismo usuario 
			return "redirect:/dashboard";
		}
	}
	
	@GetMapping("/dashboard")
	public String dashboard(HttpSession session) {
		
		Usuario usuarioEnSesion=(Usuario)session.getAttribute("usuarioEnSesion");
		if(usuarioEnSesion==null) {
			return "redirect:/";
		}
		return "dashboard.jsp";
	}
	
	@PostMapping("/login")
	//RequestParam se usa por tener un formulario normal, con el recibo el correo a traves de un string email
	public String login(@RequestParam("email") String email, @RequestParam("contrasena") String contrasena, RedirectAttributes redirectAttributes, HttpSession session) {
		//enviar email y contrasena y que el servicio verifique si son correctos
		Usuario usuarioLogin= servicios.login(email, contrasena);
		if(usuarioLogin==null) {
			//hay error
			redirectAttributes.addFlashAttribute("error_login","El correo/password es incorrecto");
			return "redirect:/";
		}else {
			//guardamos en sesion
			session.setAttribute("usuarioEnSesion", usuarioLogin);
			return "redirect:/dashboard";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("usuarioEnSesion");
		return "redirect:/";
	}
}
