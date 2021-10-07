package com.dietician.model;

import com.admin.model.AdminVO;
import com.member.model.MemberVO;
import java.util.List;

public interface DieticianDAO_interface {

	
	void insert(DieticianVO dietician);   //新增會員
	void update(DieticianVO dietician);	 //修改資料
	void update1(DieticianVO dietician);	 
	DieticianVO findByPrimaryKey(int dno);  //用dNo找一個營養師
	List<DieticianVO> getAll();   //找全部營養師
	List<DieticianVO> findByScore(double avgScore);   //找平均幾分以上的營養師
	List<DieticianVO> findBySubscribeFee(int minPrice, int maxPrice);   //找訂閱價格區間的營養師
	List<DieticianVO> findByDieticianState(int dstate); //依審核狀態找營養師
	void update_dstate(Integer dstate,Integer dno);
	public DieticianVO findByAccount(String daccount);
	void update_dpassword(String daccount, String dpassword);
	void updateTotalScore(Integer dno,Integer totalScore);
	
	
}
