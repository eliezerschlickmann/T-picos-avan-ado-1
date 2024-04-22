package br.edu.utfpr.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.utfpr.api.dto.DispositivoDTO;
import br.edu.utfpr.api.exceptions.NoteFoundException;
import br.edu.utfpr.api.model.Dispositivo;
import br.edu.utfpr.api.repository.DispositivoRepository;
import br.edu.utfpr.api.repository.GatewayRepository;


@Service
public class DispositivoService {

    @Autowired
    private DispositivoRepository dispositivoRepository;

    @Autowired
    private GatewayRepository gatewayRepository;

    public Dispositivo create(DispositivoDTO dto) throws NoteFoundException{
        var dispositivo = new Dispositivo();
        BeanUtils.copyProperties(dto, dispositivo);

        var gateway = gatewayRepository.findById(dto.gatewayid()); 
        if (gateway.isPresent()) {
            dispositivo.setGateway(gateway.get());
        } else {
            throw new NoteFoundException("Gateway não existe");
        }
        

        return dispositivoRepository.save(dispositivo);
    }

    public List<Dispositivo> getAll(){
        return dispositivoRepository.findAll();
    }

    public Optional<Dispositivo> getByid(long id){
        return dispositivoRepository.findById(id);
    }

    public Dispositivo update(Long id, DispositivoDTO dto) throws NoteFoundException{
        var res = dispositivoRepository.findById(id);

        if(res.isEmpty()){
            throw new NoteFoundException("Dispositivo " + id + " não existe.");
        }

        var dispositivo = res.get();
        dispositivo.setNome((dto.nome()));
        dispositivo.setDescricao(dto.descricao());
        dispositivo.setLocalizacao(dto.localizacao());
        var gateway = gatewayRepository.findById(dto.gatewayid()); 
        if (gateway.isPresent()) {
            dispositivo.setGateway(gateway.get());
        } else {
            throw new NoteFoundException("Gateway não existe");
        }
        

        return dispositivoRepository.save(dispositivo);
        
    }

    public void delete(long id) throws NoteFoundException{
        var res = dispositivoRepository.findById(id);

        if(res.isEmpty()){
            throw new NoteFoundException("Dispositivo " + id + " não existe.");
        }

        dispositivoRepository.delete(res.get());

    }

    public List<Dispositivo> findDispositivoByGatewayid(long gatewayid){
        return dispositivoRepository.findByGatewayGatewayid(gatewayid);
    }

}

