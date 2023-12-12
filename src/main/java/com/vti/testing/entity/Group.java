package com.vti.testing.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "`Group`")
@Data
@Getter
@Setter
@NoArgsConstructor
public class Group {
    @Column(name = "groupId")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "groupName", length = 50, nullable = false, unique = true)
    private String groupName;

    @Column(name = "CreateDate")
    @Temporal(TemporalType.DATE)
//    @CreationTimestamp
    private Date createdDate;


    @ManyToOne
    @JoinColumn(name = "CreatorId")
    private Account creator;

    @PrePersist
    public void prePersist() {
        if (createdDate == null) {
            createdDate = new Date();
        }
    }

}
