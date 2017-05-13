package so.go2.sharingthegym.data;

import java.util.Date;

/**
 * Created by lusen on 2017/5/8.
 */

public class Person {
    public Person(String name, int id, String thing, Date date, String address) {
        this.name = name;
        this.id = id;
        this.thing = thing;
        this.date = date;
        this.address = address;
    }

    private String name;
    private int id;
    private String thing;
    private Date date;
    private String address;

    public void setName(String name){this.name = name;}
    public void setId(int id){this.id = id;}
    public void setThing(String thing){this.thing = thing;}
    public void setDate(Date date){this.date = date;}

    public String getName(){return this.name;}
    public int getId(){return this.id;}
    public String getThing(){return this.thing;}
    public Date getDate(){return this.date;}
}
