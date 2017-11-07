package com.cn.bonc.controller;


import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class Action extends ActionSupport {

    private static final long serialVersionUID = 5093098907114773471L;

    @Override
    public String execute() throws Exception {
        String hello = "";
        ActionContext context = ActionContext.getContext();
        context.put("hello", hello);
        return "success";
    }

    public String Test() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        String path = request.getServletContext().getRealPath("/WEB-INF/resources/js/tableSpan.js");
        ActionContext context = ActionContext.getContext();
        context.put("path", path);
        return "success";
    }

}
