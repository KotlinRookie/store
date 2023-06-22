package com.cy.store.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cy.store.controller.ex.FileEmptyException;
import com.cy.store.controller.ex.FileSizeException;
import com.cy.store.controller.ex.FileStateException;
import com.cy.store.controller.ex.FileTypeException;
import com.cy.store.controller.ex.FileUploadIOException;
import com.cy.store.entity.User;
import com.cy.store.service.IUserService;
import com.cy.store.util.JsonResult;

@RestController
@RequestMapping("users")
public class UserController extends BaseController{
	@Autowired
	private IUserService userService;
	
	//@ResponseBody	//响应的结果以JSON格式反应给前端，需要添加 @ResponseBody 注解
	//但是因为每一个都写的话就很麻烦，于是就有了 @RestController 注解，相当于@Controller + @ResponseBody
/*	public JsonResult<Void> reg(User user) {
		//创建响应结果对象
		JsonResult<Void> result = new JsonResult<>();
		try {
			userService.reg(user);
			result.setState(200);
			result.setMessage("用户注册成功");			
		} catch (UsernameDuplicatedException e) {
			result.setState(4000);
			result.setMessage("用户名被占用");
		} catch (InsertException e) {
			result.setState(5000);
			result.setMessage("注册时产生未知的异常");
		}
		return result;
	}*/
	@RequestMapping("reg")
	public JsonResult<Void> reg(User user) {
		userService.reg(user);
		return new JsonResult<>(OK);
	}

	@RequestMapping("login")
	public JsonResult<User> login(String username, String password,HttpSession session){
		User data = userService.login(username, password);
		// 向session对象中完成数据的绑定，这里的session对象是全局的，在哪里都可以访问
		session.setAttribute("uid", data.getUid());
		session.setAttribute("username", data.getUsername());
		// 获取session中绑定的数据
		System.out.println(getuidFromSession(session));
		System.out.println(getUsernameFromSession(session));
		return new JsonResult<User>(OK,data);
	}
	
	@RequestMapping("change_password")
	public JsonResult<Void> changePassword(HttpSession session,
										   String oldPassword,
										   String newPassword){
		Integer uid = getuidFromSession(session);
		String username = getUsernameFromSession(session);
		userService.changePassword(uid, username, oldPassword, newPassword);
		return new JsonResult<>(OK);
	}
	
	@RequestMapping("get_by_uid")
	public JsonResult<User> getByUid(HttpSession session){
		User data = userService.getByUid(getuidFromSession(session));
		return new JsonResult<>(OK,data);
	}

	@RequestMapping("change_info")
	public JsonResult<Void> changeInfo
					(HttpSession session,User user){
		userService.changeInfo(getuidFromSession(session),
							   getUsernameFromSession(session),
							   user);
		return new JsonResult<>(OK);
	}
	
	/**
	 * MultipartFile接口时SpringMVC提供的一个接口，这个接口为我们包装了
	 * 获取文件类型的数据（任何类型的file都可以接收），SpringBoot他整合了
	 * SpringMVC，只需要在处理请求的方法参数列表上声明一个参数类型为MultipartFile
	 * 的参数，然后SpringBoot会自动将文件的数据赋值给这个参数
	 * 
	 * @RequestParam:表示请求中的参数,将请求中的参数注入请求处理方法的某个参数，
	 * 				如果名称不一致则可以使用@RequestParam注解进行标记和映射
	 * @param session
	 * @param file
	 * @param avatar
	 * @return
	 */
	// 设置上传文件的最大值
	public static final int AVATAR_MAX_SIZE = 10 * 1024 * 1024;
	// 限制上传文件的类型
	public static final List<String> AVATAR_TYPE= new ArrayList<>();
	// 集合的初始化
	static {
		AVATAR_TYPE.add("image/jpeg");//包含了jpg
		AVATAR_TYPE.add("image/png");
		AVATAR_TYPE.add("image/bmp");
		AVATAR_TYPE.add("image/gif");
	}
	
	@RequestMapping("change_avatar")
	public JsonResult<String> changeAvatar(HttpSession session,
										   @RequestParam("file") MultipartFile file){
		//判断文件是否为空
		if(file.isEmpty()) {
			throw new FileEmptyException("文件为空");
		}
		//判断文件大小
		if(file.getSize() > AVATAR_MAX_SIZE) {
			throw new FileSizeException("文件大小超过限制");
		}
		//判断文件类型
		String contentType = file.getContentType();
		//判断用户上传的文件是否在List里包含，如果不包含则抛出异常
		if(!AVATAR_TYPE.contains(contentType)) {
			throw new FileTypeException("文件类型不支持");
		}
		
		//上传的文件目录为: 项目根目录/upload/文件
		//获取项目目录结构 ※upload文件夹此时还不存在

		String parent = session. getServletContext().getRealPath("upload");


		
		//查看是否存在 upload 目录，如果没有则创建目录
		File dir = new File(parent);
		if(!dir.exists()) {	// 判断目录路径是否存在
			dir.mkdirs();	// 创建目录
		}
		System.out.println(parent);
		
		// 获取文件名称，使用UUID更改用户上传文件名
		String originalFilename = file.getOriginalFilename();
		System.out.println("originalFilename= " + originalFilename);
		// 例：test001.png	
		// 1-1， lastIndexOf 获取文件的最后一个.
		int index = originalFilename.lastIndexOf(".");
		// 1-2， substring 获取最后一个点后的所有数据，例： .png
		String suffix = originalFilename.substring(index);
		
		// 2-1， 前缀通过UUID随机生成(文件名一般是大写)
		// 例 SDJH2-8387D-JDS83-742DS-JDJ232.png
		String filename = 
				UUID.randomUUID().toString().toUpperCase()
				+ suffix;
		System.out.println("UUID= " + filename);
		
		// 在指定的目录下创建一个空文件
		// dir:创建的文件放在哪
		// filename:在这个目录下存放一个filename的文件
		File dest = new File(dir,filename);//此时filename文件是空文件
		
		// 将参数file中的数据写入到空文件中
		try {
			file.transferTo(dest);
		}catch (FileStateException e) {
			throw new FileStateException("文件状态异常");
		} catch (IOException e) {
			throw new FileUploadIOException("文件读写异常");
		}
		
		Integer uid = getuidFromSession(session);
		String username = getUsernameFromSession(session);
		//返回头像的路径（保存相对路径） /upload/xxx.png
		String avatar = "/upload/" + filename;
		userService.changeAvatar(uid, avatar, username);
		System.out.println(avatar);
		//返回用户头像的路径给前端页面，用于头像的展示使用
		return new JsonResult<>(OK,avatar);
	}
}