package tech.codingclub;

import tech.codingclub.utility.TaskManager;

import java.util.Date;

public class ThreadManager {

    public static void main(String[] args) {
        System.out.println("this side is yash garg");
        System.out.println("this time for running ThreadManager is :"+new Date().toString());
        TaskManager th=new TaskManager(1);
        for(int i=0;i<5;i++)
        {
         RunnableExample th1=new RunnableExample("Thread_NO "+i,0,500+i);
         th.waitTillQueueIsFreeAndAddTask(th1);
         //queue is heavy now pause in this loop
         System.out.println("$$$$$$$$$$$$$$$$$$$"+i);
        }
        System.out.println("#########################");
    }

}
