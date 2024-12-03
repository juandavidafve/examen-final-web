package co.edu.ufps.services;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Cajero;
import co.edu.ufps.entities.Cliente;
import co.edu.ufps.entities.Compra;
import co.edu.ufps.entities.DetallesCompra;
import co.edu.ufps.entities.Pago;
import co.edu.ufps.entities.Producto;
import co.edu.ufps.entities.Tienda;
import co.edu.ufps.entities.TipoDocumento;
import co.edu.ufps.entities.TipoPago;
import co.edu.ufps.entities.Vendedor;
import co.edu.ufps.exceptions.BusinessException;
import co.edu.ufps.exceptions.ResourceNotFoundException;
import co.edu.ufps.repositories.CajeroRepository;
import co.edu.ufps.repositories.ClienteRepository;
import co.edu.ufps.repositories.CompraRepository;
import co.edu.ufps.repositories.ProductoRepository;
import co.edu.ufps.repositories.TiendaRepository;
import co.edu.ufps.repositories.TipoDocumentoRepository;
import co.edu.ufps.repositories.TipoPagoRepository;
import co.edu.ufps.repositories.VendedorRepository;

@Service
public class CompraService {
	
	@Autowired
	TiendaRepository tiendaRepository;
	
	@Autowired
	CompraRepository compraRepository;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	CajeroRepository cajeroRepository;
	
	@Autowired
	ProductoRepository productoRepository;
	
	@Autowired
	TipoPagoRepository tipoPagoRepository;
	
	@Autowired
	TipoDocumentoRepository tipoDocumentoRepository;
	
	@Autowired
	VendedorRepository vendedorRepository;
	
	public Compra crearFactura(Compra compra) {
		
		if(compra.getTienda() == null) {
			throw new ResourceNotFoundException("No hay información de la tienda.");
		}
		
		if(compra.getCajero() == null) {
			throw new ResourceNotFoundException("No hay información del cajero.");
		}
		
		if(compra.getVendedor() == null) {
			throw new ResourceNotFoundException("No hay información del vendedor.");
		}
		
		if(compra.getCliente() == null) {
			throw new ResourceNotFoundException("No hay información del cliente.");
		}
		
		if(compra.getPagos() == null || compra.getPagos().size() == 0) {
			throw new ResourceNotFoundException("No hay medios de pagos asignado para esta compra.");
		}
		
		if(compra.getDetallesCompra() == null || compra.getDetallesCompra().size() == 0) {
			throw new ResourceNotFoundException("No hay productos asignados para esta compra.");
		}
		
		Optional<Tienda> tiendaOpt = tiendaRepository.findOneByUuid(compra.getTienda().getUuid());
		if(tiendaOpt.isEmpty()) {
			throw new ResourceNotFoundException("La tienda no existe.");
		}
		Tienda tienda = tiendaOpt.get();
		compra.setTienda(tienda);
		
		Optional<TipoDocumento> tipoDocumentoOpt = tipoDocumentoRepository.findOneByNombre(compra.getCliente().getTipoDocumento().getNombre());
		
		if(tipoDocumentoOpt.isEmpty()) {
			throw new ResourceNotFoundException("El tipo de documento no existe");
		}
		
		TipoDocumento tipoDocumento = tipoDocumentoOpt.get();
		
		Optional<Cliente> clienteOpt = clienteRepository.findOneByDocumentoAndTipoDocumentoNombre(compra.getCliente().getDocumento(), tipoDocumento.getNombre());;
		if(clienteOpt.isPresent()) {
			compra.setCliente(clienteOpt.get());
		}
		
		compra.getCliente().setTipoDocumento(tipoDocumento);
		
		for(DetallesCompra detalleCompra : compra.getDetallesCompra()) {
			String referencia = detalleCompra.getProducto().getReferencia();
			Optional<Producto> productoOpt = productoRepository.findOneByReferencia(referencia);
			
			if(productoOpt.isEmpty()) {
				throw new ResourceNotFoundException("La referencia del producto " + referencia + " no existe, por favor revisar los datos");
			}
			
			Producto producto = productoOpt.get();
			producto.consumir(detalleCompra.getCantidad());
			
			detalleCompra.setProducto(producto);
		}
		
		for(Pago pago : compra.getPagos()) {
			if(pago.getTipoPago() == null) {
				throw new BusinessException("Tipo de pago no permitido en la tienda");
			}
			
			String nombreTipoPago = pago.getTipoPago().getNombre();
			
			Optional<TipoPago> tipoPagoOpt = tipoPagoRepository.findOneByNombre(nombreTipoPago);
			
			if(tipoPagoOpt.isEmpty()) {
				throw new BusinessException("Tipo de pago no permitido en la tienda");
			}
			
			pago.setTipoPago(tipoPagoOpt.get());
		}
		
		Optional<Vendedor> vendedorOpt = vendedorRepository.findOneByDocumento(compra.getVendedor().getDocumento());
		
		if(vendedorOpt.isEmpty()) {
			throw new ResourceNotFoundException("El vendedor no existe en la tienda");
		}
		
		compra.setVendedor(vendedorOpt.get());
		
		Optional<Cajero> cajeroOpt = cajeroRepository.findOneByToken(compra.getCajero().getToken());
		if(cajeroOpt.isEmpty()) {
			throw new ResourceNotFoundException("El token no corresponde a ningún cajero en la tienda");
		}
		Cajero cajero = cajeroOpt.get();
		compra.setCajero(cajero);
		
		if(!cajero.getTienda().equals(tienda)) {
			throw new BusinessException("El cajero no está asignado a esta tienda");
		}
		
		return compraRepository.save(compra);
	}
}
