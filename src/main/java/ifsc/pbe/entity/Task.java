package ifsc.pbe.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "tbl_task")
public class Task {
    @Id
    @Column(name="task_name")
    private String name;

    @Column(name="task_desc")
    private String desc;

    @Column(name="task_delTime")
    private LocalTime delTime;

    @Column(name="task_dateTime")
    private LocalDateTime dateTime;

    @Column(name="task_anot")
    private String anot;

}
