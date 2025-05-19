package xyz.eo.manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xyz.eo.manager.util.PermissionConvertor;
import xyz.eo.manager.util.Permissions;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "user",
        indexes = {
            @Index(name = "idx_user_email", columnList = "email"),
            @Index(name = "idx_user_mobile", columnList = "mobile")
        })
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "mobile", unique = true)
    private String mobile;
    @Column(name = "username", unique = true)
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "role_id", nullable = false)
    private Integer roleId;
    @Convert(converter = PermissionConvertor.class)
    @Column(name = "permissions", columnDefinition = "TEXT")
    private Permissions permissions;
}