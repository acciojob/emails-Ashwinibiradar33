package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

public class Workspace extends Gmail{
    private boolean isTimeOverlap(Meeting meeting1, Meeting meeting2) {
        // Check if the start time or end time of the meetings overlap.
        return (meeting1.startTime.isBefore(meeting2.endTime) && meeting1.endTime.isAfter(meeting2.startTime));
    }

    private ArrayList<Meeting> calendar; // Stores all the meetings

    public Workspace(String emailId) {
        // The inboxCapacity is equal to the maximum value an integer can store.
        super(emailId,Integer.MAX_VALUE);
        calendar = new ArrayList<>();

    }

    public void addMeeting(Meeting meeting){
        //add the meeting to calendar
        for (Meeting existingMeeting : calendar) {
            if (isTimeOverlap(existingMeeting, meeting)) {
                throw new IllegalArgumentException("The new meeting overlaps with an existing meeting.");
            }
        }

        calendar.add(meeting);

    }

    public int findMaxMeetings(){
        // find the maximum number of meetings you can attend
        // 1. At a particular time, you can be present in at most one meeting
        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am

        calendar.sort((m1, m2) -> m1.endTime.compareTo(m2.endTime));

        int noofmeetings = 0;
        LocalTime currentTime = LocalTime.MIN;
        for(Meeting meet:calendar)
        {
            if(meet.startTime.compareTo(currentTime)>=0)
            {
                noofmeetings++;
                currentTime = meet.endTime;
            }
        }
        return noofmeetings;
    }
}
