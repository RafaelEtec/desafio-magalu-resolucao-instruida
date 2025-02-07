package com.goulart.api_agendamento_notificacao.infrastructure.repositories;

import com.goulart.api_agendamento_notificacao.infrastructure.entities.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
}