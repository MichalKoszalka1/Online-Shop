package com.Shop.Shop.Service;

import com.Shop.Shop.Service.repository.ProductRepository;
import com.Shop.Shop.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Base64;
import java.util.Objects;

@SuppressWarnings( "ALL" )
@Service
@Transactional
public class ProductService  {

    @Autowired
    private ProductRepository productRepo;


    public void  saveProductToDB(MultipartFile file, String name, String description
            , Double costPrice, String brand,int quantity,  BigDecimal mass,String conditions,BigDecimal sizes,String category)
    {
        Product p = new Product();
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        if(fileName.contains(".."))
        {
            System.out.println("not a a valid file");
        }
        try {
            p.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        p.setSizes(sizes);
        p.setConditions(conditions);
        p.setCategory (category);
        p.setMass(mass);
        p.setQuantity (quantity);
        p.setBrand(brand);
        p.setDescription(description);
        p.setName(name);
        p.setCostPrice (costPrice);

        productRepo.save(p);
    }

    public Page<Product> listAll(int pageNumber, String sortField, String sortDir,String keyword)
    {
        Sort sort = Sort.by (sortField);
        sort = sortDir.equals ("asc") ? sort.ascending () : sort.descending ();
        Pageable pageable = PageRequest.of (pageNumber -1,4,sort);

        if (keyword != null){
            return productRepo.findAll(keyword,pageable);
        }
        return productRepo.findAll (pageable);

    }
    public void deleteProductById(Long id)
    {
        productRepo.deleteById(id);
    }
    public Product getProductById(Long id) {

        return productRepo.findById(id).get();
    }



}