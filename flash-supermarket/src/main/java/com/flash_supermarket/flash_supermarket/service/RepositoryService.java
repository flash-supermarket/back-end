package com.flash_supermarket.flash_supermarket.service;

import com.flash_supermarket.flash_supermarket.utils.BusinessException;

import java.util.List;

public interface RepositoryService {
    void addStar(String userName, Integer id) throws BusinessException;
    void cancelStar(String userName, Integer id) throws BusinessException;
    void like(String userName, Integer id) throws BusinessException;
    void unlike(String userName, Integer id) throws BusinessException;
    void addHistory(String userName, Integer id) throws BusinessException;
    void deleteHistory(String userName, Integer id) throws BusinessException;
    List<Integer> listStars(String userName) throws BusinessException;
    List<Integer> listLikes(String userName) throws BusinessException;
    List<Integer> listHistory(String userName) throws BusinessException;
    List<String> getUsersByRepoId(Integer id) throws  BusinessException;
}
