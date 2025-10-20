package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Users;

@WebFilter("/*")
public class AuthFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        String uri = req.getRequestURI();

       
        if (uri.endsWith("login.jsp") 
        	    || uri.endsWith("/login") 
        	    || uri.contains("/css") 
        	    || uri.contains("/js") 
        	    || uri.contains("/images") 
        	    || uri.contains("/assets")) {
        	    chain.doFilter(request, response);
        	    return;
        	}
        
        Users user = (session != null) ? (Users) session.getAttribute("user") : null;
        
        if (user == null) {
            res.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        if (uri.contains("/ADMIN/")) {
            if (!"ADMIN".equals(user.getRolesid().getName())) {
                res.sendError(HttpServletResponse.SC_FORBIDDEN, "Bạn không có quyền truy cập trang admin");
                return;
            }
        } else if (uri.contains("/LEADER/")) {
            if (!"LEADER".equals(user.getRolesid().getName()) && !"admin".equals(user.getRolesid().getName())) {
                res.sendError(HttpServletResponse.SC_FORBIDDEN, "Bạn không có quyền truy cập trang leader");
                return;
            }
        } else if (uri.contains("/MEMBER/")) {
            if (!"MEMBER".equals(user.getRolesid().getName()) &&
                !"LEADER".equals(user.getRolesid().getName()) &&
                !"ADMIN".equals(user.getRolesid().getName())) {
                res.sendError(HttpServletResponse.SC_FORBIDDEN, "Bạn không có quyền truy cập trang member");
                return;
            }
        }

        chain.doFilter(request, response);
	}
}
