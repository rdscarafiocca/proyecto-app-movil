package com.example.proyectofinal

import org.junit.Assert.*
import org.junit.Rule

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.viewModelScope
import com.example.proyectofinal.MainViewModel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MainViewModelUnitTest {
    private lateinit var viewModel: MainViewModel

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()
    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        viewModel = MainViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun mainViewModel_CheckInitialValue() = runTest {
        val value = viewModel.comparador.value?.resultado
        assertEquals("No se ha realizado ninguna comparación aún", value)
    }

    @Test
    fun mainViewModel_TestEqualResult() = runTest {
        launch {
            viewModel.comparar("texto igual", "texto igual")
        }
        advanceUntilIdle()
        val value = viewModel.comparador.value?.resultado
        assertEquals("Los textos son iguales", value)
    }

    @Test
    fun mainViewModel_TestNotEqualResult() = runTest {
        launch {
            viewModel.comparar("texto distinto", "texto no igual")
        }
        advanceUntilIdle()
        val value = viewModel.comparador.value?.resultado
        assertEquals("Los textos son distintos", value)
    }
}