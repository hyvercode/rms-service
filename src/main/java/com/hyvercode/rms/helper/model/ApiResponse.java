package com.hyvercode.rms.helper.model;

import com.hyvercode.rms.helper.base.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse extends BaseResponse{
    private HttpStatus httpStatus;
    private String code;
    private String message;
}
