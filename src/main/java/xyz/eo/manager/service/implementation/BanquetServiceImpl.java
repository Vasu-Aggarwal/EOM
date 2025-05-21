package xyz.eo.manager.service.implementation;

import jdk.jshell.execution.Util;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.eo.manager.dto.request.banquet.AddUpdateBanquetRequest;
import xyz.eo.manager.dto.response.GetBanquetDetailsByIdResponse;
import xyz.eo.manager.entity.Banquet;
import xyz.eo.manager.entity.UserBanquetDetail;
import xyz.eo.manager.exception.ErrorMessage;
import xyz.eo.manager.exception.NotAuthorizedException;
import xyz.eo.manager.repository.BanquetRepository;
import xyz.eo.manager.repository.UserBanquetDetailRepository;
import xyz.eo.manager.service.BanquetService;
import xyz.eo.manager.util.UtilMethods;

@Service
public class BanquetServiceImpl implements BanquetService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BanquetRepository banquetRepository;

    @Autowired
    private UserBanquetDetailRepository userBanquetDetailRepository;

    @Override
    @Transactional
    public GetBanquetDetailsByIdResponse addUpdateBanquetDetails(String role, AddUpdateBanquetRequest request) {
        try {
            Banquet banquet;

            //only super admin should be able to link admins to banquet
            if (!UtilMethods.isSuperAdmin(role) && request.getLinkAdmin() != null && !request.getLinkAdmin().isEmpty()) {
                throw new NotAuthorizedException("You are not authorized to assign Admin(s)");
            }

            // Only SUPER_ADMIN can create new banquet
            if (request.getBanquetId() == null) {
                if (!UtilMethods.isSuperAdmin(role)) {
                    throw new NotAuthorizedException("You are not authorized to create a banquet");
                }

                banquet = modelMapper.map(request, Banquet.class);
            } else {
                // Fetch existing banquet for update
                banquet = banquetRepository.getBanquetById(request.getBanquetId())
                        .orElseThrow(() -> new ErrorMessage("Banquet not found", 0));

                // Only SUPER_ADMIN and ADMIN can update
                if (!UtilMethods.isAdmin(role) && !UtilMethods.isSuperAdmin(role)) {
                    throw new NotAuthorizedException("You are not authorized to update this banquet");
                }
                modelMapper.map(request, banquet);
            }

            banquetRepository.save(banquet);
            Long savedBanquetId = banquet.getBanquetId();
            for (Long userId: request.getLinkAdmin()){
                UserBanquetDetail userBanquetDetail = new UserBanquetDetail(userId, savedBanquetId);
                userBanquetDetailRepository.save(userBanquetDetail);
            }

            return modelMapper.map(banquet, GetBanquetDetailsByIdResponse.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public GetBanquetDetailsByIdResponse getBanquetDetails(Long banquetId) {
        Banquet banquet = banquetRepository.getBanquetById(banquetId).orElseThrow(() -> new ErrorMessage("Banquet not found", 0));
        return modelMapper.map(banquet, GetBanquetDetailsByIdResponse.class);
    }
}
