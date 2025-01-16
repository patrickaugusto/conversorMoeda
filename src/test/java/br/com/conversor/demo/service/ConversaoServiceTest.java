package br.com.conversor.demo.service;

import br.com.conversor.demo.dto.ConversaoResponse;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ConversaoServiceTest {

    @Mock
    private ApiService apiServiceMock;

    private ConversaoService conversaoService;

    // Método para inicializar o ambiente de testes
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa os mocks
        conversaoService = new ConversaoService(); // Instancia o serviço de conversão
        conversaoService.setApiService(apiServiceMock); // Injeta o mock no serviço
    }

    // Teste para verificar a conversão de valores
    @Test
    void testConverter() throws Exception {
        // Mock da resposta da API
        JsonObject mockResponse = new JsonObject();
        mockResponse.addProperty("USD", 1.2); // Mock da taxa de câmbio
        mockResponse.addProperty("last_update", "2025-01-14 12:30:00"); // Mock da data de atualização

        // Configura o mock para retornar a resposta simulada
        when(apiServiceMock.obterTaxasDeCambio()).thenReturn(mockResponse);

        // Chama o método que realiza a conversão
        ConversaoResponse result = conversaoService.converter(100.0, "BRL", "USD");

        // Verifica se o resultado da conversão está correto
        assertNotNull(result); // Verifica que o resultado não é nulo
        assertEquals(120.0, result.valorConvertido(), 0.01);  // Verifica a conversão (tolerância de 0.01)
        assertEquals("2025-01-14 12:30:00", result.ultimaAtualizacao()); // Verifica a data de atualização

        // Verifica que o método da API foi chamado uma vez
        verify(apiServiceMock, times(1)).obterTaxasDeCambio();
    }

    // Teste para verificar se uma exceção é lançada quando a API falha
    @Test
    void testConverterThrowsExceptionWhenApiFails() throws Exception {
        // Configura o mock para lançar uma exceção
        when(apiServiceMock.obterTaxasDeCambio()).thenThrow(new RuntimeException("API failed"));

        // Verifica que uma exceção é lançada
        assertThrows(RuntimeException.class, () -> conversaoService.converter(100.0, "BRL", "USD"));
    }
}
