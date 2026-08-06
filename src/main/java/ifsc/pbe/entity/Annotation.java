package ifsc.pbe.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tbl_annotation")
public class Annotation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "anot_id")
    private Long id;

    @Column(name = "anot_text")
    private String text;

    @Column(name = "anot_dateTime")
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "task_name")
    private Task task;
}