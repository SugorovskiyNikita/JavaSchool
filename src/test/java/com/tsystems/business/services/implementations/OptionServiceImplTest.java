package com.tsystems.business.services.implementations;

import com.tsystems.db.dao.interfaces.OptionDao;
import com.tsystems.db.dao.interfaces.TariffDao;
import com.tsystems.db.dto.OptionDto;
import com.tsystems.db.entities.Option;
import com.tsystems.db.entities.Tariff;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

/**
 * Created by nikita on 09.11.2020.
 */
@RunWith(MockitoJUnitRunner.class)
public class OptionServiceImplTest {

    @InjectMocks
    private OptionServiceImpl optionService;

    @Mock
    private OptionDao optionDao;

    @Mock
    private TariffDao tariffDao;

    @BeforeEach
    void onSetUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test() throws Exception {
        Assert.assertNotNull(optionService);
    }

    @Test
    public void testAddNew() {
        OptionDto optionDto = new OptionDto();
        Option option1 = new Option();
        Option option2 = new Option();
        Option option3 = new Option();
        Option option4 = new Option();
        optionDto.setId(6);
        option1.setId(1);
        option2.setId(2);
        option3.setId(3);
        option4.setId(4);
        when(optionDao.loadByKey(1)).thenReturn(option1);
        when(optionDao.loadByKey(2)).thenReturn(option2);
        when(optionDao.loadByKey(3)).thenReturn(option3);
        when(optionDao.loadByKey(4)).thenReturn(option4);
        Tariff tariff = new Tariff();
        tariff.setId(10);
        when(tariffDao.loadByKey(10)).thenReturn(tariff);
        when(optionDao.add(any(Option.class))).thenReturn(optionDto.convertToEntity());
        String[] requiredFromId = new String[]{"1", "2"};
        String[] forbiddenWithId = new String[]{"3", "4"};
        List<Integer> forTariffsId = new ArrayList<>();
        forTariffsId.add(10);
        OptionDto optionDto1 = optionService.addNew(optionDto, requiredFromId, forbiddenWithId, forTariffsId);
        Assert.assertEquals("Option successfully created", 6, (int) optionDto1.getId());
    }

    @Test
    public void testLoadAll() {
        Option option1 = new Option();
        Option option2 = new Option();
        option1.setId(1);
        option2.setId(2);
        List<Option> options = new ArrayList<>();
        options.add(option1);
        options.add(option2);
        when(optionDao.loadAll()).thenReturn(options);
        List<OptionDto> optionDtos = optionService.loadAll();
        Assert.assertNotNull("Option list successfully uploaded", optionDtos);

    }

    @Test
    public void testRemove() {
        optionService.remove(1);
        verify(optionDao, atLeastOnce()).remove(any());
    }

    @Test
    public void testLoadByKey() {
        Option option1 = new Option();
        option1.setId(1);
        when(optionDao.loadByKey(anyInt())).thenReturn(option1);
        OptionDto optionDto = optionService.loadByKey(1);
        Assert.assertEquals("Option successfully uploaded", 1, (int) optionDto.getId());

    }

    @Test
    public void testGetOptionsOfTariff() {
        Tariff tariff = new Tariff();
        Option option1 = new Option();
        Option option2 = new Option();
        option1.setId(1);
        option2.setId(2);
        tariff.setId(5);
        Set<Option> optionSet = new HashSet<>();
        optionSet.add(option1);
        optionSet.add(option2);
        List<Option> options = new ArrayList<>();
        options.add(option1);
        options.add(option2);
        tariff.setPossibleOptions(optionSet);
        when(optionDao.getOptionsForTariff(5)).thenReturn(options);
        List<OptionDto> optionDtos = optionService.getOptionsOfTariff(5);
        Assert.assertNotNull("Option list successfully uploaded", optionDtos);


    }
}