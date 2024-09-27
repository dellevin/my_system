package io.dellevin.service.impl;

import io.dellevin.common.service.impl.BaseServiceImpl;
import io.dellevin.dao.MarkVisiterDao;
import io.dellevin.dao.TokenDao;
import io.dellevin.entity.MarkVisiterEntity;
import io.dellevin.entity.TokenEntity;
import io.dellevin.service.ApiMarkVisiterIPService;
import io.dellevin.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiMarkVisiterIPImpl extends BaseServiceImpl<MarkVisiterDao, MarkVisiterEntity> implements ApiMarkVisiterIPService {
    @Autowired
    MarkVisiterDao markVisiterDao;

    @Override
    public int insertUrlVisitIP(MarkVisiterEntity markVisiter) {
        return markVisiterDao.insertVisitRecord(markVisiter);
    }
}
