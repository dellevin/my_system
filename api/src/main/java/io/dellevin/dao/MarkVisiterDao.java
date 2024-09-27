package io.dellevin.dao;

import io.dellevin.common.dao.BaseDao;
import io.dellevin.entity.MarkVisiterEntity;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface MarkVisiterDao extends BaseDao<MarkVisiterEntity> {
    int insertVisitRecord(MarkVisiterEntity markVisiter);
}
