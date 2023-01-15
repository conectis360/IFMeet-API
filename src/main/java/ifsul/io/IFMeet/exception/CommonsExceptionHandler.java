package ifsul.io.IFMeet.exception;

import ifsul.io.IFMeet.components.Messages;
import ifsul.io.IFMeet.exception.exceptions.BusinessException;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@ControllerAdvice
public class CommonsExceptionHandler extends ResponseEntityExceptionHandler {

    public static final int CODIGO_HTTP_BUSINESS_EXCEPTION = 460;

    @Autowired
    MessageSource messageSource;

    @Autowired
    private Messages messages;

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleException(RuntimeException ex, WebRequest request) {
        ex.printStackTrace();
        Erro erro = new Erro();
        erro.getMensagens().add(messages.get("erro.ocorreu-erro"));
        return this.handleExceptionInternal(ex, erro, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler({ EmptyResultDataAccessException.class })
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
        Erro erro = new Erro();
        erro.getMensagens().add(messages.get("recurso.nao-encontrado"));
		return handleExceptionInternal(ex, erro, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

    @ExceptionHandler({ BusinessException.class })
    public ResponseEntity<Object> handleBusinessException(BusinessException ex, WebRequest request) {
        Erro erro = new Erro();
        erro.getMensagens().add(ex.getMessage());
        return ResponseEntity.status(CODIGO_HTTP_BUSINESS_EXCEPTION).body(erro);
    }

    @ExceptionHandler({ JDBCConnectionException.class })
    public ResponseEntity<Object> handleJDBCConnectionException(JDBCConnectionException ex, WebRequest request) {
        Erro erro = new Erro();
        erro.getMensagens().add(messages.get("database.sem-conexao"));
        return handleExceptionInternal(ex, erro, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<Object> handleException(AccessDeniedException ex, WebRequest request) {
        Erro erro = new Erro();
        erro.getMensagens().add(messages.get("acesso.negado"));
        return this.handleExceptionInternal(ex, erro, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Erro erro = new Erro();
        ex.printStackTrace();
        erro.getMensagens().add(messages.get("mensagem.invalida"));
        return this.handleExceptionInternal(ex, erro, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ ResponseStatusException.class })
    public ResponseEntity<Object> handleResponseStatusException(ResponseStatusException ex, WebRequest request) {
        Erro erro = new Erro();
        erro.getMensagens().add(ex.getReason());
        return ResponseEntity.status(ex.getStatus()).body(erro);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleExceptionInternal(ex, this.createListErrors(ex.getBindingResult()), headers, HttpStatus.BAD_REQUEST, request);
    }

    private Erro createListErrors(BindingResult bidingResult) {
        Erro erro = new Erro();
        Iterator<? extends FieldError> var3 = bidingResult.getFieldErrors().iterator();

        while(var3.hasNext()) {
            FieldError fieldEror = var3.next();
            String mensagem = this.messageSource.getMessage(fieldEror, LocaleContextHolder.getLocale());
            erro.getMensagens().add(mensagem);
        }

        return erro;
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        return this.handleExceptionInternal(ex, this.createListErrors(ex.getConstraintViolations()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    private Erro createListErrors(Set<ConstraintViolation<?>> constraintViolation) {
        Erro erro = new Erro();
        Iterator<? extends ConstraintViolation> var3 = constraintViolation.iterator();

        while(var3.hasNext()) {
            ConstraintViolation<?> constrainViolation = var3.next();
            String mensagem = constrainViolation.getPropertyPath().toString().split("\\.")[1] + " " + constrainViolation.getMessage();
            erro.getMensagens().add(mensagem);
        }

        return erro;
    }



    public class Erro {
        private List<String> mensagens;

        public Erro() {
        }

        public List<String> getMensagens() {
            if (this.mensagens == null) {
                this.mensagens = new ArrayList<>();
            }

            return this.mensagens;
        }

        public void setMensagens(List<String> mensagens) {
            this.mensagens = mensagens;
        }
    }
}
