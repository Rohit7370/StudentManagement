package com.student.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "Result_Table")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resultId;

    private Integer hindi;
    private Integer english;
    private Integer maths;

    private Integer total;
    private Double average;


    public void setTotal(int hindi, int english, int maths) {
        this.total=(hindi+english+maths);
    }

}
