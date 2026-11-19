package com.example.productos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.productos.ui.theme.ProductosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProductosTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    innerPadding -> AppContent(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun AppContent(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "VistaInicioSesion") {
        composable("VistaInicioSesion") { VistaInicioSesion(navController, modifier) }
        composable("VistaMenu") { VistaMenu(navController, modifier) }
        composable("VistaCategorias") { VistaCategorias(navController, modifier) }
        composable("VistaProductos") { VistaProductos(navController, modifier) }
    }
}

@Composable
fun VistaInicioSesion(navController: NavHostController, modifier: Modifier) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    var usuario: String by remember { mutableStateOf("") }
    var contrasena: String by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
            .horizontalScroll(scrollState)
            .padding(8.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Inicio de Sesión",
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Usuario:")
        TextField(
            value = usuario,
            onValueChange = { usuario = it },
            placeholder = { Text("Ingresa tu usuario") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Contraseña:")
        TextField(
            value = contrasena,
            onValueChange = { contrasena = it },
            placeholder = { Text("Ingresa tu contraseña") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (usuario == "admin" && contrasena == "123") {
                    navController.navigate("VistaMenu")
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
            modifier = Modifier.align(Alignment.Start)
        ) {
            Text(
                "Iniciar Sesión",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun VistaMenu(navController: NavHostController, modifier: Modifier) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
            .horizontalScroll(scrollState)
            .padding(8.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Button(
            onClick = {
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color.Blue
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                "Cerrar Sesión",
                style = TextStyle(textDecoration = TextDecoration.Underline),
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Menú",
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.navigate("VistaCategorias")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                "Categorías",
                style = TextStyle(textDecoration = TextDecoration.Underline),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Button(
            onClick = {
                navController.navigate("VistaProductos")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                "Productos",
                style = TextStyle(textDecoration = TextDecoration.Underline),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun VistaCategorias(navController: NavHostController, modifier: Modifier) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    var id: String by remember { mutableStateOf("") }
    var nombre: String by remember { mutableStateOf("") }

    data class Categoria(val id: Int, val nombre: String)
    val categorias = remember {
        mutableStateListOf(
            Categoria(1, "Galletas"),
            Categoria(2, "Fritos"),
            Categoria(3, "Refrescos")
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
            .horizontalScroll(scrollState)
            .padding(8.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Button(
            onClick = {
                navController.navigate("VistaMenu")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color.Blue
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                "Menú",
                style = TextStyle(textDecoration = TextDecoration.Underline),
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Categorías",
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Id:")
        TextField(
            value = id,
            onValueChange = { id = it },
            modifier = Modifier.fillMaxWidth(),
            readOnly = true
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Nombre:")
        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            placeholder = { Text("Ingresa el nombre") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
            modifier = Modifier.align(Alignment.Start)
        ) {
            Text(
                "Guardar",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(24.dp))

        Row {
            Text("Id", modifier = Modifier.width(50.dp), fontWeight = FontWeight.Bold)
            Text("Nombre", modifier = Modifier.width(100.dp), fontWeight = FontWeight.Bold)
            Text("Eliminar", modifier = Modifier.width(100.dp), fontWeight = FontWeight.Bold)
        }
        Divider()
        categorias.forEachIndexed { index, categoria ->
            val bgColor = if (index % 2 == 0) Color(0xFFF5F5F5) else Color.White

            Row (
                modifier = Modifier
                    .background(bgColor)
            ) {
                Text(categoria.id.toString(), modifier = Modifier
                    .width(50.dp)
                )
                Text(categoria.nombre, modifier = Modifier
                    .width(100.dp)
                )
                Button(onClick = {
                    categorias.removeAt(index)
                }) {
                    Text("Eliminar")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VistaProductos(navController: NavHostController, modifier: Modifier) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    var id: String by remember { mutableStateOf("") }
    var nombre: String by remember { mutableStateOf("") }
    var precio: String by remember { mutableStateOf("") }

    data class Categoria(val id: Int, val nombre: String)
    val categorias = listOf(
        Categoria(1, "Galletas"),
        Categoria(2, "Fritos"),
        Categoria(3, "Refrescos")
    )
    var categoriaExpandido by remember { mutableStateOf(false) }
    var categoria by remember { mutableStateOf(categorias[0]) }

    data class Producto(val id: Int, val nombre: String, val precio: Double, val nombreCategoria: String)
    val productos = remember {
        mutableStateListOf(
            Producto(1, "Sponch Fresa", 23.0, categorias[0].nombre),
            Producto(2, "Emperador Combinado", 22.0, categorias[0].nombre),
            Producto(3, "Coca Cola 600 ML", 20.0, categorias[2].nombre)
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
            .horizontalScroll(scrollState)
            .padding(8.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Button(
            onClick = {
                navController.navigate("VistaMenu")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color.Blue
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                "Menú",
                style = TextStyle(textDecoration = TextDecoration.Underline),
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Productos",
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Id:")
        TextField(
            value = id,
            onValueChange = { id = it },
            modifier = Modifier.fillMaxWidth(),
            readOnly = true
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Nombre:")
        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            placeholder = { Text("Ingresa el nombre") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Precio:")
        TextField(
            value = precio,
            onValueChange = { precio = it },
            placeholder = { Text("Ingresa el precio") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Categoría:")
        ExposedDropdownMenuBox(
            expanded = categoriaExpandido,
            onExpandedChange = { categoriaExpandido = !categoriaExpandido }
        ) {
            TextField(
                value = categoria.nombre,
                onValueChange = {},
                readOnly = true,
                label = { Text("Selecciona una opción") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = categoriaExpandido)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )

            DropdownMenu(
                expanded = categoriaExpandido,
                onDismissRequest = { categoriaExpandido = false }
            ) {
                categorias.forEach { opcion ->
                    DropdownMenuItem(
                        text = { Text(opcion.nombre) },
                        onClick = {
                            categoria = opcion
                            categoriaExpandido = false
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
            modifier = Modifier.align(Alignment.Start)
        ) {
            Text(
                "Guardar",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(24.dp))

        Row {
            Text("Id", modifier = Modifier.width(50.dp), fontWeight = FontWeight.Bold)
            Text("Nombre", modifier = Modifier.width(100.dp), fontWeight = FontWeight.Bold)
            Text("Precio", modifier = Modifier.width(100.dp), fontWeight = FontWeight.Bold)
            Text("Categoría", modifier = Modifier.width(100.dp), fontWeight = FontWeight.Bold)
            Text("Eliminar", modifier = Modifier.width(100.dp), fontWeight = FontWeight.Bold)
        }
        Divider()
        productos.forEachIndexed { index, producto ->
            val bgColor = if (index % 2 == 0) Color(0xFFF5F5F5) else Color.White

            Row (
                modifier = Modifier
                    .background(bgColor)
            ) {
                Text(producto.id.toString(), modifier = Modifier
                    .width(50.dp)
                )
                Text(producto.nombre, modifier = Modifier
                    .width(100.dp)
                )
                Text(producto.precio.toString(), modifier = Modifier
                    .width(100.dp)
                )
                Text(producto.nombreCategoria, modifier = Modifier
                    .width(100.dp)
                )
                Button(onClick = {
                    productos.removeAt(index)
                }) {
                    Text("Eliminar")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppContentPreview() {
    ProductosTheme {
        AppContent()
    }
}
