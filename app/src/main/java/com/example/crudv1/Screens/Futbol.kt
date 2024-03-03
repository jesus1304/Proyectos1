package com.example.crudv1.Screens

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.util.Log
import android.widget.DatePicker
import androidx.compose.ui.platform.LocalContext
import android.widget.TimePicker
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.crudv1.R
import com.example.crudv1.navigation.SessionManager
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import java.util.Calendar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Futbol(navController: NavHostController) {
    var optionsExpanded by remember { mutableStateOf(false) }
    var optionsExpanded2 by remember { mutableStateOf(false) }

    val options = listOf(
        "Reservas",
        "Campos"
    )
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(25, 33, 156),
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Reservar Cita", color = Color.White)
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("ReservasFutbol") }) {
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
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "Location Icon",
                            tint = Color.White

                        )
                    }
                }
            )
        },
        bottomBar = {

        },
    ) { innerPadding ->

        Box(
            modifier = Modifier.fillMaxWidth()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
        )
        {
            var expanded by remember { mutableStateOf(false) }
            var fecha by rememberSaveable { mutableStateOf("") }
            val mCalendar: Calendar = Calendar.getInstance()
            val anio: Int = mCalendar.get(Calendar.YEAR)
            val mes: Int = mCalendar.get(Calendar.MONTH)
            val dia: Int = mCalendar.get(Calendar.DAY_OF_MONTH)

            val mDatePickerDialog = DatePickerDialog(
                LocalContext.current, { _: DatePicker, anio: Int, mes: Int, dia: Int ->
                    val mesFormateado = String.format("%02d", mes + 1)
                    val diaFormateado = String.format("%02d", dia)
                    fecha = "$anio/${mesFormateado}/$diaFormateado"
                }, anio, mes, dia
            )
            val horario = (14..22).map { String.format("%02d:00", it) }
            var selectedHour by remember { mutableStateOf("") }
            var medico by rememberSaveable { mutableStateOf("Santiago") }
            var showDialog by remember { mutableStateOf(false) }
            Column {


                Column {
                    Text(
                        text = "\n" +
                                "¡Reserva citas médicas al instante con nuestra app! Encuentra disponibilidad, recibe recordatorios y gestiona tus citas fácilmente. Accede a tu historial médico y garantiza tu privacidad. Tu salud, en tus manos.\n" +
                                "\n",
                        style = TextStyle(
                            fontSize = 19.sp,
                            color =Color( 45, 33, 88 )


                        ),
                        textAlign = TextAlign.Justify,
                        modifier = Modifier
                            .padding(20.dp)

                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(top = 5.dp, start = 15.dp, end = 15.dp)
                    ) {
                        val dateIcon = Icons.Default.DateRange

                        OutlinedTextField(
                            value = fecha,
                            onValueChange = { fecha = it },
                            label = {
                                Text(
                                    "Selecciona una fecha ",
                                    style = TextStyle(
                                        color = Color(25, 33, 156)

                                    ),
                                )
                            },
                            readOnly = true,
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,

                            leadingIcon = {
                                Icon(
                                    imageVector = dateIcon,
                                    contentDescription = null,
                                    tint = Color.Black,
                                    modifier = Modifier
                                        .size(32.dp)
                                        .clickable { mDatePickerDialog.show() }
                                        .align(Alignment.CenterVertically)
                                )
                            }
                        )

                    }
                    Row(
                        modifier = Modifier.padding(top = 5.dp, start = 15.dp, end = 15.dp)
                    ) {
                        val dateIcon = Icons.Default.DateRange

                        var expanded by remember { mutableStateOf(false) }

                        OutlinedButton(
                            onClick = { expanded = true },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 3.dp),
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            Text(
                                text = if (selectedHour.isNotEmpty()) selectedHour else "Selecciona una hora",
                                style = TextStyle(
                                    color = Color(25, 33, 156)
                                )
                            )
                        }

                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            modifier = Modifier.fillMaxWidth()

                        ) {
                            for (hour in horario) {
                                DropdownMenuItem(
                                    text = { Text(text = hour) },
                                    onClick = {
                                        selectedHour = hour
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }


                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.hospital),
                            contentDescription = "Descripción de la imagen",
                            modifier = Modifier
                                .padding(15.dp)
                        )

                    }
                    val db = FirebaseFirestore.getInstance()
                    var user by remember { mutableStateOf("") }
                    var mensajeConfirmacion by remember { mutableStateOf("") }
                    val context = LocalContext.current
                    var reservaConfirmada by remember { mutableStateOf(false) }
                    var numReservas by remember { mutableStateOf(0) } // Contador para el número de reservas
                    var plazasRestantes by remember { mutableStateOf(0) } // Número de plazas restantes

                    // Función para obtener el número de plazas restantes
                    fun obtenerPlazasRestantes() {
                        db.collection("Medico")
                            .whereEqualTo("medico", medico) // Filtrar por campo
                            .whereEqualTo("fecha", fecha) // Filtrar por día
                            .whereEqualTo("horario", selectedHour) // Filtrar por hora
                            .get()
                            .addOnSuccessListener { documents ->
                                val numReservas = documents.size()
                                plazasRestantes = 1 - numReservas
                            }
                            .addOnFailureListener { exception ->
                                Log.e(
                                    "Firestore",
                                    "Error al obtener las reservas: ${exception.message}"
                                )
                            }
                    }

                    LaunchedEffect(selectedHour, fecha, medico) {
                        obtenerPlazasRestantes()
                    }

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Plazas disponibles: $plazasRestantes",
                            style = TextStyle(fontSize = 18.sp),
                            modifier = Modifier.padding(bottom = 16.dp,start=105.dp)
                        )

                        Button(
                            onClick = {
                                if (fecha.isNotEmpty()) {
                                    if (plazasRestantes > 0) {
                                        showDialog = true
                                    } else {
                                        mensajeConfirmacion = "Esta hora está ocupada"
                                    }
                                } else {
                                    mensajeConfirmacion = "Seleccione una fecha primero"
                                }
                            },
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor =Color( 45, 33, 88 ),
                                contentColor = Color.White
                            ),
                        ) {
                            Text(
                                text = "Reservar", fontSize = 18.sp
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
                                            val nombreUsuario = SessionManager.getUsername(context)

                                            showDialog = false

                                            val reservaData = hashMapOf(
                                                "fecha" to fecha.toString(),
                                                "horario" to selectedHour.toString(),
                                                "medico" to medico.toString(),
                                                "user" to nombreUsuario.toString(),
                                            )

                                            db.collection("Medico")
                                                .add(reservaData)
                                                .addOnSuccessListener { documentReference ->
                                                    Log.d(
                                                        "Reserva",
                                                        "Reserva exitosa. Documento ID: ${documentReference.id}"
                                                    )

                                                    mensajeConfirmacion = "Reservado"
                                                    fecha = ""
                                                    selectedHour = ""
                                                    medico = "Santiago"
                                                    user = ""

                                                    // Incrementar el contador de reservas localmente
                                                    numReservas++

                                                    // Mostrar el número de reservas por consola
                                                    Log.d(
                                                        "Reservas",
                                                        "Número total de reservas: $numReservas"
                                                    )

                                                    // Establecer el estado de la reserva como confirmada
                                                    reservaConfirmada = true
                                                }
                                                .addOnFailureListener { exception ->
                                                    // Error al realizar la reserva
                                                    val errorMessage = exception.message
                                                        ?: "Error desconocido al realizar la reserva"
                                                    mensajeConfirmacion =
                                                        "Error al realizar la reserva: $errorMessage"
                                                    Log.e(
                                                        "Reserva",
                                                        "Error al realizar la reserva: $errorMessage"
                                                    )
                                                }
                                        },
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
                                modifier = Modifier.padding(top = 16.dp,start=110.dp),
                                color = if (mensajeConfirmacion.startsWith("Error")) Color.Red else Color.Green
                            )
                        }
                    }

                }
            }
        }
    }
}

