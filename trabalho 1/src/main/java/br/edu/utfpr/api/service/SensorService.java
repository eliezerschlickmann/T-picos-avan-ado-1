package br.edu.utfpr.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.utfpr.api.dto.SensorDTO;
import br.edu.utfpr.api.exceptions.NoteFoundException;
import br.edu.utfpr.api.model.Sensor;
import br.edu.utfpr.api.repository.SensorRepository;
import br.edu.utfpr.api.repository.DispositivoRepository;


@Service
public class SensorService {

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private DispositivoRepository dispositivoRepository;

    public Sensor create(SensorDTO dto) throws NoteFoundException{
        var sensor = new Sensor();
        BeanUtils.copyProperties(dto, sensor);

        var dispositivo = dispositivoRepository.findById(dto.dispositivoid()); // Corrigido para usar getPessoasid()
        if (dispositivo.isPresent()) {
            sensor.setDispositivo(dispositivo.get());
        } else {
            throw new NoteFoundException("Dispositivo não existe");
        }
        
        //Persistir no banco de dados
        return sensorRepository.save(sensor);
    }

    public List<Sensor> getAll(){
        return sensorRepository.findAll();
    }

    public Optional<Sensor> getByid(long id){
        return sensorRepository.findById(id);
    }

    public Sensor update(Long id, SensorDTO dto) throws NoteFoundException{
        var res = sensorRepository.findById(id);

        if(res.isEmpty()){
            throw new NoteFoundException("Sensor " + id + " não existe.");
        }

        var sensor = res.get();
        sensor.setNome((dto.nome()));
        sensor.setTipo(dto.tipo());
        var dispositivo = dispositivoRepository.findById(dto.dispositivoid()); // Corrigido para usar getPessoasid()
        if (dispositivo.isPresent()) {
            sensor.setDispositivo(dispositivo.get());
        } else {
            throw new NoteFoundException("Dispositivo não existe");
        }

        return sensorRepository.save(sensor);
        
    }

    public void delete(long id) throws NoteFoundException{
        var res = sensorRepository.findById(id);

        if(res.isEmpty()){
            throw new NoteFoundException("Sensor " + id + " não existe.");
        }

        sensorRepository.delete(res.get());

    }

    public List<Sensor> findSensorByDispositivoid(long dispositivoid){
        return sensorRepository.findByDispositivoDispositivoid(dispositivoid);
    }

}
