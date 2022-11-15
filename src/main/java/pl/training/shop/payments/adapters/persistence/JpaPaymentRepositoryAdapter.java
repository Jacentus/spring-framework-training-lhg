package pl.training.shop.payments.adapters.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.training.shop.commons.Adapter;
import pl.training.shop.payments.domain.PaymentDomain;
import pl.training.shop.payments.ports.PaymentRepository;

import java.util.Optional;

@Transactional(propagation = Propagation.MANDATORY)
@Adapter
@RequiredArgsConstructor
public class JpaPaymentRepositoryAdapter implements PaymentRepository {

    private final JpaPaymentRepository paymentRepository;
    private final JpaPersistenceMapper mapper;

    @Override
    public PaymentDomain save(PaymentDomain paymentDomain) {
        var paymentEntity = mapper.toEntity(paymentDomain);
        return mapper.toDomain(paymentRepository.save(paymentEntity));
    }

    @Override
    public Optional<PaymentDomain> getById(String id) {
        return paymentRepository.findById(id)
                .map(mapper::toDomain);
    }

}