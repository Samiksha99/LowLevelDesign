import java.util.Objects;

public class UserPair {
    private final User user1;
    private final User user2;
    public UserPair(User user1, User user2){
        this.user1 = user1;
        this.user2 = user2;
    }
    public User getUser1(){
        return user1;
    }
    public User getUser2(){
        return user2;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserPair)) return false;
        UserPair that = (UserPair) o;
        return Objects.equals(user1, that.user1) &&
                Objects.equals(user2, that.user2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user1, user2);
    }
}