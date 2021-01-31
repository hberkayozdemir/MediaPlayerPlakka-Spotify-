package Model.BuilderPattern;

public class User
{
    /*
private String username;
private String password;

// simdi 2 adet field için 3 tane constructora ihitiyaç va

// peki eğer 20 field olsaydı


    public User() {

    }

    public User(String username) {
        this.username = username;
    }

    public User (String username, String password) {
        this.username = username;
        this.password = password;
    }
}

 */
    private String phone;
    private String name_and_surname;
    private String country;
    private String id;
    private String password;


    public static class Builder
    {
        private String phone;
        private String name_and_surname;
        private String country;
        private String id;
        private String password;


        public Builder() {
        }// bos

        public Builder(String phone, String country) {
            this.phone = phone;
            this.country = country;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Builder name_and_surname(String name_and_surname) {
            this.name_and_surname = name_and_surname;
            return this;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder password(String password) {

            this.password = password;
            return this;
        }

        public User build() {
            return new User(phone, country, name_and_surname, id, password);
        }



    }

    public User(String phone, String name_and_surname, String country, String id, String password) {
        this.phone = phone;
        this.name_and_surname = name_and_surname;
        this.country = country;
        this.id = id;
        this.password = password;

    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName_and_surname() {
        return name_and_surname;
    }

    public void setName_and_surname(String name_and_surname) {
        this.name_and_surname = name_and_surname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}



