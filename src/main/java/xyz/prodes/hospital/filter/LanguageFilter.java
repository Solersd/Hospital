package xyz.prodes.hospital.filter;

import java.io.IOException;
import java.util.Locale;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import xyz.prodes.hospital.constants.HospitalConstants;

/**
 *
 * The filer using for change current language
 * @author Filipp_Stankevich
 */
public class LanguageFilter implements Filter {
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        req.setCharacterEncoding("UTF-8");
        if (session.getAttribute(HospitalConstants.PARAM_NAME_CURRENT_LOCALE) == null) {
            session.setAttribute(HospitalConstants.PARAM_NAME_CURRENT_LOCALE, req.getLocale());
        } 
        String language = request.getParameter(HospitalConstants.PARAM_NAME_LANGUAGE);
        if (language != null) {
            Locale locale = new Locale(language);
            session.setAttribute(HospitalConstants.PARAM_NAME_CURRENT_LOCALE, locale);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
