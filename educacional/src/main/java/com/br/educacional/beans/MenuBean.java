package com.br.educacional.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author Piaia
 */
@SessionScoped
@Named
public class MenuBean implements Serializable {

    private MenuModel menu = new DefaultMenuModel();

    @PostConstruct
    public void init() {
        montarMenu();
    }

    public void montarMenu() {
        this.menu = new DefaultMenuModel();
        List<DefaultMenuItem> itensSubMenu = new ArrayList<>();
        DefaultSubMenu subMenuCadastros = criarSubMenu("Cadastros", "pi pi-plus");
        itensSubMenu.add(criarMenuItem("Aluno", "/educacional/cadastro_aluno.xhtml"));
        itensSubMenu.add(criarMenuItem("Professor", "/educacional/cadastro_professor.xhtml"));
        addElements(subMenuCadastros, itensSubMenu);
    }

    private DefaultSubMenu criarSubMenu(String label, String icon) {
        DefaultSubMenu subMenu = new DefaultSubMenu();
        subMenu.setLabel(label);
        subMenu.setIcon(icon);
        return subMenu;
    }

    private DefaultMenuItem criarMenuItem(String label, String url) {
        DefaultMenuItem menuItem = new DefaultMenuItem();
        menuItem.setValue(label);
        menuItem.setUrl(url);
        return menuItem;
    }

    private void addElements(DefaultSubMenu subMenu, List<DefaultMenuItem> itens) {
        if (subMenu == null) { //Os itens ficam direto no menu
            this.menu.getElements().addAll(itens);
        } else {
            subMenu.getElements().addAll(itens);
            this.menu.getElements().add(subMenu);
        }
    }

    public MenuModel getMenu() {
        return menu;
    }

    public void setMenu(MenuModel menu) {
        this.menu = menu;
    }
}
