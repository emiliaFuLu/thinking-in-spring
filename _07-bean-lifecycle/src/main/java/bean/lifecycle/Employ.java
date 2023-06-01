package bean.lifecycle;

/**
 * employ 类
 *
 * @author <a href="mailto:fulu@witsky.cn">FuLu</a>
 * @since 2023/6/1 17:43
 */
public class Employ {
    private String name;

    private int id;

    public Employ(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Employ{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
 