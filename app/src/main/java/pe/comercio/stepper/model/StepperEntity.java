package pe.comercio.stepper.model;

/**
 * Created by Ricardo Bravo on 15/08/16.
 */

public class StepperEntity {

    private String name;
    private String lastname;
    private String address;
    private String card;

    public StepperEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }
}
