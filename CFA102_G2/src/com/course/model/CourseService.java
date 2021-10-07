package com.course.model;
import java.util.List;

public class CourseService {
	private CourseDAO_interface dao;

	public CourseService() {
		dao = new CourseJDBCDAO();
	}

	public CourseVO addCourse(Integer dno, String cname, Integer cprice, String cintroduction, Integer ctype,
			byte[] cpic, String cdescription) {

		CourseVO courseVO = new CourseVO();
		courseVO.setDno(dno);
		courseVO.setCname(cname);
		courseVO.setCprice(cprice);
		courseVO.setCintroduction(cintroduction);
		courseVO.setCtype(ctype);
		courseVO.setCpic(cpic);
		courseVO.setCdescription(cdescription);
		dao.insert(courseVO);
		return courseVO;
	}

	public CourseVO updateCourse(Integer cno, String cname, Integer cprice, String cintroduction, byte[] cpic,
			String cdescription,Integer cstate) {
		CourseVO courseVO = new CourseVO();
		courseVO.setCno(cno);
		courseVO.setCname(cname);
		courseVO.setCprice(cprice);
		courseVO.setCintroduction(cintroduction);
		courseVO.setCpic(cpic);
		courseVO.setCdescription(cdescription);
		courseVO.setCstate(cstate);
		dao.update(courseVO);
		return getOneCourse(cno);
	}

	public void updateCourseState(Integer cState, Integer cno) {
		dao.cState(cState, cno);
	}

	public CourseVO getOneCourse(Integer cno) {
		return dao.getOneBy_cNO(cno);
	}

	public List<CourseVO> getAll() {
		return dao.getAll();
	}

	public List<CourseVO> getByDno(Integer dno) {
		return dao.getBy_dNo(dno);
	}

	public List<CourseVO> getByCname(String cName) {
		return dao.getBy_cName(cName);
	}

	public List<CourseVO> getByCtype(Integer cType) {
		return dao.getBy_cType(cType);
	}
	
	public void addQuantity(CourseVO courseVO) {
		dao.updateQuantity(courseVO);
	}
}
