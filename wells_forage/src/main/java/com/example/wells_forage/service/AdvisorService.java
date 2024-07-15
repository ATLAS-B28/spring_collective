package com.example.wells_forage.service;

import com.example.wells_forage.dto.AdvisorDTO;
import com.example.wells_forage.dto.PortfolioDTO;
import com.example.wells_forage.dto.SecurityDTO;
import com.example.wells_forage.dto.UserDTO;
import com.example.wells_forage.entity.Advisor;
import com.example.wells_forage.entity.Portfolio;
import com.example.wells_forage.entity.Security;
import com.example.wells_forage.entity.User;

public interface AdvisorService {
    //Advisor
    AdvisorDTO createAdvisor(AdvisorDTO advisor);
    AdvisorDTO getAdvisorById(Long adv_id);
    AdvisorDTO updateAdvisor(AdvisorDTO advisor);
    void deleteAdvisor(AdvisorDTO advisor);
    //create and get and update user
    UserDTO createUser(UserDTO user);
    UserDTO getUserById(Long user_id);
    UserDTO updateUser(UserDTO user);
    void deleteUser(UserDTO user);
    //create, get and update securities
    SecurityDTO createSecurity(SecurityDTO security);
    SecurityDTO getSecurityById(Long sec_id);
    SecurityDTO updateSecurity(SecurityDTO security);
    void deleteSecurity(SecurityDTO security);
    //create, get and update portfolio
    PortfolioDTO createPortfolio(PortfolioDTO portfolio);
    PortfolioDTO getPortfolioById(Long portfolio_id);
    PortfolioDTO updatePortfolio(Portfolio portfolio);
    void deletePortfolio(PortfolioDTO portfolio);
}
