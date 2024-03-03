package com.example.crudv1.Screens

import android.app.DatePickerDialog
import android.util.Log
import android.widget.DatePicker
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.crudv1.navigation.SessionManager
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import org.checkerframework.checker.units.qual.C
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReservasFutbol(navController: NavHostController) {
    var clienteEncontrado by remember { mutableStateOf(false) }


    data class Reserva(
        val medico: String,
        val user:String,
        val fecha: String,
        val horario: String

    )

    var reservas = remember { mutableStateOf(emptyList<Reserva>()) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color( 25, 33, 156 ),
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Reservas Citas", color = Color.White)
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

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Button(
                onClick = { navController.navigate("Futbol") },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.padding(start = 10.dp,end = 10.dp,top=20.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor =Color( 45, 33, 88 ),
                    contentColor = Color.White
                ),
                content = {
                    Text(
                        text = "Nueva reserva",
                        color = Color.White,
                        fontSize = 20.sp,
                        style = TextStyle.Default
                    )
                }
            )

            var datos by remember { mutableStateOf("") }
            val context = LocalContext.current
            LaunchedEffect(Unit) {
                val db = FirebaseFirestore.getInstance()
                val coleccion = "Medico"
                val nombreUsuario = SessionManager.getUsername(context)
                val fechaActual = Calendar.getInstance().time

                db.collection(coleccion)
                    .whereEqualTo("user", nombreUsuario)
                    .get()
                    .addOnSuccessListener { resultado ->
                        val reservasUsuario = resultado.documents.map { cliente ->
                            Reserva(
                                fecha = cliente.getString("fecha") ?: "",
                                medico = cliente.getString("medico") ?: "",
                                horario = cliente.getString("horario") ?: "",
                                user = cliente.getString("user") ?: ""
                            )
                        }.filter { it.fecha > SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(fechaActual) }
                            .sortedBy { it.fecha }

                        reservas.value = reservasUsuario
                        clienteEncontrado = reservasUsuario.isNotEmpty()
                    }
                    .addOnFailureListener {
                        datos = "No ha podido conectar"
                    }
            }

            var nombreFiltrar by remember { mutableStateOf("") }
            var fecha by remember { mutableStateOf("") }
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

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                val dateIcon = Icons.Default.DateRange

                OutlinedTextField(
                    value = fecha,
                    onValueChange = { fecha = it },
                    label = { Text("Buscar Fecha ",color=Color( 25, 33, 156 )
                    ) },
                    readOnly = true,
                    modifier = Modifier.fillMaxWidth()
                        .padding(start=15.dp,end=15.dp),
                    singleLine = true,
                    leadingIcon = {

                        Icon(
                            imageVector = dateIcon,
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier
                                .size(30.dp)
                                .clickable { mDatePickerDialog.show() }
                                .align(Alignment.CenterVertically)

                        )
                    }
                )

            }
            val reservasFiltradasPorNombreYFecha =
                if (nombreFiltrar.isNotBlank() || fecha.isNotBlank()) {
                    reservas.value.filter {

                                (it.fecha.equals(
                                    fecha,
                                    ignoreCase = true
                                ) || fecha.isBlank())
                    }.sortedWith(compareBy({ it.fecha }, { it.horario }))
                } else {
                    reservas.value.sortedWith(compareBy({ it.fecha }, { it.horario }))
                }

            for (reserva in reservasFiltradasPorNombreYFecha) {
                Column(
                    modifier = Modifier.padding(start = 15.dp, end = 15.dp,bottom=15.dp)
                        .fillMaxWidth()
                        .border(2.dp, color = Color.LightGray, shape = RoundedCornerShape(8.dp)
                        ),

                )
                {

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.padding(vertical = 10.dp)
                    ) {
                        Column {

                            Text(
                                modifier = Modifier.padding(start = 35.dp, top = 10.dp),
                                text = " ${reserva.fecha.substring(8,10)}/${reserva.fecha.substring(5,7)}/${reserva.fecha.substring(0,4)}",
                                fontSize = 20.sp,
                                color=Color( 45, 33, 88 )

                            )
                            Text(
                                modifier = Modifier.padding(start = 55.dp, top = 10.dp),
                                text = " ${reserva.horario}h",
                                fontSize = 20.sp,
                                color=Color( 45, 33, 88 )

                            )
                        }
                        Column {
                                Text(
                                    modifier = Modifier
                                        .padding(start = 70.dp, top = 20.dp),
                                    text = "${reserva.medico}",
                                    fontSize = 23.sp,
                                    fontWeight = FontWeight.Bold,
                                    color =Color( 45, 33, 88 )

                                )
                            }
                        }
                    val db = FirebaseFirestore.getInstance()
                    val coleccion = "Medico"
                    var user by remember { mutableStateOf("") }
                    var mensajeConfirmacion by remember { mutableStateOf("") }
                    var fechaSeleccionada by remember { mutableStateOf("") }
                    var horaSeleccionada by remember { mutableStateOf("") }
                    var campoSeleccionada by remember { mutableStateOf("") }
                    var showDialog by remember { mutableStateOf(false) }

                    Button(
                        onClick = {
                            fechaSeleccionada = reserva.fecha
                            horaSeleccionada = reserva.horario
                            campoSeleccionada = reserva.medico
                            user = reserva.user
                            showDialog = true
                        },
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.padding(start = 40.dp,end = 40.dp,bottom=15.dp)
                            .fillMaxWidth()
                            .border( 2.dp, color=Color.LightGray,shape=RoundedCornerShape(8.dp)),

                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.Red
                        ),
                    ) {
                        Text(
                            text = "Cancelar",
                            fontSize = 18.sp
                        )
                    }

                    if (showDialog) {
                        AlertDialog(
                            onDismissRequest = { showDialog = false },
                            title = { Text("Confirmar eliminación") },
                            text = { Text("¿Estás seguro de eliminar esta reserva?") },
                            confirmButton = {
                                Button(
                                    onClick = {
                                        val nombreUsuario = SessionManager.getUsername(context)

                                        db.collection(coleccion)
                                            .whereEqualTo("user", nombreUsuario)
                                            .whereEqualTo("fecha", fechaSeleccionada)
                                            .whereEqualTo("horario", horaSeleccionada)
                                            .whereEqualTo("medico", campoSeleccionada)
                                            .get()
                                            .addOnSuccessListener { querySnapshot ->
                                                for (document in querySnapshot) {
                                                    db.collection(coleccion)
                                                        .document(document.id)
                                                        .delete()
                                                        .addOnSuccessListener {
                                                            mensajeConfirmacion = "Reserva cancelada correctamente"
                                                        }
                                                        .addOnFailureListener { exception ->
                                                            mensajeConfirmacion = "Error al cancelar la reserva: $exception"
                                                        }
                                                }
                                            }
                                            .addOnFailureListener { exception ->
                                                mensajeConfirmacion = "Error al buscar la reserva: $exception"
                                            }

                                        showDialog = false
                                    },

                                    ) {
                                    Text("Confirmar")
                                }
                            },
                            dismissButton = {
                                Button(
                                    onClick = { showDialog = false
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
                            modifier = Modifier.padding(top = 30.dp),
                            color = if (mensajeConfirmacion.startsWith("Error")) Color.Red else Color.Blue
                        )
                    }

                }
            }
            Button(
                onClick = { navController.navigate("Campos") },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.padding(start = 15.dp, end = 15.dp,bottom=15.dp)
                    .fillMaxWidth()
                    .border(2.dp, color = Color.LightGray, shape = RoundedCornerShape(8.dp)
                    ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                ),
                content = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        val icon = Icons.Default.Search
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            modifier = Modifier.size(28.dp),
                            tint = Color.Gray
                        )
                        Spacer(modifier = Modifier.width(8.dp)) // Espacio entre el icono y el texto
                        Text(
                            text = "Informacíón Hospitales",
                            color =Color( 45, 33, 88 )
                            ,
                            fontSize = 20.sp,
                            style = TextStyle.Default
                        )
                    }
                }
            )
        }
    }
}