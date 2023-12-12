package com.vti.testing.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "`Account`")
@Getter
@Setter
@NoArgsConstructor
public class Account {
    @Column(name = "AccountId")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Username", length = 50, nullable = false, unique = true)
    private String userName;

    @Column(name = "Email", length = 50, nullable = false, unique = true)
    private String email;

    @Column(name = "lastname", length = 50, nullable = false)
    private String lastName;

    @Column(name = "firstname", length = 50, nullable = false)
    private String firstName;

    @Formula("concat(firstname,' ',lastname)")
    private String fullName;

    @Column(name = "`password`", length = 250, nullable = false)
    private String password;

    @Column(name = "CreateDate")
    @Temporal(TemporalType.DATE)
    private Date createDate;

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
    private List<Group> groups;

    @PrePersist
    public void prePersist() {
        if (createDate == null) {
            createDate = new Date();
        }
    }


}
