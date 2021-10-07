package com.specialty.model;

import java.util.List;

public class SpecialtyService {

	private SpecialtyDAO_interface dao = new SpecialtyDAO();

	public SpecialtyVO addSpecialty(String specialtyName) {

		SpecialtyVO specialty = new SpecialtyVO();

		specialty.setSpecialtyName(specialtyName);

		dao.insert(specialty);

		return specialty;
	}

	public SpecialtyVO updateSpecialty(Integer specialtyNo, String specialtyName) {

		SpecialtyVO specialty = new SpecialtyVO();

		specialty.setSpecialtyNo(specialtyNo);
		specialty.setSpecialtyName(specialtyName);

		dao.update(specialty);

		return specialty;
	}

	public List<SpecialtyVO> getAll() {
		return dao.getAll();
	}
	
	///世柏增加
	public SpecialtyVO findBySpecialtyName(int specialtyNo){
		
		return dao.findBySpecialtyName(specialtyNo);
	}
	///世柏增加
}
