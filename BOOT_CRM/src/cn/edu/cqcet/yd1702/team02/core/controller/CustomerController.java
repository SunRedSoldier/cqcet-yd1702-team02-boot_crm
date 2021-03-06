package cn.edu.cqcet.yd1702.team02.core.controller;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.cqcet.yd1702.team02.common.utils.Page;
import cn.edu.cqcet.yd1702.team02.core.po.BaseDict;
import cn.edu.cqcet.yd1702.team02.core.po.Customer;
import cn.edu.cqcet.yd1702.team02.core.po.User;
import cn.edu.cqcet.yd1702.team02.core.service.BaseDictService;
import cn.edu.cqcet.yd1702.team02.core.service.CustomerService;

/**  
* 创建时间：2019年6月3日 下午11:30:31  
* 项目名称：BOOT_CRM  
* @author 周圣龙
* @version 1.0   
* @since JDK 1.8.0_191
* 文件名称：CustomerController.java  
* 类说明：  客户管理控制器
*/
@Controller
public class CustomerController {
	//依赖注入
	@Autowired
	private CustomerService customerService;
	@Autowired
	private BaseDictService baseDictService;
	//客户来源
	@Value("${customer.from.type}")
	private String FROM_TYPE;
	//客户所属行业
	@Value("${customer.industry.type}")
	private String INDUSTRY_TYPE;
	//客户
	@Value("${customer.level.type}")
	private String LEVEL_TYPE;
	/*
	 * 客户列表
	 */
	@RequestMapping(value = "/customer/list.action")
	public String list(@RequestParam(defaultValue = "1")Integer page,
					   @RequestParam(defaultValue = "10")Integer rows,
					   String custName, String custSource, String custIndustry,String custLevel, Model model) {
		//条件查询所有客户
		Page<Customer> customers = customerService.findCustomerList(page, rows, custName, custSource, custIndustry, custLevel);
		model.addAttribute("page", customers);
		//客户来源
		List<BaseDict> fromType = baseDictService.findBaseDictByTypeCode(FROM_TYPE);
		//客户所属行业
		List<BaseDict> industryType = baseDictService.findBaseDictByTypeCode(INDUSTRY_TYPE);
		//客户级别
		List<BaseDict> levelType = baseDictService.findBaseDictByTypeCode(LEVEL_TYPE);
		//添加参数
		model.addAttribute("fromType", fromType);
		model.addAttribute("industryType", industryType);
		model.addAttribute("levelType", levelType);
		model.addAttribute("custName", custName);
		model.addAttribute("custSource", custSource);
		model.addAttribute("custIndustry", custIndustry);
		model.addAttribute("custLevel", custLevel);
		return "customer";
	}
	/**
	*创建客户
	*
	*/
	/**
	 *  * @author 康峰
	 * 2017180208
	 * @time 2019年6月4日
	 * 
	 */
	@RequestMapping("/customer/create.action")
	@ResponseBody
		public String customerCreate(Customer customer,HttpSession session) {
			//获取session中的当前用户信息
			User user = (User) session.getAttribute("USER_SESSION");
			//将当前id存储在客户对象中		
			customer.setCust_create_id(user.getUser_id());
			//创建date对象
			Date date = new Date();
			Timestamp timeStamp = new Timestamp(date.getTime());
			customer.setCust_createtime(timeStamp);
			//执行Service层中的创建方法，返回的是受影响的行数
			int rows = customerService.createCustomer(customer);
			if (rows > 0) {
				return "OK";
			}else {
				return "FAIL";
			}
		}
	/**
	*通过id获取客户信息
	*/
	@RequestMapping("/customer/getCustomerById.action")
	@ResponseBody
	public Customer getCustomerById(Integer id){
	         Customer customer = customerService.getCustomerById(id);
	         return customer;
	}
	/**
	*更新客户
	*/
	@RequestMapping("/customer/update.action")
	@ResponseBody
	public String customerUpdate(Customer customer) {
	  int rows = customerService.updateCustomer(customer);
	      if (rows > 0) {
				return "OK";
			}else {
				return "FAIL";
			}

	}


	/**
	*删除客户
	*/
	@RequestMapping("/customer/delete.action")
	@ResponseBody
	public String customerdelete(Integer id) {
	  int rows = customerService.deleteCustomer(id);
	                          if (rows > 0) {
				return "OK";
			}else {
				return "FAIL";
			}

	}
}
