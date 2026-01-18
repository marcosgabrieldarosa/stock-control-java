package domain.stock;

import domain.product.Product;
import java.math.BigDecimal;

public class Stock {
    private long id;
    private final Product product;
    private BigDecimal currentQuantity;
    private final BigDecimal minimumQuantity;

    public Stock(BigDecimal currentQuantity,
                 BigDecimal minimumQuantity,
                 Product product) {

        if (currentQuantity == null || currentQuantity.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Quantidade atual inválida");
        }
        if (minimumQuantity == null || minimumQuantity.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Quantidade mínima inválida");
        }

        if (product == null) {
            throw new IllegalArgumentException("Produto é obrigatório");
        }

        this.product = product;
        this.currentQuantity = currentQuantity;
        this.minimumQuantity = minimumQuantity;
    }

    public Product getProduct() {
        return product;
    }

    public BigDecimal getCurrentQuantity() {
        return currentQuantity;
    }

    public BigDecimal getMinimumQuantity() {
        return minimumQuantity;
    }

    public void applyMovement(StockMovement movement) {
        if (movement == null){
            throw new IllegalArgumentException("Movimento não pode ser nulo.");
        }
        if (!movement.getProduct().equals(this.product)) {
            throw new IllegalArgumentException("O movimento deve ser do mesmo produto.");
        }

        BigDecimal newQuantity = currentQuantity.add(movement.getSignedAmount());

        if (newQuantity.compareTo(BigDecimal.ZERO) < 0 ) {
            throw new IllegalArgumentException("Saldo insuficiente para realizar o movimento.");
        }

        this.currentQuantity = newQuantity;

    }

    public boolean isBelowMinimum() {
        return currentQuantity.compareTo(minimumQuantity) < 0;
    }

    private void increase(BigDecimal amount) {
        this.currentQuantity = this.currentQuantity.add(amount);
    }

    private void decrease(BigDecimal amount) {
        BigDecimal result = this.currentQuantity.subtract(amount);
        if (result.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }
        this.currentQuantity = result;
    }


}
