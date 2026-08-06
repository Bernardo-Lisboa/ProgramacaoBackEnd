package ifsc.pbe.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "tbl_task")
public class Task {

    @Id
    @Column(name = "task_name")
    private String name;

    @Column(name = "task_desc")
    private String desc;

    @Column(name = "task_delTime")
    private LocalDateTime delTime;

    @Column(name = "task_dateTime")
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "list_name")
    private TaskList list;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Annotation> annotations;
}