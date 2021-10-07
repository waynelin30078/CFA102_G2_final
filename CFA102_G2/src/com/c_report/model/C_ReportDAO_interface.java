package com.c_report.model;

import java.util.List;

public interface C_ReportDAO_interface {
	void insert(C_ReportVO c_report);

	void updateContent(C_ReportVO c_report);

	void updateState(C_ReportVO c_report);

	void delete(C_ReportVO c_report);

	C_ReportVO getOne(Integer reportNo);

	List<C_ReportVO> getAll();

	List<C_ReportVO> getAll(Integer mNo);
}
