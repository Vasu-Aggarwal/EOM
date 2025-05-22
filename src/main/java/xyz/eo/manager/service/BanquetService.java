package xyz.eo.manager.service;

import xyz.eo.manager.dto.request.banquet.AddUpdateBanquetRequest;
import xyz.eo.manager.dto.response.GetBanquetDetailsByIdResponse;

public interface BanquetService {
    /**
     *  RULES: <br/><br/>
     * 1. Only SA will be able to create a new banquet. <br/>
     * 2. Only SA can pass the users in the <code>link_admin</code> field. <br/>
     * 3. Admin and SA are only authorized to update the banquet.<br/>
     * 4. SA can update the banquet when banquet is in <code>ACTIVE</code> or <code>IN_PROCESS</code> state.<br/>
     * 5. Admin can ONLY update the banquet when banquet is in <code>ACTIVE</code> state.<br/>
     * 6. When adding/updating the banquet, <code>link_admin</code> mentioned admin(s) must not be present. if
     * present then their state can be directly updated.
     */
    GetBanquetDetailsByIdResponse addUpdateBanquetDetails(String role, AddUpdateBanquetRequest addUpdateBanquetRequest);

    GetBanquetDetailsByIdResponse getBanquetDetails(Long banquetId);
}
