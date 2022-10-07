package dev.yangsae.springpicocli;

import dev.yangsae.springpicocli.command.LoginCommand;
import dev.yangsae.springpicocli.command.MailCommand;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import picocli.CommandLine;
import picocli.CommandLine.IFactory;

@SpringBootApplication
public class SpringPicocliApplication implements CommandLineRunner, ExitCodeGenerator {

    private IFactory factory;
    private MailCommand mailCommand;
    private LoginCommand loginCommand;
    private int exitCode;

    public SpringPicocliApplication(IFactory factory,
                                    MailCommand mailCommand,
                                    LoginCommand loginCommand) {
        this.factory = factory;
        this.mailCommand = mailCommand;
        this.loginCommand = loginCommand;
    }

    @Override
    public void run(String... args) {
        exitCode = new CommandLine(loginCommand, factory).execute("-u", "-p");
    }

    @Override
    public int getExitCode() {
        return this.exitCode;
    }

    public static void main(String[] args) {

        System.exit(
                SpringApplication.exit(
                        SpringApplication.run(SpringPicocliApplication.class, args)));
    }
}
