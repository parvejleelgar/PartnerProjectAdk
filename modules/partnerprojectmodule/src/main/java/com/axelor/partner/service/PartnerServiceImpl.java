package com.axelor.partner.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.eclipse.birt.core.exception.BirtException;
import com.axelor.data.adapter.Adapter;
import com.axelor.data.csv.CSVImporter;
import com.axelor.inject.Beans;
import com.axelor.meta.MetaFiles;
import com.axelor.meta.db.MetaFile;
import com.axelor.partner.db.Address;
import com.axelor.partner.db.Company;
import com.axelor.partner.db.Email;
import com.axelor.partner.db.Partner;
import com.axelor.partner.db.Phonecsv;
import com.axelor.partner.db.repo.PartnerRepository;
import com.axelor.report.ReportGenerator;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import com.opencsv.CSVWriter;

public class PartnerServiceImpl implements PartnerService {

  @Override
  public Email checkemail(Partner partner) {

    Email email = new Email();
    String emailaddress;

    if (partner.getEmailoto() == null) {

      String firstName = partner.getFirstName();
      String lastName = partner.getLastName();
      emailaddress = firstName + "." + lastName + "@gamil.com";
      email.setEmailstr(emailaddress);
    } else {
      email = partner.getEmailoto();
      emailaddress = email.getEmailstr();
      email.setEmailstr(emailaddress);
    }

    return email;
  }

  @Override
  @Transactional
  public void dataExport(Partner partner) {

    PartnerRepository partnerRepository = Beans.get(PartnerRepository.class);
    partner = partnerRepository.find(partner.getId());
    try {
      File file = File.createTempFile("data", "export");
      FileWriter outputfile = new FileWriter(file);
      CSVWriter writer = new CSVWriter(outputfile);
      String[] header = {
        "FullName", "FirstName", "LastName", "DateOfBirth", "Email", "PhoneNumber", "CompanyName"
      };
      writer.writeNext(header);

      //			List<Address> addressList = new ArrayList<Address>();
      //			addressList = partner.getAddressotm();
      //			for(Address ad :addressList)
      //			{
      //				System.out.print(ad.getArea());
      //				System.out.println(ad.getCountry());
      //				System.out.println(ad.getState());
      //				System.out.println(ad.getStreet());
      //				System.out.println(ad.getZip());
      //			}
      
//      List<Partner> par = pr.all().fetch();
//      
//      System.out.println(par.size()); 

      String phoneNumberStr = "", prefix = "", seperator = "|";
      List<Phonecsv> phoneArrayList = partner.getPhoneList();
      for (Phonecsv ph : phoneArrayList) {
        phoneNumberStr += prefix + ph.getPhoneNumber();
        prefix = seperator;
      }

      String companyNameStr = "";
      prefix = "";
      Set<Company> companyArrayList = partner.getCompanymtm();
      for (Company companyName : companyArrayList) {
        companyNameStr += prefix + companyName.getCompanyName();
        prefix = seperator;
      }

      String[] data1 = {
        partner.getFullName(),
        partner.getFirstName(),
        partner.getLastName(),
        partner.getDateOfBirth().toString(),
        partner.getEmailoto().getEmailstr(),
        phoneNumberStr,
        companyNameStr
      };
      writer.writeNext(data1);
      writer.close();
      MetaFile metaFile = Beans.get(MetaFiles.class).upload(file);
      partner.setMetaFile(metaFile);
    } catch (IOException e) {
      e.printStackTrace();
    }

    partnerRepository.save(partner);
  }
  
  @Override
  public void dataExportGrid() {
    
    PartnerRepository partnerRepositoryGrid = Beans.get(PartnerRepository.class);
    List<Partner> partnerList = partnerRepositoryGrid.all().fetch();
    try {
      File file =  new File("/home/axelor/Downloads/test.csv");
      FileWriter outputfile = new FileWriter(file);
      CSVWriter writer = new CSVWriter(outputfile);
      String[] header = {
        "FullName", "FirstName", "LastName", "DateOfBirth", "Email", "PhoneNumber", "CompanyName"
      };
      writer.writeNext(header);
      
      for(Partner p : partnerList) {
        String phoneNumberStr = "", prefix = "", seperator = "|";
        List<Phonecsv> phoneArrayList = p.getPhoneList();
        for (Phonecsv ph : phoneArrayList) {
          phoneNumberStr += prefix + ph.getPhoneNumber();
          prefix = seperator;
          System.out.println(ph.getPhoneNumber()); 
        }

        String companyNameStr = "";
        prefix = "";
        Set<Company> companyArrayList = p.getCompanymtm();
        for (Company companyName : companyArrayList) {
          companyNameStr += prefix + companyName.getCompanyName();
          prefix = seperator;
        }

        String[] data1 = {
          p.getFullName(),
          p.getFirstName(),
          p.getLastName(),
          p.getDateOfBirth().toString(),
          p.getEmailoto().getEmailstr(),
          phoneNumberStr,
          companyNameStr
        };
        writer.writeNext(data1);
      }
      writer.close();
    }catch (Exception e) {
      // TODO: handle exception
    }
    
  }

  @Override
  public void dataImport() {

    CSVImporter importer =
        new CSVImporter(
            "/home/axelor/eclipse-workspace/AdkProject/partnerproject/modules/partnerprojectmodule/src/main/resources/data-init/input-config.xml",
            "/home/axelor/eclipse-workspace/AdkProject/partnerproject/modules/partnerprojectmodule/src/main/resources/data-init/input");
    importer.run();
  }
  
  
}
