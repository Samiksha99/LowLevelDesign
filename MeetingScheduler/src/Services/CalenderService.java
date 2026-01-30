package Services;

import Enums.InviteStatus;
import Models.Calendar;
import Models.MeetingInvite;
import Models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CalenderService {

    private final Map<String, MeetingInvite> inviteStore;

    public CalenderService(Map<String, MeetingInvite> inviteStore) {
        this.inviteStore = inviteStore;
    }

    public List<MeetingInvite> getAcceptedInvites(String userId) {
        return inviteStore.values().stream()
                .filter(invite -> invite.getUserId().equals(userId) && invite.getStatus() == InviteStatus.ACCEPTED)
                .toList();
    }

    public List<MeetingInvite> getPendingInvites(String userId) {
        return inviteStore.values().stream()
                .filter(invite -> invite.getUserId().equals(userId) && invite.getStatus() == InviteStatus.PENDING)
                .toList();
    }

}
