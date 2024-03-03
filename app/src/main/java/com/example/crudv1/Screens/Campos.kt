import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.crudv1.R

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun Campos(navController: NavController) {
    var mostrarTextoFutbol5 by remember { mutableStateOf(false) }
    var mostrarTextoFutbol7 by remember { mutableStateOf(false) }
    var mostrarTextoFutbol11 by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color( 25, 33, 156 ),
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Información Campos", color = Color.White)
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
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            Campo(
                title = "Hospital Universitario La Paz" ,

                imageResource = R.drawable.hospital1,
                description =
                        "Es uno de los hospitales más grandes de España y uno de los más reconocidos en Madrid.\n" +
                                "Ofrece una amplia gama de servicios médicos y cuenta con tecnología de vanguardia.\n" +
                                "Es un hospital universitario afiliado a la Universidad Autónoma de Madrid, lo que significa que también es un centro de investigación médica de renombre.\n" +
                                "Está ubicado en el norte de Madrid, en el barrio de Fuencarral-El Pardo.",
                mostrarTexto = mostrarTextoFutbol5,
                onToggle = { mostrarTextoFutbol5 = !mostrarTextoFutbol5 }
            )
            Campo(
                title = "Hospital Universitario Gregorio",
                imageResource = R.drawable.hospital2,
                description = "\n" +
                        "Es otro destacado hospital universitario ubicado en Madrid.\n" +
                        "Se distingue por su excelencia en la atención médica, la investigación y la formación de profesionales de la salud.\n" +
                        "Cuenta con una amplia variedad de especialidades médicas y quirúrgicas.\n" +
                        "Está situado en el distrito de Retiro, en el centro de Madrid.",
                mostrarTexto = mostrarTextoFutbol7,
                onToggle = { mostrarTextoFutbol7 = !mostrarTextoFutbol7 }
            )
            Campo(
                title = "Hospital Universitario Ramón y Cajal",
                imageResource = R.drawable.hospital3,
                description = "\n" +
                        "Es un hospital público de alta complejidad y referencia en Madrid.\n" +
                        "Destaca por su compromiso con la investigación y la innovación en el campo de la medicina.\n" +
                        "Ofrece servicios médicos especializados y cuenta con unidades de referencia a nivel nacional e internacional.\n" +
                        "Está ubicado en el norte de Madrid, en el distrito de Fuencarral-El Pardo.",
                mostrarTexto = mostrarTextoFutbol11, // Siempre mostrar el texto para Fútbol 11
                onToggle = {mostrarTextoFutbol11 = !mostrarTextoFutbol11} // No hay necesidad de cambiar el estado para Fútbol 11
            )
        }
    }
}
@Composable
fun Campo(
    title: String,
    imageResource: Int,
    description: String,
    mostrarTexto: Boolean,
    onToggle: () -> Unit
) {
    Column(
    ) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = "Descripción de la imagen",
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
                .padding(start=15.dp,end=15.dp)
        )
        Button(
            onClick = onToggle,
            colors = ButtonDefaults.buttonColors(
                Color( 45, 33, 88 )
            ),
            border = BorderStroke(1.dp, Color.Black),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .padding(start = 18.dp,  end = 18.dp, bottom = 7.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = title,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 19.sp
            )
        }

        if (mostrarTexto) {
            Text(
                text = description,
                fontSize = 18.sp,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(top=8.dp,start=20.dp,end=20.dp,bottom=7.dp)
            )
        }
    }
}