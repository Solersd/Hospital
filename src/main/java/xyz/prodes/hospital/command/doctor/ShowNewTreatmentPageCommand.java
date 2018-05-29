/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.prodes.hospital.command.doctor;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import xyz.prodes.hospital.command.Command;
import xyz.prodes.hospital.constants.HospitalConstants;
import xyz.prodes.hospital.db.dao.DrugDAO;
import xyz.prodes.hospital.entity.Drug;
import xyz.prodes.hospital.manager.ConfigurationManager;

/**
 *
 * @author АРТЁМ
 */
public class ShowNewTreatmentPageCommand implements Command{
    private DrugDAO drugDao;

    public ShowNewTreatmentPageCommand(DrugDAO drugDao){
        this.drugDao = drugDao;
    }
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ConfigurationManager manager = new ConfigurationManager();
        String page = manager.getProperty(ConfigurationManager.ConfigEnum.SHOW_NEW_TREATMENT_PAGE);
        List<Drug> drugs = drugDao.findAll();
        String patientId = request.getParameter(HospitalConstants.PARAM_NAME_PATIENT_ID);
        request.setAttribute(HospitalConstants.PARAM_NAME_PATIENT_ID, patientId);
        request.setAttribute(HospitalConstants.PARAM_NAME_DRUGS, drugs);
        return page;
    }
}
