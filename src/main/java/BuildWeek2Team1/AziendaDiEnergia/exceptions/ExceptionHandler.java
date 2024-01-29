package BuildWeek2Team1.AziendaDiEnergia.exceptions;

import BuildWeek2Team1.AziendaDiEnergia.payloads.ErrorPayloads.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED) // 401
    public ErrorDto unauthorized(UnauthorizedException ex) {
        return new ErrorDto("ERRORE DI AUTENTICAZIONE!"+ex.getMessage(), LocalDateTime.now());
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handleItemNotFound(Exception ex) {
        return new ErrorDto(ex.getMessage(), LocalDateTime.now());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleBadRequest(BadRequestException ex) {
        return new ErrorDto("richiesta errata controlla i dati inseriti! " +ex.getMessage(), LocalDateTime.now());
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(EmailAlreadyInDbException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleEmailAlreadyInDb(Exception ex) {
        return new ErrorDto(ex.getMessage(), LocalDateTime.now());
    }

    }
