package com.c1120g1.adweb.service.impl;

import com.c1120g1.adweb.entity.Size;
import com.c1120g1.adweb.repository.SizeRepository;
import com.c1120g1.adweb.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SizeServiceImpl implements SizeService {
    @Autowired
    private SizeRepository sizeRepository;
    @Override
    public List<Size> showAllSize() {
        return sizeRepository.showAllSize();
    }
}
