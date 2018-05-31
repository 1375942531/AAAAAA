package com.aaa.actions;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.aaa.biz.IndaccountinfoBiz;
import com.aaa.biz.UtinaccountinfoBiz;
import com.aaa.entity.Indaccountinfo;
import com.aaa.entity.Indinfo;
import com.aaa.entity.Utinaccountinfo;
import com.alibaba.fastjson.JSON;
@ParentPackage("struts-default")
@Controller
public class IndaccounfoAction extends BaseAction<Indaccountinfo> {
	@Autowired
	private IndaccountinfoBiz indaccountinfoBiz; 

	private Indinfo indinfo;
	private List<Indinfo> list_indinfo;
	private List<Indaccountinfo> list_indaccountinfo;
	private Indaccountinfo indaccountinfo;
	private Integer utinaccountinfoID;
	
	//����Ա��
	@Action(value="saveIndaccountinfo", results = {@Result(name = "saveIndaccountinfo", location = "/BackJsp/LDL/success.jsp")})
	public String saveIndaccountinfo(){
		if(list_indinfo != null){
			for(int i=0;i<list_indinfo.size();i++){
				indaccountinfoBiz.saveIndaccountinfo(list_indinfo.get(i), list_indaccountinfo.get(i), utinaccountinfoID);
			}
		}else{
			indaccountinfoBiz.saveIndaccountinfo(indinfo, indaccountinfo, utinaccountinfoID);
		}
		return "saveIndaccountinfo";
	}
	//��ȡָ����˾Ա��
	@Action(value="getEmp", results = {@Result(name = "getEmp",  location = "/BackJsp/LDL/Emp.jsp")})
	public String getEmp(){
		List<Indaccountinfo> list_Indaccountinfo = indaccountinfoBiz.getEmp(utinaccountinfoID);
		getRequestMap().put("list_Indaccountinfo", list_Indaccountinfo);
		return "getEmp";
	}
	//�޸Ļ���
	@Action("updateRadices")
	public String updateRadices(){
		try {
			indaccountinfoBiz.updateRadices(indaccountinfo);
		} catch (Exception e) {
			System.out.println("�����޸��쳣��");
			e.printStackTrace();
		}
		return null;
	};
	//ģ����ѯ
	@Action(value="getFuzzyEmp")
	public String getFuzzy(){
		   try {
				List list_Indaccountinfo = indaccountinfoBiz.getFuzzy(utinaccountinfoID, indaccountinfo);
				String json_Indaccountinfo = JSON.toJSONString(list_Indaccountinfo);
				getOut().print(json_Indaccountinfo);
		} catch (Exception e) {
		    System.out.println("indaccountActionģ����ѯ�쳣��");
		}
		return null;
		}
	//��ȡһ��Ա��
		@Action(value="getEmpinfo", results = {@Result(name = "getEmpinfo",  location = "/BackJsp/LDL/employeeOpenImpl.jsp")})
		public String getEmpinfo(){
			Indaccountinfo indaccountinfoObject = indaccountinfoBiz.getOne(indaccountinfo.getIndAccountId());
			getRequestMap().put("indaccountinfoObject", indaccountinfoObject);
			return "getEmpinfo";
		}
		//�޸�
		@Action(value="updateIndaccountinfo", results = {@Result(name = "updateIndaccountinfo",  location = "/BackJsp/LDL/success.jsp")})
		public String updateIndaccountinfo(){
			for(int i=0;i<list_indinfo.size();i++){
				indinfo = list_indinfo.get(i);
			}
			indaccountinfoBiz.updateEmpInfo(indaccountinfo, indinfo, utinaccountinfoID);
			return "updateIndaccountinfo";
		}
		
	public Indinfo getIndinfo() {
		return indinfo;
	}

	public void setIndinfo(Indinfo indinfo) {
		this.indinfo = indinfo;
	}

	public Indaccountinfo getIndaccountinfo() {
		return indaccountinfo;
	}

	public void setIndaccountinfo(Indaccountinfo indaccountinfo) {
		this.indaccountinfo = indaccountinfo;
	}

	public Integer getUtinaccountinfoID() {
		return utinaccountinfoID;
	}

	public void setUtinaccountinfoID(Integer utinaccountinfoID) {
		this.utinaccountinfoID = utinaccountinfoID;
	}

	public List<Indinfo> getList_indinfo() {
		return list_indinfo;
	}

	public void setList_indinfo(List<Indinfo> list_indinfo) {
		this.list_indinfo = list_indinfo;
	}

	public List<Indaccountinfo> getList_indaccountinfo() {
		return list_indaccountinfo;
	}

	public void setList_indaccountinfo(List<Indaccountinfo> list_indaccountinfo) {
		this.list_indaccountinfo = list_indaccountinfo;
	}
	
	
}