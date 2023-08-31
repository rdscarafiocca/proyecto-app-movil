package com.example.proyectofinal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import android.content.Context

class MainViewModel : ViewModel() {
    val comparador: LiveData<Comparador> get() = _comparador
    private var _comparador = MutableLiveData<Comparador>(Comparador("No se ha realizado ninguna comparación aún"))
    private lateinit var _contexto: Context

    //Para poder leer los recursos de 'string.xml'
    fun setearContexto(contexto: Context){
        _contexto = contexto
    }

    fun comparar(cadena1: String, cadena2: String) {
        var resultado: String

        resultado = if (cadena1.isEmpty() || cadena2.isEmpty())
            _contexto.getString(R.string.campos_vacios)
        else
            if (cadena1 == cadena2)
                _contexto.getString(R.string.textos_iguales)
            else _contexto.getString(R.string.textos_distintos)

        actualizarResultado(resultado)
    }

    fun actualizarResultado(next: String) {
        viewModelScope.launch {
            _comparador.value = Comparador(next)
        }
    }
}