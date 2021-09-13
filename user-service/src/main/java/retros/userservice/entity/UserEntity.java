package retros.userservice.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table( name="users")
public class UserEntity {
    @Id
    @GeneratedValue
    @Column(name="user_id", nullable = false)
    private Long id;

    @Column( name="username", nullable = false)
    private String username;

    @Column( name="password", nullable = false)
    private String password;
}
