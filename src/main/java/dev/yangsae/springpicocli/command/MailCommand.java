package dev.yangsae.springpicocli.command;

import dev.yangsae.springpicocli.service.IMailService;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;
import picocli.CommandLine.Command;

import java.util.List;
import java.util.concurrent.Callable;

@Component
@Command(name = "mailCommand")
public class MailCommand implements Callable<Integer> {

    private final IMailService mailService;

    public MailCommand(IMailService mailService) {
        this.mailService = mailService;
    }

    @Option(names = "--to", description = "받는 사람(들)", required = true)
    List<String> to;

    @Option(names = "--subject", description = "제목")
    String subject;

    @Parameters(description = "전송할 메시지")
    String[] body = {};

    @Override
    public Integer call() {
        mailService.sendMessage(to, subject, String.join(" ", body));
        return 0;
    }
}
