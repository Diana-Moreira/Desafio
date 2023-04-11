package com.example.desafio.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.util.List;
import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "usuario")
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long idUsuario;

    private String nomeUsuario;

    private String email;

    @OneToMany(cascade = ALL)
    @JoinColumn(name = "id_usuario")
    private List<AddressEntity> listaEndereco;

}