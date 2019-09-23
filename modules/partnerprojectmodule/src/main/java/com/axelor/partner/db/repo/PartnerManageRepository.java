package com.axelor.partner.db.repo;

import java.util.Map;

import com.axelor.partner.db.Partner;

public class PartnerManageRepository extends PartnerRepository {

  @Override
  public Map<String, Object> populate(Map<String, Object> json, Map<String, Object> context) {
    if (!context.containsKey("json-enhance")) {
      return json;
    }

    try {
      Long id = (Long) json.get("id");
      Partner partner = find(id);
      json.put("address", partner.getAddressotm().get(0));
      json.put("hasImage", partner.getImage() != null);
    } catch (Exception e) {
    }

    return json;
  }
}
