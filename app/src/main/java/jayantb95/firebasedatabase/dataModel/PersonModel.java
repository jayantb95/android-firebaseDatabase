package jayantb95.firebasedatabase.dataModel;

/**
 * Created by Jayant on 31-01-2018.
 */

public class PersonModel {

    private String age;
    private String contact;
    private String name;

    public PersonModel() {
    }

    public PersonModel(String age, String contact, String name) {
        this.age = age;
        this.contact = contact;
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
