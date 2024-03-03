package com.example.crudv1.Screens
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import java.util.*

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.crudv1.R
import com.example.crudv1.navigation.SessionManager
import com.google.firebase.firestore.FirebaseFirestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun InicioSesion(navController:NavHostController) {
    var rememberPassword by rememberSaveable { mutableStateOf(false) } // Nuevo estado para recordar contraseña

    Column(
        modifier = Modifier.fillMaxWidth()
            .verticalScroll(rememberScrollState()),
    ) {

        Image(
            painter = painterResource(id = R.drawable.descarga),
            contentDescription = "Login Image",
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .padding(start=20.dp,top=120.dp,bottom=20.dp)
                .fillMaxSize(),
        )
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center

                ) {


                    var user by rememberSaveable { mutableStateOf("") }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(start=20.dp, bottom = 5.dp,end=20.dp)                    ) {

                        val icon = Icons.Default.Person

                        OutlinedTextField(
                            value = user,
                            onValueChange = { user = it },
                            label = { Text("Usuario") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            leadingIcon = {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = null,
                                    tint = Color.Black,
                                    modifier = Modifier.size(44.dp),
                                )
                            }
                        )
                    }

                    var contraseña by rememberSaveable { mutableStateOf("") }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(start=20.dp, bottom = 5.dp,top=20.dp,end=20.dp)                    ) {
                        val icon = Icons.Default.Lock

                        OutlinedTextField(
                            value = contraseña,
                            onValueChange = { contraseña = it },
                            label = { Text("Contraseña") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            visualTransformation = PasswordVisualTransformation(),
                            leadingIcon = {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = null,
                                    tint = Color.Black,
                                    modifier = Modifier.size(44.dp),
                                )
                            }

                        )
                    }
                    val db = FirebaseFirestore.getInstance()
                    val coleccion = "clientes"

                    var mensajeConfirmacion by remember { mutableStateOf("") }
                    val context = LocalContext.current

                    Button(
                        onClick = {
                            if (user.isNotBlank() && contraseña.isNotBlank()) {
                                db.collection(coleccion)
                                    .whereEqualTo("user", user.toString())
                                    .get()
                                    .addOnSuccessListener { querySnapshot ->
                                        if (!querySnapshot.isEmpty) {
                                            var credentialsMatched = false
                                            for (documentSnapshot in querySnapshot) {
                                                val storedUser = documentSnapshot.getString("user")
                                                val storedContraseña = documentSnapshot.getString("contraseña")
                                                if (user == storedUser && contraseña == storedContraseña) {
                                                    credentialsMatched = true
                                                    SessionManager.setLoggedIn(context, true)
                                                    SessionManager.setUsername(context, user)
                                                    val isLoggedIn = SessionManager.isLoggedIn(context)
                                                    if (isLoggedIn) {
                                                        navController.navigate("ReservasFutbol") // Navega a la página de inicio
                                                    }
                                                }
                                            }
                                            if (!credentialsMatched) {
                                                mensajeConfirmacion = "Usuario o contraseña incorrectos"
                                            }
                                        } else {
                                            mensajeConfirmacion = "Usuario o contraseña incorrectos"
                                        }
                                    }
                                    .addOnFailureListener {
                                        mensajeConfirmacion = "Error al verificar los datos"
                                    }
                            } else {
                                mensajeConfirmacion = "Por favor, ingresa un usuario y una contraseña"
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            Color( 45, 33, 88 )
                        ),
                        border = BorderStroke(1.dp, Color.Black),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .padding(start = 18.dp, top = 10.dp, end = 18.dp)
                            .fillMaxWidth()
                    )  {
                        Text(
                            text = "Entrar",
                            fontSize = 20.sp,
                        )
                    }
                   /* Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 50.dp, vertical = 8.dp)
                    ) {
                        var userExists by remember { mutableStateOf(false) }

                        db.collection("clientes")
                            .whereEqualTo("user", user)
                            .get()
                            .addOnSuccessListener { querySnapshot ->
                                userExists = !querySnapshot.isEmpty
                            }
                            .addOnFailureListener { exception ->
                                Log.e("Firestore", "Error al verificar el usuario: $exception")
                            }

                        Checkbox(
                            checked = rememberPassword,
                            onCheckedChange = { rememberPassword = it },
                            enabled = userExists, // Habilitar el checkbox solo si el usuario existe
                            modifier = Modifier.padding(end = 8.dp, start = 60.dp)
                        )
                        Text(
                            text = "Recordar contraseña",
                            fontSize = 16.sp,
                            modifier = Modifier.clickable {
                                if (user.isNotBlank() && userExists) {
                                    rememberPassword = !rememberPassword
                                    enviarCorreoRecuperacionContraseña(user)
                                }
                            },
                            color = if (user.isNotBlank() && userExists) Color.Black else Color.Gray // Cambiar el color del texto si el usuario existe
                        )
                    }*/
                    Spacer(modifier = Modifier.size(5.dp))
                    Text(
                        text = mensajeConfirmacion,
                        color = Color.Red,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        modifier = Modifier.padding(start=50.dp,bottom=20.dp)
                    )

                    val text = "¿Aún no tienes usuario? Regístrate"

                    Text(
                        text = buildAnnotatedString {
                            append(text)
                            addStyle(
                                style = SpanStyle(
                                    color = Color.Blue,
                                    textDecoration = TextDecoration.Underline
                                ),
                                start = text.indexOf("Regístrate"),
                                end = text.length
                            )
                        },
                        modifier = Modifier.clickable {
                            navController.navigate("ClienteGuardar")
                        }
                            .padding(start = 70.dp,bottom=20.dp)
                    )
                }
                }
            }
    }


/*

fun enviarCorreoRecuperacionContraseña(user: String) {
    val username = "jesus.sanchez.1309@gmail.com" // Tu dirección de correo electrónico
    val password = "mar2624dela@" // Tu contraseña de correo electrónico

    // Configuración de las propiedades para la sesión de correo
    val properties = Properties().apply {
        put("mail.smtp.host", "smtp.gmail.com")
        put("mail.smtp.port", "465")
        put("mail.smtp.auth", "true")
        put("mail.smtp.socketFactory.port", "465")
        put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory")
    }

    // Inicio de la sesión de correo
    val session = Session.getInstance(properties, object : Authenticator() {
        override fun getPasswordAuthentication(): PasswordAuthentication {
            return PasswordAuthentication(username, password)
        }
    })

    try {
        // Creación del mensaje de correo
        val message = MimeMessage(session).apply {
            setFrom(InternetAddress(username))
            addRecipient(Message.RecipientType.TO, InternetAddress(user))
            subject = "Recuperación de contraseña"
            setText("Estimado usuario,\n\nHemos recibido una solicitud de recuperación de contraseña. Por favor, sigue las instrucciones en este correo electrónico para restablecer tu contraseña.\n\nAtentamente,\nEl equipo de soporte")
        }

        // Envío del correo
        Transport.send(message)

        println("Correo de recuperación de contraseña enviado correctamente a $user")
    } catch (e: MessagingException) {
        println("Error al enviar el correo de recuperación de contraseña: ${e.message}")
    }
}*/
