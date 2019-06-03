package cn.edu.cqcet.yd1702.team02.core.dao;

import org.apache.ibatis.annotations.Param;

import cn.edu.cqcet.yd1702.team02.core.po.User;

/**
 * @title: UserDao.java
 * @className: UserDao
 * @description: �û�DAO��ӿ�
 * @author: ����
 * @studentId: 2017180243
 * @Date: 2019��5��28�� ����10:14:59
 */
public interface UserDao {
	/**
	 * ͨ���˺ź������ѯ�û�
	 */
	
	public User findUser(@Param("usercode") String usercode, @Param("password") String password);
}
