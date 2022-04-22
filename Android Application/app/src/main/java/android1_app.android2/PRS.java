//--- This file is made for receiving values as input from user and send them into our Firebase DB ---//
package android1_app.android2;

public class PRS extends  android.app.Application{
    private String username;
    private String email;
    private String mobileno;
    private String pass;
    private String rollNo;
    private String spotNo;
    private String car;
    private String company;
    private String noPlate;
    private String docID;
    private Long status;

    private String chosenspot;

    public PRS(){
    }


    public String getDocID() {return docID;}
    public void setDocID(String docID) {this.docID = docID ;}

    public String getChosenspot() {return chosenspot;}
    public void setChosenspot(String chosenspot) {this.chosenspot = chosenspot;}

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getRollNo() {
        return rollNo;
    }
    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getSpotNo() {
        return spotNo;
    }
    public void setSpotNo(String spotNo) { this.spotNo = spotNo;  }

    public String getNoPlate() {
        return noPlate;
    }
    public void setNoPlate(String noPlate) { this.noPlate = noPlate;  }

    public Long getStatus() { return status ;}
    public void setStatus(Long status) {this.status = status ;}

    public String getCar() {
        return car;
    }
    public void setCar(String car) {
        this.car = car;
    }

    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company= company;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileno() {
        return mobileno;
    }
    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
}

