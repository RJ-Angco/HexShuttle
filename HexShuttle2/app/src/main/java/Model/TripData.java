package Model;

import com.example.miguel.hexshuttle.CsvReaderActivity;

import java.util.ArrayList;
import java.util.List;

public class TripData {

    private String locationFrom;
    private String locationTo;
    private String plateNum;
    private String driverName;
    private List<String> studOnTrip = new ArrayList<>();
    private List<String> csvDataStudName = new ArrayList<>();
    private List<String> csvDataSid = new ArrayList<>();

    //  CONSTRUCTOR
    public TripData(String from, String to, String plateNum, String driver, CsvReaderActivity csvObj) {

        this.locationFrom = from;
        this.locationTo = to;
        this.plateNum = plateNum;
        this.driverName = driver;

        csvDataStudName = csvObj.getNamesList();
        csvDataSid = csvObj.getSidList();

    }

    public TripData(String from, String to, String plateNum, String driver) {

        this.locationFrom = from;
        this.locationTo = to;
        this.plateNum = plateNum;
        this.driverName = driver;


    }

    public TripData() {



    }


    //  LOGIC METHODS
    public boolean addStudent(String sidInput){

        int pos = 0;
        boolean isStudent = false;

        for (String element : csvDataSid) {
            if (element.equals(sidInput)) {
                isStudent = true;
            }
            pos++;
        }
        if(isStudent){
            studOnTrip.add(csvDataStudName.get(pos));
        } else {
            System.out.print("Student" + sidInput + " is not found");
        }

        return isStudent;
    }


    public boolean deleteStudent(String sidInput) {

        int pos = 0;
        boolean isStudent = false;

        for (String element : csvDataSid) {
            if (element.equals(sidInput)) {
                isStudent = true;
                studOnTrip.remove(csvDataStudName.get(pos));
            }
            pos++;
        }

        return isStudent;
    }






    // GETTERS AND SETTERS
    public String getLocationFrom() {
        return locationFrom;
    }

    public void setLocationFrom(String locationFrom) {
        this.locationFrom = locationFrom;
    }

    public String getLocationTo() {
        return locationTo;
    }

    public void setLocationTo(String locationTo) {
        this.locationTo = locationTo;
    }

    public String getPlateNum() {
        return plateNum;
    }

    public void setPlateNum(String plateNum) {
        this.plateNum = plateNum;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public List<String> getStudOnTrip() {
        return studOnTrip;
    }

    public void setStudOnTrip(List<String> studOnTrip) {
        this.studOnTrip = studOnTrip;
    }




}
