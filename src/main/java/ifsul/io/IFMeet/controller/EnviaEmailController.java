package ifsul.io.IFMeet.controller;
import ifsul.io.IFMeet.domain.EmailDetails;
import ifsul.io.IFMeet.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/v1/enviaEmail")
public class EnviaEmailController {
        @Autowired
        private EmailService emailService;

        @PostMapping("/simples")
        public void enviarEmailSimples(@RequestBody EmailDetails details){
            emailService.enviarEmailSimples(details);

        }

        @PostMapping("/comAnexo")
        public void enviarEmailComAnexo(@RequestBody EmailDetails details){
            emailService.enviarEmailComAnexo(details);
        }

    }
