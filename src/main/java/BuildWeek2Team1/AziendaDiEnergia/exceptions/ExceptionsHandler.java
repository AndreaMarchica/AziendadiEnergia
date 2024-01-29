package BuildWeek2Team1.AziendaDiEnergia.exceptions;


import BuildWeek2Team1.AziendaDiEnergia.payloads.ErrorPayloads.ErrorDto;
import BuildWeek2Team1.AziendaDiEnergia.payloads.ErrorsResponseDTO;
import BuildWeek2Team1.AziendaDiEnergia.payloads.ErrorsResponseWithListDTO;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    public ErrorsResponseWithListDTO handleBadRequest(BadRequestException e) {
        if (e.getErrorList() != null) {
            List<String> errorList = e.getErrorList().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
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
    @org.springframework.web.bind.annotation.ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED) // 401
    public ErrorDto unauthorized(UnauthorizedException ex) {
        return new ErrorDto("ERRORE DI AUTENTICAZIONE!"+ex.getMessage(), LocalDateTime.now());
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(EmailAlreadyInDbException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleEmailAlreadyInDb(Exception ex) {
        return new ErrorDto(ex.getMessage(), LocalDateTime.now());
    }

}