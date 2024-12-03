package JSIA.WebMoteros.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import JSIA.WebMoteros.dtos.ContrasenyaYEmailDto;
import JSIA.WebMoteros.services.ApiService;
@Controller
public class DeleteController {
	@Autowired
	ApiService apiService;
	@GetMapping("/deleteUsu")
	public String showLoginPage() {
		return "deleteUsu";
	}
	@DeleteMapping("/deleteUsu")
	public String deleteUsu(@RequestParam String email, @RequestParam String contrasenya) {
		System.out.println("Estamos ready");

		ContrasenyaYEmailDto passYemail = new ContrasenyaYEmailDto( email,contrasenya);
		System.out.print(passYemail.toString());
		String response = apiService.eliminarUsuario(passYemail,"usuario");

		if ("success".equalsIgnoreCase(response)) {
			
			return "redirect:/ventanaPrincipal";
		}
		return "redirect:/login?error";
	}
}
