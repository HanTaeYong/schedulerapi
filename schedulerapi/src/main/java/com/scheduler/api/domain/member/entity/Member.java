package com.scheduler.api.domain.member.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.scheduler.api.common.model.BaseDateEntity;
import com.scheduler.api.common.model.jenum.ColDel;
import com.scheduler.api.domain.member.entity.dto.MemberUpdateReqDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_member")
public class Member extends BaseDateEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", columnDefinition = "BIGINT")
    private Long id;

    @Column(name = "user_id", nullable = false, unique = true, columnDefinition = "NVARCHAR(20)")
    private String userId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password", nullable = false, columnDefinition = "NVARCHAR(126)")
    private String password;

    @Column(name = "email", nullable = false, columnDefinition = "NVARCHAR(30)")
    private String email;

    @Column(name = "name", nullable = false, columnDefinition = "NVARCHAR(30)")
    private String name;

    @Column(name = "nickname", nullable = false, columnDefinition = "NVARCHAR(30)")
    private String nickname;

    @Column(name = "deleted_dttm", columnDefinition = "DATETIME")
    private LocalDateTime deletedDttm;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "isdel", nullable = false, columnDefinition = "NVARCHAR(10) default 'USE'")
    private ColDel isDel;

    public void update(MemberUpdateReqDto reqDto) {
        this.email = reqDto.getEmail();
        this.name = reqDto.getName();
        this.nickname = reqDto.getNickname().isBlank() ? reqDto.getName() : reqDto.getNickname();
    }

    public void changePassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
