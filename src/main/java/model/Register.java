package model;

public class Register {

    private float total;

    public Register() {
        this.total = 0;
    }
    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public void updateTotal(float amount) {
        total += amount;
    }
}
