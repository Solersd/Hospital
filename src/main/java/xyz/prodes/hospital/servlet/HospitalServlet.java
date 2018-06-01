/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.prodes.hospital.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import xyz.prodes.hospital.command.factory.CommandFactory;
import xyz.prodes.hospital.constants.HospitalConstants;
import xyz.prodes.hospital.manager.ConfigurationManager;
import xyz.prodes.hospital.manager.ConfigurationManager.ConfigEnum;
import xyz.prodes.hospital.command.Command;
import xyz.prodes.hospital.exception.HospitalException;
import xyz.prodes.hospital.exception.HospitalException.ErrorEnum;

/**
 *
 * @author АРТЁМ
 */
public class HospitalServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void configurationManager(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page = null;
        Command command = CommandFactory.getCommand(request);
        ConfigurationManager configurationManager = new ConfigurationManager();
        try {
            page = command.execute(request, response);
        } catch (Exception ex) {
            request.setAttribute(HospitalConstants.PARAM_NAME_EXCEPTION, ex);           
            page = configurationManager.getProperty(ConfigEnum.ERROR_PAGE_PATH);           
        }        
        if (page == null) {
            request.setAttribute(HospitalConstants.PARAM_NAME_EXCEPTION, new HospitalException(ErrorEnum.PAGE_NOT_FOUND));
            page = configurationManager.getProperty(ConfigEnum.ERROR_PAGE_PATH);
        }        
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        configurationManager(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        configurationManager(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
