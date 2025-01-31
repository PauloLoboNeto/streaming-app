package com.android.study.ui.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class MainViewModel : ViewModel() {

    private val _uiData = MutableLiveData<String>()
    val uiData: LiveData<String> = _uiData

    fun loadData() {
        _uiData.value = "Carregando dados..."

//        Thread.sleep(6000).run {
//            _uiData.value = "Dados carregados do servidor! com bloqueio na thread"
//        }
//         Simula uma chamada de rede com atraso


        CoroutineScope(Dispatchers.IO).launch {
            delay(2000) // Simula o tempo de resposta
            minhaFuncaoSuspensa()
            withContext(Dispatchers.Main) {
                _uiData.value = "Dados carregados do servidor!"
            }
        }
    }

    suspend fun minhaFuncaoSuspensa() {
        delay(1000L) // Função suspensa que pausa a execução por 1 segundo
        println("Execução retomada após 1 segundo")
    }
}
