package com.hyvercode.rms.helper.service;

import com.hyvercode.rms.helper.base.BaseRequest;
import com.hyvercode.rms.helper.base.BaseResponse;

public interface BaseService<T extends BaseRequest,V extends BaseResponse> {
    V execute(T input);
}
