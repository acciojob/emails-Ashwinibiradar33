package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.*;

class Workspace extends Gmail {
    private static final int MAX_INBOX_CAPACITY = Integer.MAX_VALUE;
    private List<Meeting> calendar;

    public Workspace(String emailId) {
        super(emailId, MAX_INBOX_CAPACITY);
        this.calendar = new ArrayList<>();
    }

    public void addMeeting(Meeting meeting) {
        calendar.add(meeting);
    }

    public int findMaxMeetings() {
        if (calendar.isEmpty()) {
            return 0;
        }

        // Sort meetings by their end time
        Collections.sort(calendar, Comparator.comparing(Meeting::getEndTime));

        int maxMeetings = 1;
        int currentMeetings = 1;
        LocalTime prevEndTime = calendar.get(0).getEndTime();

        for (int i = 1; i < calendar.size(); i++) {
            Meeting currentMeeting = calendar.get(i);
            if (currentMeeting.getStartTime().isAfter(prevEndTime)) {
                currentMeetings++;
                prevEndTime = currentMeeting.getEndTime();
            }
        }

        return maxMeetings;
    }

    private class Meeting {
        private LocalTime startTime;
        private LocalTime endTime;

        public Meeting(LocalTime startTime, LocalTime endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public LocalTime getStartTime() {
            return startTime;
        }

        public LocalTime getEndTime() {
            return endTime;
        }
    }
}
