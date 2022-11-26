package com.market.antonio.persistence;

import com.market.antonio.domain.Product;
import com.market.antonio.domain.repository.ProductRepository;
import com.market.antonio.persistence.crud.ProductoCrudRepository;
import com.market.antonio.persistence.entity.Producto;
import com.market.antonio.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository implements ProductRepository {

    @Autowired
    private ProductoCrudRepository productoCrudRepository;

    @Autowired
    private ProductMapper mapper;

    /*hay 3 formas de injection de dependecia
    *
    * 1: es poniendo el @Autowired encima del la depencia que quieras injectar
    * 2: poniendo el @Autowired encima de el metodo set de la dependencia
    *
    * 3: la mas recomendaable es esta injectando las dependencias mediante el contructor
    *  para poder declarar los atributos inyectados como final para que sean inmutables,
    *  y además es muy recomendado para declarar dependencias obligatorias. Asimismo se evita
    *  que la dependencia en un momento determinado pueda ser null.
    *
    * */

//    @Autowired
//    public ProductoRepository(ProductoCrudRepository productoCrudRepository, ProductMapper mapper) {
//        this.productoCrudRepository = productoCrudRepository;
//        this.mapper = mapper;
//    }

    @Override
    public List<Product> getAll(){

    List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();

        return mapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId){
        List<Producto> productos=productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }



    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        Optional<List<Producto>> productos= productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity,true);
        return productos.map(prods -> mapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
       return productoCrudRepository.findById(productId).map(producto -> mapper.toProduct(producto));
    }

    @Override
    public Product save(Product product) {

        Producto producto = mapper.toProducto(product);

        return mapper.toProduct(productoCrudRepository.save(producto));
    }

    @Override
    public Product update(Product product) {

        return getProduct(product.getProductId()).map(productToUpdate -> {
            productToUpdate.setName(product.getName());
            productToUpdate.setCategoryId(product.getCategoryId());
            productToUpdate.setPrice(product.getPrice());
            productToUpdate.setStock(product.getStock());
            productToUpdate.setActive(product.isActive());
            Producto producto = mapper.toProducto(productToUpdate);
            return save(mapper.toProduct(producto));
        }).orElse(null);

    }


    @Override
    public void delete(int productId) {
        productoCrudRepository.deleteById(productId);
    }









}
