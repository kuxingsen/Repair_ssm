package cn.repair.service;

import cn.repair.pojo.Img;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImgService{
    public List<Img> getImage(){
        Img img1 = new Img();
        img1.setId(1000);
        img1.setTitle("图片");
        img1.setType(1);
        img1.setTopImage("http://10.83.4.101:8080/dachuang/1000/1.jpg");
        Img img2 = new Img();
        img2.setId(1000);
        img2.setTitle("图片");
        img2.setType(1);
        img2.setTopImage("http://10.83.4.101:8080/dachuang/1000/2.jpg");
        return new ArrayList<Img>(){{add(img1);add(img2);}};
    }
}
