package com.d_specialty.model;

import java.util.List;

public class DspecialtyService {

	private DspecialtyDAO_interface dao = new DspecialtyDAO();

	public DspecialtyVO addDspecialty(Integer specialtyNo, Integer dno) {

		DspecialtyVO dSpecialty = new DspecialtyVO();

		dSpecialty.setSpecialtyNo(specialtyNo);
		dSpecialty.setDno(dno);

		dao.insert(dSpecialty);

		return dSpecialty;

	}

	public void deleteDspecialty(Integer specialtyNo, Integer dno) {

		DspecialtyVO dSpecialty = new DspecialtyVO();

		dSpecialty.setSpecialtyNo(specialtyNo);
		dSpecialty.setDno(dno);

		dao.delete(dSpecialty);
	}

	public List<DspecialtyVO> getAll() {

		return dao.getAll();
	}

	public List<DspecialtyVO> findByDno(int dno) {

		return dao.findByDno(dno);
	}
	
	
//////世柏增加
	public List<DspecialtyVO> findByDno1(int specialtyNo){
	
	return dao.findByDno1(specialtyNo);
	}
	/////世柏增加
	
	
	

}
