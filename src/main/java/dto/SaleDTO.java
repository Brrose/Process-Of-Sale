package dto;

import java.util.ArrayList;

public record SaleDTO(float totalPrice, float totalVAT, ArrayList<ItemDTO> items, float cash, float change) {

}
