package com.example.cadastrodepessoas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PessoaAdapter (private val pessoas: List<Pessoa>) :
    RecyclerView.Adapter<PessoaAdapter.PessoaViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PessoaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            android.R.layout.simple_list_item_1, parent, false
        )
        return PessoaViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: PessoaViewHolder,
        position: Int
    ) {
        val pessoa = pessoas[position]
        holder.txtNome.text = "${pessoa.nome} - ${pessoa.idade} anos"
    }

    override fun getItemCount(): Int = pessoas.size

    inner class PessoaViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val txtNome: TextView = itemView.findViewById(android.R.id.text1)

    }
}