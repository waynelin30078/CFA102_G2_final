package com.admin.model;

import java.util.List;

public class AdminService {

	private AdminDAO_interface dao;

	public AdminService() {
		dao = new AdminJDBCDAO(); 
	}

	public AdminVO addAdmin(String aname,String aid,String apsw) {

		AdminVO adminVO = new AdminVO();

		adminVO.setAname(aname);
		adminVO.setAid(aid);
		adminVO.setApsw(apsw);
		
		dao.insert(adminVO);

		return adminVO;
	}

	public AdminVO updateAdmin(Integer ano,String aname,String aid,String apsw) {

		AdminVO adminVO = new AdminVO();

		adminVO.setAno(ano);
		adminVO.setAname(aname);
		adminVO.setAid(aid);
		adminVO.setApsw(apsw);
	
		dao.update(adminVO);

		return adminVO;
	}

	public void deleteAdmin(Integer ano) {
		dao.delete(ano);
	}

	public AdminVO getOneAdmin(Integer ano) {
		return dao.findByPrimaryKey(ano);
	}

	public List<AdminVO> getAll() {
		return dao.getAll();
	}
	
	public AdminVO findByAid(String aid) {
		return dao.findByAid(aid);
	}
}
