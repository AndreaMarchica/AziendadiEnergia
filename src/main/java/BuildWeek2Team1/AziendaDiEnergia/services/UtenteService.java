package BuildWeek2Team1.AziendaDiEnergia.services;


import BuildWeek2Team1.AziendaDiEnergia.entities.Fattura;
import BuildWeek2Team1.AziendaDiEnergia.entities.Utente;
import BuildWeek2Team1.AziendaDiEnergia.exceptions.NotFoundException;
import BuildWeek2Team1.AziendaDiEnergia.repositories.UtenteRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteDao;

    @Autowired
    private Cloudinary cloudinaryUploader;



    public Page<Utente> getAll(int page,int size,String orderBy) {
        if(size>=20) size=20;
        Pageable pageable= PageRequest.of(page,size, Sort.by(orderBy));
        return utenteDao.findAll(pageable);
    }



    public Utente findByUUID(UUID uuid){
        return utenteDao.findById(uuid).orElseThrow(()->new NotFoundException(uuid));
    }

    public void delete (UUID uuid){
        utenteDao.delete(this.findByUUID(uuid));
    }


    public Utente findByEmail(String email){
        return utenteDao.findByEmail(email).orElseThrow(()-> new NotFoundException(email));
    }

    public String uploadPicture(MultipartFile file) throws IOException {
        String url= (String) cloudinaryUploader.uploader()
                .upload(file.getBytes(), ObjectUtils.emptyMap())
                .get("url");
        return url;
    }
}
