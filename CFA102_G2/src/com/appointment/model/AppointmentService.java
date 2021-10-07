package com.appointment.model;

import java.sql.Date;
import java.util.List;

public class AppointmentService {
	private AppointmentDAO_interface dao;
	
	public AppointmentService() {
		dao = new AppointmentJDBCDAO();
	}
	
	//新增諮詢時間
	public AppointmentVO addAppointment(Integer dno, Date rdate ,String rstate) {
		
		AppointmentVO appointmentVO = new AppointmentVO();
		
		appointmentVO.setDno(dno);
		appointmentVO.setRdate(rdate);
		appointmentVO.setRstate(rstate);
		dao.insert(appointmentVO);
		
		return appointmentVO;
	}
	
	//修改諮詢時間
	public AppointmentVO updateAppointment(Integer dno, Date rdate ,String rstate, Integer aptno) {
		AppointmentVO appointmentVO = new AppointmentVO();
		appointmentVO.setDno(dno);
		appointmentVO.setRdate(rdate);
		appointmentVO.setRstate(rstate);
		appointmentVO.setAptNo(aptno);
		dao.update(appointmentVO);
		return appointmentVO;
	}
	
	//用aptno取得單一諮詢時間
	public AppointmentVO getOneAppointment(Integer aptno) {
		return dao.findByPrimaryKey(aptno);
	}
	//15天的資料
	public void createDate() {
		dao.createdDate();
	}
	public List<AppointmentVO> getAll(){
		return dao.getAll();
	}
	
}
