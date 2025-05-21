package xyz.eo.manager.service;

import xyz.eo.manager.dto.request.banquet.AddUpdateBanquetRequest;
import xyz.eo.manager.dto.response.GetBanquetDetailsByIdResponse;

public interface BanquetService {
    GetBanquetDetailsByIdResponse addUpdateBanquetDetails(String role, AddUpdateBanquetRequest addUpdateBanquetRequest);

    GetBanquetDetailsByIdResponse getBanquetDetails(Long banquetId);
}
