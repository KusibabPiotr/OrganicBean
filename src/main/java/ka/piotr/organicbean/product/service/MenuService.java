package ka.piotr.organicbean.product.service;

import ka.piotr.organicbean.product.model.domain.Menu;
import ka.piotr.organicbean.product.repository.MenuRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MenuService {

    private MenuRepository menuRepository;
    private static final Long ONLY_AVAILABLE_MENU = 1L;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

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
