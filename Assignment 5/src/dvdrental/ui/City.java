package dvdrental.ui;

public class City {
    private int id;
    private String name;
    private int countryId;

    public City(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public City(int id, String name, int countryId) {
        this.id = id;
        this.name = name;
        this.countryId = countryId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCountryId() { return countryId; }

    @Override
    public String toString() {
        return this.name;
    }
}
