package com.dark.spring.spms.entity;

import com.dark.spring.spms.data.UserData;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Customer extends User {

    @Column(name = "first_name")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String firstName;

    @Column(name = "last_name")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String lastName;


    public UserData toData(){
        return UserData.builder()
                .firstName(firstName)
                .lastName(lastName)
                .fullName(getName())
                .email(getEmail())
                .password(getPassword())
                .createdAt(Objects.nonNull(getCreatedAt()) ? getCreatedAt().toString() : "")
                .updatedAt(Objects.nonNull(getUpdatedAt()) ? getUpdatedAt().toString() : "")
                .lastLogin(Objects.nonNull(getLastLogin()) ? getLastLogin().toString() : "")
                .build();
    }

    @Override
    public String toString() {
        return "Customer{" +
                "First Name='" + firstName + '\'' +
                ", Last Name='" + lastName + '\'' +
                ", email='" + getEmail() + '\'' +
                ", name='" + getName() + '\'' +
                ", lastLogin=" + getLastLogin() + '\'' +
                '}';
    }
}