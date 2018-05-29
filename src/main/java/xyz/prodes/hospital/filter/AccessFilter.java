/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.prodes.hospital.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import xyz.prodes.hospital.constants.HospitalConstants;
import xyz.prodes.hospital.constants.Role;
import xyz.prodes.hospital.command.factory.CommandFactory.CommandEnum;
import xyz.prodes.hospital.constants.Role.UserRole;
import xyz.prodes.hospital.manager.ConfigurationManager;
import xyz.prodes.hospital.manager.ConfigurationManager.ConfigEnum;

/**
 *
 * @author АРТЁМ
 */
public class AccessFilter implements Filter{
    private final static List<CommandEnum> DOCTOR_ACCESS = new ArrayList<>();
    private final static List<CommandEnum> PATIENT_ACCESS = new ArrayList<>();
    static
    {
        DOCTOR_ACCESS.add(CommandEnum.LOGIN);
        DOCTOR_ACCESS.add(CommandEnum.LOGOUT);
        DOCTOR_ACCESS.add(CommandEnum.EDIT_PATIENT);
        DOCTOR_ACCESS.add(CommandEnum.SHOW_NEW_TREATMENT_PAGE);
        DOCTOR_ACCESS.add(CommandEnum.PRESCRIBE_TREATMENT);
        DOCTOR_ACCESS.add(CommandEnum.VIEW_DOCTOR_MAIN_PAGE);
        DOCTOR_ACCESS.add(CommandEnum.FILTER_PATIENT);
        DOCTOR_ACCESS.add(CommandEnum.SHOW_TREATMENTS);
        DOCTOR_ACCESS.add(CommandEnum.SHOW_NEW_TREATMENT);
        DOCTOR_ACCESS.add(CommandEnum.SHOW_TREATMENT_DETAILS);
        DOCTOR_ACCESS.add(CommandEnum.EDIT_TREATMENT);
        DOCTOR_ACCESS.add(CommandEnum.SHOW_EDIT_PATIENT_PAGE);
        DOCTOR_ACCESS.add(CommandEnum.END_TREATMENT);
        DOCTOR_ACCESS.add(CommandEnum.REOPEN_TREATMENT);
        
        DOCTOR_ACCESS.add(CommandEnum.LOGIN);
        PATIENT_ACCESS.add(CommandEnum.LOGOUT);
        PATIENT_ACCESS.add(CommandEnum.SHOW_PATIENT_MAIN_PAGE);
        PATIENT_ACCESS.add(CommandEnum.SHOW_TREATMENT_DETAILS_FOR_PATIENT);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {        
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;
        HttpSession session = req.getSession();
        ConfigurationManager configurationManager = new ConfigurationManager();
        String page = null;
        String path = req.getContextPath();
        String action = req.getParameter(HospitalConstants.PARAM_NAME_COMMAND);
        if (action == null) {
            return;
        }
        action = action.replace("-", "_");
        CommandEnum command = CommandEnum.valueOf(action.toUpperCase());
        boolean accessAllowed = false;
        UserRole role = (UserRole)session.getAttribute(HospitalConstants.PARAM_NAME_ROLE);
        if (role != null) {
            
            if (role == Role.UserRole.DOCTOR) {
                accessAllowed = DOCTOR_ACCESS.contains(command);
            } else if (role == Role.UserRole.PATIENT) {
                accessAllowed = PATIENT_ACCESS.contains(command);
            }
            if (accessAllowed) {
                chain.doFilter(request, response);
                return;
            } else {
                session.setAttribute(HospitalConstants.PARAM_NAME_EXCEPTION, "Access denied!");
                page = configurationManager.getProperty(ConfigEnum.ERROR_PAGE_PATH);
            }
        } else if (command == CommandEnum.LOGIN){
            chain.doFilter(request, response);
            return;
        } else {
            page = configurationManager.getProperty(ConfigEnum.LOGIN_PAGE_PATH);
        }
        res.sendRedirect(path + page);
    }

    @Override
    public void destroy() {
    }
}
