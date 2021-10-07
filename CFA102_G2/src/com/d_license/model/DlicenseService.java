package com.d_license.model;

import java.util.List;

public class DlicenseService {

	DlicenseDAO_interface dao = new DlicenseDAO();

	public DlicenseVO addDlicense(Integer dno, String licDesc, String licFile) {

		DlicenseVO dLicense = new DlicenseVO();

		dLicense.setDno(dno);
		dLicense.setLicDesc(licDesc);
		dLicense.setLicFile(licFile);

		dao.insert(dLicense);

		return dLicense;
	}

	public DlicenseVO updateDlicense(Integer dlicNo, Integer dno, String licDesc, String licFile) {

		DlicenseVO dLicense = new DlicenseVO();

		dLicense.setDlicNo(dlicNo);
		dLicense.setDno(dno);
		dLicense.setLicDesc(licDesc);
		dLicense.setLicFile(licFile);

		dao.update(dLicense);

		return dLicense;
	}

	public List<DlicenseVO> getAll() {
		return dao.getAll();
	}

	public List<DlicenseVO> findByDno(int dno) {
		return dao.findByDno(dno);
	}

}
