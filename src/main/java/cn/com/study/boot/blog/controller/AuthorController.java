package cn.com.study.boot.blog.controller;

import cn.com.study.boot.blog.dto.AuthorDTO;
import cn.com.study.boot.blog.jpa.dao.AuthorDAO;
import cn.com.study.boot.blog.jpa.dao.AuthorDetailDAO;
import cn.com.study.boot.blog.jpa.po.AuthorDetailPO;
import cn.com.study.boot.blog.jpa.po.AuthorPO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthorController {

    @Autowired
    private AuthorDAO authorDAO;

    @Autowired
    private AuthorDetailDAO authorDetailDAO;

    @PostMapping("/addAuthor")
    public Object addAuthor(@RequestBody AuthorDTO authorDTO){
        AuthorPO authorPO = new AuthorPO();
        BeanUtils.copyProperties(authorDTO,authorPO);
        AuthorDetailPO authorDetailPO = new AuthorDetailPO();
        BeanUtils.copyProperties(authorDTO,authorDetailPO);
        /*authorPO.setDetail(authorDetailPO);*/
        authorPO = authorDAO.save(authorPO);
        authorDetailPO.setAuthor(authorPO);
        authorDetailDAO.save(authorDetailPO);
        AuthorPO authorPO2 = authorDAO.findOne(authorPO.getId());
        return authorPO2;
    }

    @GetMapping("/queryAuthor/{id}")
    public Object queryAuthor(@PathVariable("id") Integer id){
        AuthorPO authorPO = authorDAO.findOne(id);
        return authorPO;
    }
}
