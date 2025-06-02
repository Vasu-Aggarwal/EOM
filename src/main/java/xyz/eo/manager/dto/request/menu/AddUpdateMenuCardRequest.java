package xyz.eo.manager.dto.request.menu;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xyz.eo.manager.dto.model.menu.MenuCardDto;
import xyz.eo.manager.dto.model.menu.MenuCategoryDto;
import xyz.eo.manager.dto.model.menu.MenuCategoryItemDto;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class AddUpdateMenuCardRequest {
    String action;
    MenuCardDto menuCard;
    List<MenuCategoryDto> menuCategory;
    List<MenuCategoryItemDto> menuCategoryItem;
}
