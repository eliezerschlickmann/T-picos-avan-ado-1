package br.edu.utfpr.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.utfpr.api.dto.AtuadorDTO;
import br.edu.utfpr.api.exceptions.NoteFoundException;
import br.edu.utfpr.api.model.Atuador;
import br.edu.utfpr.api.repository.AtuadorRepository;
import br.edu.utfpr.api.repository.DispositivoRepository;

@Service
public class AtuadorService {

    @Autowired
    private AtuadorRepository atuadorRepository;

    @Autowired
    private DispositivoRepository dispositivoRepository;

    public Atuador create(AtuadorDTO dto) throws NoteFoundException{
        var atuador = new Atuador();
        BeanUtils.copyProperties(dto, atuador);

        var dispositivo = dispositivoRepository.findById(dto.dispositivoid()); // Corrigido para usar getPessoasid()
        if (dispositivo.isPresent()) {
            atuador.setDispositivo(dispositivo.get());
        } else {
            throw new NoteFoundException("Dispositivo não existe");
        }
        
        //Persistir no banco de dados
        return atuadorRepository.save(atuador);
    }

    public List<Atuador> getAll(){
        return atuadorRepository.findAll();
    }

    public Optional<Atuador> getByid(long id){
        return atuadorRepository.findById(id);
    }

    public Atuador update(Long id, AtuadorDTO dto) throws NoteFoundException{
        var res = atuadorRepository.findById(id);

        if(res.isEmpty()){
            throw new NoteFoundException("Atuador " + id + " não existe.");
        }

        var atuador = res.get();
        atuador.setNome((dto.nome()));
        var dispositivo = dispositivoRepository.findById(dto.dispositivoid());
        if (dispositivo.isPresent()) {
            atuador.setDispositivo(dispositivo.get());
        } else {
            throw new NoteFoundException("Dispositivo não existe");
        }

        return atuadorRepository.save(atuador);
        
    }

    public void delete(long id) throws NoteFoundException{
        var res = atuadorRepository.findById(id);

        if(res.isEmpty()){
            throw new NoteFoundException("Atuador " + id + " não existe.");
        }

        atuadorRepository.delete(res.get());

    }

    public List<Atuador> findAtuadorByDispositivoid(long dispositivoid){
        return atuadorRepository.findByDispositivoDispositivoid(dispositivoid);
    }

}
