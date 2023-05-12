package LR6.entity;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {
    private long id;
    private String surname;
    private String name;
    private Double score;
}