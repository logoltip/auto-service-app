package auto.service.autoserviceapp.controller;

import auto.service.autoserviceapp.dto.request.ProductRequestDto;
import auto.service.autoserviceapp.dto.response.ProductResponseDto;
import auto.service.autoserviceapp.mapper.RequestDtoMapper;
import auto.service.autoserviceapp.mapper.ResponseDtoMapper;
import auto.service.autoserviceapp.model.Product;
import auto.service.autoserviceapp.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final RequestDtoMapper<ProductRequestDto, Product> requestDtoMapper;
    private final ResponseDtoMapper<ProductResponseDto, Product> responseDtoMapper;

    @PostMapping
    @ApiOperation(value = "Create a new product")
    public ProductResponseDto create(
            @RequestBody @Validated @ApiParam(value = "Product parameters")
            ProductRequestDto productRequestDto
    ) {
        Product product = requestDtoMapper.mapToModel(productRequestDto);
        return responseDtoMapper.mapToDto(productService.save(product));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a product by id")
    public ProductResponseDto update(
            @PathVariable @ApiParam(value = "Product id") Long id,
            @RequestBody @Validated @ApiParam(value = "Product parameters")
            ProductRequestDto productRequestDto
    ) {
        Product product = requestDtoMapper.mapToModel(productRequestDto);
        product.setId(id);
        return responseDtoMapper.mapToDto(productService.save(product));
    }
}
