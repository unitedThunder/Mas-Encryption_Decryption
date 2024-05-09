package com.a3squad.encryptor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.a3squad.encryptor.model.HypthAdditionReqDobj;
import com.a3squad.encryptor.model.RequestDto;
import com.a3squad.encryptor.util.Encryptor;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class EncryptController {
   public static final String URL = "https://vahan.parivahan.gov.in/vahanHypothecationWS/v1/addition";
   public static final String userId = "masuser";
   public static final String userPwd = "dL@12345";
   public static final String key = "R+WQ8VJry/sEOJ9NukwAohCeqxakkKHNRVgEMxI56q0=";
   public static final Logger LOG = LoggerFactory.getLogger(EncryptController.class);

   @GetMapping({"/"})
   public String encryptService() {
      return "Encrypt Service 1.0 is running...";
   }

   @PostMapping({"/encrypt"})
   public String encryptRequestBody1(@RequestBody String body, @RequestHeader String key) {
      try {
         LOG.info("Encrypting request body");
         return Encryptor.encrypt(body.getBytes(), key);
      } catch (Exception var4) {
         LOG.error("ERROR", var4.fillInStackTrace());
         return "";
      }
   }

    @PostMapping({"/decrypt"})
    public String decryptRequestBody(@RequestBody String encryptedString, @RequestHeader String key) {
       try {
          LOG.info("decrypting string: {}", encryptedString);
          return Encryptor.decrypt(encryptedString, key);
       } catch (Exception var4) {
          LOG.error("ERROR", var4.fillInStackTrace());
          return "";
       }
    }

   public static String convertToJson(HypthAdditionReqDobj vaTac) {
      String jsonString = "";
      ObjectMapper mapper = new ObjectMapper();

      try {
         jsonString = mapper.writeValueAsString(vaTac);
      } catch (Exception var4) {
         LOG.error("ERROR", var4.fillInStackTrace());
      }

      return jsonString;
   }

   public static String convertToJson(RequestDto vaTac) {
      String jsonString = "";
      ObjectMapper mapper = new ObjectMapper();

      try {
         jsonString = mapper.writeValueAsString(vaTac);
      } catch (Exception var4) {
         System.out.printf(var4.getMessage());
      }

      return jsonString;
   }
}

