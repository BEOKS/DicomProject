package com.knuipalab.dsmp.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class FindUserByEmailRequestDto {
   private List<String> emailList ;
}
