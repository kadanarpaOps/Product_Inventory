package top.dev.narvaez.product_inventory.listeners.infrastructure.input.rest.mapper;

import org.modelmapper.Converter;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import top.dev.narvaez.product_inventory.common.infrastructure.mapper.MapperStructure;
import top.dev.narvaez.product_inventory.listeners.domain.model.AuditProductModel;
import top.dev.narvaez.product_inventory.listeners.infrastructure.input.rest.dto.ResAuditProductDTO;
import top.dev.narvaez.product_inventory.products.domain.models.ProductModel;

@Component
public class AuditProductRestMapper extends MapperStructure {

    public AuditProductRestMapper() {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        Converter<ProductModel, Long> setProductId = aud -> {
            if (aud.getSource() == null) return null;
            return aud.getSource().getId();
        };

        mapper.typeMap(AuditProductModel.class, ResAuditProductDTO.class)
                .addMappings(m -> m.using(setProductId).map(
                        AuditProductModel::getProductId, ResAuditProductDTO::setProductId
                ));

    }

    public ResAuditProductDTO toResDTO(AuditProductModel auditProductModel) {
        return mapper.map(auditProductModel, ResAuditProductDTO.class);
    }

}