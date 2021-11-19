package ru.abtank.java10.less1;

public class Person {
    private String firstName;
    private String lastName;
    private String middleName;
    private String country;
    private String address;
    private String phone;
    private int age;
    private String gender;

    public static PersonBuilder builder (){
        return new PersonBuilder();
    }


    public static class PersonBuilder {
        private final Person person;


        public PersonBuilder() {
            this.person = new Person();
        }

        public Person build (){
            return person;
        }

        public PersonBuilder firstName(String str){
            person.firstName = str;
            return this;
        }
        public PersonBuilder lastName(String str){
            person.lastName = str;
            return this;
        }
        public PersonBuilder middleName(String str){
            person.middleName = str;
            return this;
        }
        public PersonBuilder country(String str){
            person.country = str;
            return this;
        }
        public PersonBuilder address(String str){
            person.address = str;
            return this;
        }
        public PersonBuilder phone(String str){
            person.phone = str;
            return this;
        }
        public PersonBuilder age(int age){
            person.age = age;
            return this;
        }
        public PersonBuilder gender(String str){
            person.gender = str;
            return this;
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", country='" + country + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}
