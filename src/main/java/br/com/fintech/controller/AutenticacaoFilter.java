package br.com.fintech.controller;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class AutenticacaoFilter extends HttpFilter implements Filter {
       
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		String uri = req.getRequestURI();
		
		String servletPath = req.getServletPath();
		//System.out.println("ServletPath:  " + servletPath);
		//System.out.println("contextPath:  " + req.getContextPath());
		//System.out.println("sessionID :  " + session.getId());
		//System.out.println("User  " + session.getAttribute("user"));				
		
		if(session.getAttribute("user") == null && !servletPath.equals("/login.jsp") && !uri.contains("resources")){					
			String task = req.getParameter("task");
					
			if (task != null && task.equals("login") ^ task.equals("cadastrar")) {
				chain.doFilter(req, resp);
			} else {
				resp.sendRedirect(req.getContextPath());
			}
			
		} else {
			chain.doFilter(req, resp);
		}
		
		
		System.out.println("-----------------------------------------------------------");
	}

}
