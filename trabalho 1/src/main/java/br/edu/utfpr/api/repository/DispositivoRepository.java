package br.edu.utfpr.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.api.model.Dispositivo;

public interface DispositivoRepository extends JpaRepository<Dispositivo, Long>{
    List<Dispositivo> findByGatewayGatewayid(long gatewayid);

}
