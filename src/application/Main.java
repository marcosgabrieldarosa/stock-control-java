package application;


import domain.product.Product;
import domain.product.Product.ProductCategory;
import domain.product.Product.ProductType;
import domain.product.Product.UnitMeasure;
import domain.stock.Stock;
import domain.stock.StockMovement;
import domain.stock.StockService;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {

        // 1. Criar produto
        Product product = new Product(
                "Cimento CP-II",
                "Cimento para construção civil",
                ProductCategory.CONSTRUCAO,
                ProductType.PHYSICAL,
                "Materiais básicos",
                UnitMeasure.KG,
                "CIM-CP2-50"
        );

        // 2. Definir preços
        product.defineCostPrice(new BigDecimal("25.00"));
        product.defineSalePrice(new BigDecimal("40.00"));

        // 3. Criar estoque
        Stock stock = new Stock(
                BigDecimal.ZERO,
                new BigDecimal("10"),
                product
        );

        // 4. Criar serviço
        StockService movementService = new StockService();

        // 5. Registrar entrada
        StockMovement entry = movementService.registerEntry(
                stock,
                new BigDecimal("50"),
                StockMovement.OriginMovement.BUY,
                "Compra inicial do fornecedor"
        );

        // 6. Registrar saída
        StockMovement exit = movementService.registerExit(
                stock,
                new BigDecimal("45"),
                StockMovement.OriginMovement.SALE,
                "Venda para cliente final"
        );

        // 7. Exibir resultados
        System.out.println("Produto: " + product.getName());
        System.out.println("Quantidade em estoque: " + stock.getCurrentQuantity());
        System.out.println("Estoque abaixo do mínimo? " + stock.isBelowMinimum());
        System.out.println("Movimento entrada: " + entry);
        System.out.println("Movimento saída: " + exit);
    }
}
