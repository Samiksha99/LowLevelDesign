package Services;

import Models.Meeting;
import Models.MeetingInvite;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MeetingService {

    private final Map<String, Meeting> meetings;

    public MeetingService(Map<String, Meeting> meetings) {
        this.meetings = new HashMap<>();
    }
    public void createMeeting(Meeting meeting) {
        if(!meeting.getStartTime().isBefore(meeting.getEndTime())) {
            throw new IllegalArgumentException("Start time must be before end time");
        }
        meetings.put(meeting.getId(), meeting);
    }

    public boolean meetingExists(String meetingId) {
        return meetings.containsKey(meetingId);
    }

    public Meeting getMeeting(String meetingId) {
        return meetings.get(meetingId);
    }

    public void cancelMeeting(String meetingId) {
        meetings.remove(meetingId);
    }
}
