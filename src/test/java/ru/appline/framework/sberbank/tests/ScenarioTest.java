package ru.appline.framework.sberbank.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import ru.appline.framework.sberbank.utils.MyAllureRunner;
import ru.appline.framework.sberbank.basetestclasses.BaseTest;

@RunWith(MyAllureRunner.class)
public class ScenarioTest extends BaseTest {
    @Test
    public void test() {
        app.getStartPage()
                .selectBaseMenu("Ипотека")
                .selectSubMenu("Ипотека на готовое жильё")
                .checkPageIsOpened("Ипотека на готовое жилье — СберБанк")
                .fillFields("Стоимость недвижимости", 5180000)
                .fillFields("Первоначальный взнос", 3_058_000)
                .fillFields("Срок кредита", 30)
                .selectCheckBoxStatus("Скидка 0,3% при покупке квартиры на ДомКлик", "false")
                .selectCheckBoxStatus("Страхование жизни", "false")
                .selectCheckBoxStatus("Молодая семья", "true")
                .selectCheckBoxStatus("Электронная регистрация сделки", "false")
                .checkCalculationResults("Ежемесячный платеж", 16922)
                .checkCalculationResults("Сумма кредита", 2122000)
                .checkCalculationResults("Необходимый доход", 21784)
                .checkCalculationResults("Процентная ставка", 11);
    }
}
