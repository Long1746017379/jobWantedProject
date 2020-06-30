package com.rj.bd.manager.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.net.URLEncoder;
import javax.mail.MessagingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rj.bd.user.entity.User;
import com.rj.bd.utils.EmailUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rj.bd.manager.entity.Manager;
import com.rj.bd.manager.service.IManagerService;

@Controller
@RequestMapping("/manager")
public class ManagerController {

	@Autowired
	private IManagerService managerServiceImpl;
	
	
	@RequestMapping(value="/query")
	public String query(HttpServletRequest request){
		System.err.println("ManagerController--->query");
		
		List<Manager> list = managerServiceImpl.queryAll();
		
		request.setAttribute("list", list);

		return "manager/list";
	}

	@RequestMapping(value="/login")
	public String login(Manager m, HttpServletRequest request){
		System.err.println("ManagerController--->login");
		System.err.println("manager--->"+m);

		Map managerMap =  managerServiceImpl.queryManager(m);
		System.err.println("managerMap--->"+managerMap);

		if (managerMap != null){
			List<Manager> list = managerServiceImpl.queryAll();
			request.setAttribute("list", list);
			return "manager/list";
		}else{
			System.err.println("该管理员不存在！！！");
			return "redirect:/jsps/manager/login.jsp";
		}
	}

	@RequestMapping(value="/register")
	public String register(Manager m){
		System.err.println("ManagerController--->register");
		System.err.println("manager--->"+m);

		managerServiceImpl.save(m);

		return "manager/login";
	}

	@RequestMapping("/addPage")
	public String addPage(){
		System.err.println("ManagerController--->addPage");

		return "manager/register";
	}

	@RequestMapping(value="/delete")
	public String delete(String id){
		System.err.println("ManagerController--->delete");
		System.err.println("要修改的id为："+id);

		managerServiceImpl.delete(id);
		return "redirect:/manager/query.action";
	}

	@RequestMapping(value = "/editPage")
	public String editPage(String id, HttpServletRequest request){
		System.err.println("ManagerController--->editPage");
		System.err.println("要修改的id为："+id);

		Manager managerMap = managerServiceImpl.queryById(id);
		System.err.println("要修改的数据为："+managerMap);
		request.setAttribute("managerMap", managerMap);

		return "manager/edit";
	}

	/**
	 * @desc 修改管理员
	 * @param m
	 * @return
	 */
	@RequestMapping(value="/edit")
	public String edit(Manager m){
		System.err.println("ManagerController--->edit");
		System.err.println("要修改的数据为："+m);
		managerServiceImpl.edit(m);
		return "redirect:/manager/query.action";
	}


	/**
	 * @desc 用户管理
	 * @return
	 */
	@RequestMapping(value = "/userManager")
	public String userManager(HttpServletRequest request){
		System.err.println("ManagerController--->userManager");

		List<User> list = managerServiceImpl.queryUserAll();

		System.err.println("userList--->"+list);
		request.setAttribute("list", list);

		//去查询判断黑名单中的数据
		List blackList = managerServiceImpl.queryUserBlackList();
		System.err.println("黑名单中数据为："+blackList);
		request.setAttribute("blackList", blackList);

		return "manager/userManager";
	}

	/**
	 * @desc 黑名单管理
	 * @return
	 */
	@RequestMapping(value = "/blacklist")
	public String blacklist(HttpServletRequest request){
		System.err.println("ManagerController--->blacklist");
		List<User> list = managerServiceImpl.queryUserBlackList();
		System.err.println("userBlackList--->"+list);
		request.setAttribute("list", list);

		return "manager/userBlackList";
	}


	/**
	 * @desc 移入黑名单
	 * @return
	 */
	@RequestMapping(value = "/addBlackList")
	public String addBlackList(String id){
		System.err.println("移入黑名单----------->");
		System.err.println("移入黑名单的id----------->"+id);
		managerServiceImpl.addBlackList(id);

		return "redirect:/manager/blacklist.action";
	}

