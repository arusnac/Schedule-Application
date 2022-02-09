package Controllers;

import com.sun.media.jfxmedia.events.NewFrameEvent;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Appointment;
import model.Contact;
import model.User;
import org.w3c.dom.Text;
import util.AppointmentMgmt;
import util.ContactMgmt;
import util.CustomerMgmt;
import util.UserMgmt;


import java.net.URL;
import java.sql.SQLException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ResourceBundle;

public class AddAppointmentController implements Initializable {
    @FXML
    public TextField appTitle;
    @FXML
    public TextField appDescription;
    @FXML
    public TextField appType;
    @FXML
    public TextField appLocation;
    @FXML
    public TextField appStartTime;
    @FXML
    public TextField appEndTime;
    @FXML
    public ComboBox<String> contact;
    @FXML
    public DatePicker datePicker;
    @FXML
    public Button save;
    @FXML
    public TextField customer;
    @FXML
    public Button cancel;
    public ObservableList<String> contactNames;
    private final DateTimeFormatter timePat = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
    int uid;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        contact.setItems(ContactMgmt.getContactNames());



        uid = User.getUserID(User.getCurrentUser());

        System.out.println(uid);

        cancel.setOnAction(e->{
            Stage stage = (Stage) cancel.getScene().getWindow();
            stage.close();
        });

        save.setOnAction(e->{
            int id;
            if(AppointmentMgmt.getAppointments().isEmpty()){
                id = 0;
            }
            else {
                //Appointment appointment = AppointmentMgmt.getAppointments().get(-1);
                id = AppointmentMgmt.getAppointments().get(AppointmentMgmt.getAppointments().size() - 1).getId() + 1;
                //id = appointment.getId();
            }
            String title = appTitle.getText();
            String description = appDescription.getText();
            String type = appType.getText();
            String location = appLocation.getText();
            String start = appStartTime.getText();
            String end = appEndTime.getText();
            LocalDate date = datePicker.getValue();
            String contactName = contact.getValue();
            String customerName = customer.getText();

            int contactID = ContactMgmt.getContactID(contactName);
            int customerID = CustomerMgmt.getCustomerID(customerName);


            LocalDateTime startLT = LocalDateTime.of(date, LocalTime.parse(start));

            ZonedDateTime startTime = ZonedDateTime.of(date, LocalTime.parse(start), ZoneId.of("UTC"));
            ZonedDateTime endTime = ZonedDateTime.of(date, LocalTime.parse(end), ZoneId.of("UTC"));

            Appointment newAppointment = new Appointment();
            newAppointment.setLocation(location);
            newAppointment.setTitle(title);
            newAppointment.setDescription(description);
            newAppointment.setType(type);
            newAppointment.setID(id);
            newAppointment.setCid(customerID);
            newAppointment.setUid(uid);
            newAppointment.setContactID(contactID);
            newAppointment.setStartTime(startTime);
            newAppointment.setEndTime(endTime);


            try {
                AppointmentMgmt.addAppointment(newAppointment);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            Stage stage = (Stage) save.getScene().getWindow();
            stage.close();


        });




    }
}
