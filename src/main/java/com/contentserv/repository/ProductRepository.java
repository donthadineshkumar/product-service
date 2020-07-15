package com.contentserv.repository;

import com.contentserv.model.Product;
import com.contentserv.model.Sequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigInteger;
import java.util.List;

@Repository
public class ProductRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    public void createProduct(Product product) {
        //Better ways are there - restricting my self to basic synchronized
        synchronized(product) {
            product.setSku(getProductSequence());
            mongoTemplate.insert(product);
        }
    }

    public void deleteProduct(@PathVariable String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        mongoTemplate.remove(query, Product.class);
    }

    public void updateProduct(Product product) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(product.getSku()));
        mongoTemplate.findAndReplace(query, product);
    }

    public List<Product> searchProduct(String searchStr) {
        Query query = new Query();
        query.addCriteria(TextCriteria
                .forDefaultLanguage()
                .matchingAny(searchStr));
        return mongoTemplate.find(query, Product.class);
    }

    public List<Product> validateProductName(String productName) {
        Query query = new Query();
        query.addCriteria(TextCriteria
                        .forDefaultLanguage()
                        .matchingPhrase(productName));
        return mongoTemplate.find(query, Product.class);
    }

    public String getProductSequence(){
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is("products"));
        Update update = new Update();
        update.inc("seq", 1);
        Sequence productSequence = mongoTemplate.findAndModify(query, update, Sequence.class);
        BigInteger productSeqVal = productSequence.getSeq();
        Query query2 = new Query();
        query2.addCriteria(Criteria.where("_id").is("categories"));
        Sequence categorySequence = mongoTemplate.findAndModify(query2, update, Sequence.class);
        BigInteger categorySeqVal = categorySequence.getSeq();
        return "PR-CAT-"+String.format("%04d", categorySeqVal)
                +"C-"+String.format("%09d",productSeqVal);
    }

}
