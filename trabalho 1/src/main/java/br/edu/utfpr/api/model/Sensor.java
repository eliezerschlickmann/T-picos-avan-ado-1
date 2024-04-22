package br.edu.utfpr.api.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_sensor")
@Data

public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long sensorid;

    @Column(name="nome",nullable = false)
    private String nome;

    @Column(name="tipo",nullable = false)
    private String tipo;


    @ManyToOne
    @JoinColumn(name="dispositivo_id")
    private Dispositivo dispositivo;
}
