package com.axelor.partner.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.client.protocol.ResponseAuthCache;

import com.axelor.partner.db.Email;
import com.axelor.partner.db.Partner;
import com.axelor.partner.service.PartnerService;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import com.axelor.rpc.Context;
import com.google.common.collect.Lists;
import com.google.inject.Inject;

public class PartnerController {

  @Inject private PartnerService service;

  public void checkemail(ActionRequest request, ActionResponse response) {

    Partner partner = request.getContext().asType(Partner.class);
    Email email = service.checkemail(partner);
    response.setValue("emailoto", email);
  }

  public void dataExport(ActionRequest request, ActionResponse response) {
    Partner partner = request.getContext().asType(Partner.class);
    service.dataExport(partner);
    response.setReload(true);
  }

  public void dataImport(ActionRequest request, ActionResponse response) {
    
    service.dataImport();
  }
  
  public void dataExportGrid(ActionRequest request, ActionResponse response) {
    
    service.dataExportGrid();
  }
    
  public void report (ActionRequest request, ActionResponse response){
    
    List<Integer> ids;
    if((ids = (List) request.getContext().get("_ids")) == null) 
    {
      throw new IllegalArgumentException("Please Select Atleast One Record");
    }  
    else
    {
      String ids_str = ids.toString();
      String partnerId = ids_str.substring(1, ids_str.length()-1);
      request.getContext().put("partnerId", partnerId);
    }
 }
}
