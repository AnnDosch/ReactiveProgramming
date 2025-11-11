package ua.com.reactive.lab5.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table("roles_has_users")
public class RolesHasUsers {

    @Id
    private Long id;

    @Column("role_id")
    private Long roleId;

    @Column("user_id")
    private Long userId;

}