package org.doit.ik;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.java.Log;

@SpringBootTest
@Log
class Sb03OracleMybatisJspApplicationTests {
	// [1] Connection 단위 테스트
	@Autowired
	DataSource dataSource;
	@Test
	void testConnection() {
		try (Connection con=this.dataSource.getConnection()){
			log.info("@ @ @ : CONNECTION : " + con);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
