package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.IEstudianteRepository;
import com.example.demo.repository.modelo.Estudiante;
import com.example.demo.service.to.EstudianteTo;

@Service
public class EstudianteServiceImpl implements IEstudianteService {
	
	@Autowired
	private IEstudianteRepository estudianteRepository;

	@Override
	public EstudianteTo guardar(EstudianteTo estudianteTO) {
		// TODO Auto-generated method stub
		this.estudianteRepository.insertar(this.convertir(estudianteTO));

		Estudiante e =this.estudianteRepository.buscarPorCedula(estudianteTO.getCedula());

		return this.convertirTo(e);
	}

	private Estudiante convertir(EstudianteTo estudianteTO) {
		Estudiante e = new Estudiante();
		e.setNombre(estudianteTO.getNombre());
		e.setApellido(estudianteTO.getApellido());
		e.setCedula(estudianteTO.getCedula());

		return e;
	}

	private EstudianteTo convertirTo(Estudiante estudianteTO) {
		EstudianteTo e = new EstudianteTo();
		e.setId(estudianteTO.getId());
		e.setNombre(estudianteTO.getNombre());
		e.setApellido(estudianteTO.getApellido());
		e.setCedula(estudianteTO.getCedula());

		return e;
	}

}