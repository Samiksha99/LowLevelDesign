package Models;

import Enums.InviteStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public class MeetingInvite {
    private final String id;
    private final String meetingId;
    private final String userId;
    private InviteStatus status;
    private LocalDateTime respondedAt;

    public void setStatus(InviteStatus status) {
        this.status = status;
    }

    public MeetingInvite(String meetingId, String userId) {
        this.id = UUID.randomUUID().toString();
        this.meetingId = meetingId;
        this.userId = userId;
        this.status = InviteStatus.PENDING;
    }

    public String getId() {
        return id;
    }

    public String getMeetingId() {
        return meetingId;
    }

    public String getUserId() {
        return userId;
    }

    public InviteStatus getStatus() {
        return status;
    }

    public LocalDateTime getRespondedAt() {
        return respondedAt;
    }

    public void accept() {
        this.status = InviteStatus.ACCEPTED;
        this.respondedAt = LocalDateTime.now();
    }

    public void reject() {
        this.status = InviteStatus.DECLINED;
        this.respondedAt = LocalDateTime.now();
    }
}