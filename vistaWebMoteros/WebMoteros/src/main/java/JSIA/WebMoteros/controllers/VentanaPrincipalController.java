package JSIA.WebMoteros.controllers;


import JSIA.WebMoteros.dtos.UsuarioDto;
import JSIA.WebMoteros.services.ApiService;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class VentanaPrincipalController {

    @Autowired
    private ApiService apiService;

    @GetMapping("/ventanaPrincipal")
    public ModelAndView showLoginPage(HttpSession session) {
    	
    	UsuarioDto usuarioDto = (UsuarioDto) session.getAttribute("usuario");
        
    	ModelAndView modelAndViewPaginaPrincipal = new ModelAndView("ventanaPrincipal");
    	
        modelAndViewPaginaPrincipal.addObject("rol", usuarioDto.getRolUsuario());
        
        System.out.println(usuarioDto.toString());
        
        return modelAndViewPaginaPrincipal;
    }

}
