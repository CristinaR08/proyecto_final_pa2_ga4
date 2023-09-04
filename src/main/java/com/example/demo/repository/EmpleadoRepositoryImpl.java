package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.repository.modelo.Empleado;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class EmpleadoRepositoryImpl implements IEmpleadoRepository {

	@PersistenceContext
	private EntityManager entityManager;

	// CRUD
	@Override
	public void ingresar(Empleado empleado) {
		this.entityManager.persist(empleado);
	}

	@Override
	public Empleado buscar(Integer id) { 
		return this.entityManager.find(Empleado.class, id);
	}

	@Override
	public Empleado buscarUser(String user) {
		TypedQuery< Empleado> query = this.entityManager.createQuery("SELECT e FROM Empleado e WHERE e.usuario = :datoUsuario",Empleado.class);
		query.setParameter("datoUsuario", user);
		
		try {
			return query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("No se encontro el usuario");
			return null;
			
		}
	}
	
	

}
