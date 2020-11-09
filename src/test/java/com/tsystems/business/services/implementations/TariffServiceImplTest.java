package com.tsystems.business.services.implementations;

import com.tsystems.db.dao.interfaces.OptionDao;
import com.tsystems.db.dao.interfaces.TariffDao;
import com.tsystems.db.dto.OptionDto;
import com.tsystems.db.dto.TariffDto;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

/**
 * Created by nikita on 09.11.2020.
 */
@RunWith(MockitoJUnitRunner.class)
public class TariffServiceImplTest {

    @InjectMocks
    private TariffServiceImpl tariffService;

    @Mock
    private TariffDao tariffDao;

    @Mock
    private OptionDao optionDao;

    @Mock
    private MessageService messageService;

    @BeforeEach
    void onSetUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void test() throws Exception {
        Assert.assertNotNull(tariffService);
    }

    @Test
    public void testLoadAll() {
        Tariff tariff = new Tariff();
        Tariff tariff1 = new Tariff();
        List<Tariff> tariffs = new ArrayList<>();
        tariffs.add(tariff);
        tariffs.add(tariff1);
        when(tariffDao.loadAll()).thenReturn(tariffs);
        List<TariffDto> tariffDtos = tariffService.loadAll();
        Assert.assertNotNull("Tariff list successfully uploaded", tariffDtos);
    }

    @Test
    public void testLoadByKey() {
        Tariff tariff = new Tariff();
        tariff.setId(1);
        when(tariffDao.loadByKey(anyInt())).thenReturn(tariff);
        TariffDto tariffDto = tariffService.loadByKey(1);
        Assert.assertEquals("Option successfully uploaded", 1, (int) tariffDto.getId());
    }

    @Test
    public void testRemove() {
        tariffService.remove(1);
        messageService.sendMessage("updateTariff");
        verify(tariffDao, atLeastOnce()).remove(any());
    }

    @Test
    public void testUpdate() {
        Tariff tariff = new Tariff();
        tariff.setId(1);
        String name = "test";
        BigDecimal cost = new BigDecimal(100.00);
        String description = "test test";
        when(tariffDao.loadByKey(anyInt())).thenReturn(tariff);
        Option option = new Option();
        Option option1 = new Option();
        option.setId(2);
        option1.setId(3);
        List<Integer> newOptions = new ArrayList<>();
        newOptions.add(option.getId());
        newOptions.add(option1.getId());
        when(optionDao.loadByKey(2)).thenReturn(option);
        when(optionDao.loadByKey(3)).thenReturn(option1);
        when(tariffDao.add(any(Tariff.class))).thenReturn(tariff);
        TariffDto tariffDto = tariffService.update(1,newOptions, name, cost, description);
        messageService.sendMessage("Update");
        Assert.assertNotNull("Tariff successfully updateTariff", tariffDto);
    }

    @Test
    public void addNew() {
        TariffDto tariffDto = new TariffDto();
        tariffDto.setId(5);
        Option option = new Option();
        Option option1 = new Option();
        option.setId(2);
        option1.setId(3);
        List<Integer> newOptions = new ArrayList<>();
        newOptions.add(option.getId());
        newOptions.add(option1.getId());
        when(optionDao.loadByKey(2)).thenReturn(option);
        when(optionDao.loadByKey(3)).thenReturn(option1);
        when(tariffDao.add(any(Tariff.class))).thenReturn(tariffDto.convertToEntity());
        TariffDto tariffDto1 = tariffService.addNew(tariffDto, newOptions);
        messageService.sendMessage("Update");
        Assert.assertEquals("Tariff successfully created", 5, (int) tariffDto1.getId());

    }
}