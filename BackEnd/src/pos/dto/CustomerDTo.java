package pos.dto;

public class CustomerDTo {

    String id;
    String name;
    String address;

    public CustomerDTo(String id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public CustomerDTo() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
