package ka.piotr.organicbean.product.service;

import ka.piotr.organicbean.product.model.domain.Menu;
import ka.piotr.organicbean.product.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private static final Long ONLY_AVAILABLE_MENU = 1L;

    public Optional<Menu> getMenu(){
        return menuRepository.findById(ONLY_AVAILABLE_MENU);
    }

    public Menu saveMenu(Menu menu){
        return menuRepository.save(menu);
    }

    public void deleteMenu(Long id){
        menuRepository.deleteById(id);
    }
}
