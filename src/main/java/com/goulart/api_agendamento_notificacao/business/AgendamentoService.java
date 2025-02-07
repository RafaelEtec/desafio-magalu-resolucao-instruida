package com.goulart.api_agendamento_notificacao.business;

import com.goulart.api_agendamento_notificacao.business.mapper.IAgendamentoMapper;
import com.goulart.api_agendamento_notificacao.controller.dto.in.AgendamentoRecord;
import com.goulart.api_agendamento_notificacao.controller.dto.out.AgendamentoRecordOut;
import com.goulart.api_agendamento_notificacao.infrastructure.exception.NotFoundException;
import com.goulart.api_agendamento_notificacao.infrastructure.repositories.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AgendamentoService {
    private final AgendamentoRepository repository;
    private final IAgendamentoMapper agendamentoMapper;

    public AgendamentoRecordOut gravarAgendamento(AgendamentoRecord agendamento){
        return agendamentoMapper.paraOut(
                repository.save(
                        agendamentoMapper.paraEntity(agendamento)));
    }

    public AgendamentoRecordOut buscarAgendamentosPorId(Long id) {
        return agendamentoMapper.paraOut(
                repository.findById(id)
                        .orElseThrow(() -> new NotFoundException("ID Não encontrado")));
    }
}