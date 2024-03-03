package com.example.crudv1.Screens
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.firestore.FirebaseFirestore
@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun ClienteGuardar(navController:NavHostController) {
    var presses by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color( 25, 33, 156 ),
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Registro", color = Color.White)
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("InicioSesion") }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description",
                            tint = Color.White

                        )
                    }
                },
                actions = {

                }
            )
        },

        ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {

            var text by rememberSaveable(stateSaver = TextFieldValue.Saver) {
                mutableStateOf(TextFieldValue("", TextRange(0, 7)))
            }
        }
    }

        Column(
            modifier = Modifier

                .padding(top=100.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
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
                        .padding(start=20.dp, bottom = 5.dp,top=7.dp,end=20.dp)                ) {
                    OutlinedTextField(
                        value = user,
                        onValueChange = { user = it },
                        label = { Text("Usuario",
                            ) },
                        modifier = Modifier
                            .fillMaxWidth(),
                        leadingIcon = {
                            val icon = Icons.Default.AccountCircle
                            Icon(
                                imageVector = icon,
                                contentDescription = null,
                                modifier = Modifier.size(44.dp),
                                tint = Color.Black // Puedes ajustar el color del icono según tus preferencias
                            )
                        }
                    )
                }
                var contraseña by rememberSaveable { mutableStateOf("") }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(start=20.dp, bottom = 5.dp,top=7.dp,end=20.dp)                ) {
                    OutlinedTextField(
                        value = contraseña,
                        onValueChange = { contraseña = it },
                        label = { Text("Contraseña") },
                        modifier = Modifier
                            .fillMaxWidth()
                        ,
                        visualTransformation = PasswordVisualTransformation(),
                        leadingIcon = {
                            val icon = Icons.Default.Lock
                            Icon(
                                imageVector = icon,
                                contentDescription = null,
                                modifier = Modifier.size(44.dp),
                                tint = Color.Black // Puedes ajustar el color del icono según tus preferencias
                            )
                        }
                    )
                }
                var nombre by rememberSaveable { mutableStateOf("") }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(start=20.dp, bottom = 5.dp,top=7.dp,end=20.dp)                ) {
                    OutlinedTextField(
                        value = nombre,
                        onValueChange = { nombre = it },
                        label = { Text("Nombre",
                            ) },
                        modifier = Modifier
                            .fillMaxWidth()
                            ,
                        leadingIcon = {
                            val icon = Icons.Default.Person
                            Icon(
                                imageVector = icon,
                                contentDescription = null,
                                modifier = Modifier.size(44.dp),
                                tint = Color.Black // Puedes ajustar el color del icono según tus preferencias
                            )
                        }
                    )
                }

                var apellido by rememberSaveable { mutableStateOf("") }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(start=20.dp, bottom = 5.dp,top=7.dp,end=20.dp)                ) {
                    OutlinedTextField(
                        value = apellido,
                        onValueChange = { apellido = it },
                        label = { Text("Apellido"
                            , ) },
                        modifier = Modifier
                            .fillMaxWidth()
                            ,
                        leadingIcon = {
                            val icon = Icons.Default.Person
                            Icon(
                                imageVector = icon,
                                contentDescription = null,
                                modifier = Modifier.size(44.dp),
                                tint = Color.Black // Puedes ajustar el color del icono según tus preferencias
                            )
                        }
                    )
                }



                var correo by rememberSaveable { mutableStateOf("") }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(start=20.dp, bottom = 5.dp,top=7.dp,end=20.dp)                ) {
                    OutlinedTextField(
                        value = correo,
                        onValueChange = { correo = it },
                        label = { Text("Correo") },
                        modifier = Modifier
                            .fillMaxWidth()
                           ,
                        leadingIcon = {
                            val icon = Icons.Default.Email
                            Icon(
                                imageVector = icon,
                                contentDescription = null,
                                modifier = Modifier.size(44.dp),
                                tint = Color.Black // Puedes ajustar el color del icono según tus preferencias
                            )
                        }
                    )
                }

                val icon = Icons.Default.Phone
                var telefono by rememberSaveable { mutableStateOf("") }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(start=20.dp, bottom = 5.dp,top=7.dp,end=20.dp)                ) {
                    OutlinedTextField(
                        value = telefono,
                        onValueChange = { telefono = it },
                        label = { Text("Teléfono") },
                        modifier = Modifier
                            .fillMaxWidth()
                            ,
                        leadingIcon = {
                            Icon(
                                imageVector = icon,
                                contentDescription = null,
                                modifier = Modifier.size(44.dp),
                                tint = Color.Black // Puedes ajustar el color del icono según tus preferencias
                            )
                        }
                    )
                }
                val db = FirebaseFirestore.getInstance()
                val coleccion = "clientes"
                var showDialog by remember { mutableStateOf(false) }
                var mensajeConfirmacion by remember { mutableStateOf("") }

                Button(
                    onClick = {
                        if (nombre.isNotEmpty() && apellido.isNotEmpty() && contraseña.isNotEmpty() &&
                            telefono.isNotEmpty() && correo.isNotEmpty() && user.isNotEmpty()
                        ) {
                            showDialog = true
                        } else {
                            mensajeConfirmacion = "Por favor, completa todos los campos" // Mensaje de error si falta algún campo
                        }

                    },
                    border = BorderStroke(1.dp, Color.Black),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp,top= 12.dp)
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor =Color( 45, 33, 88 )
                        ,
                        contentColor = Color.White
                    ),
                ) {
                    Text(
                        text = "Registrarse", fontSize = 18.sp
                    )
                }

                if (showDialog) {
                    AlertDialog(
                        onDismissRequest = {
                            showDialog = false
                        },
                        title = {
                            Text("Confirmar envío")
                        },
                        text = {
                            Text("¿Estás seguro de enviar los datos?")
                        },
                        confirmButton = {
                            Button(
                                onClick = {
                                    val data = hashMapOf(
                                        "nombre" to nombre,
                                        "apellido" to apellido ,
                                        "contraseña" to contraseña,
                                        "telefono" to telefono,
                                        "correo" to correo,
                                        "user" to user,

                                        )

                                    db.collection(coleccion)
                                        .add(data)
                                        .addOnSuccessListener {
                                            mensajeConfirmacion =
                                                "Te has registrado"
                                            nombre = ""
                                            apellido = ""
                                            contraseña = ""
                                            telefono = ""
                                            correo = ""
                                            user = ""

                                            showDialog = false
                                        }
                                        .addOnFailureListener { exception ->
                                            mensajeConfirmacion = "Error al guardar: $exception"
                                            showDialog = false
                                        }
                                }
                            ) {
                                Text("Confirmar")
                            }
                        },
                        dismissButton = {
                            Button(
                                onClick = {
                                    showDialog = false
                                }
                            ) {
                                Text("Cancelar")
                            }
                        }
                    )
                }

                if (mensajeConfirmacion.isNotEmpty()) {
                    Text(
                        text = mensajeConfirmacion,
                        modifier = Modifier.padding(top = 15.dp),
                        color = if (mensajeConfirmacion.startsWith("Error")) Color.Red else Color.Green
                    )
                }
            }
        }
    }



