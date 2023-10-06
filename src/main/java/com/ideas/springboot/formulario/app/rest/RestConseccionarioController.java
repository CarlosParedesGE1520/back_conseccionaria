package com.ideas.springboot.formulario.app.rest;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ideas.springboot.formulario.app.DTO.ConseccionarioDTO;
import com.ideas.springboot.formulario.app.entity.ConseccionarioEntity;
import com.ideas.springboot.formulario.app.model.Conseccionario;
import com.ideas.springboot.formulario.app.model.Conversacion;
import com.ideas.springboot.formulario.app.service.ConseccionariaService;
import com.ideas.springboot.formulario.app.service.ConversacionService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/conseccionario")
public class RestConseccionarioController {

	
	@Autowired
	private ConseccionariaService conseServicio;

	// agregar conversacion
	@PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
	public Conseccionario registrarCliente(@RequestBody @Valid Conseccionario conversacion) {

		return conseServicio.crearConcessiones(conversacion);
	}
	
	
	// Listar conversacion
	@GetMapping(path = "/list", produces = "application/json")
	public List<Conseccionario> listarConseccionario() {
		return conseServicio.listarConsecciones();
	}
	
//	
	// Buscar conversacion por id
	@GetMapping("/find/{nombre}")
	public List<Conseccionario> buscarPorId(@PathVariable String nombre) {
		return conseServicio.findByNombre(nombre);
	}
	
	
	@GetMapping("/listConseccionarias")
	public List<String> listConseccionarias() {
		return conseServicio.getConseccionarias();
	}
	
	
//	@GetMapping("/listFecha/{fecha}")
//	public List<Conseccionario> listPorFecha() {
//		return conseServicio.getConseccionariasFecha();
//	}
	

    @GetMapping("/listFecha/{desde}")
    public ResponseEntity<String> obtenerDatosPorFecha( @PathVariable Date desde) {
    	conseServicio.getConseccionariasFecha(desde);
    	System.out.println("SSSScccc");
        return ResponseEntity.ok("Fecha recibida: " + desde);
    }
	
	
	
	@GetMapping("/listcantidad")
	public List<ConseccionarioEntity> listPorCant( ) {
		return conseServicio.getConseccionariasCant();
	}
	
	
	@GetMapping("/listAlmacenes/{nombre}")
	public List<ConseccionarioDTO> listAlmacenes(@PathVariable String nombre ) {
		System.out.println(nombre);
		return conseServicio.getListaAlmacen(nombre);
	}
}
