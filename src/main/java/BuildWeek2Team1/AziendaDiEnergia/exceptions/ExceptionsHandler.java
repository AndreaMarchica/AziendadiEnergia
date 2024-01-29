package BuildWeek2Team1.AziendaDiEnergia.exceptions;

<<<<<<< HEAD

import BuildWeek2Team1.AziendaDiEnergia.payloads.ErrorsResponseDTO;
import BuildWeek2Team1.AziendaDiEnergia.payloads.ErrorsResponseWithListDTO;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.crossstore.ChangeSetPersister;
=======
import BuildWeek2Team1.AziendaDiEnergia.payloads.errors.ErrorsDTO;
import BuildWeek2Team1.AziendaDiEnergia.payloads.errors.ErrorsDTOWithList;
import lombok.extern.slf4j.Slf4j;
>>>>>>> develop
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

<<<<<<< HEAD
=======
import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
>>>>>>> develop
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
<<<<<<< HEAD
public class ExceptionsHandler {
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    public ErrorsResponseWithListDTO handleBadRequest(BadRequestException e) {
        if (e.getErrors() != null) {
            List<String> errorList = e.getErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
            return new ErrorsResponseWithListDTO(e.getMessage(), new Date(), errorList);
        } else {
            return new ErrorsResponseWithListDTO(e.getMessage(), new Date(), new ArrayList<>());
        }

    }

    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)  // 404
    public ErrorsResponseDTO handleNotFound(ChangeSetPersister.NotFoundException e) {
        return new ErrorsResponseDTO(e.getMessage(), new Date());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  // 500
    public ErrorsResponseDTO handleGeneric(Exception e) {
        e.printStackTrace();
        return new ErrorsResponseDTO("Ha provato a spegnere e riaccendere...?", new Date());
    }
}
=======
@Slf4j
public class ExceptionsHandler {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    public ErrorsDTOWithList handleBadRequest(BadRequestException e) {
        List<String> errorsMessages = new ArrayList<>();
        if (e.getErrorsList() != null)
            errorsMessages = e.getErrorsList().stream().map(err -> err.getDefaultMessage()).toList();
        return new ErrorsDTOWithList(e.getMessage(), new Date(), errorsMessages);
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED) // 401
    public ErrorsDTO handleUnauthorized(UnauthorizedException e) {
        Date date = new Date();
        return new ErrorsDTO(e.getMessage(), date);
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

}
>>>>>>> develop
