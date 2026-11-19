package com.example.petagram

import java.util.Properties
import javax.mail.Authenticator
import javax.mail.Message
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

object MailSender {
    private const val HOST = "sandbox.smtp.mailtrap.io"
    private const val PUERTO = "2525"
    private const val USUARIO = "18b141eb8ca4a6"
    private const val CLAVE = "a0cd5987c2f00d"

    fun enviar(nombre: String, correo: String, mensaje: String) {
        val props = Properties().apply {
            put("mail.smtp.auth", "true")
            put("mail.smtp.starttls.enable", "true")
            put("mail.smtp.host", HOST)
            put("mail.smtp.port", PUERTO)
        }
        val session = Session.getInstance(props, object : Authenticator() {
            override fun getPasswordAuthentication() =
                PasswordAuthentication(USUARIO, CLAVE)
        })
        val mail = MimeMessage(session).apply {
            setFrom(InternetAddress("petagram@example.com"))
            setRecipients(Message.RecipientType.TO, InternetAddress.parse("contacto@example.com"))
            subject = "Comentario de Petagram: $nombre"
            setText("Nombre: $nombre\nCorreo: $correo\n\nMensaje:\n$mensaje")
        }
        Transport.send(mail)
    }
}