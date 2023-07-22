package com.driver;

import java.util.*;

public class Gmail extends Email {
    Gmail()
    {

    }

    int inboxCapacity; //maximum number of mails inbox can store
    Deque<pair> pq = new ArrayDeque<>();
    HashMap<String,pair> map = new HashMap<>();
    HashSet<pair> trash = new HashSet<>();
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        pq = new ArrayDeque<>();
        map = new HashMap<>();
        trash = new HashSet<>();



    }
    class pair {
        Date date;
        String sender;
        String message;
        pair(Date date,String sender,String message)
        {
            this.date = date;
            this.message = message;
            this.sender = sender;
        }
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.


        if(pq.size()>=inboxCapacity)
        {
            pair totrush = pq.removeFirst();//poll will give me last ele
            map.remove(totrush.message);
            trash.add(totrush);

            pq.add(new pair(date,sender,message));
            map.put(message,new pair(date,sender,message));

        }
        else {
            pq.add(new pair(date,sender,message));
            map.put(message,new pair(date,sender,message));

        }

    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        if(map.containsKey(message))
        {
            pair mail_del = map.get(message);
            trash.add(mail_del);
            pq.remove(mail_del);
            map.remove(message);
        }
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        if(pq.size()>0)
        {
            return pq.peekLast().message;//peek will give me front ele
        }
        else {
            return null;
        }

    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if(pq.size()>0)
        {
            return pq.peekFirst().message;
        }
        else {
            return null;
        }

    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int count=0;
        for(pair pairs:pq)
        {
            if(pairs.date.compareTo(start)>=0 && pairs.date.compareTo(end)<=0)
            {
                count++;
            }
        }
        return count;


    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return pq.size();

    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return trash.size();

    }

    public void emptyTrash(){
        // clear all mails in the trash
        trash.clear();


    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return inboxCapacity;
    }
}
