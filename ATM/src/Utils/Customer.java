package Utils;
/*
 * 客户类
 */

import java.util.Date;

public class Customer {
	private int id;//账号的卡号
	private String password;//账户密码
	private double money;//账号金额
	private Date date;//操作时间
	private String bank_name;
	public Customer(int id, String password,double money,String bank_name){
		this.id  = id;
		this.password  = password;
		this.money  = money;
		this.bank_name = bank_name;
		
		
	}
	public String toString(){
		return "卡号为："+this.id+"密码为"+this.password+"金额："+this.money+"银行名称："+this.bank_name;
		
	}
	

}
