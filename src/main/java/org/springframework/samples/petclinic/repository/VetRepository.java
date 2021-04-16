/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.Specialty;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.transaction.annotation.Transactional;


public interface VetRepository extends Repository<Vet, Integer>{


	Collection<Vet> findAll() throws DataAccessException;
	
	
	@Query("SELECT vspec FROM Specialty vspec ORDER BY vspec.name")
	Collection<Specialty> findSpecialties() throws DataAccessException;
	
	void save(Vet vet) throws DataAccessException;
	Vet findById(int id);


	
	
	@Transactional
	@Modifying
	@Query("DELETE FROM Vet vet where vet.id=:vetId")
	void deleteVet(int vetId) throws DataAccessException;
	

}
