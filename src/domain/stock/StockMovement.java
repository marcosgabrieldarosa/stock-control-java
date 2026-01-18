package domain.stock;

import domain.product.Product;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class StockMovement{
    private Long id;
    private final Product product;
    private final TypeMovement typeMovement;
    private final BigDecimal amount;
    private final LocalDateTime movementDate;
    private final OriginMovement originMovement;
    private final String observation;


    public StockMovement(Product product,
                         TypeMovement typeMovement,
                         OriginMovement originMovement,
                         BigDecimal amount,
                         String observation) {


        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        }
        if (typeMovement == null){
            throw new IllegalArgumentException("Tipo de movimento não pode ser nulo.");
        }
        if (originMovement == null) {
            throw new IllegalArgumentException("Movimento de origem não pode ser nulo.");
        }
        if (product == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo.");
        }
        if (originMovement == OriginMovement.ADJUSTMENT &&
                (observation == null || observation.trim().isEmpty())) {
            throw new IllegalArgumentException("Ajuste de estoque exige observação");
        }

        this.movementDate = LocalDateTime.now();
        this.observation = observation;
        this.amount = amount;
        this.typeMovement = typeMovement;
        this.originMovement = originMovement;
        this.product = product;

    }

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public enum TypeMovement {
        ENTRY(1),
        EXIT(2);

        private final int code;

        TypeMovement(int code){
            this.code = code;
        }

        public int getCode(){
            return code;
        }
    }

    public boolean isEntry() {
        return typeMovement == TypeMovement.ENTRY;
    }

    public boolean isExit() {
        return typeMovement == TypeMovement.EXIT;
    }

    public enum OriginMovement {
        BUY(1),
        SALE(2),
        ADJUSTMENT(3);

        private final int code;

        OriginMovement(int code){
            this.code = code;
        }

        public int getCode(){
            return code;
        }
    }

    public BigDecimal getSignedAmount() {
        return isEntry() ? amount : amount.negate();
    }

    @Override
    public String toString() {
        return "StockMovement{" +
                "id=" + id +
                ", product=" + product.getName() +  // Assumindo getter em Product
                ", typeMovement=" + typeMovement +
                ", amount=" + amount +
                ", movementDate=" + movementDate +
                ", originMovement=" + originMovement +
                ", observation='" + observation + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StockMovement)) return false;
        StockMovement that = (StockMovement) o;
        return id != null && id.equals(that.id);
    }


    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : System.identityHashCode(this);
    }

}


