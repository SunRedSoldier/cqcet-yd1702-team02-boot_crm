package cn.edu.cqcet.yd1702.team02.core.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.edu.cqcet.yd1702.team02.core.po.User;
import cn.edu.cqcet.yd1702.team02.core.service.UserService;

/**
 * @title: UserController.java
 * @className: UserController
 * @description: �û���������
 * @author: ����
 * @studentId: 2017180243
 * @Date: 2019��5��28�� ����10:04:15
 */

@Controller
public class UserController {
	
	// ����ע��
		@Autowired
		private UserService userService;
		/**
		 * �û���¼
		 */
		@RequestMapping(value = "/login.action", method = RequestMethod.POST)
		public String login(String usercode,String password, Model model, HttpSession session) {
			// ͨ���˺ź������ѯ�û�
			User user = userService.findUser(usercode, password);
			if(user != null){		
				// ���û�������ӵ�Session
				session.setAttribute("USER_SESSION", user);
				// ��ת����ҳ��
				return "customer";
//				return "redirect:customer/list.action";
			}
			model.addAttribute("msg", "�˺Ż�����������������룡");
	         // ���ص���¼ҳ��
			return "login";
		}
		
		/**
		 * ģ������������ת���ͻ�����ҳ��ķ���
		 */
		@RequestMapping(value = "/toCustomer.action")
		public String toCustomer() {
		    return "customer";
		}
		
		/**
		 * �˳���¼
		 */
		@RequestMapping(value = "/logout.action")
		public String logout(HttpSession session) {
		    // ���Session
		    session.invalidate();
		    // �ض��򵽵�¼ҳ�����ת����
		    return "redirect:login.action";
		}
		/**
		 * ���û���½ҳ����ת
		 */
		@RequestMapping(value = "/login.action", method = RequestMethod.GET)
		public String toLogin() {
		    return "login";
		}


}
