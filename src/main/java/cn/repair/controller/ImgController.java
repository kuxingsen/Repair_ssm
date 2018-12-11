package cn.repair.controller;

import cn.repair.dto.Result;
import cn.repair.pojo.Img;
import cn.repair.service.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImgController{
    @Autowired
    ImgService imgService;

    @RequestMapping("getImage")
    public Result<Img> getImage(){
        return new Result<>(true,imgService.getImage());
    }
}
