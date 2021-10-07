package com.member.model;

import java.sql.Date;
import java.util.List;

public class MemberService {
	private MemberDAO_interface dao;
	
	public MemberService() {
		dao = new MemberJDBCDAO();
	}
	
	//�憓������
	public MemberVO addMember(String mname, String mid, String mpsw, String mmail, String mphone, Integer msex ) {
		MemberVO memberVO = new MemberVO();
		
		memberVO.setMname(mname);
		memberVO.setMid(mid);
		memberVO.setMpsw(mpsw);
		memberVO.setMmail(mmail);
		memberVO.setMphone(mphone);
		memberVO.setMsex(msex);
		dao.insert(memberVO);
		
		return memberVO;
		
	}
	//靽格��鞈��
	public MemberVO updateMember(String mname, String mpsw, String mmail, String mphone, byte[] mimg, Date mbday, Integer msex ,String mintro ,Integer mno) {
		MemberVO memberVO = new MemberVO();
		memberVO.setMname(mname);
		memberVO.setMpsw(mpsw);
		memberVO.setMmail(mmail);
		memberVO.setMphone(mphone);
		memberVO.setMimg(mimg);
		memberVO.setMbday(mbday);
		memberVO.setMsex(msex);
		memberVO.setMintro(mintro);
		memberVO.setMno(mno);
		dao.update(memberVO);
		return memberVO;
	}
	//�mno���銝���
	public MemberVO getOneMember(Integer mno) {
		return dao.findByPrimaryKey(mno);
	}
	//�������
	public List<MemberVO> getAll(){
		return dao.getAll();
	}
	
	//���id頝wd靘���
	public  MemberVO isUser(String mid,String mpsw ) {
		return dao.isUser(mid, mpsw);
	}
	//���id靘��
	public MemberVO getOneMemberByMid(String mid){
		return dao.findByMid(mid);
	}
	//閮剖��甈��
	public MemberVO disable(Integer mno,Integer mstate) {
		MemberVO memberVO = new MemberVO();
		memberVO.setMno(mno);
		memberVO.setMstate(mstate);
		dao.disable(memberVO);
		return memberVO;
	}
	public MemberVO resetPassword(String mpsw,Integer mno) {
		MemberVO memberVO = new MemberVO();
		memberVO.setMno(mno);
		memberVO.setMpsw(mpsw);
		dao.resetPassword(memberVO);
		return memberVO;
	}
	public MemberVO reset_dieticianPk(Integer mno,Integer dno) {
		MemberVO memberVO = new MemberVO();
		memberVO.setMno(mno);
		memberVO.setDno(dno);
		dao.reset_dieticianPk(memberVO);
		return memberVO;
	}
	public MemberVO reset_dieticianPKNull(Integer mno) {
		MemberVO memberVO = new MemberVO();
		memberVO.setMno(mno);
		dao.reset_dieticianPKNull(memberVO);
		return memberVO;
	}
	public void updateDieticianNo(Integer dNo,Integer mNo){
		  dao.updateDieticianNo(dNo, mNo);
		 }
	
		public List<MemberVO> getDnoNotNull(){
		return dao.getDnoNotNull();
	}	
}
