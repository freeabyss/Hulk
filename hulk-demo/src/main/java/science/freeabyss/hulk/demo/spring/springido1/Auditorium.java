package science.freeabyss.hulk.demo.spring.springido1;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * Created by abyss on 3/25/16.
 */
public class Auditorium implements InitializingBean,DisposableBean{
    public void turnOnLights() {
    }

    public void turnOffLights() {
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void destroy() throws Exception {

    }
}
