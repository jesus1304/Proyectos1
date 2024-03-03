package com.example.crudv1.navigation

import Campos
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.crudv1.Screens.MenuInicio
import com.example.crudv1.Screens.ClienteGuardar
import com.example.crudv1.Screens.ClienteModificar
import com.example.crudv1.Screens.Futbol
import com.example.crudv1.Screens.InicioSesion
import com.example.crudv1.Screens.ReservasFutbol

@Composable
fun AppNavigation() {
    val navigationController = rememberNavController()
    NavHost(navController = navigationController, startDestination = AppScreens.InicioSesion.ruta)
    {
        composable(AppScreens.MenuInicio.ruta) { MenuInicio(navigationController) }
        composable(AppScreens.ClienteGuardar.ruta) { ClienteGuardar(navigationController) }
        composable(AppScreens.ClienteModificar.ruta) { ClienteModificar(navigationController) }
        composable(AppScreens.InicioSesion.ruta) { InicioSesion(navigationController) }
        composable(AppScreens.Campos.ruta) { Campos(navigationController) }
        composable(AppScreens.Futbol.ruta) { Futbol(navigationController) }
        composable(AppScreens.ReservasFutbol.ruta) { ReservasFutbol(navigationController) }


    }
}