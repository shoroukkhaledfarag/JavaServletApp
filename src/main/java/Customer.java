
public class Customer {
    private Long Id;
    private String email;
    private String name;
    private Integer age;

    Customer(String email,String name,Integer age){
        this.email = email;
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return Id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}
