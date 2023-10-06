package com.ideas.springboot.formulario.app.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ideas.springboot.formulario.app.DTO.ConseccionarioDTO;
import com.ideas.springboot.formulario.app.entity.ConseccionarioEntity;
import com.ideas.springboot.formulario.app.model.Conseccionario;
import com.ideas.springboot.formulario.app.model.Conversacion;
import com.ideas.springboot.formulario.app.repository.IConseccionario;




@Service
public class ConseccionariaService {

	@Autowired
	private IConseccionario repo;

	// Servicio para crear Conseccionario
	public Conseccionario crearConcessiones(Conseccionario conseccionario) {
		return repo.save(conseccionario);
	}
	
	
	// Servicio para Listar Conseccionario
	public List<Conseccionario> listarConsecciones() {
		return repo.findByOrdenFecha();
	}
	
	// Servicio para buscar Conseccionario
	public List<Conseccionario> findByNombre(String nombre) {
		return repo.getConseccionarioByNombre(nombre);
	}
	
	
	// Servicio para buscar Conseccionario
	public List<String> getConseccionarias() {
		return repo.getConseccionarioActuales();
	}
	
	
	// Servicio para buscar Conseccionario
	
	//@Transactional(readOnly = true)
	public List<Conseccionario> getConseccionariasFecha(Date desde) {
		System.out.println("sssssssssssss ");
		
		
		return repo.findByFechasVenta(desde);
	}
	
	
	public List<ConseccionarioEntity> getConseccionariasCant( ) {
		
		List<Object[]> result = repo.getConseccionarioCantidad();
		List<ConseccionarioEntity> conseccionarioList = new ArrayList<>();

		for (Object[] row : result) {
		    String conNomEmpresa = (String) row[0];
		    int conCantidad = ((Number) row[1]).intValue(); // Convertir a entero

		    ConseccionarioEntity conseccionario = new ConseccionarioEntity();
		    conseccionario.setCon_nom_empresa(conNomEmpresa);
		    conseccionario.setCon_cantidad(conCantidad);

		    conseccionarioList.add(conseccionario);
		}
		return conseccionarioList;
	}
	
	
	public List<ConseccionarioDTO> getListaAlmacen(String almacen) {
		
		return repo.findDistinctByConNomEmpresaIgnoreCaseContaining(almacen.trim());
	}
}

