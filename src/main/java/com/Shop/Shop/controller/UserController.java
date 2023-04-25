package com.Shop.Shop.controller;

import com.Shop.Shop.Service.ProductService;
import com.Shop.Shop.Service.UserService;
import com.Shop.Shop.Service.repository.UserRepository;
import com.Shop.Shop.UserDetails;
import com.Shop.Shop.model.Product;
import com.Shop.Shop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Objects;


@SuppressWarnings( "ALL" )
@org.springframework.stereotype.Controller
public class UserController {
    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    public UserController(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    private UserRepository userRepository;


    @GetMapping( "/shop" )
    public String presentation() {
        return "presentation";
    }

    @GetMapping( "/registration" )
    public String registration(Model model) {
        model.addAttribute ("user", new User ( ));
        return "registration";
    }


    @PostMapping( "/save" )
    public String saveUser(User user, @RequestParam( "picture" )
    MultipartFile file) {
        String fileName = StringUtils.cleanPath (Objects.requireNonNull (file.getOriginalFilename ( )));
        if (fileName.contains ("..")) {
            System.out.println ("not a a valid file");
        }
        try {
            user.setFile (Base64.getEncoder ( ).encodeToString (file.getBytes ( )));
        } catch (IOException e) {
            e.printStackTrace ( );
        }

        BCryptPasswordEncoder auth = new BCryptPasswordEncoder ( );
        String password = auth.encode (user.getPassword ( ));
        user.setPassword (password);

        userRepository.save (user);
        return "presentation";
    }


    @GetMapping( "/account" )
    public String viewUser(@AuthenticationPrincipal UserDetails loggedUser, Model model) {
        String email = loggedUser.getUsername ( );
        User user = userRepository.findByEmail (email);
        model.addAttribute ("list", user);
        return "userInformation";
    }

    @GetMapping( "/list/edit/{id}" )
    public String changeUser(@PathVariable Long id, Model model) {
        model.addAttribute ("user", userRepository.getById (id));
        return "editUser";
    }
    @PostMapping( "/user/{id}" )
    public String saveChangedUser(@AuthenticationPrincipal UserDetails loggedUser, @PathVariable Long id, @ModelAttribute( "user" ) User user, Model model,
                                  @RequestParam( "picture" ) MultipartFile file) {

        String fileName = StringUtils.cleanPath (Objects.requireNonNull (file.getOriginalFilename ( )));
        if (fileName.contains ("..")) {
            System.out.println ("not a a valid file");
        }
        try {
            user.setFile (Base64.getEncoder ( ).encodeToString (file.getBytes ( )));
        } catch (IOException e) {
            e.printStackTrace ( );
        }

        User setUser = userRepository.getReferenceById (id);

        setUser.setFile (user.getFile ( ));
        setUser.setId (id);
        setUser.setName (user.getName ( ));
        setUser.setSurname (user.getSurname ( ));
        setUser.setPhoneNumber (user.getPhoneNumber ( ));
        setUser.setGender (user.getGender ( ));
        setUser.setEmail (user.getEmail ( ));




        userRepository.save (setUser);
        return "login";
    }

    @GetMapping( "/return" )
    public String userBack(Model model) {

        String keyword =null;

        return listByPage (model, 1,"costPrice","asc",keyword);
    }

    @GetMapping( "/login" )
    public String login() {
        return "login";
    }

    @GetMapping( "/access" )
    public String verification(@AuthenticationPrincipal UserDetails loggedUser,Model model) {
             String keyword =null;
        String email = loggedUser.getUsername ( );
        User user = userRepository.findByEmail (email);
        model.addAttribute ("list", user);
        return listByPage (model, 1,"costPrice","asc",keyword);
    }

    @RequestMapping("/page/{pageNumber}")
    public String listByPage(Model model,
                           @PathVariable(name = "pageNumber")
                           int currentPage,@Param ("sortField")String sortField,
                             @Param ("sortDir") String sortDir,
                             @Param ("keyword")String keyword
                          ) {



        Page <Product> page = productService.listAll (currentPage,sortField,sortDir,keyword);

        long totalItems = page.getTotalElements ( );
        int totalPages = page.getTotalPages ( );

        List<Product>  products =page.getContent ();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("products", products);
        model.addAttribute("keyword", keyword);

        String reverseSortDir = sortDir.equals ("asc") ? "desc": "asc";
        model.addAttribute("reverseSortDir", reverseSortDir);
        return "home";
    }


    @GetMapping( "/comBack" )
    public String editReturn(@AuthenticationPrincipal UserDetails loggedUser, Model model) {
        String email = loggedUser.getUsername ( );
        User user = userRepository.findByEmail (email);
        model.addAttribute ("list", user);
        return "userInformation";
    }


    @GetMapping( "/Recording" )
    public String Recording() {
        return "presentation";
    }

}




