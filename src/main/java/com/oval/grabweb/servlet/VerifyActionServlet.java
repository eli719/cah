package com.oval.grabweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VerifyActionServlet extends HttpServlet {

	private static final long serialVersionUID = -2311764406866233122L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("--------------Post-----------");
//		System.out.println(req.getRequestURI());
//		System.out.println(req.getPathInfo());
//		System.out.println(req.getServletPath());
		String orgCode = req.getParameter("orgCode");
		if("/addTask".equals(req.getPathInfo())) {
			synchronized (orgCode) {
				PageActionManager.currentTask.add(orgCode);
				PrintWriter pw = resp.getWriter();
				pw.print(PageActionManager.currentTask);
				pw.flush();
				pw.close();
				System.out.println("-------------add------------");
			}
		}else if("/execTask".equals(req.getPathInfo())) {
			synchronized (this) {
				try {
					PageActionManager.execTask();
					System.out.println("-------------exec------------");

				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					PageActionManager.clear();
					System.out.println("-------------clear------------");
				}
			}
		}
	}
}
