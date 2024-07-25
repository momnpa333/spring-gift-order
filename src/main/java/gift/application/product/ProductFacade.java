package gift.application.product;

import gift.application.product.dto.OptionCommand;
import gift.application.product.dto.OptionCommand.Purchase;
import gift.application.product.dto.OptionModel;
import gift.application.product.dto.OptionModel.Info;
import gift.application.product.dto.ProductCommand;
import gift.application.product.dto.ProductModel;
import gift.application.product.service.CategoryService;
import gift.application.product.service.OptionService;
import gift.application.product.service.ProductKakaoService;
import gift.application.product.service.ProductService;

import java.util.List;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ProductFacade {

    private final OptionService optionService;
    private final ProductService productService;
    private final ProductKakaoService productKakaoService;

    public ProductFacade(OptionService optionService,
        ProductService productService,
        ProductKakaoService productKakaoService) {
        this.optionService = optionService;
        this.productService = productService;
        this.productKakaoService = productKakaoService;
    }

    @Transactional
    public Pair<ProductModel.Info, List<OptionModel.Info>> createProduct(
        ProductCommand.Register productCommand,
        OptionCommand.RegisterMany optionCommand
    ) {
        ProductModel.Info productModel = productService.createProduct(productCommand);
        List<OptionModel.Info> optionModel = optionService.createOption(productModel.id(),
            optionCommand);
        return Pair.of(productModel, optionModel);
    }

    @Transactional
    public Pair<ProductModel.Info, List<OptionModel.Info>> updateProduct(
        Long productId,
        ProductCommand.Update productCommand
    ) {
        ProductModel.Info productModel = productService.updateProduct(productId, productCommand);
        List<OptionModel.Info> optionModel = optionService.getOptions(productId);
        return Pair.of(productModel, optionModel);
    }

    @Transactional
    public OptionModel.Info purchase(Long memberId, Purchase command) {
        OptionModel.Info info = optionService.purchaseOption(command);
        productKakaoService.sendPurchaseMessage(memberId, info.name());
        return info;
    }

}
