package lab.springwebclient.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.yml")
public class OrderProperties {
    private final OrderServerInfo orderProperties = new OrderServerInfo();

    private static class OrderServerInfo{
        private String host;
        private String path;

        public String getHost() {
            return host;
        }

        public String getPath() {
            return path;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }

    public OrderServerInfo getOrderProperties() {
        return orderProperties;
    }
}
