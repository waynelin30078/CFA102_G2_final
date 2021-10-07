package com.p_order_detail.model;

import java.sql.Connection;
import java.util.List;

public class P_order_detailService {

	private P_order_detailDAO_interface dao;

	public P_order_detailService() {
		dao = new P_order_detailJDBCDAO();
	}

	public P_order_detailVO addP_order_detail(Integer porderNo, Integer pno, Integer porderQuantity, Integer pprice,
			Integer pratings) {

		P_order_detailVO p_order_detailVO = new P_order_detailVO();

		p_order_detailVO.setPorderNo(porderNo);
		p_order_detailVO.setPno(pno);
		p_order_detailVO.setPorderQuantity(porderQuantity);
		p_order_detailVO.setPprice(pprice);
		p_order_detailVO.setPratings(pratings);
		dao.insert(p_order_detailVO);

		return p_order_detailVO;
	}

	public P_order_detailVO updateP_order_detail(Integer porderNo, Integer pno, Integer porderQuantity, Integer pprice,
			Integer pratings) {

		P_order_detailVO p_order_detailVO = new P_order_detailVO();

		p_order_detailVO.setPorderNo(porderNo);
		p_order_detailVO.setPno(pno);
		p_order_detailVO.setPorderQuantity(porderQuantity);
		p_order_detailVO.setPprice(pprice);
		p_order_detailVO.setPratings(pratings);
		dao.update(p_order_detailVO);

		return p_order_detailVO;
	}

	public P_order_detailVO updateQuantity(Integer porderNo, Integer pno, Integer porderQuantity) {
		
		P_order_detailVO p_order_detailVO = new P_order_detailVO();
		
		p_order_detailVO.setPorderNo(porderNo);
		p_order_detailVO.setPno(pno);
		p_order_detailVO.setPorderQuantity(porderQuantity);
		dao.update(p_order_detailVO);

		return p_order_detailVO;
	}

	public P_order_detailVO updatePrice(Integer porderNo, Integer pno, Integer pprice) {
		
		P_order_detailVO p_order_detailVO = new P_order_detailVO();
		
		p_order_detailVO.setPorderNo(porderNo);
		p_order_detailVO.setPno(pno);
		p_order_detailVO.setPprice(pprice);
		dao.update(p_order_detailVO);

		return p_order_detailVO;
	}

	public P_order_detailVO updateRatings(Integer porderNo, Integer pno, Integer pratings) {
		
		P_order_detailVO p_order_detailVO = new P_order_detailVO();
		
		p_order_detailVO.setPorderNo(porderNo);
		p_order_detailVO.setPno(pno);
		p_order_detailVO.setPratings(pratings);
		dao.updateRatings(p_order_detailVO);

		return p_order_detailVO;
	}

	public void deleteP_order_detail(Integer pOrderNo, Integer pNo) {
		dao.delete(pOrderNo, pNo);
	}

	public P_order_detailVO getOneP_order_detail(Integer pOrderNo, Integer pNo) {
		return dao.findByPrimaryKey(pOrderNo, pNo);
	}

	public List<P_order_detailVO> getAll() {
		return dao.getAll();
	}

	public List<P_order_detailVO> getAll_ByOrderNo(Integer pOrderNo) {
		return dao.getAll_ByOrderNo(pOrderNo);
	}
	
	public void insert2(P_order_detailVO p_order_detailVO, Connection con) {
		dao.insert2(p_order_detailVO, con);
	}

}
