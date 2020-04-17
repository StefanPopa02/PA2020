import com.github.javafaker.Faker;

public class FakeGenerator {
    private Faker faker = new Faker();
    public String getFullName() {
        return faker.name().fullName();
    }
    public String getName(){
        return faker.name().name();
    }
    public String getLastName(){
        return faker.name().lastName();
    }
    public String getCountry(){
        return faker.country().name();
    }
}
