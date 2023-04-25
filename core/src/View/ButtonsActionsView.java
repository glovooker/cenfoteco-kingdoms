package View;

import BL.GameController;
import BL.bridge_dice_buttons.dadoMovimiento.DadoMovimiento;
import BL.characters_abstract_fabric.abstract_product.Army;
import BL.decorator.GestorDecorador;
import BL.decorator.concrete_decorator.DecoratedArtillery;
import BL.decorator.concrete_decorator.DecoratedInfantry;
import BL.decorator.concrete_decorator.DecoratedTank;
import BL.prototype.TileActor;
import Model.Castle;
import Model.Coordinate;
import Model.Player;
import View.Components.HudMovements;
import View.Screens.PlayScreen;
import View.Components.ButtonComponent;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.Viewport;

public class ButtonsActionsView extends Stage {

    private ButtonComponent buttonAttack;
    private ButtonComponent buttonSpecialAttack;
    private ButtonComponent buttonTank;
    private ButtonComponent buttonArtillery;
    private ButtonComponent buttonInfatery;
    private ButtonComponent buttonEndTurn;
    private ButtonComponent buttonLeft;
    private ButtonComponent buttonRight;
    private ButtonComponent buttonUp;
    private ButtonComponent buttonDown;
    private ButtonComponent buttonMovements;

    private Castle selectedCastle;

    private static final int HEIGHT = 100;
    private static final int WIDTH = 140;
    private final PlayScreen playScreen;

    private ButtonComponent armyButton;
    private ButtonComponent armytargetButton;

    private HudMovements hudMovements;
    private final GameController gameController = GameController.getInstance();
    private DecoratedArtillery decoratedArtillery;
    private DecoratedInfantry decoratedInfantry;
    private DecoratedTank decoratedTank;

    public ButtonsActionsView(PlayScreen screen, Viewport gamePort){
        super(gamePort);
        playScreen = screen;
        hudMovements = new HudMovements(this);
        gameController.getTimer().addObservers(hudMovements);
        defineButtonAttack("AttackButton.png");
        defineButtonSpecialAttack("SpecialAttackButton.png");
        defineButtonTank("SummonTankButton.png");
        defineButtonArtillery("SummonArtilleryButton.png");
        defineButtonInfantery("SummonInfanteryButton.png");
        defineButtonEndTurn("EndTurnButton.png");
        defineButtonMovements("MovementsButton.png");
        defineButtonLeft("Left.png");
        defineButtonRight("Right.png");
        defineButtonUp("Up.png");
        defineButtonDown("Down.png");

    }

