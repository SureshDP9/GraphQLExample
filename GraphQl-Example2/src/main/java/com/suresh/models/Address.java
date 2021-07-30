package com.suresh.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Address {	
   private String street;	
   private String city;
   private String state;
   private String pincode;
}
