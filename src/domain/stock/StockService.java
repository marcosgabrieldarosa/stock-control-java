package domain.stock;

import java.math.BigDecimal;

public class StockService {

    public StockMovement registerEntry(
            Stock stock,
            BigDecimal quantity,
            StockMovement.OriginMovement origin,
            String observation
    ) {
        validate(stock, quantity, origin);

        StockMovement movement = new StockMovement(
                stock.getProduct(),
                StockMovement.TypeMovement.ENTRY,
                origin,
                quantity,
                observation
        );

        stock.applyMovement(movement);
        return movement;
    }

    public StockMovement registerExit(
            Stock stock,
            BigDecimal quantity,
            StockMovement.OriginMovement origin,
            String observation
    ) {
        validate(stock, quantity, origin);

        StockMovement movement = new StockMovement(
                stock.getProduct(),
                StockMovement.TypeMovement.EXIT,
                origin,
                quantity,
                observation
        );

        stock.applyMovement(movement);
        return movement;
    }

    private void validate(
            Stock stock,
            BigDecimal quantity,
            StockMovement.OriginMovement origin
    ) {
        if (stock == null) {
            throw new IllegalArgumentException("Stock não pode ser nulo");
        }

        if (quantity == null || quantity.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }

        if (origin == null) {
            throw new IllegalArgumentException("Origem do movimento é obrigatória");
        }
    }
}
