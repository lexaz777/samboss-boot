package ru.zakharov.samboss.entities;

import javax.persistence.*;

@Entity
@Table(name = "scan_settings")
public class ScanSettings {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "time")
    private String time;

    @OneToOne
    @JoinColumn(name = "target_id")
    private Target target;

    public ScanSettings() {
    }

    public ScanSettings(String title, String time, Target target) {
        this.title = title;
        this.time = time;
        this.target = target;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }
}
