package Model;

public class StudentData {

    private String sid;
    private String Name;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public String toString() {
        return "StudentData{" +
                "sid='" + sid + '\'' +
                ", Name='" + Name + '\'' +
                '}';
    }


}
