package zk.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import zk.springboot.config.ZKCEApplication;
//import zk.springboot.config.ZKEEApplication;

@SpringBootApplication
//@ZKEEApplication
@ZKCEApplication
public class ZKApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZKApplication.class, args);
	}
}
