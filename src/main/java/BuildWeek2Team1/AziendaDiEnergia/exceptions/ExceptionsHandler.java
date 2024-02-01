package BuildWeek2Team1.AziendaDiEnergia.exceptions;

import BuildWeek2Team1.AziendaDiEnergia.payloads.errors.ErrorsDTO;
import BuildWeek2Team1.AziendaDiEnergia.payloads.errors.ErrorsDTOWithList;
import BuildWeek2Team1.AziendaDiEnergia.payloads.errors.ErrorsPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class ExceptionsHandler {


    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    public ErrorsDTOWithList handleBadRequest(BadRequestException e) {
        List<String> errorsMessages = new ArrayList<>();
        if (e.getErrorsList() != null) {
            System.out.println("errore!!!!!!" + errorsMessages);
            errorsMessages = e.getErrorsList().stream().map(err -> err.getDefaultMessage()).toList();
        }
        return new ErrorsDTOWithList(e.getMessage(), new Date(),errorsMessages);
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED) // 401
    public ErrorsDTO handleUnauthorized(UnauthorizedException e) {
        Date date = new Date();
        return new ErrorsDTO("errore con l'autenticazione! "+e.getMessage(), date);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN) // 403
    public ErrorsDTO handleAccessDenied(AccessDeniedException ex) {
        Date date = new Date();
        return new ErrorsDTO("Il tuo ruolo non permette di accedere a questa funzionalità!", date);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    public ErrorsPayload handleNotFound(NotFoundException e) {
        return new ErrorsPayload(e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
    public ErrorsPayload handleGeneric(Exception e) {
        e.printStackTrace();
        return new ErrorsPayload("Errore generico, risolveremo il prima possibile", LocalDateTime.now());
    }
      @org.springframework.web.bind.annotation.ExceptionHandler(EmailAlreadyInDbException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsPayload handleEmailAlreadyInDb(Exception ex) {
        return new ErrorsPayload(ex.getMessage(), LocalDateTime.now());
    }
    @ExceptionHandler(ComuniNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorsPayload handleComuniNotFound(ComuniNotFoundException e) {
        return new ErrorsPayload("Località non trovata" + e.getMessage(), LocalDateTime.now());
    }
    @ExceptionHandler(ProvinceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorsPayload handleProvinceNotFound(ProvinceNotFoundException e) {
        return new ErrorsPayload("Provincia non trovata: " + e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(IndirizzoAlreadyPresentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsPayload handleProvinceNotFound(IndirizzoAlreadyPresentException e) {
        return new ErrorsPayload("Indirizzo già presente nel db" + e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(IndirizzoNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsPayload handleProvinceNotFound(IndirizzoNotFoundException e) {
        return new ErrorsPayload( e.getMessage(), LocalDateTime.now());
    }
}
