package JSIA.WebMoteros.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import JSIA.WebMoteros.dtos.ClubDto;
import JSIA.WebMoteros.dtos.ContrasenyaYEmailDto;
import JSIA.WebMoteros.dtos.LoginRequestDto;
import JSIA.WebMoteros.dtos.UsuarioDto;
import JSIA.WebMoteros.dtos.Usuariosdtos;
import jakarta.servlet.http.HttpSession;

@Service
public class ApiService {

	@Value("${api.endpoint}")
	private String apiEndpoint;

	public String sendLoginData(LoginRequestDto loginRequest, String campo, HttpSession session) {
	    // Crear una instancia de RestTemplate
	    try {
	        URI uri = new URI("http://localhost:8081/apiMoteros/api/" + campo + "/login");
	        URL url = uri.toURL();
	        
	        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
	        conexion.setRequestMethod("POST");
	        conexion.setRequestProperty("Content-Type", "application/json");
	        conexion.setDoOutput(true);

	        // Crear el cuerpo de la solicitud con Jackson
	        ObjectMapper mapper = new ObjectMapper();
	        String jsonInput = mapper.writeValueAsString(loginRequest);
	        
	        try (OutputStream os = conexion.getOutputStream()) {
	            os.write(jsonInput.getBytes());
	            os.flush();
	        }
	        
	        int codigoRespuesta = conexion.getResponseCode();
	        if (codigoRespuesta == HttpURLConnection.HTTP_OK) {
	            // Leer la respuesta del servidor
	            BufferedReader in = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
	            StringBuilder response = new StringBuilder();
	            String inputLine;
	            while ((inputLine = in.readLine()) != null) {
	                response.append(inputLine);
	            }
	            in.close();
	            
	            // Convertir el JSON recibido en un objeto UsuarioDto
	            UsuarioDto usuario = mapper.readValue(response.toString(), UsuarioDto.class);
	            
	            if (usuario != null) {
	                // Guardar el objeto UsuarioDto en la sesión
	            	
	                session.setAttribute("usuario", usuario);
	                
	                return "success";
	            } else {
	                System.out.println("Error: La respuesta de la API no contiene un usuario válido.");
	                return "error";
	            }
	        } else {
	            System.out.println("Error en la conexión: " + codigoRespuesta);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return "error";
	}
	
	
	public String enviarRegistroClub(ClubDto nuevoClub) {
		
		try {
			
			URI uri = new URI("http://localhost:8081/apiMoteros/api/clubs");
			URL url = uri.toURL();
			
			HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
			
			conexion.setRequestProperty("content-Type", "application/json");
			conexion.setDoOutput(true);
			
			//Pasar el dto  a json para enviarlo a la api
			
			ObjectMapper mapper = new ObjectMapper();
			
			String dtoAJson = mapper.writeValueAsString(nuevoClub);
			System.out.println(dtoAJson);
			
			//Se envian los datos al servidor
			try (OutputStream os = conexion.getOutputStream()) {
	            os.write(dtoAJson.getBytes());
	            os.flush();
	            
	            // Leer la respuesta del servidor
	            int codigoRespuesta = conexion.getResponseCode();
	            
	            if (codigoRespuesta == HttpURLConnection.HTTP_OK) {
	            	
	                BufferedReader in = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
	                
	                StringBuilder response = new StringBuilder();
	                
	                String inputLine;
	                
	                while ((inputLine = in.readLine()) != null) {
	                	
	                    response.append(inputLine);
	                }
	                in.close();
	                
	                return "success"; // Puedes analizar más la respuesta si es necesario
	                
	            } else {
	            	
	                System.out.println("Error en la conexión: " + codigoRespuesta);
	            }
	        }
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return "error";
	}
	

	public String sendAddUsu(Usuariosdtos usuDto) {
		// Crear una instancia de RestTemplate
		try {
		
			URI uri = new URI("http://localhost:8081/apiMoteros/api/usuarios");
            URL url = uri.toURL();
            
	        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
	        conexion.setRequestMethod("POST");
	        conexion.setRequestProperty("Content-Type", "application/json");
	        conexion.setDoOutput(true);

	        // Crear cuerpo de la solicitud con Jackson
	        ObjectMapper mapper = new ObjectMapper();
	        String jsonInput = mapper.writeValueAsString(usuDto);
	        
	        System.out.println(jsonInput);
	        
	        try (OutputStream os = conexion.getOutputStream()) {
	            os.write(jsonInput.getBytes());
	            os.flush();
	        }
	       

	        int codigoRespuesta = conexion.getResponseCode();
	        if (codigoRespuesta == HttpURLConnection.HTTP_OK) {
	        	System.out.print("credenciales validas");
	            BufferedReader in = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
	            String inputLine;
	            StringBuilder response = new StringBuilder();
	            while ((inputLine = in.readLine()) != null) {
	                response.append(inputLine);
	            }
	            in.close();
	            return "success";
	        } else {
	            System.out.println("Error en la conexión: " + codigoRespuesta);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return "asdf";
	}
	
	public String eliminarUsuario(ContrasenyaYEmailDto DtoPassEmail, String campo) {
		// Crear una instancia de RestTemplate
		try {
			System.out.println("entramos en eliminar");
			URI uri = new URI("http://localhost:8081/apiMoteros/api/" + campo + "/deleteUsu");
            URL url = uri.toURL();
            
	        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
	        conexion.setRequestMethod("DELETE");
	        conexion.setRequestProperty("Content-Type", "application/json");
	        conexion.setDoOutput(true);

	        // Crear cuerpo de la solicitud con Jackson
	        ObjectMapper mapper = new ObjectMapper();
	        String jsonInput = mapper.writeValueAsString(DtoPassEmail);
	        
	        System.out.println(jsonInput);
	        
	        try (OutputStream os = conexion.getOutputStream()) {
	            os.write(jsonInput.getBytes());
	            os.flush();
	        }
	        System.out.println("Estamos en el final de eliminarUsu");

	        int codigoRespuesta = conexion.getResponseCode();
	        if (codigoRespuesta == HttpURLConnection.HTTP_OK) {
	        	System.out.print("credenciales validas");
	            BufferedReader in = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
	            String inputLine;
	            StringBuilder response = new StringBuilder();
	            while ((inputLine = in.readLine()) != null) {
	                response.append(inputLine);
	            }
	            in.close();
	            return "success";
	        } else {
	            System.out.println("Error en la conexión: " + codigoRespuesta);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return "asdf";
	}

}

