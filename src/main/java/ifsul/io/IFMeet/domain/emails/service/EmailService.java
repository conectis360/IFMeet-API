package ifsul.io.IFMeet.domain.emails.service;

import ifsul.io.IFMeet.config.Constants;
import ifsul.io.IFMeet.domain.convite.model.Convite;
import ifsul.io.IFMeet.domain.emails.model.EmailDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Service
public class EmailService {

    @Autowired private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}") private String sender;

    public void enviarEmailSimples(EmailDetails details) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());

            javaMailSender.send(mailMessage);
        }
        catch (Exception e) {
            e.getMessage();
        }
    }

    public void enviarEmailComAnexo(EmailDetails details) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {
            mimeMessageHelper
                    = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getRecipient());
            mimeMessageHelper.setText(details.getMsgBody(), true);
            mimeMessageHelper.setSubject(
                    details.getSubject());

            javaMailSender.send(mimeMessage);
        }

        catch (MessagingException e) {
            e.getMessage();
        }
    }

    public void enviarEmailDeCadastro(Convite convite) {
        log.debug("entrou enviarEmailDeCadastro");
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(convite.getEmailAluno());
            mimeMessageHelper.setText(this.retornaEmailCadastro(convite), true);
            mimeMessageHelper.setSubject(Constants.CONVITE_ASSUNTO);

            javaMailSender.send(mimeMessage);
        }
        catch (MessagingException e) {
            e.getMessage();
        }
    }

    private String retornaEmailCadastro(Convite convite) {
        String email = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n" +
                "<html>\n" +
                "  <head>\n" +
                "    <!-- Compiled with Bootstrap Email version: 1.3.1 --><meta http-equiv=\"x-ua-compatible\" content=\"ie=edge\">\n" +
                "    <meta name=\"x-apple-disable-message-reformatting\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "    <meta name=\"format-detection\" content=\"telephone=no, date=no, address=no, email=no\">\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
                "    <style type=\"text/css\">\n" +
                "      body,table,td{font-family:Helvetica,Arial,sans-serif !important}.ExternalClass{width:100%}.ExternalClass,.ExternalClass p,.ExternalClass span,.ExternalClass font,.ExternalClass td,.ExternalClass div{line-height:150%}a{text-decoration:none}*{color:inherit}a[x-apple-data-detectors],u+#body a,#MessageViewBody a{color:inherit;text-decoration:none;font-size:inherit;font-family:inherit;font-weight:inherit;line-height:inherit}img{-ms-interpolation-mode:bicubic}table:not([class^=s-]){font-family:Helvetica,Arial,sans-serif;mso-table-lspace:0pt;mso-table-rspace:0pt;border-spacing:0px;border-collapse:collapse}table:not([class^=s-]) td{border-spacing:0px;border-collapse:collapse}@media screen and (max-width: 600px){.w-full,.w-full>tbody>tr>td{width:100% !important}*[class*=s-lg-]>tbody>tr>td{font-size:0 !important;line-height:0 !important;height:0 !important}.s-2>tbody>tr>td{font-size:8px !important;line-height:8px !important;height:8px !important}.s-5>tbody>tr>td{font-size:20px !important;line-height:20px !important;height:20px !important}.s-10>tbody>tr>td{font-size:40px !important;line-height:40px !important;height:40px !important}}\n" +
                "    </style>\n" +
                "  </head>\n" +
                "  <body class=\"bg-light\" style=\"outline: 0; width: 100%; min-width: 100%; height: 100%; -webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; font-family: Helvetica, Arial, sans-serif; line-height: 24px; font-weight: normal; font-size: 16px; -moz-box-sizing: border-box; -webkit-box-sizing: border-box; box-sizing: border-box; color: #000000; margin: 0; padding: 0; border-width: 0;\" bgcolor=\"#f7fafc\">\n" +
                "    <table class=\"bg-light body\" valign=\"top\" role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"outline: 0; width: 100%; min-width: 100%; height: 100%; -webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; font-family: Helvetica, Arial, sans-serif; line-height: 24px; font-weight: normal; font-size: 16px; -moz-box-sizing: border-box; -webkit-box-sizing: border-box; box-sizing: border-box; color: #000000; margin: 0; padding: 0; border-width: 0;\" bgcolor=\"#f7fafc\">\n" +
                "      <tbody>\n" +
                "        <tr>\n" +
                "          <td valign=\"top\" style=\"line-height: 24px; font-size: 16px; margin: 0;\" align=\"left\" bgcolor=\"#f7fafc\">\n" +
                "            <table class=\"container\" role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width: 100%;\">\n" +
                "              <tbody>\n" +
                "                <tr>\n" +
                "                  <td align=\"center\" style=\"line-height: 24px; font-size: 16px; margin: 0; padding: 0 16px;\">\n" +
                "                    <!--[if (gte mso 9)|(IE)]>\n" +
                "                      <table align=\"center\" role=\"presentation\">\n" +
                "                        <tbody>\n" +
                "                          <tr>\n" +
                "                            <td width=\"600\">\n" +
                "                    <![endif]-->\n" +
                "                    <table align=\"center\" role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width: 100%; max-width: 600px; margin: 0 auto;\">\n" +
                "                      <tbody>\n" +
                "                        <tr>\n" +
                "                          <td style=\"line-height: 24px; font-size: 16px; margin: 0;\" align=\"left\">\n" +
                "                            <table class=\"s-10 w-full\" role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width: 100%;\" width=\"100%\">\n" +
                "                              <tbody>\n" +
                "                                <tr>\n" +
                "                                  <td style=\"line-height: 40px; font-size: 40px; width: 100%; height: 40px; margin: 0;\" align=\"left\" width=\"100%\" height=\"40\">\n" +
                "                                    &#160;\n" +
                "                                  </td>\n" +
                "                                </tr>\n" +
                "                              </tbody>\n" +
                "                            </table>\n" +
                "                            <table class=\"card\" role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-radius: 6px; border-collapse: separate !important; width: 100%; overflow: hidden; border: 1px solid #e2e8f0;\" bgcolor=\"#ffffff\">\n" +
                "                              <tbody>\n" +
                "                                <tr>\n" +
                "                                  <td style=\"line-height: 24px; font-size: 16px; width: 100%; margin: 0;\" align=\"left\" bgcolor=\"#ffffff\">\n" +
                "                                    <table class=\"card-body\" role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width: 100%;\">\n" +
                "                                      <tbody>\n" +
                "                                        <tr>\n" +
                "                                          <td style=\"line-height: 24px; font-size: 16px; width: 100%; margin: 0; padding: 20px;\" align=\"left\">\n" +
                "                                            <h1 class=\"h3\" style=\"padding-top: 0; padding-bottom: 0; font-weight: 500; vertical-align: baseline; font-size: 28px; line-height: 33.6px; margin: 0;\" align=\"left\">Voc&#234; foi convidado para fazer parte do IF Meet!</h1>\n" +
                "                                            <table class=\"s-2 w-full\" role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width: 100%;\" width=\"100%\">\n" +
                "                                              <tbody>\n" +
                "                                                <tr>\n" +
                "                                                  <td style=\"line-height: 8px; font-size: 8px; width: 100%; height: 8px; margin: 0;\" align=\"left\" width=\"100%\" height=\"8\">\n" +
                "                                                    &#160;\n" +
                "                                                  </td>\n" +
                "                                                </tr>\n" +
                "                                              </tbody>\n" +
                "                                            </table>\n" +
                "                                            <h5 class=\"text-teal-700\" style=\"color: #13795b; padding-top: 0; padding-bottom: 0; font-weight: 500; vertical-align: baseline; font-size: 20px; line-height: 24px; margin: 0;\" align=\"left\">&#201; uma plataforma que ajuda voc&#234; e seu orientador gerenciar suas reuni&#245;es</h5>\n" +
                "                                            <table class=\"s-5 w-full\" role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width: 100%;\" width=\"100%\">\n" +
                "                                              <tbody>\n" +
                "                                                <tr>\n" +
                "                                                  <td style=\"line-height: 20px; font-size: 20px; width: 100%; height: 20px; margin: 0;\" align=\"left\" width=\"100%\" height=\"20\">\n" +
                "                                                    &#160;\n" +
                "                                                  </td>\n" +
                "                                                </tr>\n" +
                "                                              </tbody>\n" +
                "                                            </table>\n" +
                "                                            <table class=\"hr\" role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width: 100%;\">\n" +
                "                                              <tbody>\n" +
                "                                                <tr>\n" +
                "                                                  <td style=\"line-height: 24px; font-size: 16px; border-top-width: 1px; border-top-color: #e2e8f0; border-top-style: solid; height: 1px; width: 100%; margin: 0;\" align=\"left\">\n" +
                "                                                  </td>\n" +
                "                                                </tr>\n" +
                "                                              </tbody>\n" +
                "                                            </table>\n" +
                "                                            <table class=\"s-5 w-full\" role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width: 100%;\" width=\"100%\">\n" +
                "                                              <tbody>\n" +
                "                                                <tr>\n" +
                "                                                  <td style=\"line-height: 20px; font-size: 20px; width: 100%; height: 20px; margin: 0;\" align=\"left\" width=\"100%\" height=\"20\">\n" +
                "                                                    &#160;\n" +
                "                                                  </td>\n" +
                "                                                </tr>\n" +
                "                                              </tbody>\n" +
                "                                            </table>\n" +
                "                                            <div class=\"space-y-3\">\n" +
                "                                              <p class=\"text-gray-700\" style=\"line-height: 24px; font-size: 16px; color: #4a5568; width: 100%; margin: 0;\" align=\"left\">\n" +
                "                                                Olá! {nomeOrientando}<br>\n" +
                "                                                Para se cadastrar &#233; f&#225;cil:<br>\n" +
                "                                                1. Clique no bot&#227;o abaixo!<br>\n" +
                "                                                2. Digite seus dados corretamente no formul&#225;rio!<br>\n" +
                "                                                3. Espere o e-mail de ativa&#231;&#227;o<br>\n" +
                "                                                4. &#201; s&#243; usar!<br>\n" +
                "                                                Esse email é valido até {dataValida}" +
                "                                              </p>\n" +
                "                                            </div>\n" +
                "                                            <table class=\"s-5 w-full\" role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width: 100%;\" width=\"100%\">\n" +
                "                                              <tbody>\n" +
                "                                                <tr>\n" +
                "                                                  <td style=\"line-height: 20px; font-size: 20px; width: 100%; height: 20px; margin: 0;\" align=\"left\" width=\"100%\" height=\"20\">\n" +
                "                                                    &#160;\n" +
                "                                                  </td>\n" +
                "                                                </tr>\n" +
                "                                              </tbody>\n" +
                "                                            </table>\n" +
                "                                            <table class=\"hr\" role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width: 100%;\">\n" +
                "                                              <tbody>\n" +
                "                                                <tr>\n" +
                "                                                  <td style=\"line-height: 24px; font-size: 16px; border-top-width: 1px; border-top-color: #e2e8f0; border-top-style: solid; height: 1px; width: 100%; margin: 0;\" align=\"left\">\n" +
                "                                                  </td>\n" +
                "                                                </tr>\n" +
                "                                              </tbody>\n" +
                "                                            </table>\n" +
                "                                            <table class=\"s-5 w-full\" role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width: 100%;\" width=\"100%\">\n" +
                "                                              <tbody>\n" +
                "                                                <tr>\n" +
                "                                                  <td style=\"line-height: 20px; font-size: 20px; width: 100%; height: 20px; margin: 0;\" align=\"left\" width=\"100%\" height=\"20\">\n" +
                "                                                    &#160;\n" +
                "                                                  </td>\n" +
                "                                                </tr>\n" +
                "                                              </tbody>\n" +
                "                                            </table>\n" +
                "                                            <table class=\"btn btn-success\" role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-radius: 6px; border-collapse: separate !important;\">\n" +
                "                                              <tbody>\n" +
                "                                                <tr>\n" +
                "                                                  <td style=\"line-height: 24px; font-size: 16px; border-radius: 6px; margin: 0;\" align=\"center\" bgcolor=\"#198754\">\n" +
                "                                                    <a href=\"http://localhost:9000/registrar={codigoSecreto}\" target=\"_blank\" style=\"color: #ffffff; font-size: 16px; font-family: Helvetica, Arial, sans-serif; text-decoration: none; border-radius: 6px; line-height: 20px; display: block; font-weight: normal; white-space: nowrap; background-color: #198754; padding: 8px 12px; border: 1px solid #198754;\">Ir para p&#225;gina de cadastro</a>\n" +
                "                                                  </td>\n" +
                "                                                </tr>\n" +
                "                                              </tbody>\n" +
                "                                            </table>\n" +
                "                                          </td>\n" +
                "                                        </tr>\n" +
                "                                      </tbody>\n" +
                "                                    </table>\n" +
                "                                  </td>\n" +
                "                                </tr>\n" +
                "                              </tbody>\n" +
                "                            </table>\n" +
                "                            <table class=\"s-10 w-full\" role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width: 100%;\" width=\"100%\">\n" +
                "                              <tbody>\n" +
                "                                <tr>\n" +
                "                                  <td style=\"line-height: 40px; font-size: 40px; width: 100%; height: 40px; margin: 0;\" align=\"left\" width=\"100%\" height=\"40\">\n" +
                "                                    &#160;\n" +
                "                                  </td>\n" +
                "                                </tr>\n" +
                "                              </tbody>\n" +
                "                            </table>\n" +
                "                          </td>\n" +
                "                        </tr>\n" +
                "                      </tbody>\n" +
                "                    </table>\n" +
                "                    <!--[if (gte mso 9)|(IE)]>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody>\n" +
                "              </table>\n" +
                "                    <![endif]-->\n" +
                "                  </td>\n" +
                "                </tr>\n" +
                "              </tbody>\n" +
                "            </table>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "      </tbody>\n" +
                "    </table>\n" +
                "  </body>\n" +
                "</html>";
        email = email.replace("{nomeOrientando}", convite.getNomeAluno());

        Date dataHoraAtual = retornaDataConvite();
        String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
        String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);

        email = email.replace("{dataValida}", data + " - " + hora );
        email = email.replace("{codigoSecreto}", convite.getCodigoSecreto());

        return email;
    }
    private Date retornaDataConvite() {
        Date dataHoraAtual = new Date();

        dataHoraAtual.setHours(23);
        dataHoraAtual.setMinutes(59);
        dataHoraAtual.setSeconds(59);
        dataHoraAtual.setDate(dataHoraAtual.getDate() + 1);

        return dataHoraAtual;
    }

}

