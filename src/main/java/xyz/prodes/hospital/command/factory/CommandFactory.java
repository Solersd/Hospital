/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.prodes.hospital.command.factory;

import javax.servlet.http.HttpServletRequest;
import xyz.prodes.hospital.command.doctor.EditPatientCommand;
import xyz.prodes.hospital.command.LoginCommand;
import xyz.prodes.hospital.command.LogoutCommand;
import xyz.prodes.hospital.command.doctor.NewTreatmentCommand;
import xyz.prodes.hospital.command.doctor.ShowNewTreatmentPageCommand;
import xyz.prodes.hospital.command.doctor.ShowDoctorMainPageCommand;
import xyz.prodes.hospital.constants.HospitalConstants;
import xyz.prodes.hospital.db.dao.DrugDAO;
import xyz.prodes.hospital.db.dao.PatientDAO;
import xyz.prodes.hospital.db.dao.PrescriptionDAO;
import xyz.prodes.hospital.db.dao.TreatmentDAO;
import xyz.prodes.hospital.command.Command;
import xyz.prodes.hospital.command.MainPage;
import xyz.prodes.hospital.command.doctor.EditTreatmentCommand;
import xyz.prodes.hospital.command.doctor.EndTreatmentCommand;
import xyz.prodes.hospital.command.doctor.FilterPatientCommand;
import xyz.prodes.hospital.command.doctor.ReopenTreatmentCommand;
import xyz.prodes.hospital.command.doctor.ShowEditPatientPageCommand;
import xyz.prodes.hospital.command.doctor.ShowTreatmentDetailsPageCommand;
import xyz.prodes.hospital.command.doctor.ShowTreatmentsPageCommand;
import xyz.prodes.hospital.command.patient.ShowPatientMainPageCommand;
import xyz.prodes.hospital.command.patient.ShowTreatmentDetailsForPatientCommand;

/**
 *
 * @author АРТЁМ
 */
public class CommandFactory {
    
    public enum CommandEnum {
        LOGIN, LOGOUT,
        VIEW_DOCTOR_MAIN_PAGE, SHOW_NEW_TREATMENT_PAGE, PRESCRIBE_TREATMENT, EDIT_PATIENT, FILTER_PATIENT, SHOW_TREATMENTS, SHOW_NEW_TREATMENT,
        SHOW_TREATMENT_DETAILS, EDIT_TREATMENT, END_TREATMENT, SHOW_EDIT_PATIENT_PAGE, REOPEN_TREATMENT,
        SHOW_PATIENT_MAIN_PAGE, SHOW_TREATMENT_DETAILS_FOR_PATIENT;
    }

    public static Command getCommand(HttpServletRequest request) {

        String action = request.getParameter(HospitalConstants.PARAM_NAME_COMMAND);
        if (action == null) {
            return new LoginCommand();
        }
        action = action.replace("-", "_");
        switch (CommandEnum.valueOf(action.toUpperCase())) {
            case LOGIN:
                return new LoginCommand();
            case VIEW_DOCTOR_MAIN_PAGE:
                return new ShowDoctorMainPageCommand(new PatientDAO());
            case EDIT_PATIENT:
                return new EditPatientCommand(new PatientDAO());
            case SHOW_EDIT_PATIENT_PAGE:
                return new ShowEditPatientPageCommand(new PatientDAO());
            case EDIT_TREATMENT:
                return new EditTreatmentCommand(new TreatmentDAO(), new PrescriptionDAO(), new DrugDAO());
            case END_TREATMENT:
                return new EndTreatmentCommand(new TreatmentDAO(), new PrescriptionDAO(), new DrugDAO());
            case REOPEN_TREATMENT:
                return new ReopenTreatmentCommand(new TreatmentDAO(), new PrescriptionDAO(), new DrugDAO());
            case SHOW_TREATMENTS:
                return new ShowTreatmentsPageCommand(new TreatmentDAO());
            case SHOW_NEW_TREATMENT:
                return new ShowNewTreatmentPageCommand(new DrugDAO());
            case SHOW_TREATMENT_DETAILS:
                return new ShowTreatmentDetailsPageCommand(new TreatmentDAO(), new PrescriptionDAO(), new DrugDAO());
            case PRESCRIBE_TREATMENT:
                return new NewTreatmentCommand(new TreatmentDAO(), new PrescriptionDAO());
            case FILTER_PATIENT:
                return new FilterPatientCommand(new PatientDAO());
            case SHOW_PATIENT_MAIN_PAGE:
                return new ShowPatientMainPageCommand(new TreatmentDAO());
            case SHOW_TREATMENT_DETAILS_FOR_PATIENT:
                return new ShowTreatmentDetailsForPatientCommand(new TreatmentDAO(), new PrescriptionDAO(), new DrugDAO());
            case LOGOUT:
                return new LogoutCommand();
            default:
                return new MainPage();
        }
    }    
}
