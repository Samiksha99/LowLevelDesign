import Models.Calendar;
import Models.Meeting;
import Models.MeetingInvite;
import Models.User;
import Services.CalenderService;
import Services.MeetingInviteService;
import Services.MeetingService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // In-memory stores (repositories)
        Map<String, Meeting> meetingStore = new HashMap<>();
        Map<String, MeetingInvite> inviteStore = new HashMap<>();


        // Services
        MeetingService meetingService = new MeetingService(meetingStore);
        MeetingInviteService inviteService =
                new MeetingInviteService(meetingService,inviteStore);
        CalenderService calendarService = new CalenderService(inviteStore);

        User alice = new User("1", "Alice");
        User bob = new User("2", "Bob");
        User user3 = new User("3", "Charlie");
        User user4 = new User("4", "Diana");

        Meeting meeting = new Meeting(
                alice.getId(),
                "Design Review",
                LocalDateTime.of(2026, 2, 10, 10, 0),
                LocalDateTime.of(2026, 2, 10, 11, 0)
        );

        meetingService.createMeeting(meeting);
        // Send invite to Bob
        MeetingInvite invite =
                new MeetingInvite(meeting.getId(), bob.getId());
        inviteService.createInvite(invite);

        // Bob checks pending invites
        System.out.println("Pending invites before accept: "
                + calendarService.getPendingInvites(bob.getId()).size());

        // Bob accepts only "Design Review"
        calendarService.getPendingInvites(bob.getId()).stream()
                .filter(i ->
                        "Design Review".equals(
                                meetingService.getMeeting(i.getMeetingId()).getTitle()
                        )
                )
                .forEach(i ->
                        inviteService.acceptMeetingInvite(i.getId(), bob.getId())
                );

        // Bob checks calendar
        System.out.println("Accepted invites after accept: "
                + calendarService.getAcceptedInvites(bob.getId()).size());
        // Bob checks pending invites
        System.out.println("Pending invites before accept: "
                + calendarService.getPendingInvites(bob.getId()).size());
    }
}