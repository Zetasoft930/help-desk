package ao.co.celsodesousa.helpDesk;

import ao.co.celsodesousa.helpDesk.security.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class UdemySpringAngulaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(UdemySpringAngulaApiApplication.class, args);
	}

}