    public void defineButtonAttack(String imgButton){
        buttonAttack = new ButtonComponent(this, imgButton, WIDTH, HEIGHT, 0, 440, new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                 Army currentArmy = gameController.getGameState().getSelectedArmy();
                 Army enemyArmy = gameController.getGameState().getSelectedEnemyArmy();

                if(gameController.getPlayerInTurn()==gameController.getPlayer1()) {
                    selectedCastle = gameController.getPlayer2().getCastle();
                } else if(gameController.getPlayerInTurn()==gameController.getPlayer2()){
                    selectedCastle = gameController.getPlayer1().getCastle();
                }
                System.out.println(selectedCastle);
                if ((gameController.getPlayerInTurn().getChest().getAttacksInChest() != 0 || gameController.lanzarDados().get(4) == 1) && (currentArmy != null && enemyArmy != null)) {

                    if (currentArmy!=null && enemyArmy!=null) {
                        Coordinate currentArmyPosition = currentArmy.getPosition();
                        Coordinate enemyArmyPosition = enemyArmy.getPosition();
                        int currentX = currentArmyPosition.getX();
                        int currentY = currentArmyPosition.getY();
                        int enemyX = enemyArmyPosition.getX();
                        int enemyY = enemyArmyPosition.getY();

                        if ((enemyX == currentX && (enemyY == currentY + 1 || enemyY == currentY - 1))
                                || ((enemyX == currentX + 1 || enemyX == currentX - 1) && enemyY == currentY)
                                || ((enemyX == currentX + 1 || enemyX == currentX - 1) && (enemyY == currentY + 1 || enemyY == currentY - 1))) {
                            gameController.atacar(currentArmy, enemyArmy);
                            gameController.getPlayerInTurn().getChest().setAttacksInChest(gameController.getPlayerInTurn().getChest().getAttacksInChest() - 1);
                        } else {
                            System.out.println("No se puede atacar a esa unidad");
                        }
                    }


                } else if ((gameController.getPlayerInTurn().getChest().getAttacksInChest() != 0 || gameController.lanzarDados().get(4) == 1) && (armyButton != null && selectedCastle!=null))
                {

                    Coordinate currentArmyPosition = currentArmy.getPosition();

                    if(currentArmy!=null){
                        int castleX = selectedCastle.getCoordinates().getX();
                        int castleY = selectedCastle.getCoordinates().getY();
                        int currentX = currentArmyPosition.getX();
                        int currentY = currentArmyPosition.getY();


                        if (castleX == currentX && (castleY == currentY + 1 || castleY == currentY - 1)) {
                            gameController.atacarCastillo(selectedCastle, currentArmy);
                            System.out.println("Atacando Castillo");
                        }
                    }else {
                        System.out.println("No se puede atacar");
                    }

                }else {
                    System.out.println("No se puede atacar");
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    public void defineButtonSpecialAttack(String imgButton){
        buttonSpecialAttack = new ButtonComponent(this, imgButton, WIDTH, HEIGHT, 0, 380, new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                armyButton = playScreen.getSelectedArmyButton();
                armytargetButton = playScreen.getSelectedEnemyArmyButton();

                System.out.println("asd "+gameController.invocarInfanteria());
                System.out.println(gameController.lanzarDados().get(3));
                System.out.println(gameController.getPlayerInTurn().getChest().getSpecialAttackInChest());

                if (armyButton == armytargetButton){
                    armytargetButton = null;
                }
                System.out.println(armyButton);
                System.out.println(armytargetButton);

                if ((gameController.getPlayerInTurn().getChest().getSpecialAttackInChest() != 0 || gameController.lanzarDados().get(3) == 1) && (armyButton != null && armytargetButton != null)) {
                    Army currentArmy = gameController.getGameState().getSelectedArmy();
                    Army targertArmy = gameController.getGameState().getSelectedEnemyArmy();
                    armyButton = null;
                    armytargetButton = null;
                    if (currentArmy!=null && targertArmy!=null) {

                        Coordinate currentArmyPosition = currentArmy.getPosition();
                        Coordinate targetArmyPosition = targertArmy.getPosition();

                        switch (currentArmy.getSpecialAttack()){
                            case "Healer":
                                decoratedArtillery.heal(targertArmy);
                                break;
                            case "Add attack":
                                break;
                            case "Add defense":
                                break;
                            case "Lower defense":
                                break;
                            case "Ranged attack":
                                decoratedInfantry.rangedAttack(currentArmyPosition, targetArmyPosition);
                                break;
                            case "Life for double movement":
                                break;
                            case "Bomb attack":
                                break;
                            case "Protect ally":
                                break;
                        }
                    }

                } else if ((gameController.getPlayerInTurn().getChest().getSpecialAttackInChest() != 0 || gameController.lanzarDados().get(3) == 1) && (armyButton != null && armytargetButton == null)) {
                    Army currentArmy = gameController.getGameState().getSelectedArmy();
                    System.out.println("armmy "+currentArmy);
                    armyButton = null;
                    armytargetButton = null;
                    if (currentArmy!=null) {

                        switch (currentArmy.getSpecialAttack()) {
                            case "Double attack":
                                System.out.println("Double attack");
                                //decoratedInfantry.doubleAttackPower();
                                break;
                            case "Double defense":
                                //decoratedInfantry.doubleDefensePower();
                                break;
                        }

                    }

                }else {
                    armyButton = null;
                    armytargetButton = null;
                    System.out.println("No se puede atacar");
                }


                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    public void defineButtonArtillery(String imgButton){
        buttonArtillery = new ButtonComponent(this, imgButton, WIDTH, HEIGHT, 145, 320, new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Army army = gameController.invocarArtilleria();
                invoke(army);
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    public void defineButtonTank(String imgButton){
        buttonTank = new ButtonComponent(this, imgButton, WIDTH, HEIGHT, 145, 380, new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Army army = gameController.invocarTanque();
                invoke(army);
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }
    public void defineButtonInfantery(String imgButton){
        buttonInfatery = new ButtonComponent(this, imgButton, WIDTH, HEIGHT, 145, 440, new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Army army = gameController.invocarInfanteria();
                invoke(army);
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    public void defineButtonMovements(String imgButton){
        buttonMovements = new ButtonComponent(this, imgButton, WIDTH, HEIGHT,0, 320, new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Army army = gameController.getGameState().getSelectedArmy();

                if(army != null) {
                    DadoMovimiento movement = gameController.move();
                    Player player = gameController.getPlayerInTurn();

                    if (movement != null) {
                        player.setMovementDice(movement);
                        hudMovements.getMovementsAmount().setText(player.getMovementDice().getMovimiento());
                        playScreen.getHudChest().updateLabels(gameController.getGameState());
                    }
                }

                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }


    public void defineButtonLeft(String imgButton){
        buttonLeft = new ButtonComponent(this, imgButton, 90, 75+20, 15,70, new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                ButtonComponent armyButton = playScreen.getSelectedArmyButton();

                if(gameController.getPlayerInTurn().getMovementDice() != null && armyButton != null) {
                    Army currentArmy = gameController.getGameState().getSelectedArmy();
                    Coordinate currentArmyPosition = currentArmy.getPosition();
                    int currentX = currentArmy.getPosition().getX();
                    int currentY = currentArmy.getPosition().getY();
                    int futureX = currentX - 1;
                    TileActor currentTile = BoardView.getTileByPosition(currentX, currentY);
                    TileActor futureTile = BoardView.getTileByPosition(futureX, currentArmyPosition.getY());
                    if (currentArmyPosition.getX() > 0 && isValidMove(currentArmy, futureTile)) {
                        Coordinate newCoordinate = new Coordinate(futureX, currentArmyPosition.getY());
                        moveArmy(armyButton, currentArmy, futureTile, newCoordinate, currentTile);
                    }
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }
    public void defineButtonRight(String imgButton){
        buttonRight = new ButtonComponent(this, imgButton, 90, 75+20, 175, 70, new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                ButtonComponent armyButton = playScreen.getSelectedArmyButton();

                if(gameController.getPlayerInTurn().getMovementDice() != null && armyButton != null) {
                    Army currentArmy = gameController.getGameState().getSelectedArmy();
                    Coordinate currentArmyPosition = currentArmy.getPosition();
                    int currentX = currentArmy.getPosition().getX();
                    int currentY = currentArmy.getPosition().getY();
                    int futureX = currentX + 1;
                    TileActor currentTile = BoardView.getTileByPosition(currentX, currentY);
                    TileActor futureTile = BoardView.getTileByPosition(futureX, currentArmyPosition.getY());
                    if (currentArmyPosition.getX() < 19 && isValidMove(currentArmy, futureTile)) {
                        Coordinate newCoordinate = new Coordinate(futureX, currentArmyPosition.getY());
                        moveArmy(armyButton, currentArmy, futureTile, newCoordinate, currentTile);
                    }
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });

    }
    public void defineButtonUp(String imgButton){
        buttonUp = new ButtonComponent(this, imgButton, 90, 75, 95, 165, new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                ButtonComponent armyButton = playScreen.getSelectedArmyButton();
                System.out.println("Up");

                if(gameController.getPlayerInTurn().getMovementDice() != null && armyButton != null) {
                    Army currentArmy = gameController.getGameState().getSelectedArmy();
                    Coordinate currentArmyPosition = currentArmy.getPosition();
                    int currentX = currentArmy.getPosition().getX();
                    int currentY = currentArmy.getPosition().getY();
                    int futureY = currentY + 1;
                    TileActor currentTile = BoardView.getTileByPosition(currentX, currentY);
                    TileActor futureTile = BoardView.getTileByPosition(currentArmyPosition.getX(), futureY);
                    if (currentArmyPosition.getY() < 20 && isValidMove(currentArmy, futureTile)) {
                        Coordinate newCoordinate = new Coordinate(currentArmyPosition.getX(), futureY);
                        moveArmy(armyButton, currentArmy, futureTile, newCoordinate, currentTile);
                    }
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });

    }
    public void defineButtonDown(String imgButton){
        buttonDown = new ButtonComponent(this, imgButton, 90, 75, 95, 0, new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                ButtonComponent armyButton = playScreen.getSelectedArmyButton();

                if(gameController.getPlayerInTurn().getMovementDice() != null && armyButton != null) {
                    Army currentArmy = gameController.getGameState().getSelectedArmy();
                    Coordinate currentArmyPosition = currentArmy.getPosition();
                    int currentX = currentArmy.getPosition().getX();
                    int currentY = currentArmy.getPosition().getY();
                    int futureY = currentY - 1;
                    TileActor currentTile = BoardView.getTileByPosition(currentX, currentY);
                    TileActor futureTile = BoardView.getTileByPosition(currentArmyPosition.getX(), futureY);
                    if (currentArmyPosition.getY() > 1 && isValidMove(currentArmy, futureTile)) {
                        Coordinate newCoordinate = new Coordinate(currentArmyPosition.getX(), futureY);
                        moveArmy(armyButton, currentArmy, futureTile, newCoordinate, currentTile);
                    }
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    public void defineButtonEndTurn(String imgButton){
        buttonEndTurn = new ButtonComponent(this, imgButton, WIDTH, HEIGHT,70, 260, new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(gameController.getGameState().getTime() > 0) {
                    gameController.setTime();
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }


    private void invoke(Army army){
        if(army != null) {
            playScreen.showArmyData(army);
            gameController.getGameState().getBoard().getArmyList().add(army);
            gameController.getGameState().setPrefaReady(true);
        }
    }

    private boolean isValidMove(Army army, TileActor futureTile) {
        DadoMovimiento movementsInDice = gameController.getGameState().getPlayerInTurn().getMovementDice();
        return movementsInDice.getMovimiento() > 0 && army.getMovements() > 0 && futureTile.hasWayCreated() && !futureTile.isInvaded();
    }


    private void moveArmy(ButtonComponent button,  Army army, TileActor futureTile, Coordinate newCoordinate, TileActor currentTile) {
        army.setPosition(newCoordinate);
        button.setPosition(newCoordinate, 50);
        futureTile.setInvaded(true);
        currentTile.setInvaded(false);
        DadoMovimiento movementsInDice = gameController.getGameState().getPlayerInTurn().getMovementDice();
        movementsInDice.setMovimiento(movementsInDice.getMovimiento() - 1);
        hudMovements.getMovementsAmount().setText(movementsInDice.getMovimiento());
        army.setMovements(army.getMovements() - 1);
    }

}
