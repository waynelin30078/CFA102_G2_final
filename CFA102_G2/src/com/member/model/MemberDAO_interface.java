package com.member.model;


import java.util.*;
public interface MemberDAO_interface {
	public void insert(MemberVO memberVO);
	public void update(MemberVO memberVO);
	public MemberVO findByPrimaryKey(Integer mNo);
	public List<MemberVO> getAll();
	public MemberVO isUser(String mid ,String mpsw);  //�撣唾���Ⅳ�
	public MemberVO findByMid(String mid); //�撣唾�
	public void disable(MemberVO memberVO);  //��甈��
	public void resetPassword (MemberVO memberVO);  //��撖Ⅳ

	//========================撱箄悸�憓�===============
	public List<MemberVO> getDnoNotNull();
	//========================撱箄悸�憓���===============
	void reset_dieticianPKNull(MemberVO memberVO);
	void reset_dieticianPk(MemberVO memberVO);
	public void updateDieticianNo(Integer dNo, Integer mNo);
}
