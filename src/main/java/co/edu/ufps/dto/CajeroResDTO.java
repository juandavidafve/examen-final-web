package co.edu.ufps.dto;

import co.edu.ufps.entities.Cajero;
import lombok.Data;

@Data
public class CajeroResDTO {
    private String documento;
    private String nombre;

    public static CajeroResDTO fromEntity(Cajero cajero) {
        CajeroResDTO dto = new CajeroResDTO();
        dto.setDocumento(cajero.getDocumento());
        dto.setNombre(cajero.getNombre());
        return dto;
    }
}