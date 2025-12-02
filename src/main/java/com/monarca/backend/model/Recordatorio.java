package com.monarca.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "recordatorios")
public class Recordatorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recordatorio")
    private Long idRecordatorio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cita", nullable = false)
    private Cita cita;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Tipo tipo;

    @Enumerated(EnumType.STRING)
    private Estado estado = Estado.pendiente;

    @Column(name = "fecha_programada", nullable = false)
    private LocalDateTime fechaProgramada;

    private LocalDateTime fechaEnviado;

    @Column(columnDefinition = "TEXT")
    private String mensaje;

    @Column(name = "intentos_envio")
    private Integer intentosEnvio = 0;

    @Column(columnDefinition = "TEXT")
    private String errorMensaje;

    private LocalDateTime fechaCreacion;

    public enum Tipo {
        email, sms, whatsapp, push
    }

    public enum Estado {
        pendiente, enviado, fallido
    }

    public Recordatorio() {
    }

    // Getters and Setters

    public Long getIdRecordatorio() {
        return idRecordatorio;
    }

    public void setIdRecordatorio(Long idRecordatorio) {
        this.idRecordatorio = idRecordatorio;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaProgramada() {
        return fechaProgramada;
    }

    public void setFechaProgramada(LocalDateTime fechaProgramada) {
        this.fechaProgramada = fechaProgramada;
    }

    public LocalDateTime getFechaEnviado() {
        return fechaEnviado;
    }

    public void setFechaEnviado(LocalDateTime fechaEnviado) {
        this.fechaEnviado = fechaEnviado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Integer getIntentosEnvio() {
        return intentosEnvio;
    }

    public void setIntentosEnvio(Integer intentosEnvio) {
        this.intentosEnvio = intentosEnvio;
    }

    public String getErrorMensaje() {
        return errorMensaje;
    }

    public void setErrorMensaje(String errorMensaje) {
        this.errorMensaje = errorMensaje;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
