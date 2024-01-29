package BuildWeek2Team1.AziendaDiEnergia.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ResponseStatus;


import java.util.Arrays;
import java.util.List;

@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException{
    private List<ObjectError> errorList;

    public BadRequestException(String message){super(message);
    }

    public BadRequestException(String message, List<ObjectError> list){

        super("Errori nel body"+message);
        this.errorList=list;
    }



}
