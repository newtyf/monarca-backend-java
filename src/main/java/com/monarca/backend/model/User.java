package com.monarca.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Rol rol;

    @Column(length = 20)
    private String telefono;

    @Enumerated(EnumType.STRING)
    private Estado estado = Estado.activo;

    private LocalDateTime fechaRegistro;

    private LocalDateTime fechaActualizacion;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<HorarioUser> horarios = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserServicio> serviciosAsignados = new ArrayList<>();

    public enum Rol {
        admin, empleado, recepcionista
    }

    public enum Estado {
        activo, inactivo
    }

    public User() {
    }

    // Getters and Setters

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public List<HorarioUser> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<HorarioUser> horarios) {
        this.horarios = horarios;
    }

    public List<UserServicio> getServiciosAsignados() {
        return serviciosAsignados;
    }

    public void setServiciosAsignados(List<UserServicio> serviciosAsignados) {
        this.serviciosAsignados = serviciosAsignados;
    }
}
