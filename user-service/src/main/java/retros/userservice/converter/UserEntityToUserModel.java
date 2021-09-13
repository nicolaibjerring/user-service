package retros.userservice.converter;

import org.springframework.core.convert.converter.Converter;
import retros.userservice.entity.UserEntity;
import retros.userservice.model.User;

public class UserEntityToUserModel implements Converter<UserEntity, User> {
    @Override
    public User convert(UserEntity input) {
         return User.builder()
                    .userId(input.getId())
                    .username(input.getUsername())
                    .password(input.getPassword())
                    .build();
    }
}
