package org.doit.ik;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing  
// BaseEntity 클래스 JPA를 이용한 AuditingEntityListener 를 활성화 시키는 어노테이션
public class Sb08MjtGuestbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sb08MjtGuestbookApplication.class, args);
	}

}
