package com.weisen.www.code.yjf.merchant.service.dto;

import java.util.List;

public class Rewrite_DishesbuyDTO {
	
		private Long userid;

	    private Long merchantid;

	    private String createdate;
	    
	    private Long orderid;
	    
	    private List<Rewrite_DishesMenuDTO> menu;
	    

		public Long getUserid() {
			return userid;
		}

		public void setUserid(Long userid) {
			this.userid = userid;
		}

		public Long getMerchantid() {
			return merchantid;
		}

		public void setMerchantid(Long merchantid) {
			this.merchantid = merchantid;
		}

		public String getCreatedate() {
			return createdate;
		}

		public void setCreatedate(String createdate) {
			this.createdate = createdate;
		}

		public List<Rewrite_DishesMenuDTO> getMenu() {
			return menu;
		}

		public void setMenu(List<Rewrite_DishesMenuDTO> menu) {
			this.menu = menu;
		}

		public Long getOrderid() {
			return orderid;
		}

		public void setOrderid(Long orderid) {
			this.orderid = orderid;
		}   
	
	 

}
