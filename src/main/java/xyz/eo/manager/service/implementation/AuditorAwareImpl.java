package xyz.eo.manager.service.implementation;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String>  {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.empty();
    }
}
