package com.example.wells_forage.service.implementation;

import com.example.wells_forage.dto.AdvisorDTO;
import com.example.wells_forage.dto.PortfolioDTO;
import com.example.wells_forage.dto.SecurityDTO;
import com.example.wells_forage.dto.UserDTO;
import com.example.wells_forage.entity.Advisor;
import com.example.wells_forage.entity.Portfolio;
import com.example.wells_forage.entity.Security;
import com.example.wells_forage.entity.User;
import com.example.wells_forage.repository.AdvisorRepository;
import com.example.wells_forage.service.AdvisorService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AdvisorServiceImpl implements AdvisorService {

    private ModelMapper modelMapper;
    private AdvisorRepository advisorRepository;


    @Override
    public AdvisorDTO createAdvisor(AdvisorDTO advisor) {
        //create advisor
        Advisor advisor1 = modelMapper.map(advisor, Advisor.class);
        advisorRepository.save(advisor1);
        return modelMapper.map(advisor1, AdvisorDTO.class);
    }

    @Override
    public AdvisorDTO getAdvisorById(Long adv_id) {
        //get by id
        Advisor advisor = advisorRepository.findById(adv_id).get();
        return modelMapper.map(advisor, AdvisorDTO.class);
    }

    @Override
    public AdvisorDTO updateAdvisor(AdvisorDTO advisor) {
        //update the advisor
        //get the advisor by id
        //update the advisor
        //save the advisor
        //return the advisor
        Advisor exitingAdvisor = advisorRepository.findById(advisor.getAdv_id()).get();
        exitingAdvisor.setFirstName(advisor.getFirstName());
        exitingAdvisor.setLastName(advisor.getLastName());
        exitingAdvisor.setUser(advisor.getUser());
        advisorRepository.save(exitingAdvisor);
        return modelMapper.map(exitingAdvisor, AdvisorDTO.class);
    }

    @Override
    public void deleteAdvisor(AdvisorDTO advisor) {

    }

    @Override
    public UserDTO createUser(UserDTO user) {
        return null;
    }

    @Override
    public UserDTO getUserById(Long user_id) {
        return null;
    }

    @Override
    public UserDTO updateUser(UserDTO user) {
        return null;
    }

    @Override
    public void deleteUser(UserDTO user) {

    }

    @Override
    public SecurityDTO createSecurity(SecurityDTO security) {
        return null;
    }

    @Override
    public SecurityDTO getSecurityById(Long sec_id) {
        return null;
    }

    @Override
    public SecurityDTO updateSecurity(SecurityDTO security) {
        return null;
    }

    @Override
    public void deleteSecurity(SecurityDTO security) {

    }

    @Override
    public PortfolioDTO createPortfolio(PortfolioDTO portfolio) {
        return null;
    }

    @Override
    public PortfolioDTO getPortfolioById(Long portfolio_id) {
        return null;
    }

    @Override
    public PortfolioDTO updatePortfolio(Portfolio portfolio) {
        return null;
    }

    @Override
    public void deletePortfolio(PortfolioDTO portfolio) {

    }
}
