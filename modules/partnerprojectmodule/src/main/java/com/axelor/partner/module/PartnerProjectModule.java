package com.axelor.partner.module;

import com.axelor.app.AxelorModule;
import com.axelor.partner.db.repo.PartnerManageRepository;
import com.axelor.partner.db.repo.PartnerRepository;
import com.axelor.partner.service.PartnerService;
import com.axelor.partner.service.PartnerServiceImpl;

public class PartnerProjectModule extends AxelorModule {

  protected void configure() {

    bind(PartnerService.class).to(PartnerServiceImpl.class);
    bind(PartnerRepository.class).to(PartnerManageRepository.class);
  }
}
