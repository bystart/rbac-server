package icu.bystart.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.bystart.base.mapper.CommonMapper;
import icu.bystart.base.service.BaseService;

/**
 * 基础服务实现类
 * @param <M> Mapper类型
 * @param <T> 实体类型
 */
public class BaseServiceImpl<M extends CommonMapper<T>, T> extends ServiceImpl<M, T> implements BaseService<T> {
} 