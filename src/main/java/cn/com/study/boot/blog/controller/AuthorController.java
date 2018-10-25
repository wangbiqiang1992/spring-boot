package cn.com.study.boot.blog.controller;

import cn.com.study.boot.blog.dto.AuthorDTO;
import cn.com.study.boot.blog.jpa.dao.AuthorDAO;
import cn.com.study.boot.blog.jpa.dao.AuthorDetailDAO;
import cn.com.study.boot.blog.jpa.dao.AuthorExtDAO;
import cn.com.study.boot.blog.jpa.po.AuthorDetailPO;
import cn.com.study.boot.blog.jpa.po.AuthorExtPO;
import cn.com.study.boot.blog.jpa.po.AuthorPO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthorController {

    @Autowired
    private AuthorDAO authorDAO;

    @Autowired
    private AuthorExtDAO authorExtDAO;

    @Autowired
    private AuthorDetailDAO authorDetailDAO;

    @PostMapping("/addAuthor")
    public Object addAuthor(@RequestBody AuthorDTO authorDTO){
        AuthorPO authorPO = new AuthorPO();
        BeanUtils.copyProperties(authorDTO,authorPO);
        AuthorExtPO authorExtPO =  new AuthorExtPO();
        BeanUtils.copyProperties(authorDTO,authorExtPO);
        authorPO.setAuthorExt(authorExtPO);
        authorPO = authorDAO.save(authorPO);

        AuthorDetailPO authorDetailPO = new AuthorDetailPO();
        BeanUtils.copyProperties(authorDTO,authorDetailPO);
        authorDetailPO.setAuthor(authorPO);
        authorDetailDAO.save(authorDetailPO);
        return authorDAO.findOne(authorPO.getId());
    }

    @GetMapping("/queryAuthor/{id}")
    public Object queryAuthor(@PathVariable("id") Integer id){
        AuthorPO authorPO = authorDAO.findOne(id);
        return authorPO;
    }
}
