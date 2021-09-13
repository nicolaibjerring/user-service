package retros.userservice.converter;

import org.springframework.core.convert.converter.Converter;
import retros.userservice.entity.UserEntity;
import retros.userservice.model.User;

public class UserModelToUserEntity implements Converter<User, UserEntity> {
    @Override
    public UserEntity convert(User input) {
        return UserEntity.builder()
                .username(input.getUsername())
                .password(input.getPassword())
                .build();
    }
}
