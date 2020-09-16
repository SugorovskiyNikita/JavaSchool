package com.tsystems.services.interfaces;

import com.tsystems.entities.Option;

import java.util.List;

/**
 * Created by nikita on 16.09.2020.
 */
public interface OptionService extends GenericService<Option, Integer> {

    @Override
    void add(Option option);

    @Override
    List<Option> loadAll();

    @Override
    Option loadByKey(Integer key);
}
