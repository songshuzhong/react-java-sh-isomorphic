package com.cn.bonc.common;

import java.io.FileReader;
import java.io.PrintWriter;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.PreResultListener;

public class EPMStrutsInterceptor extends AbstractInterceptor implements ServletResponseAware {

	/**
	 * author:yusongqi
	 */
	private static final long serialVersionUID = -6871026433315944223L;

	private HttpServletResponse responce;
	
	@Override
	public String intercept(final ActionInvocation paramActionInvocation) throws Exception {
        
		paramActionInvocation.addPreResultListener(new PreResultListener() {
			
			public void beforeResult(ActionInvocation invocation, String resultCode) {
				
				System.out.println("执行中...");
				Object result=invocation.getInvocationContext().get("path");
				System.out.println(result);
				if (result!=null) {
					String path=result.toString();
					ScriptEngineManager engineManager=new ScriptEngineManager();
					ScriptEngine engine=engineManager.getEngineByName("nashorn");
					HttpServletResponse response=(HttpServletResponse) paramActionInvocation.getInvocationContext().get("com.opensymphony.xwork2.dispatcher.HttpServletResponse");
					String result_js;
					try {
						PrintWriter out=response.getWriter();
						String html=engine.eval(new FileReader(path)).toString();
						out.write(html);
						out.close();
					} catch (Exception e) {
                        e.printStackTrace();
					}
				}
				
			}
		});
	    System.out.println("执行前...");
	    String invoke=paramActionInvocation.invoke();
	    System.out.println("执行后...");
		return null;
	}
	
	public void setServletResponse(HttpServletResponse response) {
        this.responce=response;
		
	}
	
}
