package com.study.restfulapi.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "사용자 상세 정보를 위한 도메인 객체")
public class User {

    private Integer id;
    @Size(min = 2, message = "Name MUST be over 2 characters.")
    @ApiModelProperty(notes = "사용자 이름을 입력해 주세요.")
    private String name;
    @Past
    @ApiModelProperty(notes = "사용자 등록일을 입력해 주세요.")
    private Date joinDate;
    
    //민감한 데이터는 노출시키지 않는다. 필드 레벨에서 막기
//    @JsonIgnore
    @ApiModelProperty(notes = "사용자 암호을 입력해 주세요.")
    private String password;
//    @JsonIgnore
@ApiModelProperty(notes = "사용자 주민번호를 입력해 주세요.")
    private String ssn;
}