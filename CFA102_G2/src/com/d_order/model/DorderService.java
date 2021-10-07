package com.d_order.model;

import java.sql.Timestamp;
import java.util.List;

public class DorderService {

	private DorderDAO_interface dao = new DorderDAO();

	public DorderVO addDorder(Integer dno, Integer mno, Timestamp subStart, Timestamp subEnd, Integer mthFee,
			 Integer autoSubs) {

		DorderVO dOrder = new DorderVO();

		dOrder.setDno(dno);
		dOrder.setMno(mno);
		dOrder.setSubStart(subStart);
		dOrder.setSubEnd(subEnd);
		dOrder.setMthFee(mthFee);
		dOrder.setAutoSubs(autoSubs);

		dao.insert(dOrder);

		return dOrder;
	}

	public DorderVO updateDorder(Integer dorderNo, Integer dno, Integer mno, Timestamp subStart, Timestamp subEnd,
			Integer mthFee, Integer autoSubs) {

		DorderVO dOrder = new DorderVO();

		dOrder.setDorderNo(dorderNo);
		dOrder.setDno(dno);
		dOrder.setMno(mno);
		dOrder.setSubStart(subStart);
		dOrder.setSubEnd(subEnd);
		dOrder.setMthFee(mthFee);
		dOrder.setAutoSubs(autoSubs);

		dao.update(dOrder);

		return dOrder;
	}

	public DorderVO findByPrimaryKey(int dorderNo) {
		return dao.findByPrimaryKey(dorderNo);
	}

	public List<DorderVO> getAll() {
		return dao.getAll();
	}

	public List<DorderVO> getActiveOrder() {
		return dao.getActiveOrder();
	}

	public List<DorderVO> getActiveOrderByDNo(int dno) {
		return dao.getActiveOrderByDNo(dno);
	}
	public void update_autoSubs( Integer dno, Integer mno, Integer autoSubs) {

		DorderVO dOrder = new DorderVO();
		dOrder.setDno(dno);
		dOrder.setMno(mno);
		dOrder.setAutoSubs(autoSubs);
		dao.update_autoSubs(dOrder);
	}
}
