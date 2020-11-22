package com.study.restfulapi.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
//민감한 데이터는 노출시키지 않는다. 클래스 레벨에서 막기
//@JsonIgnoreProperties(value = {"password","ssn"})
@NoArgsConstructor
@JsonFilter("UserInfoV2")
public class UserV2 extends User {

    private String grade;

}
