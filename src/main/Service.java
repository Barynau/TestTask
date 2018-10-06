package main;

import java.util.Comparator;

public class Service {
    private boolean company;// Posh true ,Grotty false
    private Integer start;
    private Integer finish;


    public Service(boolean company, Integer start, Integer finish) {
        this.company = company;
        this.start = start;
        this.finish = finish;

    }

    public Service(String string) {
        String temp = new String("PG");
        if (string.charAt(0) == temp.charAt(0)) {
            company = true;
            start = ((Integer.parseInt(string.substring(5, 7))) * 60) + (Integer.parseInt(string.substring(8, 10)));
            finish = ((Integer.parseInt(string.substring(11, 13))) * 60) + (Integer.parseInt(string.substring(14, 16)));
        } else {
            company = false;
            start = ((Integer.parseInt(string.substring(7, 9))) * 60) + (Integer.parseInt(string.substring(10, 12)));
            finish = ((Integer.parseInt(string.substring(13, 15))) * 60) + (Integer.parseInt(string.substring(16, 18)));
        }
    }


    public boolean isCompany() {
        return company;
    }

    public Integer getStart() {
        return start;
    }

    public Integer getFinish() {
        return finish;
    }

 public static Comparator<Service> startComparator =new Comparator<Service>() {
     @Override
     public int compare(Service service, Service t1) {
         return service.getStart()-t1.getStart();
     }
 };

    public boolean equals(Service obj){
return this.company==obj.company;
}
    @Override
    public String toString() {

        String result = new String();
        if (company) {
           result= result.concat("Posh ");
        } else {
            result=result.concat("Grotty ");
        }

        if (start % 60 == 0 || start % 60 < 10) {
            result=result.concat((start / 60) + ":0" + (start % 60) + " ");
        } else {
            result= result.concat((start / 60) + ":" + (start % 60) + " ");
        }
        if (finish % 60 == 0 || finish % 60 < 10) {
            result= result.concat((finish / 60) + ":0" + (finish % 60));
        } else {
            result=result.concat((finish / 60) + ":" + (finish % 60));
        }

        return result;


    }
}
