package bean.lifecycle;

import com.fulu.domain.User;

/**
 * User Holder 类
 *
 * @author <a href="mailto:fulu@witsky.cn">FuLu</a>
 * @since 2023/6/1 13:59
 */
public class UserHolder {
    private final User user;

    private Integer number;

    public UserHolder(User user) {
        this.user = user;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                ", number=" + number +
                '}';
    }
}
 