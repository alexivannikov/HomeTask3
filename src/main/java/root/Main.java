package root;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import root.model.BankBookDto;
import root.service.BankBookServiceImpl;

import java.math.BigDecimal;

@SpringBootApplication
public class Main {
    public static void main(String [] args) throws Exception {
        SpringApplication.run(Main.class, args);

    }
}
