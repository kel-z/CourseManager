package model;

public interface Building {

    public void setName(String n);

    public String getName();

    public boolean tripAlarm();

    public boolean ringAlarm(int n);

    public int getAlarm();

}
