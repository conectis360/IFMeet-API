package ifsul.io.IFMeet.service;

import ifsul.io.IFMeet.controller.dto.LoginDTO;
import ifsul.io.IFMeet.payload.response.JwtResponse;

public interface AuthService {
    public JwtResponse fazLogin(LoginDTO loginDTO);
}
