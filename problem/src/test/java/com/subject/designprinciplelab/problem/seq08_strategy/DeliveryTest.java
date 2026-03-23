package com.subject.designprinciplelab.problem.seq08_strategy;

import com.subject.designprinciplelab.problem.seq08_strategy.constant.Country;
import com.subject.designprinciplelab.problem.seq08_strategy.constant.DeliveryType;
import com.subject.designprinciplelab.problem.seq08_strategy.service.DeliveryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
public class DeliveryTest {
    private Country country;
    private DeliveryType type;
    private double weight;

    @Autowired
    private DeliveryService deliveryService;

    @Test
    void calculateFeeExpressKorea() {
        country = Country.KOREA;
        type = DeliveryType.EXPRESS;
        double expressKoreaFee = 5_000;
        double fee = deliveryService.calculateDeliveryFee(type, 1.0, country);

        assertThat(fee).isEqualTo(expressKoreaFee);
    }

    @Test
    void calculateFeeStandardJapan() {
        country = Country.JAPAN;
        type = DeliveryType.STANDARD;
        weight = 1.6;
        double standardJapanFee = weight * 1.2 + 15000;
        double fee = deliveryService.calculateDeliveryFee(type, weight, country);

        assertThat(fee).isEqualTo(standardJapanFee);
    }

    @Test
    void calculateFeePonyExpressUSA() {
        country = Country.USA;
        type = DeliveryType.PONY_EXPRESS;
        weight = 1.9;
        double ponyExpressUSAdFee = weight * 10.0 + 100_000;
        double fee = deliveryService.calculateDeliveryFee(type, weight, country);

        assertThat(fee).isEqualTo(ponyExpressUSAdFee);
    }
}