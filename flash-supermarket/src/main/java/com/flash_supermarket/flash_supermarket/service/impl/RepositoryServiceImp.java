package com.flash_supermarket.flash_supermarket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.flash_supermarket.flash_supermarket.dao.History;
import com.flash_supermarket.flash_supermarket.dao.Likes;
import com.flash_supermarket.flash_supermarket.dao.Star;
import com.flash_supermarket.flash_supermarket.mapper.HistoryMapper;
import com.flash_supermarket.flash_supermarket.mapper.LikesMapper;
import com.flash_supermarket.flash_supermarket.mapper.StarMapper;
import com.flash_supermarket.flash_supermarket.service.RepositoryService;
import com.flash_supermarket.flash_supermarket.utils.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : Extrafy
 * description  :
 * createDate   : 2025/6/6 21:02
 */
@Slf4j
@Service
public class RepositoryServiceImp implements RepositoryService {
    @Autowired
    private StarMapper starMapper;

    @Autowired
    private LikesMapper likesMapper;

    @Autowired
    private HistoryMapper historyMapper;


    @Override
    public void addStar(String userName, Integer id) throws BusinessException{
        if (userName == null){
            throw new BusinessException(400, "用户名为空");
        }
        if (id == null){
            throw new BusinessException(400, "仓库 id 为空");
        }
        Star star = new Star(userName, id);
        starMapper.insert(star);
    }

    @Override
    public void cancelStar(String userName, Integer id) throws BusinessException{
        if (userName == null){
            throw new BusinessException(400, "用户名为空");
        }
        if (id == null){
            throw new BusinessException(400, "仓库 id 为空");
        }
        QueryWrapper<Star> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userName);
        queryWrapper.eq("repository_id", id);
        int res = starMapper.delete(queryWrapper);
        if (res == 0){
            throw new BusinessException(400, "收藏不存在");
        }
    }

    @Override
    public void like(String userName, Integer id) throws BusinessException{
        if (userName == null){
            throw new BusinessException(400, "用户名为空");
        }
        if (id == null){
            throw new BusinessException(400, "仓库 id 为空");
        }
        Likes likes = new Likes(userName, id);
        likesMapper.insert(likes);
    }

    @Override
    public void unlike(String userName, Integer id) throws BusinessException{
        if (userName == null){
            throw new BusinessException(400, "用户名为空");
        }
        if (id == null){
            throw new BusinessException(400, "仓库 id 为空");
        }
        QueryWrapper<Likes> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userName);
        queryWrapper.eq("repository_id", id);
        int res = likesMapper.delete(queryWrapper);
        if (res == 0){
            throw new BusinessException(400, "点赞不存在");
        }
    }

    @Override
    public void addHistory(String userName, Integer id) throws BusinessException{
        if (userName == null){
            throw new BusinessException(400, "用户名为空");
        }
        if (id == null){
            throw new BusinessException(400, "仓库 id 为空");
        }
        History history = new History(userName, id);
        historyMapper.insert(history);
    }

    @Override
    public void deleteHistory(String userName, Integer id) throws BusinessException{
        if (userName == null){
            throw new BusinessException(400, "用户名为空");
        }
        if (id == null){
            throw new BusinessException(400, "仓库 id 为空");
        }
        QueryWrapper<History> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userName);
        queryWrapper.eq("repository_id", id);
        int res = historyMapper.delete(queryWrapper);
        if (res == 0){
            throw new BusinessException(400, "历史记录不存在");
        }
    }

    @Override
    public List<Integer> listStars(String userName) throws BusinessException{
        if (userName == null){
            throw new BusinessException(400, "用户名为空");
        }
        List<Star> starList = starMapper.selectList(
                new LambdaQueryWrapper<Star>()
                        .eq(Star::getUserName, userName)
                        .select(Star::getRepositoryId)
        );
        return starList.stream()
                .map(Star::getRepositoryId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Integer> listLikes(String userName) throws BusinessException{
        if (userName == null){
            throw new BusinessException(400, "用户名为空");
        }
        List<Likes> likesList = likesMapper.selectList(
                new LambdaQueryWrapper<Likes>()
                        .eq(Likes::getUserName, userName)
                        .select(Likes::getRepositoryId)
        );
        return likesList.stream()
                .map(Likes::getRepositoryId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Integer> listHistory(String userName) throws BusinessException{
        if (userName == null){
            throw new BusinessException(400, "用户名为空");
        }
        List<History> historyList= historyMapper.selectList(
                new LambdaQueryWrapper<History>()
                        .eq(History::getUserName, userName)
                        .select(History::getRepositoryId)
        );
        return historyList.stream()
                .map(History::getRepositoryId)
                .collect(Collectors.toList());
    }
}

