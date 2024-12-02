package co.edu.ufps.dto;

import co.edu.ufps.entities.Vendedor;
import lombok.Data;

@Data
public class VendedorDTO {
    private String documento;

    public static VendedorDTO fromEntity(Vendedor vendedor) {
        VendedorDTO dto = new VendedorDTO();
        dto.setDocumento(vendedor.getDocumento());
        return dto;
    }

    public Vendedor toEntity() {
        Vendedor vendedor = new Vendedor();
        vendedor.setDocumento(this.documento);
        return vendedor;
    }
}