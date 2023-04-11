package com.example.desafio.entities;

import lombok.Data;
import jakarta.persistence.*;
import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "endereco")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_endereco")
    private Long idEndereco;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;

}