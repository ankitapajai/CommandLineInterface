package com.bootcoding.UserCommandApp.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
   private String id;
   private String name;
   private String address;
   private String mailId;
   private long phone;

}
