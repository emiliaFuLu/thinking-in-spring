package bean.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;

/**
 * {@link DestructionAwareBeanPostProcessor} 实现
 *
 * @author <a href="mailto:fulurjj@gmail.com">FuLu</a >
 * @since 2023/7/5 00:16
 */
public class MyDestructionAwareBeanPostProcessor implements DestructionAwareBeanPostProcessor{

    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        if ("userHolder".equals(beanName) && UserHolder.class.equals(bean.getClass())) {
            UserHolder userHolder = (UserHolder) bean;
            // afterSingletonsInstantiated() = The userHolder v9
            userHolder.setRemark("The userHolder V9");
            System.out.println("postProcessBeforeDestruction() : " + userHolder.getRemark());
        }
    }
}
