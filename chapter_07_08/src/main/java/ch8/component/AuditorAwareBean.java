package ch8.component;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditorAwareBean")
public class AuditorAwareBean implements AuditorAware<String> {
    private static boolean flag = true;
    @Override
    public Optional<String> getCurrentAuditor() {
        flag = !flag;
        return flag ? Optional.of("Antey1988") : Optional.of("OlegPetrov");
    }
}
