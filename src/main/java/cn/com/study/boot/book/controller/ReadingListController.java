package cn.com.study.boot.book.controller;

import cn.com.study.boot.book.jpa.dao.BookDAO;
import cn.com.study.boot.book.jpa.po.BookPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/readingList")
public class ReadingListController {

    @Autowired
    private BookDAO bookDAO;

    @GetMapping("/{reader}")
    public String readersBooks(@PathVariable("reader") String reader, Model model){
        List<BookPO> bookPOs = bookDAO.findByReader(reader);
        if(null != bookPOs){
            model.addAttribute("books",bookPOs);
        }
        return "readingList";
    }

    @PostMapping("/{reader}")
    public String addToReadingList(@PathVariable String reader,BookPO book){
        book.setReader(reader);
        bookDAO.save(book);
        return "redirect:/readingList/{reader}";
    }
}
