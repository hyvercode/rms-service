package com.hyvercode.rms.service;

import com.hyvercode.rms.helper.exception.BusinessException;
import com.hyvercode.rms.model.entity.Outlet;
import com.hyvercode.rms.model.response.outlet.OutletResponse;
import com.hyvercode.rms.repository.OutletRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class ProfileService {
    private OutletRepository outletRepository;
    private HttpServletRequest httpServletRequest;

    public ProfileService(OutletRepository outletRepository, HttpServletRequest httpServletRequest) {
        this.outletRepository = outletRepository;
        this.httpServletRequest = httpServletRequest;
    }

    public OutletResponse outletProfile(String outletId){
        Optional<Outlet> optional = outletRepository.findById(outletId);
        if(!optional.isPresent()){
            throw new BusinessException(HttpStatus.CONFLICT,"30020","Outlet ID Not Found");
        }

        if(optional.get().getActive().equals("N")){
            throw new BusinessException(HttpStatus.CONFLICT,"30020","Outlet not Active");
        }

        Outlet outlet = optional.get();
        OutletResponse response = new OutletResponse();
        BeanUtils.copyProperties(outlet,response);

        return response;
    }
}
