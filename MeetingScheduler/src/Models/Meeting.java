package Models;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Meeting {
    private final String id;
    private final String organiserId;
    private final String title;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    private final List<MeetingInvite> invites = new ArrayList<>();

    public Meeting(String organiserId,
                   String title,
                   LocalDateTime startTime,
                   LocalDateTime endTime) {
        this.id = UUID.randomUUID().toString();
        this.organiserId = organiserId;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getId() {
        return id;
    }

    public String getOrganiserId() {
        return organiserId;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public List<MeetingInvite> getInvites() {
        return invites;
    }

    public void addInvite(MeetingInvite invite) {
        invites.add(invite);
    }
}
