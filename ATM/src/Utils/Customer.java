package Utils;
/*
 * �ͻ���
 */

import java.util.Date;

public class Customer {
	private int id;//�˺ŵĿ���
	private String password;//�˻�����
	private double money;//�˺Ž��
	private Date date;//����ʱ��
	private String bank_name;
	public Customer(int id, String password,double money,String bank_name){
		this.id  = id;
		this.password  = password;
		this.money  = money;
		this.bank_name = bank_name;
		
		
	}
	public String toString(){
		return "����Ϊ��"+this.id+"����Ϊ"+this.password+"��"+this.money+"�������ƣ�"+this.bank_name;
		
	}
	

}
