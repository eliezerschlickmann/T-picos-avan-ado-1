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
@Table(name = "tb_dispositivo")
@Data

public class Dispositivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long dispositivoid;

    @Column(name="nome",nullable = false)
    private String nome;

    @Column(name="descricao",nullable = false)
    private String descricao;

    @Column(name="localizacao",nullable = false)
    private String localizacao;


    @ManyToOne
    @JoinColumn(name="gateway_id")
    private Gateway gateway;
}
