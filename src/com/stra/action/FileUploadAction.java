package com.stra.action;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class FileUploadAction extends ActionSupport {

	private static final long serialVersionUID = 5635292791366012647L;
	private File uploadFile;// 得到上传的文件,此属性对应于表单中文件字段的名称  
	//下面的这两个属性的命名必须遵守上定的规则，即为"表单中文件字段的名称" + "相应的后缀"
	private String uploadFileContentType;// 得到上传的文件的数据类型
	private String uploadFileFileName;// 得到上传的文件的名称
	public File getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}
	public String getUploadFileContentType() {
		return uploadFileContentType;
	}
	public void setUploadFileContentType(String uploadFileContentType) {
		this.uploadFileContentType = uploadFileContentType;
	}
	public String getUploadFileFileName() {
		return uploadFileFileName;
	}
	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}
	public String execute() throws Exception { 
		String realPath = ServletActionContext.getServletContext().getRealPath("/files");  
        System.out.println(realPath);  
        if(uploadFile !=null ){  
            File destFile = new File(new File(realPath), uploadFileFileName);//根据 parent 抽象路径名和 child 路径名字符串创建一个新 File 实例。  
            if(!destFile.getParentFile().exists())//判断路径"/images"是否存在  
                destFile.getParentFile().mkdirs();//如果不存在，则创建此路径  
            //将文件保存到硬盘上，因为action运行结束后，临时文件就会自动被删除  
            FileUtils.copyFile(uploadFile, destFile);  
            ActionContext.getContext().put("message", "文件上传成功！");  
        }  
        return null;  
	}
	
	
	
	

}
