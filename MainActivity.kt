package com.example.cadastrodepessoas

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cadastrodepessoas.ui.theme.CadastroDePessoasTheme
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private val listaPessoas = mutableListOf<Pessoa>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PessoaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerPessoas)
        adapter = PessoaAdapter(listaPessoas)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val  fab: FloatingActionButton = findViewById(R.id.fabAddPessoa)
        fab.setOnClickListener {abrirFormulario()}

    }

    private fun abrirFormulario(){
        val view = layoutInflater.inflate(R.layout.formulario_pessoa, null)
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(view)

        val edtNome = view.findViewById<EditText>(R.id.edtNome)
        val edtIdade = view.findViewById<EditText>(R.id.edtIdade)
        val btnSalvar = view.findViewById<Button>(R.id.btnSalvar)

        btnSalvar.setOnClickListener {
            val  nome = edtNome.text.toString()
            val idade = edtIdade.text.toString().toIntOrNull()

            if (nome.isNotBlank() && idade != null){
                listaPessoas.add(Pessoa(nome, idade))
                adapter.notifyItemInserted(listaPessoas.size - 1)
                dialog.dismiss()
            }else{
                Toast.makeText(this, "Preencha os campos",
                    Toast.LENGTH_SHORT).show()
            }
        }
        dialog.show()
    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CadastroDePessoasTheme {
        Greeting("Android")
    }
}