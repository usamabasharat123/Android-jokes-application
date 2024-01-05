package com.example.jokes;

public class Joke {
    private String setup;
    private String delivery;

    public Joke(String setup,String delivery){
        this.setup=setup;
        this.delivery=delivery;
    }

    public void setSetup(String setup1){
        setup = setup1;
    }

    public String getSetup() {
        return setup;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getDelivery() {
        return delivery;
    }
}
