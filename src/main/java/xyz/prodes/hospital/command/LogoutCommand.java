/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.prodes.hospital.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import xyz.prodes.hospital.constants.HospitalConstants;
import xyz.prodes.hospital.manager.ConfigurationManager;

/**
 *
 * @author АРТЁМ
 */
public class LogoutCommand implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String page = null;
        HttpSession session = request.getSession();
        session.removeAttribute(HospitalConstants.PARAM_NAME_USER);
        session.removeAttribute(HospitalConstants.PARAM_NAME_ROLE);
        ConfigurationManager manager = new ConfigurationManager();
        page = manager.getProperty(ConfigurationManager.ConfigEnum.LOGIN_PAGE_PATH);
        return page;
    }
    
}
