package retros.userservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import retros.userservice.converter.UserEntityToUserModel;
import retros.userservice.converter.UserModelToUserEntity;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new UserEntityToUserModel());
        registry.addConverter(new UserModelToUserEntity());
    }

}
