package xyz.eo.manager.service.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.eo.manager.dto.request.banquet.AddUpdateBanquetRequest;
import xyz.eo.manager.dto.response.GetBanquetDetailsByIdResponse;
import xyz.eo.manager.entity.Banquet;
import xyz.eo.manager.entity.UserBanquetDetail;
import xyz.eo.manager.exception.ErrorMessageException;
import xyz.eo.manager.exception.NotAuthorizedException;
import xyz.eo.manager.repository.BanquetRepository;
import xyz.eo.manager.repository.UserBanquetDetailRepository;
import xyz.eo.manager.service.BanquetService;
import xyz.eo.manager.util.ErrorMessage;
import xyz.eo.manager.util.UtilMethods;
import xyz.eo.manager.util.enums.BanquetStatus;
import xyz.eo.manager.util.enums.UserBanquetStatus;

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
                throw new NotAuthorizedException(ErrorMessage.NOT_AUTHORIZED_TO_ASSIGN_ADMINS);
            }

            // Only SUPER_ADMIN can create new banquet
            if (request.getBanquetId() == null) {
                if (!UtilMethods.isSuperAdmin(role)) {
                    throw new NotAuthorizedException(ErrorMessage.NOT_AUTHORIZED_TO_ADD_BANQUET);
                }

                banquet = modelMapper.map(request, Banquet.class);
                banquet.setStatus(BanquetStatus.ACTIVE.getStatus());
            } else {
                // Fetch existing banquet for update
                banquet = banquetRepository.getBanquetById(request.getBanquetId())
                        .orElseThrow(() -> new ErrorMessageException(ErrorMessage.RESOURCE_NOT_FOUND, 0));

                // Only SUPER_ADMIN and ADMIN can update
                if ((UtilMethods.isAdmin(role) || UtilMethods.isSuperAdmin(role))) {
                    if (UtilMethods.isSuperAdmin(role) && (banquet.getStatus().equals(BanquetStatus.INACTIVE.getStatus()) || banquet.getStatus().equals(BanquetStatus.ACTIVE.getStatus()) || banquet.getStatus().equals(BanquetStatus.IN_PROCESS.getStatus())))
                        modelMapper.map(request, banquet);
                    else if (UtilMethods.isAdmin(role) && banquet.getStatus().equals(BanquetStatus.ACTIVE.getStatus()))
                        modelMapper.map(request, banquet);
                    else
                        throw new ErrorMessageException(ErrorMessage.BANQUET_NOT_ACTIVE, 0);
                } else {
                    throw new NotAuthorizedException(ErrorMessage.NOT_AUTHORIZED_TO_UPDATE_BANQUET);
                }
            }

            banquetRepository.save(banquet);
            Long savedBanquetId = banquet.getBanquetId();
            for (Long userId: request.getLinkAdmin()){
                UserBanquetDetail userPresence = userBanquetDetailRepository.getUbdByUserAndBanquet(userId,
                        savedBanquetId).orElse(null);

                /*if admin is already present with inactive or deleted state then throw error, SA have to update the
                state of admin to active.*/
                if(userPresence != null) {
                    if (userPresence.getStatus().equals(UserBanquetStatus.INACTIVE.getStatus()) || userPresence.getStatus().equals(UserBanquetStatus.DELETED.getStatus()))
                        throw new ErrorMessageException(ErrorMessage.LINK_ADMIN_ALREADY_PRESENT_INACTIVE_DELETED, 0);
                } else {
                    UserBanquetDetail userBanquetDetail = new UserBanquetDetail(userId, savedBanquetId,
                            UserBanquetStatus.ACTIVE.getStatus());
                    userBanquetDetailRepository.save(userBanquetDetail);
                }
            }

            return modelMapper.map(banquet, GetBanquetDetailsByIdResponse.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public GetBanquetDetailsByIdResponse getBanquetDetails(Long banquetId) {
        Banquet banquet =
                banquetRepository.getBanquetById(banquetId).orElseThrow(() -> new ErrorMessageException(ErrorMessage.RESOURCE_NOT_FOUND, 0));
        return modelMapper.map(banquet, GetBanquetDetailsByIdResponse.class);
    }
}
