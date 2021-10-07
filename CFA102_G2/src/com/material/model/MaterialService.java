package com.material.model;

import java.util.List;

public class MaterialService {
	private MaterialDAO_interface dao;

	public MaterialService() {
		dao = new MaterialJDBCDAO();
	}
	
	public MaterialVO addMaterialService(Integer cno,String matFile) {
		MaterialVO matVO = new MaterialVO();
		matVO.setCno(cno);
		matVO.setMatFile(matFile);
		dao.insert(matVO);
		return matVO;
	}
	public MaterialVO updateMaterial(Integer matno,String matFile) {
		MaterialVO matVO = new MaterialVO();
		matVO.setMatNo(matno);
		matVO.setMatFile(matFile);
		dao.update(matVO);

		return matVO;
	}
	
	public void deleteMaterial(Integer matno) {
		dao.delete(matno);
	}
	
	public MaterialVO getOneMaterial(Integer matno) {
		return dao.getOne(matno);
	}
	
	public List<MaterialVO> getAll() {
		return dao.getAll();
	}
	
	
	
	
	
	
	
	
}
