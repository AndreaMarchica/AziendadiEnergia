package BuildWeek2Team1.AziendaDiEnergia.services;

import BuildWeek2Team1.AziendaDiEnergia.Service.IndirizzoService;
import BuildWeek2Team1.AziendaDiEnergia.config.CloudinaryConfig;
import BuildWeek2Team1.AziendaDiEnergia.config.EmailSender;
import BuildWeek2Team1.AziendaDiEnergia.entities.Cliente;
import BuildWeek2Team1.AziendaDiEnergia.entities.Indirizzo;
import BuildWeek2Team1.AziendaDiEnergia.exceptions.NotFoundException;
import BuildWeek2Team1.AziendaDiEnergia.payloads.clienti.NewClienteDTO;
import BuildWeek2Team1.AziendaDiEnergia.repositories.ClienteRepository;
import BuildWeek2Team1.AziendaDiEnergia.repositories.IndirizzoRepo;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private IndirizzoRepo indirizzoRepo;
    @Autowired
    private IndirizzoService indirizzoService;
    @Autowired
    private Cloudinary cloudinaryUploader;

    @Transactional
    public Cliente save(NewClienteDTO body){
        Cliente cliente = new Cliente();
        cliente.setRagioneSociale(body.ragioneSociale());
        cliente.setPartitaIva(body.partitaIva());
        cliente.setEmail(body.email());
        cliente.setDataInserimento(LocalDate.now());
        cliente.setDataUltimoContatto(body.dataUltimoContatto());
        cliente.setFatturatoAnnuale(body.fatturatoAnnuale());
        cliente.setPec(body.pec());
        cliente.setTelefono(body.telefono());
        cliente.setEmailContatto(body.emailContatto());
        cliente.setNomeContatto(body.nomeContatto());
        cliente.setCognomeContatto(body.cognomeContatto());
        cliente.setTelefonoContatto(body.telefonoContatto());
        cliente.setLogoAziendale("https://ui-avatars.com/api/?name=" + body.nomeContatto() + "+" + body.cognomeContatto());



        Indirizzo indirizzo1 = new Indirizzo();
        indirizzo1.setAdress(body.indirizzo1Adress());
        indirizzo1.setCivico(body.indirizzo1Civico());
        indirizzo1.setCap(body.indirizzo1Cap());
        indirizzo1.setLocalita(body.indirizzo1Localita());


        Indirizzo indirizzo2 = null;
        if (body.indirizzo2Adress() != null && body.indirizzo2Civico() != 0 && body.indirizzo2Cap() != 0L && body.indirizzo2Localita() != null) {
            indirizzo2 = new Indirizzo();
            indirizzo2.setAdress(body.indirizzo2Adress());
            indirizzo2.setCivico(body.indirizzo2Civico());
            indirizzo2.setCap(body.indirizzo2Cap());
            indirizzo2.setLocalita(body.indirizzo2Localita());

        }
        cliente.setIndirizzo1(indirizzo1);
        cliente.setIndirizzo2(indirizzo2);

        indirizzoService.saveIndirizzo(indirizzo1);
        if (indirizzo2 != null) {
            indirizzoService.saveIndirizzo(indirizzo2);
        }



        return clienteRepository.save(cliente);
    }

    public Page<Cliente> getClienti(int pageNumber, int size, String sort) {
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(sort));
        return clienteRepository.findAll(pageable);
    }

    public Page<Cliente> getClienti2(String nomeContatto, LocalDate dataUltimoContatto, LocalDate dataInserimento,
                                     long minimo, long massimo, int pageNumber, int size, String sort) {
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(sort));
        Set<Cliente> clienti = new HashSet<>();

        if (minimo > 0 && massimo < 99999999){
            Set<Cliente> lista1 = clienteRepository.findByFatturatoAnnualeBetween(minimo, massimo);
            clienti.addAll(lista1);
            System.out.println(lista1 + "Prima condizione");
        }
        if (dataUltimoContatto != null) {
            Set<Cliente> lista1 = clienteRepository.findByDataUltimoContatto(dataUltimoContatto);
            clienti.addAll(lista1);
            System.out.println(lista1 + "Seconda condizione");

        }
        if (dataInserimento != null) {
            Set<Cliente> lista1 = clienteRepository.findByDataInserimento(dataInserimento);
            clienti.addAll(lista1);
            System.out.println(lista1 + "Terza condizione");

        }
        if (!nomeContatto.equals("*")) {
            Set<Cliente> lista1 = clienteRepository.findByNomeContattoContainingIgnoreCase(nomeContatto);
            clienti.addAll(lista1);
            System.out.println(lista1 + "Quarta condizione");

        }

        //Secondo filtraggio

        if (minimo > 0 && massimo < 99999999){
            clienti = clienti.stream().filter(cliente -> cliente.getFatturatoAnnuale() > minimo && cliente.getFatturatoAnnuale() < massimo).collect(Collectors.toSet());
        }

        if (dataUltimoContatto != null) {
            clienti = clienti.stream().filter(cliente -> cliente.getDataUltimoContatto().isEqual(dataUltimoContatto)).collect(Collectors.toSet());
        }

        if (dataInserimento != null) {
            clienti = clienti.stream().filter(cliente -> cliente.getDataInserimento().isEqual(dataInserimento)).collect(Collectors.toSet());

        }
        if (nomeContatto != null) {
            clienti = clienti.stream().filter(cliente -> cliente.getNomeContatto().contains(nomeContatto)).collect(Collectors.toSet());

        }
        List<Cliente> filteredList = new ArrayList<>();
        if(minimo == 0 && massimo == 99999999 && dataUltimoContatto == null && dataInserimento == null && nomeContatto.equals("*")){
            System.out.println("True");
            return getClienti(pageNumber, size, sort);

        } else {
            filteredList.addAll(clienti);
            System.out.println("False");
            return new PageImpl<>(filteredList, pageable, clienti.size());


        }
    }

    public Cliente findById(UUID id) {
        return clienteRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(UUID id) {
        Cliente found = this.findById(id);
        clienteRepository.delete(found);
    }

    public Cliente findByIdAndUpdate(UUID id, NewClienteDTO body) {

        Cliente found = this.findById(id);
        found.setRagioneSociale(body.ragioneSociale());
        found.setEmail(body.email());
        found.setPartitaIva(body.partitaIva());
        found.setDataUltimoContatto(body.dataUltimoContatto());
        found.setFatturatoAnnuale(body.fatturatoAnnuale());
        found.setPec(body.pec());
        found.setTelefono(body.telefono());
        found.setEmailContatto(body.emailContatto());
        found.setNomeContatto(body.nomeContatto());
        found.setCognomeContatto(body.cognomeContatto());
        found.setTelefonoContatto(body.telefonoContatto());
        found.setLogoAziendale(body.logoAziendale());

        if (found.getIndirizzo1() != null ) {
            Indirizzo indirizzo1 = found.getIndirizzo1();
            indirizzo1.setAdress(body.indirizzo1Adress());
            indirizzo1.setCivico(body.indirizzo1Civico());
            indirizzo1.setCap(body.indirizzo1Cap());
            indirizzo1.setLocalita(body.indirizzo1Localita());
        }


        if (found.getIndirizzo2() != null) {
            Indirizzo indirizzo2 = found.getIndirizzo2();
            indirizzo2.setAdress(body.indirizzo2Adress());
            indirizzo2.setCivico(body.indirizzo2Civico());
            indirizzo2.setCap(body.indirizzo2Cap());
            indirizzo2.setLocalita(body.indirizzo2Localita());
        }

        return clienteRepository.save(found);


    }

    public Cliente findByEmail(String email) {
        return clienteRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Cliente con email " + email + " non trovata!"));
    }

    public Set<Cliente> getClientiByFatturatoAnnuale(long minimo, long massimo) {
        return clienteRepository.findByFatturatoAnnualeBetween(minimo, massimo);
    }

    public Set<Cliente> getClientiByDataInserimento(LocalDate dataDiInserimento) {
        return clienteRepository.findByDataInserimento(dataDiInserimento);
    }
    public Set<Cliente> getClientiByDataUltimoContatto(LocalDate dataUltimoContato){
        return clienteRepository.findByDataUltimoContatto(dataUltimoContato);
    }
    public Set<Cliente> getClientiByNomeContattoContaining(String nomeContatto){
        return clienteRepository.findByNomeContattoContainingIgnoreCase(nomeContatto);

    }
    public String uploadPicture(MultipartFile file) throws IOException {
        String url= (String) cloudinaryUploader.uploader()
                .upload(file.getBytes(), ObjectUtils.emptyMap())
                .get("url");
        return url;
    }

}