	/**
	 * @desc 移出黑名单，就是将黑名单中的数据删除
	 * @return
	 */
	@RequestMapping(value = "/outBlackList")
	public String outBlackList(String id){
		System.err.println("移出黑名单----------->");
		System.err.println("移出黑名单的id----------->"+id);
		managerServiceImpl.outBlackList(id);

		return "redirect:/manager/blacklist.action";
	}

	/**
	 * @desc 管理员审核，是否同意移出黑名单
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/agree")
	public String agree(String id) throws ParseException, UnsupportedEncodingException, MessagingException {
		System.err.println("审核同意----------->");
		System.err.println("审合同意--->移出黑名单的id----------->"+id);

		managerServiceImpl.outBlackList(id);

		//查询出要拉出黑名单的用户数据信息
		Map userMap = managerServiceImpl.queryByIdToUser(id);
		String email = (String) userMap.get("uemail");
		String userName = (String) userMap.get("uname");
		System.err.println("userEmail--->"+email);

		//发送邮件通知
		String content = "祝贺您，"+userName+"用户您已被我们管理员拉出黑名单列表";
		EmailUtils.createMimeMessage(email,"审核通过",content);

		return "redirect:/manager/blacklist.action";
	}

	@RequestMapping(value = "/reject")
	public String reject(String id) throws ParseException, UnsupportedEncodingException, MessagingException {
		System.err.println("驳回----------->");
		System.err.println("驳回id----------->"+id);

		//查询出要拉出黑名单的用户数据信息
		Map map = managerServiceImpl.queryByIdToUser(id);
		String email = (String) map.get("uemail");
		String userName = (String) map.get("uname");

		//发送邮件通知
		String content = "非常抱歉，"+userName+"用户您的申诉已被我们管理员驳回";
		EmailUtils.createMimeMessage(email,"驳回通知",content);

		return "redirect:/manager/blacklist.action";
	}


	/**
	 * @desc 导出黑名单数据到excel
	 * @return
	 */
	@RequestMapping(value = "/exportBlackListToExcel")
	public String exportBlackListToExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.err.println("导出黑名单数据到excel");

		List<User> users = managerServiceImpl.queryUserBlackList();
		System.err.println("黑名单中数据为："+users);

		exportExcel(users, request, response);

		return null;
	}

	private void exportExcel(List<User> list, HttpServletRequest request, HttpServletResponse response) throws IOException {
		//设置Http响应头
		this.settings(response);

		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet("黑名单数据信息");
		// 产生表格标题行（创建表头）
		HSSFRow oneRow = sheet.createRow(0);
		String[] tableTop = { "用户编号", "用户姓名", "用户账号", "用户密码", "用户邮箱" };

		for(int i =0; i < tableTop.length; i++) {
			oneRow.createCell(i).setCellValue(tableTop[i]);
		}

		//循环将student表中的数据写入到表格中（从第二行开始写入数据）
		for(int i = 0; i < list.size();i++) {
			//创建每一行
			HSSFRow rowbody = sheet.createRow(i+1);

			//获取到每一项数据
			User user = (User) list.get(i);

			HSSFCell cell_body0=rowbody.createCell(0);
			cell_body0.setCellValue(user.getUid());

			HSSFCell cell_body1=rowbody.createCell(1);
			cell_body1.setCellValue(user.getUname());

			HSSFCell cell_body2=rowbody.createCell(2);
			cell_body2.setCellValue(user.getUaccount());

			HSSFCell cell_body3=rowbody.createCell(3);
			cell_body3.setCellValue(user.getUpassword());

			HSSFCell cell_body4=rowbody.createCell(4);
			cell_body4.setCellValue(user.getUemail());
		}

		ServletOutputStream out = response.getOutputStream();
		workbook.write(out);
		out.close();

	}

	/**
	 * @desc 设置Http响应头
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	public void settings(HttpServletResponse response) throws UnsupportedEncodingException {
		//0.设置
		String fileName = "黑名单信息.xls";
		fileName=URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Connection", "close");  //设置http头的短连接
		response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName);  // 将此次需要下载的excel文件以附件形式展示出来
	}

}
