package com.axelor.partner.service;

import com.axelor.partner.db.Email;
import com.axelor.partner.db.Partner;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;

public interface PartnerService {

  public Email checkemail(Partner partner);

  public void dataExport(Partner partner);

  public void dataImport();
  
  public void dataExportGrid();
}
