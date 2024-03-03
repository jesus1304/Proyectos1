package com.example.crudv1.Screens
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.crudv1.navigation.SessionManager
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun ClienteModificar(navController:NavHostController) {
    var presses by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color( 25, 33, 156 ),
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Modificar datos", color = Color.White)
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("MenuInicio") }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description",
                            tint = Color.White

                        )
                    }
                },
                actions = {

                    IconButton(onClick = { navController.navigate("MenuInicio") }) {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = "Localized description",
                            tint = Color.White

                        )
                    }
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


                val context = LocalContext.current
                var showDialog by remember { mutableStateOf(false) }
                var mensajeConfirmacion by remember { mutableStateOf("") }

                LaunchedEffect(Unit) {
                    val db = FirebaseFirestore.getInstance()
                    val coleccion = "clientes"
                    val nombreUsuario = SessionManager.getUsername(context)

                    db.collection(coleccion)
                        .whereEqualTo("user", nombreUsuario)
                        .get()
                        .addOnSuccessListener { querySnapshot ->
                            for (document in querySnapshot) {
                                user = document.getString("user") ?: ""
                                nombre = document.getString("nombre") ?: ""
                                apellido = document.getString("apellido") ?: ""
                                correo = document.getString("correo") ?: ""
                                contraseña = document.getString("contraseña") ?: ""
                                telefono = document.getString("telefono") ?: ""
                            }
                        }
                        .addOnFailureListener {
                            // Manejar el error si no se pueden obtener los datos del usuario
                        }
                }

                Button(
                    onClick = {
                        if (nombre.isNotEmpty() && apellido.isNotEmpty() && contraseña.isNotEmpty() && correo.isNotEmpty() && telefono.isNotEmpty()) {
                            showDialog = true
                        } else {
                            mensajeConfirmacion = "Por favor, completa todos los campos"
                        }
                    },
                    border = BorderStroke(1.dp, Color.Black),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp,top= 12.dp)
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color( 25, 33, 156 ),
                        contentColor = Color.White
                    ),
                ) {
                    Text(
                        text = "Modificar", fontSize = 18.sp
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
                                        "apellido" to apellido,
                                        "contraseña" to contraseña,
                                        "telefono" to telefono,
                                        "correo" to correo,
                                        "user" to user
                                    )
                                    val db = FirebaseFirestore.getInstance()
                                    val coleccion = "clientes"
                                    val nombreUsuario = SessionManager.getUsername(context)

                                    db.collection(coleccion)
                                        .whereEqualTo("user", nombreUsuario)
                                        .get()
                                        .addOnSuccessListener { querySnapshot ->
                                            if (!querySnapshot.isEmpty) {
                                                for (document in querySnapshot) {
                                                    db.collection(coleccion)
                                                        .document(document.id)
                                                        .set(data)
                                                        .addOnSuccessListener {
                                                            mensajeConfirmacion = "Datos modificados"
                                                            showDialog = false // Cierra el diálogo al confirmar
                                                        }
                                                        .addOnFailureListener { exception ->
                                                            mensajeConfirmacion = "Error al modificar: $exception"
                                                        }
                                                }
                                            } else {
                                                mensajeConfirmacion = "No se encontraron datos para modificar"
                                            }
                                        }
                                        .addOnFailureListener { exception ->
                                            mensajeConfirmacion = "Error al buscar el documento: $exception"
                                        }
                                }
                            ) {
                                Text("Confirmar")
                            }
                        },
                        dismissButton = {
                            Button(
                                onClick = {
                                    showDialog = false // Cierra el diálogo al cancelar
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



