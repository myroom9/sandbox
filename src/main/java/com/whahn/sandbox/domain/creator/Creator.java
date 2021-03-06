package com.whahn.sandbox.domain.creator;

import com.whahn.sandbox.domain.channel.Channel;
import com.whahn.sandbox.common.BaseEntity;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;

@Entity
@Getter
@Builder
@Table(name = "creators")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Creator extends BaseEntity {

    public Creator(String name, String birth, int sex) {
        this.name = name;
        this.birth = birth;
        this.sex = sex;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT(1) UNSIGNED")
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 8)
    private String birth;

    @Column(nullable = false)
    private int sex;

    @Override
    public String toString() {
        return ToStringBuilder
                .reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }
}
