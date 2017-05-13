package so.go2.sharingthegym.data;

/**
 * Created by lusen on 2017/5/8.
 */

public class Person {
    public Person(String name, int id, int sn, String starttime, int time) {
        this.name = name;
        this.id = id;
        this.sn = sn;
        this.starttime = starttime;
        this.time = time;
    }

    private String name;
    private int id;
    private int sn;
    private int time;
    private String starttime;

    public void setName(String name){this.name = name;}
    public void setId(int id){this.id = id;}
    public void setSn(int sn){this.sn = sn;}
    public void setTime(int time){this.time = time;}
    public void setStarttime(String starttime){this.starttime = starttime;}

    public String getName(){return this.name;}
    public int getId(){return this.id;}
    public int getSn (){return this.sn;}
    public int getTime(){return this.time;}
    public String getStarttime(){return this.starttime;}

}
