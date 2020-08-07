package tech.codingclub.database;

import tech.codingclub.entity.Coders;

import java.util.Date;
import java.util.List;

public class CRUDExample {
    public static void main(String[] args) {
        System.out.println("This side is yash garg");
        System.out.println("Reading rows at "+new Date().toString());
        //one row
        Coders coder=new GenericDB<Coders>().getRow(tech.codingclub.tables.Coders.CODERS,Coders.class,null);
        //what if you want to recive all rows do it directly
        System.out.println("create entries in table: ");
         readTableCoders();
         //update
        //int ->Integer
        System.out.println("updated table table: ");
        new GenericDB<String>().updateColumn(tech.codingclub.tables.Coders.CODERS.NAME,"Coding Mafia", tech.codingclub.tables.Coders.CODERS, tech.codingclub.tables.Coders.CODERS.NAME.eq("SPAMMER"));
        readTableCoders();
    }

    private static void readTableCoders() {
        List<Coders> x=(List<Coders>) new GenericDB<Coders>().getRows(tech.codingclub.tables.Coders.CODERS,Coders.class,null,null);
        for (Coders obj:x)
        {
            System.out.println("Row: "+obj.getName()+" "+obj.getAge());
        }
    }
}
