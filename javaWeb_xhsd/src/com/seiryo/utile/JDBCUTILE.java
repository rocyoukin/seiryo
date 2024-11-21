package com.seiryo.utile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *@ClassName: JDBCUTILE
 *@Description: JDBC ������
 *@author carry
 *@date 2024-09-11 10:07:49
 */
public class JDBCUTILE {
	
	//���� MYSQL �� JDBC ����
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	//private static final String DRIVER = "oracle.jdbc.OracleDriver";
	
	//MYSQL ���ݿ�����·��
	private static final String URL = "jdbc:mysql://localhost:3306/xhsd?characterEncoding=UTF-8";
	//private static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	
	//MYSQL �˺�
	private static final String USER_NAME = "root";
	
	//MYSQL ����
	private static final String USER_PASS = "123123";
	
	//�������ݿ�
	private static Connection con = null;
	
	//���� PreparedStatement ����
	private static PreparedStatement prepar = null;
	
	//���� ResultSet ����
	private static ResultSet rst = null;
	
	/**
	 *@MethodName: INITDATABASE
	 *@Description: ��ʼ�����ݿ�
	 *@return void
	 *@author carry
	 *@date 2024-09-11 10:35:38
	 */
	private static void INITDATABASE() throws Exception{
		
		//1.�������ݿ�����
		Class.forName(DRIVER);
		
		//2.�������ݿ�
		con =  DriverManager.getConnection(URL,USER_NAME,USER_PASS);
		
	}
	
	/**
	 *@MethodName: prepareStatement
	 *@Description: ��װ prepareStatement
	 *@return void
	 *@author carry
	 *@date 2024-09-11 10:39:10
	 */
	private static void prepareStatement(String sql,Object ...objects) throws Exception{
		
		//3.ͨ�� prepareStatement ʵ�����ݿ�����
		prepar = con.prepareStatement(sql);
		
		//4.�����������
		for(int i = 0; i < objects.length; i++) {
			prepar.setObject(i + 1, objects[i]);
		}
		
	}
	
	/**
	 *@MethodName: JDBCUPDATA
	 *@Description: JDBC - ��ɾ��
	 *@return void
	 *@author carry
	 *@date 2024-09-11 10:10:27
	 *@object Object ...objects ����ɱ䳤�ȵ�����
	 */
	public static boolean JDBCUPDATA(String sql,Object ...objects) throws Exception{
		
		//���ó�ʼ�����ݿ�ķ���
		INITDATABASE();
		
		//��װ prepareStatement
		prepareStatement(sql,objects);
		
		//��ռλ��������֮����ִ��sql	
		boolean bo = prepar.executeUpdate() > 0?true:false;
		
		//�ر�ʹ����Դ
		THISCLOSEDATA(con,prepar,rst);
		
		return bo;
		
	}
	
	/**
	 *@MethodName: JDBCSELECT
	 *@Description: JDBC - ��ѯ
	 *@return void
	 *@author carry
	 *@date 2024-09-11 10:11:24
	 */
	public static ResultSet JDBCSELECT(String sql,Object ...objects) throws Exception{
		
		//���ó�ʼ�����ݿ�ķ���
		INITDATABASE();
		
		//��װ prepareStatement
		prepareStatement(sql,objects);
		
		//ִ��SQL��ͨ�� ResultSet ���շ���ֵ �����б���
		rst = prepar.executeQuery();
		
		return rst;
		
	}
	
	/**
	 *@MethodName: THISCLOSEDATA
	 *@Description: �رձ�����ʹ�õ�������Դ
	 *@return void
	 *@author carry
	 *@date 2024-09-11 10:43:02
	 */
	private static void THISCLOSEDATA(Connection con,PreparedStatement prepar,ResultSet rst) throws Exception{
		
		if(rst != null) {
			rst.close();
		}
		
		if(prepar != null) {
			prepar.close();
		}
		
		if(con != null) {
			con.close();
		}
		
	} 
	
	/**
	 *@MethodName: CLOSEDATA
	 *@Description: �ر��ⲿʹ�õ�������Դ
	 *@return void
	 *@author carry
	 *@date 2024-09-11 10:52:22
	 */
	public static void CLOSEDATA(Connection con,PreparedStatement prepar,ResultSet rst) throws Exception{
		
		if(rst != null) {
			rst.close();
		}
		
		if(prepar != null) {
			prepar.close();
		}
		
		if(con != null) {
			con.close();
		}
		
	}

}
