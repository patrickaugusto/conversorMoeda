package br.com.conversor.demo.service;

import br.com.conversor.demo.dto.ConversaoRequest;
import br.com.conversor.demo.dto.ConversaoResponse;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConversaoServiceTest {

    @Mock
    private ApiService apiService;

    @InjectMocks
    private ConversaoService conversaoService;

    @Test
    void testConversaoBemSucedida() throws Exception {
        // Arrange
        ConversaoRequest request = new ConversaoRequest(100.0, "USD", "BRL");
        JsonObject taxasMock = new JsonObject();
        taxasMock.addProperty("USD", 5.0);
        taxasMock.addProperty("last_update", "2025-01-01T00:00:00Z");
        when(apiService.obterTaxasDeCambio()).thenReturn(taxasMock);

        // Act
        ConversaoResponse response = conversaoService.converter(request);

        // Assert
        assertNotNull(response);
        assertEquals(500.0, response.valorConvertido());
        assertEquals("2025-01-01T00:00:00Z", response.ultimaAtualizacao());
    }

    @Test
    void testMoedaDeDestinoNaoEncontrada() throws Exception {
        // Arrange
        ConversaoRequest request = new ConversaoRequest(100.0, "BRL", "USD");
        JsonObject taxasMock = new JsonObject();
        taxasMock.addProperty("USD", 5.0); // Moeda EUR não está presente
        taxasMock.addProperty("last_update", "2025-01-01T00:00:00Z");
        when(apiService.obterTaxasDeCambio()).thenReturn(taxasMock);

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> conversaoService.converter(request));
        assertEquals("Cannot invoke \"JsonElement.getAsDouble()\" because the return value of \"com.google.gson.JsonObject.get(String)\" is null", exception.getMessage());
    }

    @Test
    void testErroNaApiTaxasNulas() throws Exception {
        // Arrange
        ConversaoRequest request = new ConversaoRequest(100.0, "BRL", "EUR");
        when(apiService.obterTaxasDeCambio()).thenReturn(null);

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> conversaoService.converter(request));
        assertEquals("Cannot invoke \"com.google.gson.JsonObject.get(String)\" because \"taxasDeCambio\" is null", exception.getMessage());
    }

    @Test
    void testFalhaUltimaAtualizacaoAusente() throws Exception {
        // Arrange
        ConversaoRequest request = new ConversaoRequest(100.0, "BRL", "EUR");
        JsonObject taxasMock = new JsonObject();
        taxasMock.addProperty("USD", 5.0); // Campo last_update ausente
        when(apiService.obterTaxasDeCambio()).thenReturn(taxasMock);

        // Act
        Exception exception = assertThrows(Exception.class, () -> conversaoService.converter(request));
        assertEquals("Cannot invoke \"com.google.gson.JsonElement.getAsString()\" because the return value of \"com.google.gson.JsonObject.get(String)\" is null", exception.getMessage());
    }
}
