package com.superwin.referralservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "referrals")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Referral {

    @Id
    private UUID id;

    @Column(name = "profile_id", nullable = false,updatable = false)
    private UUID profileId;

    @Column(name = "referral_code", nullable = false, updatable = false)
    private Long referralCode;

    @Column(name = "referred_code")
    private Long referredCode;

    @Column(name = "level_1", columnDefinition = "jsonb")
    private String level1;

    @Column(name = "level_2", columnDefinition = "jsonb")
    private String level2;

    @Column(name = "level_3", columnDefinition = "jsonb")
    private String level3;

    @Column(name = "level_4", columnDefinition = "jsonb")
    private String level4;

    @Column(name = "level_5", columnDefinition = "jsonb")
    private String level5;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public String getLevelByNumber(Integer num){
        return switch (num) {
            case 1 -> getLevel1();
            case 2 -> getLevel2();
            case 3 -> getLevel3();
            case 4 -> getLevel4();
            case 5 -> getLevel5();
            default -> "Done!";
        };

    }

}
