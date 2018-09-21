package com.guidosit.gamexchange.datageneration;

import com.guidosit.gamexchange.category.Category;
import com.guidosit.gamexchange.category.CategoryService;
import com.guidosit.gamexchange.game.Game;
import com.guidosit.gamexchange.game.GameService;
import com.guidosit.gamexchange.user.User;
import com.guidosit.gamexchange.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("data-generation")
public class DataGenerationController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private GameService gameService;
    @Autowired
    private UserService userService;

    @GetMapping
    public void generateData(){
        Category category = new Category();
        category.setName("RPG");
        categoryService.saveCategory(category);

        category = new Category();
        category.setName("Ação");
        categoryService.saveCategory(category);

        category = new Category();
        category.setName("Puzzle");
        categoryService.saveCategory(category);

        category = new Category();
        category.setName("Luta");
        categoryService.saveCategory(category);

        Game game = new Game();
        game.setName("Naruto");
        game.setDescription("Melhor jogo do ano");
        game.setPlatform("Ps4");
        game.setCategory(categoryService.getCategoryByName("Luta").orElse(null));
        gameService.save(game);

        game = new Game();
        game.setName("Mortal Kombat X");
        game.setDescription("Muita morte dahora");
        game.setPlatform("Xbox One");
        game.setCategory(categoryService.getCategoryByName("Luta").orElse(null));
        gameService.save(game);

        game = new Game();
        game.setName("Tetris");
        game.setDescription("Classico velho");
        game.setPlatform("GameBoy");
        game.setCategory(categoryService.getCategoryByName("Puzzle").orElse(null));
        gameService.save(game);

        game = new Game();
        game.setName("Mario");
        game.setDescription("Aquele que te comeu atras do armario");
        game.setPlatform("Nintendo Switch");
        game.setCategory(categoryService.getCategoryByName("RPG").orElse(null));
        gameService.save(game);

        game = new Game();
        game.setName("God of War");
        game.setDescription("Tem um pouquinho de aventura também");
        game.setPlatform("Ps4");
        game.setCategory(categoryService.getCategoryByName("Ação").orElse(null));
        gameService.save(game);

        User user = new User();
        user.setName("Thiago Gonçalves da Silva");
        user.setEmail("thigonsilva@gmail.com");
        user.setNickname("Thigas");
        user.setPassword("123456");
        userService.save(user);

        user = new User();
        user.setName("Hugo Melo");
        user.setEmail("hugo@gmail.com");
        user.setNickname("Hugo");
        user.setPassword("123456");
        userService.save(user);

        user = new User();
        user.setName("José Elder");
        user.setEmail("jose@gmail.com");
        user.setNickname("José");
        user.setPassword("123456");
        userService.save(user);
    }

}
