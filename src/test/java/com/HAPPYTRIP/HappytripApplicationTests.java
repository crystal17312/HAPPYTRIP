package com.HAPPYTRIP;

import com.HAPPYTRIP.domain.Board;
import com.HAPPYTRIP.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;


@SpringBootTest
class HappytripApplicationTests {

	@Autowired
	private BoardRepository boardRepository;

	@Test
	void test() {
	}


}
