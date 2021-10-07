package com.d_license.model;

import java.util.List;



public interface DlicenseDAO_interface {
	
	void insert(DlicenseVO dLicense); // 新增證照  營養師會員前台

	void update(DlicenseVO dLicense);	// 修改證照  營養師會員前台
	
	List<DlicenseVO> getAll(); // 找全部訂單

	List<DlicenseVO> findByDno(int dno); // 營養師編號編號找證照


}
