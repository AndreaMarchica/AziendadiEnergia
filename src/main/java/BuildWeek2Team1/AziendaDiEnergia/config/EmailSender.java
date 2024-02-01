package BuildWeek2Team1.AziendaDiEnergia.config;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {
    private String mailgunApiKey;
    private String mailgunDomainname;

    public EmailSender(@Value("${mailgun.apikey}") String mailgunApiKey,
                       @Value("${mailgun.domainname}") String mailgunDomainName) {
        this.mailgunApiKey = mailgunApiKey;
        this.mailgunDomainname = mailgunDomainName;
    }

    public void sendEmail(String email) {
        HttpResponse<JsonNode> response = Unirest.post("https://api.mailgun.net/v3/" + this.mailgunDomainname + "/messages")
                .basicAuth("api", this.mailgunApiKey)
                .queryString("from", "admin1 <amdin1@gmail.com>")
                .queryString("to", email)
                .queryString("subject", "Registrazione avvenuta con successo!")
                .queryString("text", "Complimenti per esserti registrato!")
                .asJson();
    }
}
