package com.tsystems.services.implementations;

import com.tsystems.dao.interfaces.OptionDao;
import com.tsystems.entities.Option;
import com.tsystems.services.interfaces.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by nikita on 16.09.2020.
 */
@Service
@Transactional
public class OptionServiceImpl implements OptionService {

    private final OptionDao optionDao;

    @Autowired
    public OptionServiceImpl(OptionDao optionDao) {
        this.optionDao = optionDao;
    }
    @Override
    public void add(Option option) {
        optionDao.add(option);
    }

    @Override
    public List<Option> loadAll() {
        return optionDao.getAll();
    }

    @Override
    public Option loadByKey(Integer key) {
        return optionDao.getById(key);
    }
}
