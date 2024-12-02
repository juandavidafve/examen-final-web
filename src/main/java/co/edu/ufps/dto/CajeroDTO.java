package co.edu.ufps.dto;

import co.edu.ufps.entities.Cajero;
import lombok.Data;

@Data
public class CajeroDTO {
    private String token;

    public static CajeroDTO fromEntity(Cajero cajero) {
        CajeroDTO dto = new CajeroDTO();
        dto.setToken(cajero.getToken());
        return dto;
    }

    public Cajero toEntity() {
        Cajero cajero = new Cajero();
        cajero.setToken(this.token);
        return cajero;
    }
}