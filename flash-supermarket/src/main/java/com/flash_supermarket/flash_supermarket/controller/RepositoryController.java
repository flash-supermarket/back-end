package com.flash_supermarket.flash_supermarket.controller;

import com.flash_supermarket.flash_supermarket.dao.History;
import com.flash_supermarket.flash_supermarket.dao.Likes;
import com.flash_supermarket.flash_supermarket.dao.Star;
import com.flash_supermarket.flash_supermarket.response.CustomResponse;
import com.flash_supermarket.flash_supermarket.service.RepositoryService;
import com.flash_supermarket.flash_supermarket.utils.BusinessException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : Extrafy
 * description  :
 * createDate   : 2025/6/6 20:48
 */
@CrossOrigin
@RestController
@Tag(name = "点赞收藏", description = "点赞收藏管理")
@RequestMapping("/api")
public class RepositoryController {
    @Autowired
    private RepositoryService repositoryService;

    // 收藏
    @PostMapping("/star")
    @Operation(summary = "收藏", description = "用户收藏仓库")
    public CustomResponse star(@RequestBody Star star){
        CustomResponse customResponse = new CustomResponse();
        try {
            repositoryService.addStar(star.getUserName(), star.getRepositoryId());
            customResponse.setData(true);
            customResponse.setMessage("收藏成功");
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }

    // 取消收藏
    @DeleteMapping("/unstar")
    @Operation(summary = "取消收藏")
    public CustomResponse cancelStar(@RequestBody Star star) {
        CustomResponse customResponse = new CustomResponse();
        try {
            repositoryService.cancelStar(star.getUserName(), star.getRepositoryId());
            customResponse.setData(true);
            customResponse.setMessage("取消收藏成功");
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }

    // 点赞
    @PostMapping("/like")
    @Operation(summary = "点赞", description = "用户点赞仓库")
    public CustomResponse like(@RequestBody Likes likes){
        CustomResponse customResponse = new CustomResponse();
        try {
            repositoryService.like(likes.getUserName(), likes.getRepositoryId());
            customResponse.setData(true);
            customResponse.setMessage("点赞成功");
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }

    // 取消点赞
    @DeleteMapping("/unlike")
    @Operation(summary = "取消点赞")
    public CustomResponse unlike(@RequestBody Likes likes) {
        CustomResponse customResponse = new CustomResponse();
        try {
            repositoryService.unlike(likes.getUserName(), likes.getRepositoryId());
            customResponse.setData(true);
            customResponse.setMessage("取消点赞成功");
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }

    // 添加历史记录
    @PostMapping("/addHistory")
    @Operation(summary = "添加历史记录", description = "用户添加历史记录")
    public CustomResponse addHistory(@RequestBody History history){
        CustomResponse customResponse = new CustomResponse();
        try {
            repositoryService.addHistory(history.getUserName(), history.getRepositoryId());
            customResponse.setData(true);
            customResponse.setMessage("添加历史记录成功");
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }

    // 删除历史记录
    @DeleteMapping("/deleteHistory")
    @Operation(summary = "删除历史记录")
    public CustomResponse deleteHistory(@RequestBody History history) {
        CustomResponse customResponse = new CustomResponse();
        try {
            repositoryService.deleteHistory(history.getUserName(), history.getRepositoryId());
            customResponse.setData(true);
            customResponse.setMessage("删除历史记录成功");
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }

    // 获取用户所有收藏
    @GetMapping("/listStar/{userName}")
    @Operation(summary = "获取用户所有收藏信息", description = "传入用户的 userName, 获取用户所有收藏信息")
    @Parameters(value = {
            @Parameter(name = "userName", description = "用户名", in = ParameterIn.PATH, example = "WFY")
    })
    public CustomResponse listStar(@PathVariable String userName){
        CustomResponse customResponse = new CustomResponse();
        try {
            List<Integer> ids = repositoryService.listStars(userName);
            customResponse.setData(ids);
            customResponse.setMessage("获取成功");
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }

    // 获取用户所有点赞
    @GetMapping("/listLike/{userName}")
    @Operation(summary = "获取用户所有点赞信息", description = "传入用户的 userName, 获取用户所有点赞信息")
    @Parameters(value = {
            @Parameter(name = "userName", description = "用户名", in = ParameterIn.PATH, example = "WFY")
    })
    public CustomResponse listLike(@PathVariable String userName){
        CustomResponse customResponse = new CustomResponse();
        try {
            List<Integer> ids = repositoryService.listLikes(userName);
            customResponse.setData(ids);
            customResponse.setMessage("获取成功");
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }

    // 获取用户所有历史记录
    @GetMapping("/listHistory/{userName}")
    @Operation(summary = "获取用户所有历史信息", description = "传入用户的 userName, 获取用户所有历史记录, 用于推荐")
    @Parameters(value = {
            @Parameter(name = "userName", description = "用户名", in = ParameterIn.PATH, example = "WFY")
    })
    public CustomResponse listHistory(@PathVariable String userName){
        CustomResponse customResponse = new CustomResponse();
        try {
            List<Integer> ids = repositoryService.listHistory(userName);
            customResponse.setData(ids);
            customResponse.setMessage("获取成功");
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }

    // 获取用户所有点赞
    @GetMapping("/listLikeById/{repoId}")
    @Operation(summary = "根据帖子ID查所有点赞用户", description = "传入帖子的 Id, 获取所有点赞用户")
    @Parameters(value = {
            @Parameter(name = "repoId", description = "帖子Id", in = ParameterIn.PATH, example = "1")
    })
    public CustomResponse listLikeByRepoId(@PathVariable String repoId){
        CustomResponse customResponse = new CustomResponse();
        try {
            List<String> userNames = repositoryService.getUsersByRepoId(Integer.parseInt(repoId));
            customResponse.setData(userNames);
            customResponse.setMessage("获取成功");
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }
}

