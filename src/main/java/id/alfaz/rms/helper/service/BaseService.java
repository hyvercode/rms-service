package id.alfaz.rms.helper.service;

import id.alfaz.rms.helper.base.BaseRequest;
import id.alfaz.rms.helper.base.BaseResponse;

public interface BaseService<T extends BaseRequest,V extends BaseResponse> {
    V execute(T input);
}
