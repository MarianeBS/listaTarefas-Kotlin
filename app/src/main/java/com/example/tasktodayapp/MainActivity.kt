package com.example.tasktodayapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tasktodayapp.model.Tarefa.Tarefa
import com.example.tasktodayapp.ui.theme.Blue
import com.example.tasktodayapp.ui.theme.Purple200
import com.example.tasktodayapp.ui.theme.TaskTodayAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreenContent(DrawerState(initialValue = DrawerValue.Closed))
        }
    }
}

@Composable
fun MainScreenContent(drawerState: DrawerState){
    val scaffoldState = rememberScaffoldState(drawerState = drawerState)
    val scope = rememberCoroutineScope()
    //val tabIndex = by remember {  mutableStateOf(0)  }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                backgroundColor = (Color(0xFFB76EF8)),
                contentColor = Color.Black,
                title = { Text(text = "TaskTodayApp")},
                navigationIcon = {
                    IconButton(
                        onClick = {
                            scope.launch {
                                scaffoldState.drawerState.open()
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Drawer Menu"
                        )
                    }
                }
            )
        },
        drawerBackgroundColor = (Color(0xFFC384FC)),
        drawerGesturesEnabled = drawerState.isOpen,
        drawerContent = {
            Box(
                modifier = Modifier
                    .background(color = Color(0xFFC384FC))
                    .height(16.dp)
                    .size(80.dp),

            )
            Column(modifier = Modifier.fillMaxWidth())
            {
                    Image(
                        painter = painterResource(R.drawable.barbie),
                        contentDescription = "I'm Barbie Girl!",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(200.dp)
                            .clip(CircleShape)
                            .align(Alignment.CenterHorizontally)
                    )
            }

            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Adicionar", modifier = Modifier.fillMaxWidth(), fontSize = 20.sp, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Atualizar", modifier = Modifier.fillMaxWidth(), fontSize = 20.sp, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Excluir", modifier = Modifier.fillMaxWidth(), fontSize = 20.sp, textAlign = TextAlign.Center)

        },
        content = {
                paddingValues -> Log.i("paddingValues", "$paddingValues")
            Column(
                modifier = Modifier
                    .background(color = Color(0xFFEFBBFA))
                    .fillMaxSize()
            ) {
                MySearchField(modificador = Modifier.fillMaxWidth())

                val provaMat = Tarefa(
                    "Estudar Prova de Matemática",
                    "Realizada em 28/06/2023",
                    Date(),
                    Date(),
                    status = 0.0
                )

                val monografia = Tarefa(
                    "Finalizar Monografia TCC",
                    "Terminar até 21/06/2023",
                    Date(),
                    Date(),
                    status = 0.0
                )

                val jetpackCompose = Tarefa(
                    "Estudar sobre Jetpack Compose",
                    "No tempo livre",
                    Date(),
                    Date(),
                    status = 0.0
                )

                val tailwind = Tarefa(
                    "Estudar sobre Tailwind CSS",
                    "Todas as manhãs",
                    Date(),
                    Date(),
                    status = 0.0
                )

                val provaLiteratura = Tarefa(
                    "Estudar Prova de Literatura",
                    "Realizada em 26/06/2023",
                    Date(),
                    Date(),
                    status = 0.0
                )

                var minhaListaDeTarefas = listOf<Tarefa>(provaMat, monografia, jetpackCompose, tailwind, provaLiteratura)

                MyTaskWidgetList(minhaListaDeTarefas)
            }//Column
        },//content
        bottomBar = {
            BottomAppBar(
                backgroundColor = (Color(0xFFB76EF8)),
                contentColor = Color.Black,
                content = { Text("App realizado por: Mariane Batista de Souza")
                    Spacer(modifier = Modifier.width(29.dp))
                    Text("RM: 22294")
                }
            )
        },

        isFloatingActionButtonDocked = false,
        floatingActionButton = { ExtendedFloatingActionButton(
            backgroundColor = (Color(0xFFB76EF8)),
            contentColor = Color.Black,
            icon = {
                   Icon(
                       imageVector = Icons.Default.AddCircle,
                       contentDescription = "Add Task"
                   )
            },
            text = { Text("ADD")  },
            onClick = { /*TODO*/ })

        }
    ) //Scaffold
} //MainScreenContent

@Composable
fun MyTaskWidgetList(listaDeTarefas: List<Tarefa>){
    listaDeTarefas.forEach(
        action = { MyTaskWidget(modificador = Modifier.fillMaxWidth(), taredasASerMostrada = it) }
    )
} //MyTaskWidgetList

@Composable
fun MySearchField(modificador: Modifier){
    TextField(
        value = "",
        onValueChange = {},
        modifier = modificador,
        placeholder = { Text(text = "Pesquisar tarefas")},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon")
        }
    )
} //MySearchField

@Composable
fun MyTaskWidget(
    modificador: Modifier,
    taredasASerMostrada: Tarefa
){
    val dateFormatter = SimpleDateFormat("EEE, MMM dd, yyyy", Locale.getDefault())

        Column(modifier = modificador
            //.border(width = 0.5.dp, color = Color.Gray, shape = RoundedCornerShape(8.dp))
            .padding(3.dp)
        ){
            Row(modifier = modificador) {
                Column(modifier = Modifier
                    .width(150.dp)
                    .padding(10.dp)){
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Icons of a pendent task")
                    Text(
                        text = dateFormatter.format(taredasASerMostrada.pzoFinal),
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Normal,
                        fontSize = 12.sp
                    )
                }
                Column(){
                    Text(
                        text = taredasASerMostrada.nome,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic
                    )

                    Text(
                        text = taredasASerMostrada.detalhes,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Normal
                    )
                }

            Spacer(modifier = Modifier.height(16.dp))
            }

    }
    Spacer(modifier = Modifier.height(16.dp))
} //MyTaskWidget


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreenContent(DrawerState(initialValue = DrawerValue.Closed))
}