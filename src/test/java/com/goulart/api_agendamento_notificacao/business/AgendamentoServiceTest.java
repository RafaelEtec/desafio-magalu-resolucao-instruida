package com.goulart.api_agendamento_notificacao.business;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.goulart.api_agendamento_notificacao.business.mapper.IAgendamentoMapper;
import com.goulart.api_agendamento_notificacao.controller.dto.in.AgendamentoRecord;
import com.goulart.api_agendamento_notificacao.controller.dto.out.AgendamentoRecordOut;
import com.goulart.api_agendamento_notificacao.infrastructure.entities.Agendamento;
import com.goulart.api_agendamento_notificacao.infrastructure.enums.StatusNotificacaoEnum;
import com.goulart.api_agendamento_notificacao.infrastructure.repositories.AgendamentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AgendamentoServiceTest {

    @InjectMocks
    private AgendamentoService agendamentoService;

    @Mock
    private AgendamentoRepository agendamentoRepository;

    @Mock
    IAgendamentoMapper agendamentoMapper;

    private AgendamentoRecord agendamentoRecord;
    private AgendamentoRecordOut agendamentoRecordOut;
    private Agendamento agendamentoEntity;

    @BeforeEach
    void setUp() {
        agendamentoEntity = new Agendamento(1L, "email@email.com", "11977774444", LocalDateTime.of(2025, 01, 02, 11, 01, 01), null, LocalDateTime.of(2025, 01, 02, 11, 01, 01), "teste - teste - teste - teste", StatusNotificacaoEnum.AGENDADO);
        agendamentoRecord = new AgendamentoRecord("email@email.com", "11977774444", "teste - teste - teste - teste", LocalDateTime.of(2025, 01, 02, 11, 01, 01));
        agendamentoRecordOut = new AgendamentoRecordOut(1L, "email@email.com", "11977774444", "teste - teste - teste - teste", LocalDateTime.of(2025, 01, 02, 11, 01, 01), StatusNotificacaoEnum.AGENDADO);
    }

    @Test
    void deveGravarAgendamentoComSucesso() throws Exception {
        when(agendamentoMapper.paraEntity(agendamentoRecord)).thenReturn(agendamentoEntity);
        when(agendamentoRepository.save(agendamentoEntity)).thenReturn(agendamentoEntity);
        when(agendamentoMapper.paraOut(agendamentoEntity)).thenReturn(agendamentoRecordOut);

        AgendamentoRecordOut out = agendamentoService.gravarAgendamento(agendamentoRecord);

        verify(agendamentoMapper, times(1)).paraEntity(agendamentoRecord);
        verify(agendamentoRepository, times(1)).save(agendamentoEntity);
        verify(agendamentoMapper, times(1)).paraOut(agendamentoEntity);

        assertThat(out).usingRecursiveComparison().isEqualTo(agendamentoRecordOut);
    }
}