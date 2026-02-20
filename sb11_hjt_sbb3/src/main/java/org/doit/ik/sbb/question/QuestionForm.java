package org.doit.ik.sbb.question;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionForm { // DTO
   
   // 널, "" 허용 X
   @NotEmpty(message = "제목은 필수 항목입니다.")
   @Size(max = 200) // 단위는 byte
   private String subject;
   
   @NotEmpty(message = "내용은 필수 항목입니다.")
   private String content;
   
}