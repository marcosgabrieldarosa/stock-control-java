package domain.product;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Product {
    private Long id;
    private String name;
    private UnitMeasure unitMeasure;
    private BigDecimal costPrice;
    private BigDecimal salePrice;
    private String description;
    private String subcategory;
    private boolean active;
    private String sku;
    private ProductType productType;
    private ProductCategory productCategory;

    public Product(final String name,
                   String description,
                   ProductCategory productCategory,
                   ProductType productType,
                   String category,
                   UnitMeasure unitMeasure,
                   String sku){

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio");
        }
        if (sku == null || sku.trim().isEmpty()) {
            throw new IllegalArgumentException("SKU não pode ser nulo ou vazio");
        }
        if (!sku.matches("^[A-Za-z0-9-]+$")) {
            throw new IllegalArgumentException("SKU deve conter apenas letras, números e hífen (-)");
        }
        if (productType == null){
            throw new IllegalArgumentException("Tipo de produto não pode ser nulo");
        }
        if (productCategory == null){
            throw new IllegalArgumentException("Categoria do produto não pode ser nula");
        }
        if (unitMeasure == null) {
            throw new IllegalArgumentException("Unidade de medida é obrigatória");
        }

        this.name = name;
        this.sku = sku;
        this.description = description;
        this.subcategory = category;
        this.unitMeasure = unitMeasure;
        this.productType = productType;
        this.productCategory = productCategory;
        this.active = true;
    }

    public String getName() {
        return name;
    }

    public void activate() {
        if (this.active){
            throw new IllegalStateException("Erro! O produto já está ativo!");
        }
        this.active = true;

    }

    public void deactivate() {
        if (!this.active) {
            throw new IllegalStateException("Erro! O produto já está desativado!");
        }
        this.active = false;
    }

    public void defineCostPrice(BigDecimal costPrice) {
        if (costPrice == null || costPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O valor não pode ser nulo ou negativo!");
        }
        this.costPrice = costPrice;
    }

    public void defineSalePrice(BigDecimal salePrice) {
        if (salePrice == null || salePrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O valor não pode ser nulo ou negativo!");
        }
        if (costPrice != null && salePrice.compareTo(costPrice) < 0) {
            throw new IllegalArgumentException("O preço de venda não pode ser menor que o preço de custo!");
        }
        this.salePrice = salePrice;
    }

    public boolean canBeSold() {
        return active && salePrice != null && salePrice.compareTo(BigDecimal.ZERO) > 0;
    }


    public enum ProductType {
        PHYSICAL(1),
        DIGITAL(2),
        SERVICE(3);

        private final int code;

        ProductType(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    public enum ProductCategory {
        ALVENARIA(1),
        FERRAMENTAS(2),
        CONSTRUCAO(3),
        ACABAMENTO(4);

        private final int code;

        ProductCategory(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    public BigDecimal getProfitMargin() {
        if (costPrice == null){
            throw new IllegalStateException("Preço de custo não definido");
        }
        if (salePrice == null) {
            throw new IllegalStateException("Preço de venda não definido");
        }
        if (costPrice.compareTo(BigDecimal.ZERO) == 0){
            throw new IllegalStateException("Preço de custo não pode ser zero");
        }
        BigDecimal profit = salePrice.subtract(costPrice);

        return profit
                .divide(costPrice, 4, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100));
    }

    public BigDecimal getProfitValue() {
        if (costPrice == null || salePrice == null) {
            throw new IllegalStateException("Preços não definidos");
        }
        return salePrice.subtract(costPrice);
    }

    public enum UnitMeasure {
        UNIDADE(1),
        KG(2),
        GRAMA(3),
        LITRO(4),
        MEDIDOR(5),
        METRO(6),
        CENTIMETRO(7),
        POLEGADA(8);

        private final int code;

        UnitMeasure(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sku='" + sku + '\'' +
                ", productType=" + productType +
                ", productCategory=" + productCategory +
                ", active=" + active +
                ", costPrice=" + costPrice +
                ", salePrice=" + salePrice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(sku, product.sku);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku);
    }

}
