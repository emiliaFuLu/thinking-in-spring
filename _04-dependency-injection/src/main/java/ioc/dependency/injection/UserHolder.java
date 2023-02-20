package ioc.dependency.injection;

import com.fulu.domain.User;

/**
 * {@link User} 的 UserHolder 类
 *
 * @author fulu
 * @create 2023年02月16日 22:01
 * @since
 **/
public class UserHolder {

    private User user;

    public UserHolder() {
    }

    public UserHolder(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                '}';
    }
}
