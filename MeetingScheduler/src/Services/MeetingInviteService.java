package Services;

import Enums.InviteStatus;
import Enums.MeetingStatus;
import Models.MeetingInvite;

import java.util.Map;

public class MeetingInviteService {

    private final MeetingService meetingService;
    private final Map<String, MeetingInvite> inviteStore;

    public MeetingInviteService(MeetingService meetingService, Map<String, MeetingInvite> inviteStore) {
        this.meetingService = meetingService;
        this.inviteStore = inviteStore;

    }
    public void createInvite(MeetingInvite invite) {
        inviteStore.put(invite.getId(), invite);
    }

    // synchronized for demo; DB transaction in real system
    public synchronized void acceptMeetingInvite(String inviteId, String userId) {
        MeetingInvite invite = inviteStore.get(inviteId);

        if (invite == null) {
            throw new IllegalStateException("Invite not found");
        }

        if (!invite.getUserId().equals(userId)) {
            throw new IllegalArgumentException("Unauthorized user");
        }

        // Idempotent
        if (invite.getStatus() == InviteStatus.ACCEPTED) {
            return;
        }

        if (!meetingService.meetingExists(invite.getMeetingId())) {
            throw new IllegalStateException("Meeting no longer exists");
        }

        invite.accept();
    }
}
