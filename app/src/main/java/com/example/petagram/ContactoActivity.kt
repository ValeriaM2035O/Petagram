package com.example.petagram

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class ContactoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacto)

        findViewById<MaterialToolbar>(R.id.toolbarContacto)
            .setNavigationOnClickListener { finish() }

        val tilNombre = findViewById<TextInputLayout>(R.id.tilNombre)
        val tilCorreo = findViewById<TextInputLayout>(R.id.tilCorreo)
        val tilMensaje = findViewById<TextInputLayout>(R.id.tilMensaje)
        val etNombre = findViewById<TextInputEditText>(R.id.etNombre)
        val etCorreo = findViewById<TextInputEditText>(R.id.etCorreo)
        val etMensaje = findViewById<TextInputEditText>(R.id.etMensaje)
        val btnEnviar = findViewById<Button>(R.id.btnEnviar)

        btnEnviar.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val correo = etCorreo.text.toString().trim()
            val mensaje = etMensaje.text.toString().trim()

            tilNombre.error = if (nombre.isEmpty()) "Campo obligatorio" else null
            tilMensaje.error = if (mensaje.isEmpty()) "Campo obligatorio" else null
            tilCorreo.error = when {
                correo.isEmpty() -> "Campo obligatorio"
                !Patterns.EMAIL_ADDRESS.matcher(correo).matches() -> "Correo no válido"
                else -> null
            }
            if (tilNombre.error != null || tilCorreo.error != null || tilMensaje.error != null) {
                return@setOnClickListener
            }

            btnEnviar.isEnabled = false
            // El envío de correo debe hacerse fuera del hilo principal
            Thread {
                try {
                    MailSender.enviar(nombre, correo, mensaje)
                    runOnUiThread {
                        Toast.makeText(this, "Comentario enviado", Toast.LENGTH_LONG).show()
                        etNombre.text?.clear()
                        etCorreo.text?.clear()
                        etMensaje.text?.clear()
                    }
                } catch (e: Exception) {
                    Log.e("MailSender", "Error al enviar", e)
                    runOnUiThread {
                        Toast.makeText(this, "No se pudo enviar el correo", Toast.LENGTH_LONG).show()
                    }
                } finally {
                    runOnUiThread { btnEnviar.isEnabled = true }
                }
            }.start()
        }
    }
}